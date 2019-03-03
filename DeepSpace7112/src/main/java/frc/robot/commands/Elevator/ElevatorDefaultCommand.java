/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Elevator;

import java.util.function.Supplier;

import com.spikes2212.genericsubsystems.basicSubsystem.commands.MoveBasicSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.SubsystemComponents;
import frc.robot.SubsystemConstants;

public class ElevatorDefaultCommand extends CommandGroup {
  public OI oi = new OI();
  public static Supplier<Double> stallSpeed = () ->
  SubsystemConstants.Elevator.kStallMaxMultiplier.get() *
  (SubsystemComponents.Elevator.getElevatorHeight()/SubsystemConstants.Elevator.kEncoderMaxHeight.get());
  //^^^ Gets the speed needed at the elevator's maximum height, and multiplies it by the percentage rose so far.
  /**
   * Determines if the elevator needs to be held at place or follow a joystic's value, snd act accordingly.
   */
  public ElevatorDefaultCommand() {
    if(oi.getBTJoystick()<0.05 && oi.getBTJoystick()>-0.05)
      addSequential(new MoveBasicSubsystem(Robot.elevator, stallSpeed.get()));
    else
      addSequential(new MoveBasicSubsystem(Robot.elevator, oi::getBTJoystick));
  }
}
