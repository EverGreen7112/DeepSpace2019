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
 * The width, height, and center (in X and Y values) of the reflective, 
 * as well as whether or not it is seen at the moment.
 * This is divided into two classes for the two reflectives, BigReflective and SmallReflective, 
 * each containig their own data.
 */
public class ImageProccessingSuppliers {

    public static final NetworkTable camNetworkTable = NetworkTableInstance.getDefault().getTable("ImageProcessing");

    /**The property suppliers for the big reflective (0 in the Network Tables) - Width, Height, Center (in X and Y values), and whether or not it's updating. */
    public static class BigReflective {
        public static final Supplier<Double>
        widthSupplier = () -> camNetworkTable.getEntry("width0").getDouble(0),
        heightSupplier = () -> camNetworkTable.getEntry("height0").getDouble(0),
        xSupplier = () -> camNetworkTable.getEntry("x0").getDouble(0),
        ySupplier = () -> camNetworkTable.getEntry("y0").getDouble(0);
        
        /**Predicate for whether or not the Reflective is seen - 
         *  True if information about the big reflective is recieved, and false if not.*/
        public static final Supplier<Boolean>
         isUpdated = () -> camNetworkTable.getEntry("isUpdated0").getBoolean(false);


    }

    /**The property suppliers for the small reflective (1 in the Network Tables) - Width, Height, Center (in X and Y values), and whether or not it's updating. */
    public static class SmallReflective {
        
        public static final Supplier<Double>
        widthSupplier = () -> camNetworkTable.getEntry("width1").getDouble(0),
        heightSupplier = () -> camNetworkTable.getEntry("height1").getDouble(0),
        xSupplier = () -> camNetworkTable.getEntry("x1").getDouble(0),
        ySupplier = () -> camNetworkTable.getEntry("y1").getDouble(0);

        /**Supplier for whether or not the Reflective is seen - 
         *  True if information about the small reflective is recieved, and false if not.*/
        public static final Supplier<Boolean>
         isUpdated = () -> camNetworkTable.getEntry("isUpdated1").getBoolean(false);

    }


    /** Supplier for the x value of the center point of the small reflective from the network.*/
    public static final Supplier<Double> bigReflectiveCenter = () -> 
        (camNetworkTable.getEntry("x0").getDouble(0));

    /**Supplier for the x values of the center point of the big reflective from the networkt tables. */
    public static final Supplier<Double> smallReflectiveCenter = () -> 
        (camNetworkTable.getEntry("x1").getDouble(0));

    /**Supplier for the x value of the center point of the object on camera's screen.  */
    public static final Supplier<Double> twoReflectivesCenter = () -> (bigReflectiveCenter.get()
        + smallReflectiveCenter.get()) / 2; //Calaculate the center point by getting the mean of the x values of the big and small reflectives.


    /**The PIDSource of the PID Drive Arcade  - contains the Type of the PID system (Displacement or Rate 
     * and the getter for the result to pass to the controller (Here the center of the reflecives seen by the camera. ).*/
    public static final PIDSource center = new PIDSource() {
        @Override
        public void setPIDSourceType(PIDSourceType pidSource) {
        }

        @Override
        public double pidGet() {
            if (SmallReflective.isUpdated.get())
                return twoReflectivesCenter.get();
            if (BigReflective.isUpdated.get())
                return bigReflectiveCenter.get();
            return 0;
        }

        @Override
        public PIDSourceType getPIDSourceType() {
            return PIDSourceType.kDisplacement;
        }

    };
}
