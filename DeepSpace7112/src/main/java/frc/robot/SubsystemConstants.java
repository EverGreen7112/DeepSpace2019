/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;

/**
 * Add your docs here.
 */
public class SubsystemConstants {
    public static class roller {
		public static final Supplier<Double> ROLLER_IN_SPEED = ConstantHandler.addConstantDouble("Roller In Speed", 0.3);
		public static final Supplier<Double> ROLLER_OUT_SPEED = ConstantHandler.addConstantDouble("Roller Out Speed", -0.3);
	}
}
