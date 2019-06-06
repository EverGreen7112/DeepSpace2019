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
public interface RobotMap {

  public interface MotorPorts
  {
    public int
      chassisBackRight = 2,
      ElevatorA = 3,
      gripperRight = 4,
      chassisBackLeft = 5,
      ElevatorB = 6,
      chassisFrontRight = 7,
      chassisFrontLeft = 8,
      gripperLeft = 9;
  }

  public interface PistonsPorts
  {
    public int
      toungeForward = 0,
      toungeReverse = 1,
      gripperMovementForward = 2,
      gripperMovementReverse = 3,
      pushForward = 4,
      pushReverse = 5;
  }

  public interface AnalogPorts
  {
    public int
      elevator = 0,
      gripper = 1;
  }

  public interface SwitchPorts
  {
    public int
      elevator = 6;
  }

  public interface EncoderPorts
  {
    public int
      elevatorA = 0,
      elevatorB = 1;
  }

  public interface CameraPorts
  {
    public int
      front = 0,
      side = 1;
  }
}