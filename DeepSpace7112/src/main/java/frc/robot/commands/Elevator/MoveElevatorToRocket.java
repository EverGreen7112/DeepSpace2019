/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Elevator;
import java.util.function.Supplier;

import frc.Library.OI.Switches.Classes.Switch;
import frc.Library.OI.Switches.Classes.SwitchHandler;
import frc.Library.Subsystems.BasicSubsystems.Commands.MoveBasicSubsystemToTarget;
import frc.robot.Robot;
import frc.robot.SubsystemConstants;
import frc.robot.SubsystemMethods;
import frc.robot.SubsystemConstants.ElevatorConsts.RocketHeights;
import frc.robot.SubsystemConstants.ElevatorConsts.RocketStall;

/**
 * A {@link MoveBasicSubsystemToTarget MoveBasicSubsystemToTarget command} 
 * that moves the the elevator to a floor of the rocket by input {@link Objective} - a level on the rocket.
 * It also has a static switch, to be turned off if the sensors don't function or any other malfunction.
 */
public class MoveElevatorToRocket extends MoveBasicSubsystemToTarget implements SubsystemMethods
{
  /**Whether or not the sensors seem to function. This works as sort of a static switch for the commands -
   * If false, this command will end if it's executing, and will not execute aything when called 
   * (ending after one iteration of nothing). It should also be present on the shuffleboard such that 
   * the drivers can see if the automatic rise will not work. */
  public static Switch sensorsFunction = SwitchHandler.addSwitch("Elevator Commands - Move to Rocket - Sensors are functioning");
  
  public MoveElevatorToRocket(Objective objective)
  {
    super(
      Robot.elevator, 
      ElevatorMethods::getElevatorHeight, 
      SubsystemConstants.ElevatorConsts.SpeedModifiers.kTargetSpeedModifier, 
      objective.height, 
      objective.stallModifier,
      "Elevator",
      objective.targetName);
  }
  
  public enum Objective
  {
    kBottomHatch(RocketHeights.kBottomHatch, RocketStall.kBottomHatch, "Bottom Hatch"),
    kMiddleHatch(RocketHeights.kMiddleHatch, RocketStall.kMiddleHatch, "Middle Hatch"),
    kTopHatch(RocketHeights.kTopHatch, RocketStall.kTopHatch, "Top Hatch"),
    kBottomCargo(RocketHeights.kBottomCargo, RocketStall.kBottomCargo, "Bottom Cargo"),
    kMiddleCargo(RocketHeights.kMiddleCargo, RocketStall.kMiddleCargo, "Middle Cargo"),
    kTopCargo(RocketHeights.kTopCargo, RocketStall.kTopCargo, "Top Cargo");

    public Supplier<Double> height;
    public Supplier<Double> stallModifier;
    public String targetName;

    private Objective(Supplier<Double> height,Supplier<Double> stallModifier, String targetName)
    {
      this.height = height;
      this.stallModifier = stallModifier;
      this.targetName = targetName;
    }
  }

  @Override
  protected void execute() {
    if(sensorsFunction.get())
    {
      if(switchOn.get())
      {      
        Robot.hatch.setReverse(); //Make sure the hatch is closed, so it does not get hit while the elevator moves.
      }

      super.execute();
    }
  }

  @Override
  protected boolean isFinished() {
    return super.isFinished() || !sensorsFunction.get(); 
  }
}