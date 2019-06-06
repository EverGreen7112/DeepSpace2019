/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.Library.Subsystems.DriveTrain.Commands;

import java.util.function.Supplier;

import com.spikes2212.genericsubsystems.drivetrains.TankDrivetrain;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTank;

import frc.Library.OI.Switches.Classes.Switch;
import frc.Library.OI.Switches.Classes.SwitchHandler;

public class DriveTankWithSwitch extends DriveTank {
  Switch switchOn;

  public DriveTankWithSwitch(TankDrivetrain drivetrain, Supplier<Double> leftSpeedSupplier,
    Supplier<Double> rightSpeedSupplier, String switchName) {
    super(drivetrain, leftSpeedSupplier, rightSpeedSupplier);
    switchOn = SwitchHandler.addSwitch(switchName);
  }

  public DriveTankWithSwitch(TankDrivetrain drivetrain, Supplier<Double> leftSpeedSupplier,
    Supplier<Double> rightSpeedSupplier, String switchName, boolean defaultValue) {
    super(drivetrain, leftSpeedSupplier, rightSpeedSupplier);
    switchOn = SwitchHandler.addSwitch(switchName, defaultValue);
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
