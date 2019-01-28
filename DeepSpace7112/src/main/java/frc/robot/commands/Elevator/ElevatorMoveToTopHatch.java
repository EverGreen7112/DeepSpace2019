/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Elevator;

import com.spikes2212.genericsubsystems.basicSubsystem.commands.MoveBasicSubsystemWithPID;
import com.spikes2212.utils.PIDSettings;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.SubsystemComponents;
import frc.robot.SubsystemConstants;

public class ElevatorMoveToTopHatch extends CommandGroup {
  /**
   * The command makes the elevator go to the top hatch using a PID loop with the encoder.
   * If the elevator passes by the hall effect sensor the encoder will reset.
   */
  public ElevatorMoveToTopHatch() {
    if(SubsystemComponents.Elevator.opticSwitch.get()){
      SubsystemComponents.Elevator.encoder.reset();
    }
    addSequential(new MoveBasicSubsystemWithPID(Robot.elevator, SubsystemComponents.Elevator.encoder, 
                  SubsystemConstants.Elevator.kRocketTopHatchHeight, 
                  new PIDSettings(SubsystemConstants.Elevator.kp.get(), 
                  SubsystemConstants.Elevator.ki.get(), 
                  SubsystemConstants.Elevator.kd.get(), 
                  SubsystemConstants.Elevator.kTolerance.get(), 
                  SubsystemConstants.Elevator.kWaitTime.get()), 2, true));
  }
}
