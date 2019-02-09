/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.awt.image.BufferedImage;
import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;

/**
 * Add your docs here.
 */
public class SubsystemConstants {

    public static interface chassis{
        public static final Supplier<Double> kDrivingSpeedModifier = ConstantHandler.addConstantDouble("Driving Speed Modifier", 0.5);
    }

    public static interface cameras
    {
        public static final int
            kCamerawidth = 320,
            kCameraHeight = 240,
            kCameraExposure = 50;
    }
}
