/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Cameras;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.SubsystemConstants;

public class CycleCameras extends Command {
  public static int currentCamera;
  public CycleCameras() {
  }
  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.cameraHandler.switchCamera(++currentCamera % SubsystemConstants.CameraConsts.kCamerasNumber.get());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }
}
