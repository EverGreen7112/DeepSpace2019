/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Gripper;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.SubsystemComponents;
import frc.robot.SubsystemConstants;

public class GripperOut extends Command {
  public GripperOut() {
    requires(Robot.gripper);
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("out");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    SubsystemComponents.Gripper.motors.set(SubsystemConstants.gripper.kGripperOutSpeed.get());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns tru
  protected void end() {
    // Robot.gripper.move(0); //was constantly ran with execution

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
