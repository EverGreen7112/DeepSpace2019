/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.Library.Subsystems.BasicSubsystems.Commands;

import java.util.function.Supplier;

import com.spikes2212.genericsubsystems.basicSubsystem.BasicSubsystem;
import com.spikes2212.genericsubsystems.basicSubsystem.commands.MoveBasicSubsystem;

import frc.Library.OI.Switches.Classes.SwitchHandler;

/**This command creates a switch in the shuffleboard upon construction, 
 * and moves a given {@link BasicSubsystem} if the switch is on.
 * This is done to more easily disable unused or malfunctioning commands. */
public class MoveBasicSubsystemWithSwitch extends MoveBasicSubsystem {
  public Supplier<Boolean> switchOn;


  /**
   * Adds a switch to the shuffleboard to enable or disable this command, and set the objeccts for this command.
   * @param subsystem - the {@link BasicSubsystem} to be moves whent his commannd is executed. 
   * @param speedSupplier - the supplier for the speed to move the {@link BasicSubsystem} when this command is executed.
   * @param name - The name for the shuffleboard switch for this command. The names are formatted as "Button Switches - " + buttonName.
   */
  public MoveBasicSubsystemWithSwitch(BasicSubsystem subsystem, Supplier<Double> speedSupplier, String name) {
    super(subsystem, speedSupplier);
    switchOn = SwitchHandler.addSwitch(name);
  }

  /**
   * Adds a switch to the shuffleboard to enable or disable this command, and set the objeccts for this command.
   * @param subsystem - the {@link BasicSubsystem} to be moves whent his commannd is executed. 
   * @param speedSupplier - the supplier for the speed to move the {@link BasicSubsystem} when this command is executed.
   * @param subsystemName - the name of the subsystem to be moved.
   * @param name - the name of this command, for the shuffleboard 
   * switch . The names are formatted as {@code subsystemName + "Commands - " + commandName}.
   * @param defaultValue - The switche's value at RobotInit().
   */
  public MoveBasicSubsystemWithSwitch(BasicSubsystem subsystem, 
    Supplier<Double> speedSupplier,
    String subsystemName,
    String name, 
    boolean defaultValue) {
    super(subsystem, speedSupplier);
    switchOn = SwitchHandler.addSwitch(name, defaultValue);
  }

  /**(Called repeatedly when this Command is scheduled to run) <p>
   * Moves the given {@link BasicSubsystem} according to the given 
   * speed suplier if the switch returns true.
   */
  @Override
  protected void execute() {

    if(switchOn.get())
    {
      super.execute();
    }

  }

  @Override
  protected boolean isFinished() {
    return super.isFinished() || switchOn.get();
  }
}