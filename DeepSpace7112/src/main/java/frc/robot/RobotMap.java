/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  
  // //----------Chassis----------
  // public static final int 
  //             chassisTalonL = 5,
  //             chassisVictorL = 0,
  //             chassisTalonR = 6,
  //             chassisVictorR = 1; //all temp

//---------Chassis---------
	
	/**
	 * The chassis ports.
	 * 
	 * @see {@link org.usfirst.frc.team7112.robot.subsystems.Chassis Chassis}
	 */
	public static final int 
			Chassis_Talon_BackLeft = 1,
			Chassis_Talon_FrontLeft = 3,
			Chassis_Talon_BackRight = 4,
			Chassis_Talon_FrontRight = 5;
	
	//Encoders
	public static final int
			Chassis_Encoder_Left_A = 3,
			Chassis_Encoder_Left_B = 4,
			Chassis_Encoder_Right_A = 5,
			Chassis_Encoder_Right_B = 6;
	
	
	//---------Claw---------
	
	//Talon
	public static final int
			Claw_Talon = 0;
	
	//MicroSwitches
	public static final int 
			Claw_MicroSwitch_In = 7;
	
	//---------Climber---------
	
	//Sparks
	public static final int
			Climber_Tape_Spark = 0,
			Climber_Rope_BackSpark = 1,
			Climber_Rope_FrontSpark = 2;
		
	//---------Angle---------
	
	//Talon
	public static final int
			Angle_Talon = 2;
	
	//MicroSwitch
	public static final int
			Angle_MicroSwitch = 8;
	
	//Encoder
	public static final int
			Angle_Encoder_A = 0,
      Angle_Encoder_B = 1;
      

  public static final int cameraA = 0;
}
