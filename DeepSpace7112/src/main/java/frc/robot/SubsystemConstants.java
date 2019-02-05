/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.awt.image.BufferedImage;

/**
 * Add your docs here.
 */
public class SubsystemConstants {
    public static interface cameras
    {
        public static final int
            kCamerawidth = 320,
            kCameraHeight = 240,
            kCameraExposure = 50,
            kCameraImageType = BufferedImage.TYPE_3BYTE_BGR; //All temp.
    }
}
