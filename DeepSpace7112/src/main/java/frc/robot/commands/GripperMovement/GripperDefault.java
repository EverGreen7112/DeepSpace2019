/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.GripperMovement;

import frc.robot.Robot;
import frc.robot.commands.GripperMovement.FoldGripper;

public class GripperDefault extends FoldGripper {
  public GripperDefault() {
    requires(Robot.gripper);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
   if(Robot.oi.getBTJoystickRight()>0.9 && !FoldGripper.gripperFolded.get())
   {
     Robot.dbc.addBoolean("ggtrgt", () -> true);
     super.execute();
   }

   else if(Robot.oi.getBTJoystickRight()<-0.9 && FoldGripper.gripperFolded.get())
   {
     Robot.dbc.addBoolean("dcdcdfr4t", () -> true);
     super.execute();
   }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
