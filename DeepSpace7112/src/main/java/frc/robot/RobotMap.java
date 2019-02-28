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
              //all temp
  
//----------cameras----------
    public static final int
                cameraA = 0,
                cameraB = 1;  //All temp
  
//----------Elevator----------
  public static final int  
              elevatorMotorF = 3, //CAN
              elevatorMotorB = 6, //CAN
              elevatorEncoderA = 0, //DIO
              elevatorEncoderB = 1, //DIO
              elevatorOpticSwitch = 6, //DIO
              elevatorLazerDistanceSensor = 1; //Analog
              //All tem

//----------ClimbingShaft----------
  public static final int 
              shaftTalon = 1, //CAN
              shaftBottomLimiter = 2; //DIO



//----------Climbing Movement----------
  public static final int 
                climbingMovementMotor = 0, //CAN
                climbingMovementEncoderA = 1, //DIO
                climbingMovementEncoderB = 2; //DIO

//----------Gripper----------
  public static final int 
            gripperMotorLeft = 9  , //CAN
            gripperMotorRight = 4, //CAN
            gripperLeftPistonF= 0, //Left Forward
            gripperLeftPistonR= 1, //Left Reverse
            gripperRightPistonF = 2, //Right Forward
            gripperRightPistonR = 3, //Right Reverse
            gripperAnalogLazerSensor = 5; //analog
            
//----------Gripper Movement----------
 public static final int
            gripperMovementLeftPistonF = 0, //Left Forward 
            gripperMovementLeftPistonR = 1, //Left Reverse
            gripperMovementRightPistonF = 2, //Left Forward
            gripperMovementRightPistonR = 3, //Left Reverse
            gripperMovementTopMicroswitch = 4,
            gripperMovementBottomMicroSwitch = 5;
}

