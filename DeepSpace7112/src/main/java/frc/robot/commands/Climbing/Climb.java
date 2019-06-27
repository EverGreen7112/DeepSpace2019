/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Climbing;

import com.spikes2212.genericsubsystems.basicSubsystem.commands.MoveBasicSubsystem;
import com.spikes2212.genericsubsystems.basicSubsystem.commands.MoveBasicSubsystemToTarget;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;

import frc.robot.Robot;
import frc.robot.SubsystemComponents;
import frc.robot.SubsystemConstants;
import frc.robot.commands.Elevator.*;

public class Climb extends CommandGroup {
  /**
   * This is the command that makes the Robot climb.*/

  public Climb() {

    //-----Sequence-----
    addSequential(new MoveBasicSubsystem(Robot.frame, SubsystemConstants.ClimbingFrame.kFrameMotorSpeedModifierUp), 1);
    addParallel(new MoveBasicSubsystem(Robot.elevatorClimb, SubsystemConstants.Elevator.SpeedModifiers.kElevatorClimbingSpeedModifier)); //Move the elevator
    addSequential(new MoveBasicSubsystem(Robot.frame, SubsystemConstants.ClimbingFrame.kFrameMotorSpeedModifier));
    //addSequential(new MoveBasicSubsystem(Robot.climbingMovement, SubsystemConstants.ClimbingMovement.kClimbingSpeed));
    //addSequential(new MoveBasicSubsystem(Robot.frame, -SubsystemConstants.Climbingframe.kframeMotorStallSpeed.get()));
  }
}
