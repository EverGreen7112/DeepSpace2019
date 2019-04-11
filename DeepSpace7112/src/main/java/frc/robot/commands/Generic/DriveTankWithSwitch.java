/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Generic;

import java.util.function.Supplier;

import com.spikes2212.genericsubsystems.drivetrains.TankDrivetrain;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTank;

import frc.robot.ConstantHandlerEG;

public class DriveTankWithSwitch extends DriveTank {
  Supplier<Boolean> switchOn;

  public DriveTankWithSwitch(TankDrivetrain drivetrain, Supplier<Double> leftSpeedSupplier,
    Supplier<Double> rightSpeedSupplier, String buttonName) {
    super(drivetrain, leftSpeedSupplier, rightSpeedSupplier);
    switchOn = ConstantHandlerEG.addConstantBool(String.format("Button Switches - {0}", buttonName), true);
  }

  public DriveTankWithSwitch(TankDrivetrain drivetrain, Supplier<Double> leftSpeedSupplier,
    Supplier<Double> rightSpeedSupplier, String buttonName, boolean defaultValue) {
    super(drivetrain, leftSpeedSupplier, rightSpeedSupplier);
    switchOn = ConstantHandlerEG.addConstantBool(String.format("Button Switches - {0}", buttonName), defaultValue);
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
