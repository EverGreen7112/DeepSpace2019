/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;


public class SubsystemConstants { //ALL TEMP

    public static interface chassis {
        public static final Supplier<Double> kDrivingSpeedModifier = ConstantHandler.addConstantDouble("Driving Speed Modifier", 0.5);
    }

	public static interface PID  { 
		/**The point the system will try to drive to - the center of the reflectives seen by the camera. */
		public static final Supplier<Double> kSetPoint = ConstantHandler.addConstantDouble("Arcade PID - Set point", 0);
		/**The range of SpeedMultipliers the system can output for the fix - here, from -0.5 to 0.5. */
		public static final Supplier<Double> kOutputRange = ConstantHandler.addConstantDouble("PID Output Range", 1);
		/** The proportional constant for the PID */
        public static final Supplier<Double> kP = ConstantHandler.addConstantDouble("Arcade PID proportional constant", 1);
        /**The Integral Constant for the PID. */ 
        public static final Supplier<Double> kI = ConstantHandler.addConstantDouble("Arcade PID integral constant", 0.01);
        /**The Derviative constant for the PID. */
        public static final Supplier<Double> kD = ConstantHandler.addConstantDouble("Arcade PID derivative constant", 0.1);
        /**The tolerance constant for the PID - how much deviation should the robot tolerate before trying to move back to the line? */
        public static final Supplier<Double> kTolerance = ConstantHandler.addConstantDouble("Arcade PID tolerance", 0.02);
        /**The Wait Time constant for the PID - How much time should the robot stay in a too deviated position before trying to move back to the line? */
        public static final Supplier<Double> kWaitTime = ConstantHandler.addConstantDouble("Arcade PID wait time", 1);
        /**The Movement  Constant for the PID - How fast will the system move when driven. */
		public static final Supplier<Double> kMovement = ConstantHandler.addConstantDouble("Arcade PID movement speed.", 5); //All temp	
	}

    public static interface cameras{

		public static final Supplier<Integer> kCameraWidth = ConstantHandler.addConstantInt("Camera Width", 320);
		public static final Supplier<Integer> kCameraHeight = ConstantHandler.addConstantInt("Camera Height", 240);
		public static final Supplier<Integer> kCameraExposure = ConstantHandler.addConstantInt("Camera Exposure", 50);
	}

    public static interface Elevator{
		/**The distance the elevator passes between the encoder's ticks. */
		public static Supplier<Double> kDistancePerPulse = ConstantHandler.addConstantDouble("Elevator distance per pulse", 0.8157894); //19 ticks per motor turn, 15.5 cm per crank turn
		public static Supplier<Double> kElevatorMotorSpeedModifier = ConstantHandler.addConstantDouble("Elevator speed modifier", 0.8); //temp
		public static Supplier<Double> kElevatorStallSpeedModifier = ConstantHandler.addConstantDouble("Elevator Stall speed modfier", 0.17); //temp

		public static Supplier<Double> kRocketBottomHatchHeight = ConstantHandler.addConstantDouble("Rocket bottom hatch height", 48.26); 
		public static Supplier<Double> kRocketMiddleHatchHeight = ConstantHandler.addConstantDouble("Rocket middle hatch height", 119.38); 
		public static Supplier<Double> kRocketTopHatchHeight = ConstantHandler.addConstantDouble("Rocket top hatch hight", 190.5); 
		
		public static Supplier<Double> kRocketBottomCargoHeight = ConstantHandler.addConstantDouble("Rocket bottom cargo hight", 69.85); 
		public static Supplier<Double> kRocketMiddleCargoHeight = ConstantHandler.addConstantDouble("Rocket middle cargo hight", 140.97); 
		public static Supplier<Double> kRocketTopCargoHeight = ConstantHandler.addConstantDouble("Rocket top cargo hight", 212.09); 
		
		public static Supplier<Double> kElevatorMaxHeight = ConstantHandler.addConstantDouble("Elevator Max height", 225);
		public static Supplier<Double> kElevatorEncoderMaxHeight = ConstantHandler.addConstantDouble("Elevator Encoder max hight", 250); //temp
		public static Supplier<Double> kElevatorEncoderMinHeight = ConstantHandler.addConstantDouble("Elevator Encoder max hight", 0); //temp

	}
	
    public static interface gripper {
		/**The speed of the gripper when it catches things.*/
		public static final Supplier<Double> kGripperInSpeed = ConstantHandler.addConstantDouble("Gripper In Speed", 0.3);
		/**The speed of the gripper when it releases things. */
		public static final Supplier<Double> kGripperOutSpeed = ConstantHandler.addConstantDouble("Gripper Out Speed", -0.3);
		/**The voltage of the optic sensor, which when reached, means the gripper catched the cargo.  */
		public static final Supplier<Double> kVoltageLimit = ConstantHandler.addConstantDouble("Optic Sensor voltage limit", 1.5);
		
	}
	
	public static interface ClimbingShaft {
    	public static final Supplier<Double> shaftMotorSpeedModifier = ConstantHandler.addConstantDouble("Shaft Motor Speed Modifier", 0.6);
		public static final Supplier<Double> kShaftMotorStallSpeed = ConstantHandler.addConstantDouble("Shaft Stall speed", 0.3);
	}

	public static interface ClimbingMovement {
		public static Supplier<Double> kClimbingSpeed = ConstantHandler.addConstantDouble("Climbing movement speed modifier", 0.5); //temp
		public static Supplier<Double> kTargetHeight = ConstantHandler.addConstantDouble("Climbing movement target height", 1); //temp
	}
}
