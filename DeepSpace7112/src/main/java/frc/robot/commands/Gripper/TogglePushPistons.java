/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Gripper;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.ConstantHandlerEG;
import frc.robot.SubsystemComponents;
import frc.robot.SwitchHandler;

public class TogglePushPistons extends Command {
  public static boolean reversed = true;
  public static Supplier<Boolean> switchOn;

  public TogglePushPistons(boolean switchDefault) {
    switchOn = SwitchHandler.addButtonSwitch("Gripper - Push Pistons Toggle", switchDefault);
  }

    /**Input a boolean value to specificlly open or close the thingie:
   * True to open, false to close.
   */
  public TogglePushPistons(boolean reversedSet, boolean switchDefault) {
    reversed = reversedSet;
    switchOn = SwitchHandler.addButtonSwitch("Gripper - Push Pistons Toggle", switchDefault);

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // SubsystemComponents.Gripper.PushPiston.set(Value.kReverse);
    if(reversed)
    {
      System.out.println("Set Push Piston Reverse");
      SubsystemComponents.Gripper.PushPiston.set(Value.kForward);
      reversed = false;
    }

    else
    {
      System.out.println("Set Push Piston Forward");
      SubsystemComponents.Gripper.PushPiston.set(Value.kReverse);
      reversed = true;
    }
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
