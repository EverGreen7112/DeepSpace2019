/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;

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

    // These are the constants for the PID.
    public static final Supplier<Double> 
        kP = ConstantHandler.addConstantDouble("Camera - kP", 1),
        /**The Integral Constant for the PID. */ 
        kI = ConstantHandler.addConstantDouble("Camera - kI", 0.01),
        /**The Derviative constant for the PID. */
        kD = ConstantHandler.addConstantDouble("Camera - kD", 0.1),
        /**The tolerance constant for the PID - how much deviation should the robot tolerate before trying to move back to the line? */
        kTolerance = ConstantHandler.addConstantDouble("Camera - tolerance", 0.02),
        /**The Wait Time constant for the PID - How much time should the robot stay in a too deviated position before trying to move back to the line? */
        kWaitTime = ConstantHandler.addConstantDouble("Camera - wait time", 1), 
        /**The Movement  Constant for the PID - How much */
        kMovement = ConstantHandler.addConstantDouble("Camera - Movement", 5); //All temp 


public static final NetworkTable kCamNetworkTable = NetworkTableInstance.getDefault().getTable("ImageProcessing");

/**The property suppliers for the big reflective (0 in the Network Tables) - Width, Height, Center (in X and Y values), and whether or not it's updating. */
public static class BigReflective {
    public static final Supplier<Double>
    kWidthSupplier = () -> kCamNetworkTable.getEntry("width0").getDouble(0),
    kHeightSupplier = () -> kCamNetworkTable.getEntry("height0").getDouble(0),
    kXSupplier = () -> kCamNetworkTable.getEntry("x0").getDouble(0),
    kYSupplier = () -> kCamNetworkTable.getEntry("y0").getDouble(0);
    
    /**Predicate for whether or not the Reflective is seen - 
     *  True if information about the big reflective is recieved, and false if not.*/
    public static final Supplier<Boolean>
    kIsUpdated = () -> kCamNetworkTable.getEntry("isUpdated0").getBoolean(false);


}

public static class SmallReflective {
    
    public static final Supplier<Double>
    kWidthSupplier = () -> kCamNetworkTable.getEntry("width1").getDouble(0),
    kHeightSupplier = () -> kCamNetworkTable.getEntry("height1").getDouble(0),
    kXSupplier = () -> kCamNetworkTable.getEntry("x1").getDouble(0),
    kYSupplier = () -> kCamNetworkTable.getEntry("y1").getDouble(0);

    /**Predicate for whether or not the Reflective is seen - 
     *  True if information about the small reflective is recieved, and false if not.*/
    public static final Supplier<Boolean>
    kIsUpdated = () -> kCamNetworkTable.getEntry("isUpdated1").getBoolean(false);

}



public static final Supplier<Double> 
 kBigReflectiveCenter = () -> 
     (kCamNetworkTable.getEntry("x0").getDouble(0)), //Supplier for the x value of the center point of the small reflective from the network.
 kSmallReflectiveCenter = () -> 
     (kCamNetworkTable.getEntry("x1").getDouble(0)); //Supplier for the x values of the center point of the big reflective from the networkt tables.

 /**Supplier for the x value of the center point of the object on camera's screen.  */
public static final Supplier<Double> kTwoReflectivesCenter = () -> (kBigReflectiveCenter.get()
     + kSmallReflectiveCenter.get()) / 2; //Calaculate the center point by getting th mean of the x values of the big and small reflectives.


public static final PIDSource CENTER = new PIDSource() {

    @Override
    public void setPIDSourceType(PIDSourceType pidSource) {
    }

    @Override
    public double pidGet() {
        if (SmallReflective.kIsUpdated.get())
            return kTwoReflectivesCenter.get();
        if (BigReflective.kIsUpdated.get())
            return kBigReflectiveCenter.get();
        return 0;
    }

    @Override
    public PIDSourceType getPIDSourceType() {
        return PIDSourceType.kDisplacement;
    }

};
}
