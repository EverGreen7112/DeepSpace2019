/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Climbing;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

import com.spikes2212.genericsubsystems.basicSubsystem.BasicSubsystem;
import com.spikes2212.genericsubsystems.basicSubsystem.commands.MoveBasicSubsystem;
import com.spikes2212.genericsubsystems.basicSubsystem.utils.limitationFunctions.TwoLimits;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.SubsystemComponents;
import frc.robot.SubsystemConstants;
import frc.robot.commands.Elevator.*;

public class Climb extends CommandGroup {
  /**
   * Add your docs here.
   */
  private Supplier<Double> speed;
  private BasicSubsystem subsystem;
  private Consumer<Double> speedConsumer;
  private Predicate<Double> canMove;

  public Climb() {
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm

    subsystem = new BasicSubsystem(SubsystemComponents.ClimbingMovement.Motor::set, new TwoLimits(SubsystemComponents.ClimbingShaft.bottomLimiter::get, SubsystemComponents.ClimbingShaft.topLimiter::get));

    //-----Sequence-----
    addParallel(new ElevatorMoveToTarget(SubsystemConstants.ClimbingMovement.kClimbingSpeed, SubsystemConstants.ClimbingMovement.kTargetHeight));
    addSequential(new MoveBasicSubsystem(subsystem, speed));
  }
}
