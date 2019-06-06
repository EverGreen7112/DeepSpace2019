/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.Library.Subsystems.PistonSubsystems.Commands;

import frc.Library.OI.Switches.Commands.CommandWithSwitch;
import frc.Library.Subsystems.PistonSubsystems.Classes.PistonSubsystem;

public class TogglePistonSubsystem extends CommandWithSwitch {
  PistonSubsystem pistons;
  public TogglePistonSubsystem(PistonSubsystem pistons, String switchName) {
    super(switchName);
    requires(pistons);
    this.pistons = pistons;
  }

  public TogglePistonSubsystem(PistonSubsystem pistons, String switchName, boolean defaultValue) {
    super(switchName, defaultValue);
    requires(pistons);
    this.pistons = pistons;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(switchOn.get())
    {
      pistons.toggle();
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }
}
