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
              chassisVictorFL = 8, //CAN
              chassisVictorBL = 5, //CAN
              chassisVictorFR = 2, //CAN
              chassisVictorBR = 7; //CAN
  
//----------cameras----------
  public static final int
    cameraA = 0,
    cameraB = 1;  //All temp
  
//----------Elevator----------
  public static final int
              elevatorMotorA = 3, //CAN
              elevatorMotorB = 6, //CAN
              elevatorEncoderA = 0, //DIO
              elevatorEncoderB = 1, //DIO
              elevatorOpticSwitch = 6, //DIO
              elevatorLazerDistanceSensor = 2; //Analog
              //All temp

//----------Climbing Frame----------
  public static final int 
    frameTalon = 1, //CAN
    frameBottomLimiter = 3; //DIO

//----------Climbing Movement----------
  public static final int 
    climbingMovementMotor = 0, //CAN
    climbingMovementEncoderA = 1, //DIO
    climbingMovementEncoderB = 2; //DIO

//----------Gripper----------
  public static final int 
    gripperMotorLeft = 9  , //CAN
    gripperMotorRight = 4, //CAN
    gripperLeftPistonF= 0, //Left Forward -TEMP-
    gripperLeftPistonR= 1, //Left Reverse -TEMP-
    gripperRightPistonF = 2, //Right Forward -TEMP-
    gripperRightPistonR = 3, //Right Reverse -TEMP-
    gripperAnalogLazerSensor = 5; //analog
            
//----------Gripper Movement----------
  public static final int
    gripperMovementPushPistonF = 6, 
    gripperMovementPushPistonR = 2, 
    gripperMovementLockPistonF = 5, 
    gripperMovementLockPistonR = 4, 
    gripperMovementPistonF = 7,
    gripperMovementPistonR = 3,
    gripperMovementTopMicroswitch = 4, 
    gripperMovementBottomMicroSwitch = 5; 
}
/**
 * By Number:
  * Motors:
    * 0 - Climbing Movement
    * 1 - Frame
    * 3 - ElevatorA
    * 4 - Gripper right
    * 6 - ElevatorB
    * 9 - Gripper left
  * Pistons:
    * 2 - push reverse
    * 4 - lock reverse
    * 5 - lock forward
    * 6 - push forward
    * 7 - movement forward
 */
