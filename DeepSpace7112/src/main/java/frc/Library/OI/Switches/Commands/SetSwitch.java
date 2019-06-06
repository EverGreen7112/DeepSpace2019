/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.Library.OI.Switches.Commands;

import frc.Library.OI.Switches.Classes.Switch;
import frc.Library.OI.Switches.Classes.SwitchHandler;

/**A command that sets a {@link Switch shuffleboard switch} an input value. <p>
 * This command has a shuffleboard switch (set at constucion), which, if diabled,
 * will stop this command from running.
*/
public class SetSwitch extends CommandWithSwitch {

  private Switch[] switches;
  private boolean value;

  /**
   * Constructs a {@link CommandWithSwitch} which when ran, sets the input switches at input value
   * @param name - The name of this command and its shuffleboard switch.
   * @param value - The value to set the switches
   * @param switches - The switches to swt.
   */
  public SetSwitch(String name, boolean value, Switch... switches) {
    super(name);
    this.switches = switches;
  }

    /**
   * Constructs a {@link CommandWithSwitch} which when ran, sets the input switches at input value
   * @param name - The name of this command and its shuffleboard switch.
   * @param defaultValue - The default value of this command's switch
   * @param valueToSet - The value to set the switches
   * @param switches - The switches to swt.
   */
  public SetSwitch(String name, boolean defaultValue, boolean valueToSet, Switch... switches) {
    super(name, defaultValue);
    this.switches = switches;
  }

  /**Runs once when this command is called: if this command is enabled, 
   * sets the switches given at construction to the value given at construction.*/
  @Override
  protected void execute() 
  {
    if(switchOn.get())
    {
      SwitchHandler.set(value, switches);
    }
  }

  /**Always returns true - this command should run only once.*/
  @Override
  protected boolean isFinished() {
    return true;
  }
}
