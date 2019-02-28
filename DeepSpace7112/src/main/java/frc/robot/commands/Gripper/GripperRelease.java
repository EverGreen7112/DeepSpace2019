/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Gripper;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.SubsystemComponents;

public class GripperRelease extends Command {
  public GripperRelease() {
    requires(Robot.gripper);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    // SubsystemComponents.Gripper.leftPiston.set(Value.kForward); //Commented since RobotB does not have Solenoids 
    // SubsystemComponents.Gripper.rightPiston.set(Value.kForward); //Commented since RobotB does not have Solenoids
  }

  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    // SubsystemComponents.Gripper.leftPiston.set(Value.kReverse); //Commented since RobotB does not have Solenoids
    // SubsystemComponents.Gripper.rightPiston.set(Value.kReverse); //Commented since RobotB does not have Solenoids
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
