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
  
  //----------Chassis----------
  public static final int 
              chassisVictorFL = 7, //CAN
              chassisVictorBL = 8, //CAN
              chassisVictorFR = 2, //CAN
              chassisVictorBR = 5; //CAN
//all temp
  
  //----------cameras----------
  public static final int
              cameraA = 0,
              cameraB = 1;  //All temp
  
  //----------Elevator----------
  public static final int  
              elevatorMotorL = 3, //CAN
              elevatorMotorR = 4, //CAN
              elevatorEncoderA = 0, //DIO
              elevatorEncoderB = 1, //DIO
              elevatorOpticSwitch = 6, //DIO
              elevatorLazerDistanceSensor = 1; //Analog
              //All tem

//----------ClimbingShaft----------
public static final int 
            shaftTalon = 6, //CAN
            shaftBottomLimiter = 2; //DIO



//----------Climbing Movement----------
public static final int 
              climbingMovementMotor = 1, //CAN
              climbingMovementEncoderA = 1, //DIO
              climbingMovementEncoderB = 2; //DIO

//----------Gripper----------
  public static final int 
            gripperMotorRight = 9, //CAN
            gripperMotorLeft = 10, //CAN
            gripperAnalogLazerSensor = 3; //analog
            

            
//----------Gripper Movement----------
public static final int
            gripperMovementVictor  = 0; //CAN
}
