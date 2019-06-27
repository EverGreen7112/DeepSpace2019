/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Elevator;

import java.util.function.Supplier;

import com.spikes2212.genericsubsystems.basicSubsystem.BasicSubsystem;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.SubsystemComponents;
import frc.robot.SubsystemConstants;
import frc.robot.commands.GripperMovement.FoldGripper;

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
  public Supplier<Double> stallModifier;
  public static double lastStall;

  /**The constructor for this class, which sets its speed and target.
   * @param speedModifier - the speed modifier for the elevator's movement to the target.
   * @param target - the height of the target that the elevator need to move to, relative to the ground.
   * @param stallModifier - the supplier of the stall neccesiry to hold th elevator in  place of the target.
   * If you do not wish to hold th elevator after it moves, set it as 0.*/
  public MoveElevatorToTarget(Supplier<Double> speedModifier, Supplier<Double> target, Supplier<Double> stallModifier) {

    requires(Robot.elevator);
    this.speedModifier = speedModifier;
    this.target = () -> {
      if(FoldGripper.gripperFolded.get()) //If the gripper is folded:
      {
        return target.get() - SubsystemConstants.gripper.kCargoGripperLaserDiffrence.get(); 
        //Subtruct from the target the difference between the laser and ther gripper when folded
      }

      else //If the gripper is open
      {
        return target.get() - SubsystemConstants.gripper.kHatchGripperLaserDiffrence.get();
        //Subtruct from the target the difference between the laser and ther gripper when folded        
      }

      //These subtruction are required in order to move the gripper to the desired
      //target, instead of the laser, as the the laser is whatgives the height and so *it*
      //is the thng being moves to target.
    };

    this.stallModifier = stallModifier;
    lastStall = stallModifier.get();
  }

  /**When the comand starts: determine if the elevator is above or below the target, and set up the stall..*/
  //At the start of the command:
  @Override
  protected void initialize() {
    Robot.dbc.addNumber("Target #" + counter, () -> target.get());
    if(target.get() - SubsystemComponents.Elevator.getElevatorHeightByLaser() > 0) //If the elevator is below the target:
    {
      startedAboveTarget = false; //Turn the apropriate boolean false.
    }
    else //If the elevator is above the target or is on the target
    {
       startedAboveTarget = true; //Turn the apropriate boolean true
    }
    // Robot.dbc.addBoolean("flag #"+counter, () -> startedAboveTarget);
    // Robot.dbc.addNumber("Stall #" + counter++, () -> stallModifier.get());
    resetFlag = true; //Since this is the start of the command, reset flag is i
    defaultToggled = false;
    ElevatorDefault.lockedSpeed = stallModifier.get(); //Set the stall speed by the stall suplier
    ElevatorDefault.speedLock = true; //Toggle the stalling (Wwhich will be acrtive in ElevatorDefault
    //, once this command ends,) true.
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // System.out.println(String.format("Elevator to Target: \nSpeed: " +speedModifier.get() + "\nTarget: " + target.get() +"\nPosition: " + SubsystemComponents.Elevator.getElevatorHeightByLaser()));
    if(target.get() - SubsystemComponents.Elevator.getElevatorHeightByLaser() > 0) //If the elevator is below the target:
    {
      System.out.println("Moving Elevator Up: " + speedModifier.get());
      SubsystemComponents.Gripper.PushPiston.set(Value.kReverse); //And make sure the hatch is closed.
      Robot.elevator.move(speedModifier.get()); //Move the elevator upwards with given speed.
    }
    else //If the elevator is above the target:
    {
      System.out.println("Moving Elevator Down: " + -speedModifier.get());
      SubsystemComponents.Gripper.PushPiston.set(Value.kReverse); //Make sure the hatch is closed
      Robot.elevator.move(-speedModifier.get()); //Move the elevator downwards.
      // if(SubsystemComponents.Elevator.opticSwitch.get() && resetFlag) //If the optic switch is pressed and it still need to be reset:
      // {
      //   SubsystemComponents.Elevator.encoder.reset(); //reset the encoder
      //   resetFlag = false; //Turn resetFlag false.
      // }
    }
  }

  /**Finishe the command once the elevator has passed the target, or the emergency escape is toggled.. */
  @Override
  protected boolean isFinished() { 
    if(startedAboveTarget) //If the elevator started above the target
      return SubsystemComponents.Elevator.getElevatorHeightByLaser() <= target.get() || defaultToggled;
      //Return true once the elevator is below it, or the emergency escape it toggled.
    else //if the elevator started below the target
      return SubsystemComponents.Elevator.getElevatorHeightByLaser() >= target.get() || defaultToggled;
      //Return true once the elevator is above it, or the emergency escape it toggled.
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.dbc.addBoolean("end is working", () -> true);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    
  }
}
