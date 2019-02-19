/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Cameras;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class SwitchToCameraB extends CommandGroup {
  /**
   * The command to switch the CameraHandler to view camera B (from port 1).
   */
  public SwitchToCameraB() {
    Robot.cameraHandler.switchCamera(RobotMap.cameraB);
  }
}