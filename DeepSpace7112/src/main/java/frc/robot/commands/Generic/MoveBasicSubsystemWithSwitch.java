/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Generic;

import java.util.function.Supplier;

import com.spikes2212.genericsubsystems.basicSubsystem.BasicSubsystem;
import com.spikes2212.genericsubsystems.basicSubsystem.commands.MoveBasicSubsystem;

import frc.robot.ConstantHandlerEG;

/**This command creates a switch in the shuffleboard upon construction, 
 * and moves a given {@link BasicSubsystem} if the switch is on.
 * This is done to more easily disable unused or malfunctioning commands. */
public class MoveBasicSubsystemWithSwitch extends MoveBasicSubsystem {
  public Supplier<Boolean> switchOn;


  /**
   * Adds a switch to the shuffleboard to enable or disable this command, and set the objeccts for this command.
   * @param subsystem - the {@link BasicSubsystem} to be moves whent his commannd is executed. 
   * @param speedSupplier - the supplier for the speed to move the {@link BasicSubsystem} when this command is executed.
   * @param buttonName - The name for the shuffleboard switch for this command. The names are formatted as "Button Switches - " + buttonName.
   */
  public MoveBasicSubsystemWithSwitch(BasicSubsystem subsystem, Supplier<Double> speedSupplier, String buttonName) {
    super(subsystem, speedSupplier);
    switchOn = ConstantHandlerEG.addConstantBool(String.format("Button Switches  - {0}", buttonName), true);
  }

  /**
   * Adds a switch to the shuffleboard to enable or disable this command, and set the objeccts for this command.
   * @param subsystem - the {@link BasicSubsystem} to be moves whent his commannd is executed. 
   * @param speedSupplier - the supplier for the speed to move the {@link BasicSubsystem} when this command is executed.
   * @param buttonName - The name for the shuffleboard switch for this command. The names are formatted as "Button Switches - " + buttonName.
   * @param defaultValue - The switche's value at RobotInit().
   */
  public MoveBasicSubsystemWithSwitch(BasicSubsystem subsystem, Supplier<Double> speedSupplier, String buttonName, boolean defaultValue) {
    super(subsystem, speedSupplier);
    switchOn = ConstantHandlerEG.addConstantBool(String.format("Button Switches  - {0}", buttonName), defaultValue);
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
}
