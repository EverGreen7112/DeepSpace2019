/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.GripperMovement;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.Library.Subsystems.PistonSubsystems.Commands.SetPistonSubsystem;
import frc.Library.Subsystems.PistonSubsystems.Commands.TogglePistonSubsystem;
import frc.robot.Robot;
import frc.robot.SubsystemComponents;

public class FlipGripper extends CommandGroup {
  /**@return
   * <b>true</b> if the gripper is folded (with the wheels pointing upwards).
   * <li> <b>false</b> if the gripper is not folded (with the wheels pointing forward). </li> */
  public static Supplier<Boolean> gripperFolded = Robot.gripperMovement::isForward;
  /**@return 
   * <b>Forward</b> if the gripper is folded - the hatch system needs to be closed when the gripper opens, 
   * so it does not hit the floor.
   * <li><b>Curent State</b> of the pistons if the gripper isn't folded, as there is no need to change their 
   * cureent state.</li>
  */
  private static Supplier<Value> hatchPistonsValueToSet = () -> 
  { 
    if(gripperFolded.get()) //If the gripper is unfolded
    {
      return Value.kForward; //If it is folded, return Forward value (close the hatch system).
    }
    
    return Robot.hatch.stateSupplier.get(); //Return the hatch system's current state (do nothing)

  };
  
  /**
   * Flips the gripper:
   * <ul>
   * <li>If the gripper is folded, closes the {@link SubsystemComponents.Gripper#PushPiston hatch system},
   * so it does not get hit when the gripper opens.</li>
   * <li>Flips the gripper by toggling its {@link SubsystemComponents.GripperMovement#MovementPiston movement pistons}</li>
   * </ul>*/
  public FlipGripper() {
    addSequential(new SetPistonSubsystem(Robot.hatch, hatchPistonsValueToSet , "Gripper Movement"));
    addSequential(new TogglePistonSubsystem(Robot.gripperMovement, "Gripper - Command Switches - Fold Gripper - Toggle Gripper Movement Pistons"));
  }
}
