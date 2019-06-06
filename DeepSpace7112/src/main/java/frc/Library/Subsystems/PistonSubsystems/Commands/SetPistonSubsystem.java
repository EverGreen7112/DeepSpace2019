/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.Library.Subsystems.PistonSubsystems.Commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.Library.OI.Switches.Commands.CommandWithSwitch;
import frc.Library.Subsystems.PistonSubsystems.Classes.PistonSubsystem;

public class SetPistonSubsystem extends CommandWithSwitch {
  /**Supplier for the state to be set.
   * When this command executes, the */
  private Supplier<Value> stateSupplier;
  private PistonSubsystem pistons;
  private boolean continous;
  /**
   * 
   * @param pistons
   * @param stateSupplier
   * @param switchName
   */
  public SetPistonSubsystem(PistonSubsystem pistons, Supplier<Value> stateSupplier, String switchName) {
    super(switchName);
    requires(pistons);
    this.stateSupplier = stateSupplier;
    this.pistons = pistons;
  }
  
  public SetPistonSubsystem(PistonSubsystem pistons, Supplier<Value> stateSupplier, String switchName, boolean defaultValue) {
    super(switchName, defaultValue);
    requires(pistons);
    this.stateSupplier = stateSupplier;
    this.pistons = pistons;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(switchOn.get())
    {
      pistons.set(stateSupplier.get());
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return continous;
  }
}
