/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.Library.OI.Switches.Commands;

import frc.Library.OI.Switches.Classes.Switch;
import frc.Library.OI.Switches.Classes.SwitchHandler;

public class ToggleSwitch extends CommandWithSwitch {
  private Switch[] switches;

  public ToggleSwitch(String name, Switch... switches) {
    super(name);
    this.switches = switches;
  }
  
  public ToggleSwitch(String name, boolean defaultValue, Switch... switches) {
    super(name, defaultValue);
    this.switches = switches;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(switchOn.get())
    {
      SwitchHandler.toggle(switches);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }
}
