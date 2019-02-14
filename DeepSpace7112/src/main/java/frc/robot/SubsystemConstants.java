/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;

/**
 * Add your docs here.
 */
public class SubsystemConstants {

    public static interface chassis{
        public static final Supplier<Double> kDrivingSpeedModifier = ConstantHandler.addConstantDouble("Driving Speed Modifier", 0.5);
    }

    public static interface cameras{
        public static final int
            kCamerawidth = 320,
            kCameraHeight = 240,
			kCameraExposure = 50;
	}

    public static interface Elevator{
		public static Supplier<Double> kDistancePerPulse = ConstantHandler.addConstantDouble("Elevator distance per pulse", 1); //temp
		public static Supplier<Double> kRocketBottomHatchHeight = ConstantHandler.addConstantDouble("Rocket bottom hatch hight", 1); //temp
		public static Supplier<Double> kRocketMiddleHatchHeight = ConstantHandler.addConstantDouble("Rocket middle hatch hight", 2); //temp
		public static Supplier<Double> kRocketTopHatchHeight = ConstantHandler.addConstantDouble("Rocket top hatch hight", 3); //temp
		public static Supplier<Double> kElevatorMotorSpeedModifier = ConstantHandler.addConstantDouble("Elevator speed modifier", 0.8); //temp
		public static Supplier<Double> kElevatorMaxHight = ConstantHandler.addConstantDouble("Elevator Max hight", 2.25);
		
		//pid settings, currently not in use
		public static Supplier<Double> kp = ConstantHandler.addConstantDouble("Elevator - kp", 1); //temp
		public static Supplier<Double> ki = ConstantHandler.addConstantDouble("Elevator - ki", 0.01); //temp
		public static Supplier<Double> kd = ConstantHandler.addConstantDouble("Elevator - kd", 0.1); //temp
		public static Supplier<Double> kTolerance = ConstantHandler.addConstantDouble("Elevator - tolerance", 0.02); //temp
		public static Supplier<Double> kWaitTime = ConstantHandler.addConstantDouble("Elevator - wait time", 1);
	}
	
    public static interface gripper {
		public static final Supplier<Double> gripperInSpeed = ConstantHandler.addConstantDouble("gripper In Speed", 0.3);
		public static final Supplier<Double> gripperOutSpeed = ConstantHandler.addConstantDouble("gripper Out Speed", -0.3);
		public static final double kVoltageLimit = 1.5;
	}
	
	public static interface ClimbingShaft{
    	public static final Supplier<Double> shaftMotorSpeedModifier = ConstantHandler.addConstantDouble("Shaft Motor Speed Modifier", 0.3);
	}

	public static interface GripperMovement{
		public static Supplier<Double> kClimbingSpeed = ConstantHandler.addConstantDouble("kClimbingSpeed", 0.5); //temp
		public static Supplier<Double> kGripperSpeed = ConstantHandler.addConstantDouble("kClimbingSpeed", 0.5); //temp   
	}
		public static interface ClimbingMovement{
		public static Supplier<Double> kClimbingSpeed = ConstantHandler.addConstantDouble("ClimbingMovementSpeedModifier", 0.5); //temp
		public static Supplier<Double> kTargetHeight = 1.0; //temp
	}
}
