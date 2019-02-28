/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.function.Supplier;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

/**
 * This class contains all the Suppliers for the information from the Network tables of the PID -
 * The width, height, and center (in  X value by the camera's pixels) of each reflective, 
 * as well as whether or not it is seen at the moment. <p>
 * 
 * These suppliers are divided into two the two reflectives, who each get a respective class:
 * BigReflective and SmallReflective, each containig their own data.
 */
public class ImageProccessingSuppliers {
    /**This is the table of informationn gained from the Image Proccesing python code. */
    public static final NetworkTable camNetworkTable = NetworkTableInstance.getDefault().getTable("ImageProcessing");
    /**The lastest center poit. If information for one or more reflectives are missing,
     *  the robot will use the lastest valid information.*/
    public static Supplier<Double> lastCenter;
    /**The property suppliers for the closer reflective (0 in the Network Tables) - Width, Height, Center (in X values), and whether or not it's currently transmiting innformation about it ("is updated"). */
    public static class Reflective0 {
        public static final Supplier<Double>
         widthSupplier = () -> camNetworkTable.getEntry("width0").getDouble(0),
         heightSupplier = () -> camNetworkTable.getEntry("height0").getDouble(0),
         centerXSupplier = () -> camNetworkTable.getEntry("x0").getDouble(0);
        
        /**The supplier for whether or not the Reflective is seen - 
         *  True if information about the big reflective is recieved, and false if not.
         */
        public static final Supplier<Boolean>
         isUpdated = () -> camNetworkTable.getEntry("isUpdated0").getBoolean(false);
    }

    /**The property suppliers for the further reflective (1 in the Network Tables) - Width, Height, Center (in X values), and whether or not it's currently transmiting innformation ("is updated"). */
    public static class Reflective1 {
        public static final Supplier<Double>
         widthSupplier = () -> camNetworkTable.getEntry("width1").getDouble(0),
         heightSupplier = () -> camNetworkTable.getEntry("height1").getDouble(0),
         centerXSupplier = () -> camNetworkTable.getEntry("x1").getDouble(0);

        /**Supplier for whether or not the Reflective is seen - 
         *  True if information about the small reflective is recieved, and false if not.*/
        public static final Supplier<Boolean>
         isUpdated = () -> camNetworkTable.getEntry("isUpdated1").getBoolean(false);


    }


    /**Supplier for the x value of the center point of a big and a small reflective together.  */
    public static final Supplier<Double> twoReflectivesCenter = () -> (Reflective0.centerXSupplier.get()
        + Reflective1.centerXSupplier.get()) / 2; //Calculate the center point by getting the mean of the x values of the big and small reflectives' center.


    /**The PIDSource of the PID Drive Arcade  - contains the Type of the PID system (Displacement or Rate, here Displacement),
     * and the getter for the number which passes to the controller (Here the center of the reflecives seen by the camera. ).*/
    public static final PIDSource center = new PIDSource() {
        @Override
        public void setPIDSourceType(PIDSourceType pidSource) {
        }

        
        /**@return the center value of the seen reflectives: <p>
         *<ul>
         *<li>If both are seen, the midpoint between the centers. </li>
         *<li>If information about one or more of the reflectives is missing, the latest valid value. */
        @Override
        public double pidGet() {
            if (Reflective0.isUpdated.get() && Reflective1.isUpdated.get()) {
                lastCenter = () -> twoReflectivesCenter.get(); //Puts the valid value in a variable for the next iiteration
                return twoReflectivesCenter.get();
            }
            else {
                return lastCenter.get();
            }
        }

        /**returns The type of the PID system - either displacement (position control) or rate (velocity control).
         *  Our system is of the Displacement type.
         * @return The currently selected PID source paramater - displacement. */
        @Override
        public PIDSourceType getPIDSourceType() {
            return PIDSourceType.kDisplacement; 
        }

    };
}
