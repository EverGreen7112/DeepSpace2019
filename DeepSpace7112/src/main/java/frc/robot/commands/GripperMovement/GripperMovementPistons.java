/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.GripperMovement;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.SubsystemComponents;

public class GripperMovementPistons extends Command {
  public GripperMovementPistons() {
    requires(Robot.gripper);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    SubsystemComponents.GripperMovement.LockPiston.set(Value.kReverse);
    if(SubsystemComponents.GripperMovement.MovementPiston.get().compareTo(Value.kForward) == 0)
      SubsystemComponents.GripperMovement.MovementPiston.set(Value.kReverse);
    else
      SubsystemComponents.GripperMovement.MovementPiston.set(Value.kForward);

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
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
