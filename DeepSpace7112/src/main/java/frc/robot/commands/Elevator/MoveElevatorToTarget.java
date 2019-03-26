/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Elevator;

import java.util.function.Supplier;

import com.spikes2212.genericsubsystems.basicSubsystem.BasicSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.SubsystemComponents;
import frc.robot.SubsystemConstants;

/**
 * A {@link Command} that moves the the elevator to a target, at a set speed.
 * If the elevator is above the target, it will move down.
 * If the elevator is under the target, it will move up.
 */
public class MoveElevatorToTarget extends Command {

  /**The speed modifier of the elevator as it moves to target.*/
  private Supplier<Double> speedModifier;
  /**The height of the target relative  to the ground.*/ 
  public Supplier<Double> target;
  /**Whether or not the elevator was above the target when the command initilized.*/
  private boolean startedAboveTarget;
  /**Whether it not the encoder should be reset yet. */
  private boolean resetFlag;
  /**If the command malfunctions, the driver will be able to press a button to turn this true and finish the command. */
  public static boolean defaultToggled;
  public static int counter = 0;
  public static Supplier<Double> stallSpeed;

  /**The constructor for this class, which sets its speed and target.
   * @param speedModifier - the speed modifier for the elevator's movement to the target.
   * @param target - the height of the target that the elevator need to move to, relative to the ground.*/
  public MoveElevatorToTarget(Supplier<Double> speedModifier, Supplier<Double> target, Supplier<Double> stallSpeed) {
    requires(Robot.elevator);
    this.speedModifier = speedModifier;
    this.target = target;
    this.stallSpeed = stallSpeed;
  }

  /**When the comand starts: determine if the elevator is above or below the target.*/
  //At the start of the command:
  @Override
  protected void initialize() {
    Robot.dbc.addNumber("Target #" + counter, () -> target.get());
    if(target.get() - SubsystemComponents.Elevator.getElevatorHeight() > 0) //If the elevator is below the target:
    {
      startedAboveTarget = false; //Turn the apropriate boolean false.
    }
    
    else //If the elevator is above the target or is on the target
    {
       startedAboveTarget = true; //Turn the apropriate boolean true
    }
    Robot.dbc.addBoolean("flag #"+counter++, () -> startedAboveTarget);
    resetFlag = true; //Since this is the start of the command, reset flag is i
    defaultToggled = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // System.out.println(String.format("Elevator to Target: \nSpeed: " +speedModifier.get() + "\nTarget: " + target.get() +"\nPosition: " + SubsystemComponents.Elevator.getElevatorHeight()));
    if(target.get() - SubsystemComponents.Elevator.getElevatorHeight() > 0) //If the elevator is below the target:
    {
      System.out.println("Moving Elevator Up: "+ speedModifier.get());
      Robot.elevator.move(speedModifier.get()); //Move the elevator upwards.
    }
    
    else //If the elevator is above the target:
    {
      System.out.println("Moving Elevator Down: " + -speedModifier.get());
      Robot.elevator.move(-speedModifier.get()); //Move the elevator downwards.
      // if(SubsystemComponents.Elevator.opticSwitch.get() && resetFlag) //If the optic switch is pressed and it still need to be reset:
      // {
      //   SubsystemComponents.Elevator.encoder.reset(); //reset the encoder
      //   resetFlag = false; //Turn resetFlag false.
      // }
    }
  }

  /**If the elevator has passed the target, stop the command. */
  @Override
  protected boolean isFinished() {
    if(startedAboveTarget)
      return SubsystemComponents.Elevator.getElevatorHeight() <= target.get() || defaultToggled; // 
    else
      return SubsystemComponents.Elevator.getElevatorHeight() >= target.get() || defaultToggled;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.elevator.move(stallSpeed.get());
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
