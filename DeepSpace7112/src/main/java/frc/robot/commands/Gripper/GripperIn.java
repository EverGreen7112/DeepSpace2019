/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Gripper;

import java.util.function.Predicate;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.SubsystemComponents;
import frc.robot.SubsystemConstants;

public class GripperIn extends Command {
  public GripperIn() {
    requires(Robot.gripper);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("In");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    SubsystemComponents.Gripper.motors.set(SubsystemConstants.gripper.kGripperInSpeed.get()); //testing - maybe setting setting the speed directly through the motor instead of using spikeslib will set a right speed.
    // Robot.gripper.move(SubsystemConstants.gripper.kGripperInSpeed.get()); //commented for testing - currently this only produces 0.3 speed.

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  protected void end() {
    // SubsystemComponents.Gripper.motors.set(0); //testing
    //Robot.gripper.move(0); //Commented because it was constantly being called in execution. currently in WhenReleased.
    

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
