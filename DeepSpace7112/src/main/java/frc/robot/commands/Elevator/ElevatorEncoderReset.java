/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.SubsystemComponents;

/**
 * Resets the encoder when the optic switch is triggered. 
 * This is important, as if it is not reset often, the encoder can easily reach significant errors.
 * <p>
 * When the robot starts a match, its encoder is at 0 while the gripper is below the switch.
 * This means the point of refrence is different from the usuall, which means we recieve incorrect values.
 * To fix that, the code does not use the encoder unless it has been reset at least once. This code also deals
 *with the apropriate boolean for this to true. 
 */
public class ElevatorEncoderReset extends Command {
  public ElevatorEncoderReset() {
    requires(Robot.elevator);
  }

  /** Called just before this Command runs the first time - 
   * turns the encoderWasReset to true, showing the encoder was reset at least once.*/
  @Override
  protected void initialize() {
    SubsystemComponents.Elevator.encoderWasReset = true;
  }

  /** Called repeatedly when this Command is scheduled to run - 
   * Checks if the elevator's optic switch was pressed, and if so reset the encoder.
   * <P>
   * As this command is the elevator's default command,
   * this check is repeatedy and constantly being done in the background.  
  */

  @Override
  protected void execute() {
    if(SubsystemComponents.Elevator.opticSwitch.get()) {
      SubsystemComponents.Elevator.encoder.reset();
    }
  }

  /**Make this return true when this Command no longer needs to run execute() - 
   * As resetting repeatedly is always required to avoid errors, this is always false. */
  @Override
  protected boolean isFinished() {
    return false;
  }

  /** Called once after isFinished returns true - this never happens, so this is empty.*/
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
