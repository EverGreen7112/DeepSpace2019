/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.Library.Subsystems.BasicSubsystems.Commands;

import java.util.function.Supplier;

import com.spikes2212.genericsubsystems.basicSubsystem.BasicSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import frc.Library.OI.Switches.Commands.CommandWithSwitch;
import frc.robot.Robot;

/**
 * A {@link Command} that moves the the subsystem to a target, at a set speed.
 * If the subsystem is above the target, it will move down.
 * If the subsystem is under the target, it will move up.
 */
public class MoveBasicSubsystemToTarget extends CommandWithSwitch {

  /**The subsystem to be moved to target. */
  private BasicSubsystem subsystem;
  /**The speed modifier of the subsystem as it moves to target.*/
  private Supplier<Double> speedModifier;
  /**Supplier of the distance of the subsystem from a certain point a.*/
  private Supplier<Double> distanceSupplier;
  /**Supplier of the target's ditance from the same point a the distance supplier mesures the 
   * subsystem's distance.*/ 
  public Supplier<Double> target;
  /**Whether or not the basic subssystem's distance from the was positive or negative when the command 
   * initilized.*/
  private boolean startedPositiveDistance;
 /**In case of malfunction, turning this boolean true will automatically exit the command.<p>
  * No usage of this is currently implemented within this class, but programmers can use buttons or autonomus
  * checks to trigger it if a malfunction appears. */
  //TODO automatically turn true if takes too long.
  /**A counter fo debugging, to distinguish between variables of different objects of this command. */
  private static int counter = 0;
  /**A supplier for the percentage of power required to give to the subsysystem's motors in order to 
   * get it to stay in place after it reached its target  */
  private Supplier<Double> stallModifier;
  /**The shuffleboard switch for this command - it will autoatically quit if it is turned off while theexecuting,
   * and will not execute at all if it is off.
   */

  /**The constructor for this class, which sets its speed and target.
   * */
  
   /**
    * 
    * @param subsystem - The subsystem to be moved to target.
    * @param distanceSupplier - Supplier of the distance of the subsystem from a certain point.
    * @param speedModifier - The speed modifier of the subsystem as it moves to target.
    * @param target -Supplier of the target's ditance from the same point the distanceSupplier mesures from.
    * @param stallModifier - the supplier of the stall neccesiry to hold the subsystem in place of the target.
    * If you do not wish to hold th subsystem after it moves, set it as 0.
    * @param subsystemName - The name of the subsystem to be moved to target, to be used for this command's switch
    * @param targetName - The name of the target to move the subsystem to, to be used for this command's switch../
    */
   public MoveBasicSubsystemToTarget(
    BasicSubsystem subsystem,
    Supplier<Double> distanceSupplier,
    Supplier<Double> speedModifier, 
    Supplier<Double> target, 
    Supplier<Double> stallModifier,
    String subsystemName,
    String targetName) {
    super(String.format("{0} - Command Switches - Move to {1}", subsystemName, targetName));
    requires(subsystem);
    this.subsystem = subsystem;
    this.distanceSupplier = distanceSupplier;
    this.speedModifier = speedModifier;
    this.target = target;
    this.stallModifier = stallModifier;
  }

  /**When the comand starts: determine if the subsystem is above or below the target, and set up the stall..*/
  //At the start of the command:
  @Override
  protected void initialize() {
    Robot.dbc.addNumber("Target #" + counter, () -> target.get());
    if(target.get() - distanceSupplier.get() > 0) //If the subsystem is below the target:
    {
      startedPositiveDistance = false; //Turn the apropriate boolean false.
    }
    else //If the subsystem is above the target or is on the target
    {
       startedPositiveDistance = true; //Turn the apropriate boolean true
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(switchOn.get())
    {
      // System.out.println(String.format("subsystem to Target: \nSpeed: " +speedModifier.get() + "\nTarget: " + target.get() +"\nPosition: " + distanceSupplier.get()));
      if(target.get() - distanceSupplier.get() > 0) //If the subsystem is below the target:
      {
        System.out.println("Moving subsystem Up: " + speedModifier.get());
        subsystem.move(speedModifier.get()); //Move the subsystem upwards with given speed.
      }

      else //If the subsystem is above the target:
      {
        System.out.println("Moving subsystem Down: " + -speedModifier.get());
        subsystem.move(-speedModifier.get()); //Move the subsystem downwards.
      }
    }
  }

  /**Finishe the command once the subsystem has passed the target, or if the command's switch is turned off.*/
  @Override
  protected boolean isFinished() { 
    return //Return true:
      (startedPositiveDistance && distanceSupplier.get() <= target.get()) //IF it passed the target on the way down
      || (!startedPositiveDistance && distanceSupplier.get() >= target.get()) //OR if it passed the target on the way up
      || super.isFinished(); //OR if the switch was turned off. 

  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    subsystem.move(stallModifier.get());
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() { 
  }
}
