/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;

/**This is the class that contains each constant neccisaty */
public class SubsystemConstants {

	/**The constants for the chassis' <a href="https://bit.ly/2H6fnUT">PID loop</a>*/
	public static interface PID  { //All temp.
		/**The point the system will try to drive to - the center of the reflectives seen by the camera. */
		public static final Supplier<Double> kSetPoint = ConstantHandler.addConstantDouble("Arcade PID - Set point", 0);
		/**The range of speed multipliers the system can output for the fix - here, from -0.5 to 0.5. (|-0.5 - 0.5| = 1)*/
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
	/**The constants for the chassis subsystem, which controls the robot's wheels. */
	public static interface chassis {
		public static final Supplier<Double> kDrivingSpeedModifier = ConstantHandler.addConstantDouble("Driving Spped Modifier", 0.5); //temp
	}

	/**the constants for the CameraHandler, which shows camera video on the shuffleboard.  */
    public static interface cameras {
		public static final Supplier<Integer> kCameraWidth = ConstantHandler.addConstantInt("Camera Width", 320);
		public static final Supplier<Integer> kCameraHeight = ConstantHandler.addConstantInt("Camera Height", 240);
		public static final Supplier<Integer> kCameraExposure = ConstantHandler.addConstantInt("Camera Exposure", 50);
	}

	/**The constants for the elevator subsystem which lifts the gripper to the desired hatches:
	 * The heights of the hatches and cargo on the rocket and the speed modifiers during its different movements
	 * (Motor, Target and Stall), as well as the distance passd each tick of the encoder. */
    public static interface Elevator{

		//----------Speed Modifiers----------
	 	 /**The elevator's speed as it moves to a hatch or a cargo on the rocket.  */
		 public static Supplier<Double> kTargetSpeedModifier = ConstantHandler.addConstantDouble("Elevator Move to target speed modfier", 0.15); //temp
	 	 /**The modifier needed to give to the motors in order get the elevator to stay up while not moving.*/
	  	 public static Supplier<Double> kElevatorStallSpeedModifier = ConstantHandler.addConstantDouble("Elevator Stall speed modfier", 0.17); //temp
	 	 /**The modifier needed to give to the motors in order get the elevator to stay up while not moving, given maximum weight on it.*/		 
		 public static Supplier<Double> kStallMaxMultiplier = ConstantHandler.addConstantDouble("Stall elevator supplier", 0.59); //temp

		//----------Hatch Heights----------
		 public static Supplier<Double> kRocketBottomHatchHeight = ConstantHandler.addConstantDouble("Rocket bottom hatch height", 48.26); 
		 public static Supplier<Double> kRocketMiddleHatchHeight = ConstantHandler.addConstantDouble("Rocket middle hatch height", 119.38); 
		 public static Supplier<Double> kRocketTopHatchHeight = ConstantHandler.addConstantDouble("Rocket top hatch height", 190.5); 
		
		//----------Cargo Heights----------
		 public static Supplier<Double> kRocketBottomCargoHeight = ConstantHandler.addConstantDouble("Rocket bottom cargo height", 69.85); 
		 public static Supplier<Double> kRocketMiddleCargoHeight = ConstantHandler.addConstantDouble("Rocket middle cargo height", 140.97); 
	 	 public static Supplier<Double> kRocketTopCargoHeight = ConstantHandler.addConstantDouble("Rocket top cargo height", 212.09); 
		
		//----------Elevator Limit Heights----------
		
	 	 /**The highest point the elevator can reach relative to the ground.*/		 
		 public static Supplier<Double> kMaxHeight = ConstantHandler.addConstantDouble("Elevator Max height", 225);
		 /**The height of the encoder reset switch - the point where it is set zero (its refrence point).
		  * The distance given from its information will be relative to this point. */
		 public static Supplier<Double> kEncoderBonusHeight = ConstantHandler.addConstantDouble("Elevator bonus height", 40); //temp
		 /**The highest point the elevator can reach, relative to the reset switch, used to check the validity of the height sensors.  */ 
		 public static Supplier<Double> kEncoderMaxHeight = ConstantHandler.addConstantDouble("Elevator Encoder max height", kMaxHeight.get() - kEncoderBonusHeight.get());
		 /**The lowest point the elevator can reach, relative to the reset switch, used to check the validity of the height sensors. */
		 public static Supplier<Double> kEncoderMinHeight = ConstantHandler.addConstantDouble("Elevator Encoder max height", 0); //temp
		 
		//----------Miscellaneous----------
		 /**The distance the elevator passes between the encoder's ticks. */
	 	 public static Supplier<Double> kDistancePerPulse = ConstantHandler.addConstantDouble("Elevator distance per pulse", 0.8157894); //19 ticks per motor turn, 15.5 cm per crank turn

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
		public static final Supplier<Double> kShaftMotorSpeedModifier = ConstantHandler.addConstantDouble("Shaft Motor Speed Modifier", 0.6);
		public static final Supplier<Double> kShaftMotorSpeedModifierUp = ConstantHandler.addConstantDouble("Shaft Motor Speed Modifier Upwards", -0.6);
		public static final Supplier<Double> kShaftMotorStallSpeed = ConstantHandler.addConstantDouble("Shaft Stall speed", 0.3);
	}

	public static interface ClimbingMovement {
		public static Supplier<Double> kClimbingSpeed = ConstantHandler.addConstantDouble("Climbing movement speed modifier", 0.5); //temp
		public static Supplier<Double> kClimbingSpeedForward = ConstantHandler.addConstantDouble("Climbing movemnt speed forward modifier -FOR TESTING-", -0.5); //temp
		public static Supplier<Double> kTargetHeight = ConstantHandler.addConstantDouble("Climbing movement target height", 1); //temp
	}
}
