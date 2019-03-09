/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Elevator;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.SubsystemComponents;
import frc.robot.SubsystemConstants;

public class ElevatorDefault extends Command {
  public ElevatorDefault() {
    requires(Robot.elevator);
  }
  public static Supplier<Boolean> stallMode = () -> Robot.oi.getBTJoystickLeft() < 0.05 && Robot.oi.getBTJoystickLeft() > -0.05;

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.dbc.addBoolean("stalling", stallMode);
    Robot.dbc.addNumber("Stall Speed", SubsystemComponents.Elevator::getStallSpeed);
    Robot.dbc.addNumber("Stall Speed", SubsystemComponents.Elevator::getElevatorHeight);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // if(stallMode.get()) 
    // {
    //   System.out.println("Stalling Elevator");
    //   Robot.elevator.move(SubsystemComponents.Elevator.getStallSpeed());
    // }

    // else
    // {
      System.out.println("Moving Elevator: " + Robot.oi.getBTJoystickLeft());
      Robot.elevator.move(Robot.oi.getBTJoystickLeft());
    // }
    
    /*if(SubsystemComponents.Elevator.opticSwitch.get()) 
    {
      SubsystemComponents.Elevator.encoderWasReset = true;
      SubsystemComponents.Elevator.encoder.reset();
    }

    if(oi.getBTJoystick()<0.05 && oi.getBTJoystick()>-0.05) {
      stalling = true;
      Robot.elevator.move(SubsystemComponents.Elevator.getStallSpeed());
      // SubsystemComponents.Elevator.motors. //testing
    }

    else {
      stalling = false;
      Robot.elevator.move(oi.getBTJoystick()); 
    }*/ //commented for testing
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
