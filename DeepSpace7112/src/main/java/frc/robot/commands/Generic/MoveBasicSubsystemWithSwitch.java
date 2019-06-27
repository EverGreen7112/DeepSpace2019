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

public class MoveBasicSubsystemWithSwitch extends MoveBasicSubsystem {
  public Supplier<Boolean> switchOn;

  public MoveBasicSubsystemWithSwitch(BasicSubsystem subsystem, Supplier<Double> speedSupplier,
  Supplier<Boolean> switchOn) {
    super(subsystem, speedSupplier);
    this.switchOn = switchOn;
  }

  public MoveBasicSubsystemWithSwitch(BasicSubsystem subsystem, double speed,
  Supplier<Boolean> switchOn) {
    super(subsystem, speed);
    this.switchOn = switchOn;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    if(switchOn.get())
    {
      super.execute();
    }

  }
}
