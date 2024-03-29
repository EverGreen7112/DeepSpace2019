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
	public static interface SmartP
	{
		public static Supplier<Double> kA = ConstantHandler.addConstantDouble("Smart P - a Constant", 0.8);
		public static Supplier<Double> kMaxDeviationFix = ConstantHandler.addConstantDouble("Smart P - Max deviation Fix Speed", 85);
		public static Supplier<Double> kSpeedModifier = ConstantHandler.addConstantDouble("Smart P - Speed Moifier", 0.6);
	}
	public static interface PID  { //All temp.
		/**The point the system will try to drive to - the center of the reflectives seen by the camera. */
		public static final Supplier<Double> kSetPoint = () -> ImageProccessingSuppliers.center.pidGet();
		/**The range of speed multipliers the system can output for the fix - here, from -0.5 to 0.5. (|-0.5 - 0.5| = 1)*/
		public static final Supplier<Double> kOutputRange = ConstantHandler.addConstantDouble("PID Output Range", 1);
		/** The proportional constant for the PID */
        public static final Supplier<Double> kP = ConstantHandler.addConstantDouble("PID proportional constant", 0);
        /**The Integral Constant for the PID. */ 
        public static final Supplier<Double> kI = ConstantHandler.addConstantDouble("PID integral constant", 0);
        /**The Derviative constant for the PID. */
        public static final Supplier<Double> kD = ConstantHandler.addConstantDouble("PID derivative constant", 0);
        /**The tolerance constant for the PID - how much deviation should the robot tolerate before trying to move back to the line? */
        public static final Supplier<Double> kTolerance = ConstantHandler.addConstantDouble("PID tolerance", 0.02);
        /**The Wait Time constant for the PID - How much time should the robot stay in a too deviated position before trying to move back to the line? */
        public static final Supplier<Double> kWaitTime = ConstantHandler.addConstantDouble("PID wait time", 1);
        /**The Movement  Constant for the PID - How fast will the system move when driven. */
		public static final Supplier<Double> kMovement = ConstantHandler.addConstantDouble("PID movement speed.", 5); //All temp	
	}
	/**The constants for the chassis subsystem, which controls the robot's wheels. */
	public static interface Chassis {
		public static final Supplier<Double> kDrivingSpeedModifier = ConstantHandler.addConstantDouble("Driving Speed Modifier", 0.8);
		public static final Supplier<Double> kDefenseSpeedModifier = ConstantHandler.addConstantDouble("Defense Speed Modifier", 0.4);
		public static final Supplier<Double> kSlowSpeedModifier = ConstantHandler.addConstantDouble("Slow Speed Modifier", 0.1);
		public static final Supplier<Double> kFastSpeedModifier = ConstantHandler.addConstantDouble("Fast Speed Modifier", 0.5);


		// public static Supplier<Double> kCurrentSpeedModifier = ConstantHandler.addConstantDouble("Current Speed Modifier", 0.8);
		// public static final Supplier<Double> kDrivingSpeedModifier = ConstantHandler.addConstantDouble("Driving Spped Modifier", 0.85); //temp

	}

	/**the constants for the CameraHandler, which shows camera video on the shuffleboard.  */
    public static interface cameras {
		public static final Supplier<Integer> kCameraWidth = 
			ConstantHandler.addConstantInt("Camera Width", 320);
		public static final Supplier<Integer> kCameraHeight = 
			ConstantHandler.addConstantInt("Camera Height", 240);
		public static final Supplier<Integer> kCameraExposure = 
			ConstantHandler.addConstantInt("Camera Exposure", 50);
		public static final Supplier<Integer> kCamerasNumber = 
			ConstantHandlerEG.addConstantInt("Cameras Number", 2); //The amount of cameras.
	}

	/**The constants for the elevator subsystem which lifts the gripper to the desired hatches:
	 * The heights of the hatches and cargo on the rocket and the speed modifiers during its different movements
	 * (Motor, Target and Stall), as well as the distance passd each tick of the encoder. */
	public static interface Elevator
	{

		public static interface SpeedModifiers
		{
			/**The elevator's speed modifier as it is moved by the joystick */
			public static Supplier<Double> kJoystickSpeedModifier = 
				ConstantHandler.addConstantDouble("Elevator Joystick Move Speed Modifier", 0.8);
			/**The elevator's speed as it moves to a hatch or a cargo on the rocket.*/
			public static Supplier<Double> kTargetSpeedModifier = 
				ConstantHandler.addConstantDouble("Elevator Move to target speed modfier", 0.7); //temp
			/**The modifier needed to give to the motors in order get the elevator to stay up while not moving, given maximum weight on it.*/		 
			public static Supplier<Double> kStallMaxMultiplier = 
				ConstantHandler.addConstantDouble("Stall elevator supplier", 0.17); //temp
			 public static Supplier<Double> kElevatorClimbingSpeedModifier = ConstantHandler.addConstantDouble("Elevator climbing speed modifier", 0.2); //temp
		}

		public static interface RocketHeights
		{
			public static Supplier<Double> kBottomHatch = 
				ConstantHandler.addConstantDouble("Rocket bottom hatch height", 48.26);
			public static Supplier<Double> kMiddleHatch = 
				ConstantHandler.addConstantDouble("Rocket middle hatch height", 119.38);
			public static Supplier<Double> kTopHatch = 
				ConstantHandler.addConstantDouble("Rocket top hatch height", 190.5);
			public static Supplier<Double> kBottomCargo = 
				ConstantHandler.addConstantDouble("Rocket bottom cargo height", 69.85); 
			public static Supplier<Double> kMiddleCargo = 
				ConstantHandler.addConstantDouble("Rocket middle cargo height", 140.97); 
			public static Supplier<Double> kTopCargo = 
				ConstantHandler.addConstantDouble("Rocket top cargo height", 212.09);
		}

		public static interface RocketStall
		{
			public static Supplier<Double> kTopHatch = 
				ConstantHandler.addConstantDouble("Rocket top hatch stall speed", 0.15);
			public static Supplier<Double> kMiddleHatch = 
				ConstantHandler.addConstantDouble("Rocket middle hatch stall speed", 0.13);
			public static Supplier<Double> kBottomHatch = 
				ConstantHandler.addConstantDouble("Rocket bottom hatch stall speed", 0);
			public static Supplier<Double> kTopCargo = 
				ConstantHandler.addConstantDouble("Rocket top cargo stall speed", 0.15);
			public static Supplier<Double> kMiddleCargo = 
				ConstantHandler.addConstantDouble("Rocket middle cargo stall speed", 0.13); 
			public static Supplier<Double> kBottomCargo = 
				ConstantHandler.addConstantDouble("Rocket bottom cargo stall speed", 0);
		}

		public static interface ElevatorLimitHeights
		{
			/**The highest point the elevator can reach relative to the ground.*/		 
			public static Supplier<Double> kMaxHeight = ConstantHandler.addConstantDouble("Elevator Max height", 225);
			/**The height of the encoder reset switch - the point where it is set zero (its refrence point).
			 * The distance given from its information will be relative to this point. */
			public static Supplier<Double> kBonusEncoderHeight = ConstantHandler.addConstantDouble("Elevator bonus height", 47);
			/**The highest point the elevator can reach, relative to the reset switch, used to check the validity of the height sensors.  */ 
			public static Supplier<Double> kEncoderMaxHeight = ConstantHandler.addConstantDouble("Elevator Encoder max height", kMaxHeight.get() - kBonusEncoderHeight.get());
			/**The lowest point the elevator can reach, relative to the reset switch, used to check the validity of the height sensors. */
			public static Supplier<Double> kEncoderMinHeight = ConstantHandler.addConstantDouble("Elevator Encoder min height", 0);
		}

		public static interface Laser
		{
			/**The voltage that lazerSensor.getVoltage() returns when the elevator is at maximum height. */
			public static Supplier<Double> trueMaxHeightVoltage = 
				ConstantHandler.addConstantDouble("Elevator maximum height voltage", 4); //temp
			/**The voltage that lazerSensor.getVoltage() returns when the elevator is at maximum height */
			public static Supplier<Double> minHeightVoltage = 
				ConstantHandler.addConstantDouble("Elevator minimum height voltage", 0); //temp
			/**The voltage that {@link SubsystemComponents.Elevator#lazerSensor lazerSensor}.getVoltage() returns when the elevator is at maximum height 
			 * relative to the value it returns at minimum height,<p>
			 * Used so we can derive the percentage of height the elevator rose from the laser sensor. */
			public static Supplier<Double> relativeMaxHeightVoltage = 
				ConstantHandler.addConstantDouble ( 
					"Elevator relative maximum height voltage", 
					trueMaxHeightVoltage.get()-minHeightVoltage.get());
			/**The slope in the linear function that returns height from voltage - 93.333333333333  */
			public static Supplier<Double> kLaserFunctionSlope = ConstantHandler.addConstantDouble("Laser function slope", 116.6666666666666666666666);
			/**The b constant (y intercept) in the linear function that returns distance from the elevator's laserSensor.getVoltage - 28.8 */
			public static Supplier<Double> kLaserFunctionB = ConstantHandler.addConstantDouble("Laser Function b",-20.666666666666666666);
		}

		public static interface Sensors
		{
		 /**The distance the elevator passes between the encoder's ticks. */
		  public static Supplier<Double> kDistancePerPulse = ConstantHandler.addConstantDouble("Elevator distance per pulse", 0.8157894); //19 ticks per motor turn, 15.5 cm per crank turn
		}

		public static interface targetMove
		{
			public static Supplier<Double> raiseBy = 
				ConstantHandler.addConstantDouble("Elevator raise  - Raise by value", 1.5);
			public static Supplier<Double> lowereBy = 
				ConstantHandler.addConstantDouble("Elevator lower  - lower by value", -1.5);
		}
	}
	
    public static interface gripper {
		/**The speed of the gripper when it catches things.*/
		public static final Supplier<Double> kGripperInSpeed = ConstantHandler.addConstantDouble("Gripper In Speed", 0.7);
		/**The speed of the gripper when it releases things. */
		public static final Supplier<Double> kGripperOutSpeed = ConstantHandler.addConstantDouble("Gripper Out Speed", -0.85);
		/**The voltage of the optic sensor, which when reached, means the gripper catched the cargo.  */
		public static final Supplier<Double> kVoltageLimit = ConstantHandler.addConstantDouble("Optic Sensor voltage limit", 0.7); //temp
		/**The difference between the  */
		public static final Supplier<Double> kCargoGripperLaserDiffrence = ConstantHandler.addConstantDouble("Gripper-Laser diffrence - unflipped", 0); //temp
		public static final Supplier<Double> kHatchGripperLaserDiffrence = ConstantHandler.addConstantDouble("Gripper-Laser difference - flipped", 0); //temp
	}
	
	public static interface ClimbingFrame {
		public static final Supplier<Double> kFrameMotorSpeedModifier = ConstantHandler.addConstantDouble("Frame Motor Speed Modifier", 0.35);
		public static final Supplier<Double> kFrameMotorSpeedModifierUp = ConstantHandler.addConstantDouble("Frame Motor Speed Modifier Upwards", -0.1);
	}

	public static interface ClimbingMovement {
		public static Supplier<Double> kClimbingSpeed = ConstantHandler.addConstantDouble("Climbing movement speed modifier", 0.5); //temp
		// public static Supplier<Double> kTargetHeight = ConstantHandler.addConstantDouble("Climbing movement target height", 1); //temp //Commented due to no use.
		//------------Testing----------
			// public static Supplier<Double> kClimbingSpeedForward = ConstantHandler.addConstantDouble("Climbing movemnt speed forward modifier -FOR TESTING-", -0.5); //temp
	}
}
