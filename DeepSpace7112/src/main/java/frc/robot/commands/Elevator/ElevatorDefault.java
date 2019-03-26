/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Elevator;

import java.util.function.Supplier;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.SubsystemComponents;
import frc.robot.SubsystemConstants;

public class ElevatorDefault extends Command {
  public ElevatorDefault() {
    requires(Robot.elevator);
  }

  // public static Supplier<Boolean> stallMode = () -> Robot.oi.getBTJoystickLeft() < 0.05 && Robot.oi.getBTJoystickLeft() > -0.05;
  public static boolean speedLock = false;
  public static boolean switchHit = false;
  public static double lockedSpeed = 0;
  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    // Robot.dbc.addBoolean("stalling", stallMode);
    Robot.dbc.addBoolean("stalling", () -> speedLock);
    // Robot.dbc.addNumber("Stall Speed", SubsystemComponents.Elevator::getStallSpeed);
    Robot.dbc.addNumber("Stall Speed", SubsystemComponents.Elevator::getElevatorHeight);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(speedLock) 
    {
      System.out.println("Stalling Elevator: " + lockedSpeed);
      // Robot.elevator.move(SubsystemComponents.Elevator.getStallSpeed());
      Robot.elevator.move(lockedSpeed);
    }

    else
    {
      // System.out.println("Moving Elevator: " + Robot.oi.getBTJoystickLeft());

      if(Robot.oi.getBTJoystickLeft() < 0 && !switchHit && SubsystemComponents.Elevator.opticSwitch.get())
      {
        SubsystemComponents.Elevator.encoder.reset();
        switchHit = true;
        SubsystemComponents.Elevator.encoderWasReset = true;
      }

      if(Robot.oi.getBTJoystickLeft() > 0)
      {
        switchHit = false;
      }

      if(Math.abs(Robot.oi.getBTJoystickLeft()) > 0.13)
      {
        Robot.elevator.move(Robot.oi.getBTJoystickLeft());
      }

      else
      {
        Robot.elevator.move(0);
      }

    }
    
    // if(SubsystemComponents.Elevator.opticSwitch.get()) 
    // {
    //   System.out.println("aaa");
    //   SubsystemComponents.Elevator.encoderWasReset = true;
    //   SubsystemComponents.Elevator.encoder.reset();
    // }

    // if(Robot.oi.getBTJoystickLeft()<0.05 && Robot.oi.getBTJoystickLeft()>-0.05) {
    //   System.out.println("Stalling Elevator");
    //   SubsystemComponents.Elevator.motorA.setNeutralMode(NeutralMode.Brake); 
    //   SubsystemComponents.Elevator.motorB.setNeutralMode(NeutralMode.Brake);
    // }

    // else {
    //   // stalling = false;
    //   System.out.println("Moving Elevator: " + Robot.oi.getBTJoystickLeft());
    //   SubsystemComponents.Elevator.motorA.setNeutralMode(NeutralMode.Coast); 
    //   SubsystemComponents.Elevator.motorB.setNeutralMode(NeutralMode.Coast);
    //   Robot.elevator.move(Robot.oi.getBTJoystickLeft()); 
    // } //commented for testing
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
