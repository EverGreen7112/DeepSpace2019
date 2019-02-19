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
              chassisTalonFL = 1, //CAN
              chassisTalonBL = 2, //CAN
              chassisTalonFR = 3, //CAN
              chassisTalonBR = 4; //CAN
//all temp
  
  //----------cameras----------
  public static final int
              cameraA = 0,
              cameraB = 1;  //All temp
  
  //----------Elevator----------
  public static final int  
              elevatorTalonL = 7, //CAN
              elevatorTalonR = 8, //CAN
              elevatorEncoderA = 3, //DIO
              elevatorEncoderB = 4, //DIO
              elevatorMicroswitch = 5, //DIO 
              elevatorOpticSwitch = 6, //DIO
              elevatorLazerDistanceSensor = 1; //Analog
              //All tem

//----------ClimbingShaft----------
public static final int 
            shaftTalon = 1, 
            shaftTopLimiter = 0, 
            shaftBottomLimiter = 1; //temp



//----------Climbing Movement----------
public static final int 
              climbingMovementTalon = 0,
              climbingMovementEncoderA = 1,
              climbingMovementEncoderB = 2; //temp

//----------Gripper----------
  public static final int 
            gripperLeftPistonF= 0, //Left Forward
            gripperLeftPistonR= 1, //Left Reverse
            gripperRightPistonF = 2, //Right Forward
            gripperRightPistonR = 3, //Right Reverse
            gripperMicroswitch = 4, 
            gripperAnalogLazerSensor = 5, //analog
            gripperMotorLeft = 6,
            gripperMotorRight = 7;
            //All temp
            
//----------Gripper Movement----------
 public static final int
            gripperMovementLeftPistonF = 0, //Left Forward 
            gripperMovementLeftPistonR = 1, //Left Reverse
            gripperMovementRightPistonF = 2, //Left Forward
            gripperMovementRightPistonR = 3, //Left Reverse
            gripperMovementTopMicroswitch = 4,
            gripperMovementBottomMicroSwitch = 5;
}

