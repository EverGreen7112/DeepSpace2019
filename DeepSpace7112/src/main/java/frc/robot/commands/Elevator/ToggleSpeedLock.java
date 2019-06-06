/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.Library.OI.Switches.Classes.SwitchHandler;
import frc.robot.OI;
import frc.robot.SubsystemConstants;

public class ToggleSpeedLock extends Command implements SubsystemConstants.ElevatorConsts {

  /**
   * Called once when the command is ran - keeps the current speed of the elevator, and changes
   * {@link ElevatorDefault} to stall mode, where it will 
  */
  @Override
  protected void execute() {
    ElevatorDefault.lockedSpeed = OI.getBTJoystick() * SpeedModifiers.kJoystickSpeedModifier.get();
    SwitchHandler.toggle(ElevatorDefault.speedLock);
  }

  /**Always return true - the command should finish after one iteration */
  @Override
  protected boolean isFinished() {
    return true;
  }
}
