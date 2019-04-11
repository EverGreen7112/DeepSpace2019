/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Elevator;

import java.util.function.Supplier;

import frc.robot.ConstantHandlerEG;

/**This command moves uses the laser sensor of our robot  */
public class MoveElevatorToTargetWithSwitch extends MoveElevatorToTarget {
  Supplier<Boolean> switchOn;

  /**The constructor for this command, which sets up its movement and switch. 
   * @param speedModifier - the speed modifier for the elevator's movement to the target.
   * @param target - the height of the target that the elevator need to move to, relative to the ground.
   * @param stallModifier - the supplier for the percentnage of power required to give to the elevator 
   * in order to hold it in place after  it reached itstarget.
   * If you do not wish to hold the elevator after it moves, set this as 0.
   * @param buttonName - The name for the switch in the shuffleboard. It is formatted as "Button Switches - " + buttonName 
   */
  public MoveElevatorToTargetWithSwitch(Supplier<Double> speedModifier, Supplier<Double> target, Supplier<Double> stallModifier, String buttonName) {
    super(speedModifier, target, stallModifier);
    switchOn = ConstantHandlerEG.addConstantBool(String.format("Button Switches - ", buttonName), true);
  }
  
  
  /**The constructor for this command, which sets up its movement and switch. 
   * @param speedModifier - the speed modifier for the elevator's movement to the target.
   * @param target - the height of the target that the elevator need to move to, relative to the ground.
   * @param stallModifier - the supplier for the percentnage of power required to give to the elevator 
   * in order to hold it in place after  it reached itstarget.
   * If you do not wish to hold the elevator after it moves, set this as 0.
   * @param buttonName - The name for the switch in the shuffleboard. It is formatted as "Button Switches - " + buttonName 
   * @param defaultValue - The value the switch will recieve at RobotInit().
   */
  public MoveElevatorToTargetWithSwitch(Supplier<Double> speedModifier, Supplier<Double> target, Supplier<Double> stallModifier, String buttonName, boolean defaultValue) {
    super(speedModifier, target, stallModifier);
    switchOn = ConstantHandlerEG.addConstantBool(String.format("Button Switches - ", buttonName), defaultValue);
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
