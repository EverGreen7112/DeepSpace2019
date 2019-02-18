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

/**
 * A {@link Command} that moves the the elevator to a target, at a set speed.
 * If the elevator is above the target, it will move down.
 * If the elevator is under the target, it will move up.
 */
public class ElevatorMoveToTarget extends Command {

  private Supplier<Double> speed; //the speed of the elevator
  private Supplier<Double> target; //the target
  private BasicSubsystem subsystem; //the elevator subsystem
  private boolean flag; //if above the target

  public ElevatorMoveToTarget(Supplier<Double> speed, Supplier<Double> target) {
    requires(Robot.elevator);
    this.speed = speed;
    this.target = target;
  }

  // Called just before this Command runs the first time
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
      subsystem.move(speed.get());
    }
    else
    {
      subsystem.move(-speed.get());
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
    subsystem.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
