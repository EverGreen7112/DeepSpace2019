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
              chassisVictorFR = 7, //CAN
              chassisVictorBR = 2; //CAN
  
//----------cameras----------
  public static final int
    cameraA = 0,
    cameraB = 1;
  
//----------Elevator----------
  public static final int
              elevatorMotorA = 3, //CAN
              elevatorMotorB = 6, //CAN
              elevatorEncoderA = 0, //DIO
              elevatorEncoderB = 1, //DIO
              elevatorOpticSwitch = 6, //DIO
              elevatorLazerDistanceSensor = 2; //Analog

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
    gripperRightPistonF = 2, //Right Forward
    gripperRightPistonR = 3, //Right Reverse
    gripperAnalogLazerSensor = 5; //analog
            
//----------Gripper Movement----------
  public static final int
    gripperMovementPushPistonF = 5, 
    gripperMovementPushPistonR = 4, 
    gripperMovementLockPistonF = 1, 
    gripperMovementLockPistonR = 2, 
    gripperMovementPistonF = 7,
    gripperMovementPistonR = 3,
    gripperMovementTopMicroswitch = 4, 
    gripperMovementBottomMicroSwitch = 5; 
}

/*
 * By Number:
  * Motors (Victors):
    * 0 - Climbing Movement;
    * 1 - Frame;
    * 2 - Chassis Front-Right;
    * 3 - ElevatorA;
    * 4 - Gripper right;
    * 5 - Chassis back-left;
    * 6 - ElevatorB;
    * 7 - Chassis back-right;
    * 8 - Chassis front-left;
    * 9 - Gripper left;
  * Pistons:
    * 1 - push forward;
    * 2 - push reverse;
    * 4 - lock reverse;
    * 5 - lock forward;
    * 7 - movement forward;
  * Cameras:
    * 0 - Front;
    * 1 - Back;
  * Encoders:
    * 0 - Elevator A;
    * 1 - Elevator B;
    * 1 - Clmbing Movement A;
    * 2 - Climbing Movement B;
  * Switches:
    * 3 - Frame bottom;
    * 4 - Gripper Movement top;
    * 5 - Gripper movement bottom;
    * 6 - Elevator optic;
  * Laser:
    * 2 - Elevator distance;
    * 5 - Gripper CargoCaught;
 */