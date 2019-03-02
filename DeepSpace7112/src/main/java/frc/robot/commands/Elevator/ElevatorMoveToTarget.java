/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Elevator;

import java.util.function.Supplier;

import com.spikes2212.genericsubsystems.basicSubsystem.BasicSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.SubsystemComponents;
import frc.robot.SubsystemConstants;

/**
 * A {@link Command} that moves the the elevator to a target, at a set speed.
 * If the elevator is above the target, it will move down.
 * If the elevator is under the target, it will move up.
 */
public class ElevatorMoveToTarget extends Command {

  /**The speed modifier of the elevator as it moves to target.*/
  private Supplier<Double> speedModifier;
  /**The height of the target relative  to the ground.*/ 
  private Supplier<Double> target;
  /**The subsystem which will move to the target - the elevator subsystem */
  private BasicSubsystem subsystem;
  /**Whether or not the elevator is above the target?*/
  private boolean flag;

  /**The constructor for this class, which sets its speed and target.
   * @param speedModifier - the speed modifier for the elevator's movement to the target.
   * @param target - the height of the target that the elevator need to move to, relative to the ground.
  */
  public ElevatorMoveToTarget(Supplier<Double> speedModifier, Supplier<Double> target) {
    requires(Robot.elevator);
    this.speedModifier = speedModifier;
    this.target = target;
  }

  /**Called just before this Command runs the first time -
   * initlizes the subsystem as elevatora ans sets the current state of {@link #flag}.*/
  @Override
  protected void initialize() {
    subsystem = Robot.elevator;
    if(target.get() - SubsystemComponents.Elevator.encoder.getDistance() > 0)
      flag = false;
    else flag = true;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(SubsystemComponents.Elevator.opticSwitch.get()){
      SubsystemComponents.Elevator.encoder.reset();
    }
   
    if(target.get() - SubsystemComponents.Elevator.encoder.getDistance() > 0){
      subsystem.move(speedModifier.get());
    }
    
    else
    {
      subsystem.move(-speedModifier.get());
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(flag)
      return SubsystemComponents.Elevator.encoder.getDistance() <= target.get();
    else
      return SubsystemComponents.Elevator.encoder.getDistance() >= target.get();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    subsystem.move(SubsystemConstants.Elevator.kElevatorStallSpeedModifier.get());
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
