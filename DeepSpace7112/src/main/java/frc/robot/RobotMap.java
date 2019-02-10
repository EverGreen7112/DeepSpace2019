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
  //----------Elevator----------
  public static final int 
              elevatorTalonL = 7, //CAN
              elevatorTalonR = 8, //CAN
              elevatorEncoderA = 3, //DIO
              elevatorEncoderB = 4, //DIO
              elevatorMicroswitch = 5, //DIO 
              elevatorOpticSwitch = 6; //DIO
//temp

  //----------ClimbingShaft----------
public static final int shaftTalon = 5; //temp
public static final int shaftTopLimiter = 0; //temp
public static int shaftBottomLimiter = 1; //temp
  //----------Gripper Movement----------
  public static final int
              gripperMovementVictor  = 0,
              gripperMovementTopMicroswitch = 2, 
              gripperMovementBottomMicroswitch = 3;  //All temp 



//----------Climbing Movement----------
public static final int 
              climbingMovementTalon = 0,
              climbingMovementEncoderA = 1,
              climbingMovementEncoderB = 2; //temp

  //----------Gripper----------
    public static final int gripperMotorRight = 5, 
                            gripperMotorLeft = 0,
                            gripperMicroswitch = 1,
                            gripperAnalogLazerSensor = 0; //temp
}
