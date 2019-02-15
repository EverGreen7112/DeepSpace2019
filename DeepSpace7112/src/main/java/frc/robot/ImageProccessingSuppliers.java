/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.awt.image.BufferedImage;
import java.util.function.Supplier;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

/**
 * Add your docs here.
 */
public class ImageProccessingConstants {
    
    public static final int
    kCameraWidth = 320,
    kCameraHeight = 240,
    kCameraExposure = 50,
    kCameraImageType = BufferedImage.TYPE_3BYTE_BGR; //All temp.
        // the range of the outputs. between -0.5 and 0.5
public static final int kCameraOutputRange = 1;

public static final NetworkTable kCamNetworkTable = NetworkTableInstance.getDefault().getTable("ImageProcessing");

public static final Supplier<Double> kWidthSupplier = () -> kCamNetworkTable.getEntry("width0").getDouble(0);
public static final Supplier<Double> kHeightSupplier = () -> kCamNetworkTable.getEntry("hight0").getDouble(0);
public static final Supplier<Double> kXSupplier = () -> kCamNetworkTable.getEntry("x0").getDouble(0);
public static final Supplier<Double> kYSupplier = () -> kCamNetworkTable.getEntry("y0").getDouble(0);
public static final Supplier<Boolean> IsUpdated = () -> kCamNetworkTable.getEntry("isUpdated0").getBoolean(false);




// calculates the center of the big reflective
public static final Supplier<Double> kBigReflectiveCenter = () -> ((kCamNetworkTable.getEntry("x0").getDouble(0)
        + 0.5 * kCamNetworkTable.getEntry("width0").getDouble(0)) / kCameraWidth - 0.5);
// calculates the center of the small reflective
public static final Supplier<Double> kSmallReflectiveCenter = () -> ((kCamNetworkTable.getEntry("x1").getDouble(0)
        + 0.5 * kCamNetworkTable.getEntry("width1").getDouble(0)) / kCameraWidth - 0.5);
// calculates the center of the two reflectives
public static final Supplier<Double> kTwoReflectiveCenter = () -> (kBigReflectiveCenter.get()
        + kSmallReflectiveCenter.get()) / 2;

public static final Supplier<Boolean> IS_UPDATED_0 = () -> kCamNetworkTable.getEntry("isUpdated0").getBoolean(false);
public static final Supplier<Boolean> IS_UPDATED_1 = () -> kCamNetworkTable.getEntry("isUpdated1").getBoolean(false);

public static final PIDSource CENTER = new PIDSource() {

    @Override
    public void setPIDSourceType(PIDSourceType pidSource) {
    }

    @Override
    public double pidGet() {
        if (kCamNetworkTable.getEntry("isUpdated1").getBoolean(false))
            return kTwoReflectiveCenter.get();
        if (kCamNetworkTable.getEntry("isUpdated0").getBoolean(false))
            return kBigReflectiveCenter.get();
        return 0;
    }

    @Override
    public PIDSourceType getPIDSourceType() {
        return PIDSourceType.kDisplacement;
    }

};
}
