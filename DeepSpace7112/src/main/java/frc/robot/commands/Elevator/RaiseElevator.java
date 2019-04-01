/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.SubsystemComponents;
import frc.robot.SubsystemConstants;

public class RaiseElevator extends CommandGroup {
  /**
   * Add your docs here.
   */
  public RaiseElevator() {
    double target = SubsystemComponents.Elevator.getElevatorHeightByLaser() + 
      SubsystemConstants.Elevator.targetMove.raiseBy.get();
    SubsystemComponents.Gripper.PushPiston.set(Value.kReverse);
    addSequential(new MoveElevatorToTarget(
      SubsystemConstants.Elevator.SpeedModifiers.kTargetSpeedModifier,
      () -> target, 
      () -> MoveElevatorToTarget.lastStall));
  }
}
