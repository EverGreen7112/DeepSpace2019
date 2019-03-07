/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.PID.driveArcadeWithPID;
import frc.robot.commands.Gripper.GripperIn;
import frc.robot.commands.Cameras.SwitchToCameraA;
import frc.robot.commands.Cameras.SwitchToCameraB;
import frc.robot.commands.Climbing.Climb;
import frc.robot.commands.Elevator.ElevatorMoveToTarget;
import frc.robot.commands.Gripper.GripperOut;
import frc.robot.commands.Gripper.StopGripper;
import frc.robot.commands.GripperMovement.GripperMovementPistons;
import frc.robot.commands.GripperMovement.pushPistonF;
import frc.robot.commands.GripperMovement.pushPistonR;
import frc.robot.commands.GripperMovement.throwHatch;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

import com.spikes2212.genericsubsystems.basicSubsystem.commands.MoveBasicSubsystem;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  //--------------------Decleratins--------------------

  //----------Joysticks----------
  /**The joystick that controlls the left motors. */
  private Joystick drivingJSLeft;
  /**The Joystick that conntrols the right motors. */
  private Joystick drivingJSRight;
  /**The button joystick which is used to execute the various commands to the robot - Climbing, catching, releasing and controlling the PID. */
  private Joystick buttonJS;

  //----------Buttons----------
  /**The button that is used to catch objects with the gripper. The */
  private Button catchButton;
  private Button cycle;
  /**The button that is used to release objects cought with the gripper. When it's released, the gripper catches back an object not fully released.*/
  private Button releaseButton;
  /**The button to switch the StreamViewer on the shuffleboard to view the Camera from port 0.  */
  private Button switchToA;
  /**The button to switch the StreamViewer on the shuffleboard to view the Camera from port 1.  */
  private Button switchToB;
  /**The button to straighten the robot (make it fix deviation from a painted line.) */
  private Button straighten;
  /**The button to move the gripper to the bottom hatch of the rocket or to the hatch of the cargo ship. */
  private Button bottomHatch;
  /**The button to move the gripper to the middle hatch of the rocket. */
  private Button middleHatch;
  /**The button to move the gripper to the top hatch of the rocket. */
  private Button topHatch;
  /**The button to move the gripper to the bottom cargo of the rocket or to the cargo of the cargo ship. */
  private Button bottomCargo;
  /**The button to move the gripper to the middle cargo of the rocket. */
  private Button middleCargo;
  /**The button to move the gripper to the top hatch of the rocket. */
  private Button topCargo;
  /**The button to move forward the back wheels for the climbing movement<p>
   * -FOR TESTING-
   */
  private Button ClimbingMovementF;
  /**The button to move backwards the back wheels for the climbing movement<p>
   * -FOR TESTING-
   */
  private Button ClimbingMovementB;
  /**The button to make the robot climb the level 3 hab. It must only be pressed after the gripper is laid on the level. */
  private Button climb;
  /**The button to flip the gripper from "ball mode" to "hatch mode"*/
  private Button flipGripper;
  /**The button to move the climbing frame up */
  private Button ClimbingFrameU;
  /**The button to move the climbing frame down */
  private Button ClimbingFrameD;
  private Button throwHatch;
  private Button setPistonF;
  private Button setPistonR;

  /**The method to adjust the Driving Joysticks' value, turning the speed by value into a curve instead of a line - 
   * instead of each movement of the joystick increasing the speed equally, the furthest you move it the more
   * each movement increases the speed.
   * @param input - the joystick's axis value input to be adjusted
   * @return The adjusted input
    */
  private double adjustInput(double input){
    return input * Math.abs(input);
  }
  
  /**return the Y axis of the {@link #buttonJS Button Joytick}, used to move the elevator, adjusted to move more slowly to increase safety.    */
  public double getBTJoystick() {
    return buttonJS.getRawAxis(1) * 0.7;
  }

  /**@return the {@link #adjustInput(double) adjusted} current Y axis of the {@link #drivingJSLeft left driving Joystic}*/
  public double getLeftJoystick() {
    return -adjustInput(drivingJSLeft.getY()) * SubsystemConstants.chassis.kDrivingSpeedModifier.get() * 1.07;
  }

    
  /**@return the {@link #adjustInput(double) adjusted} current Y axis of the {@link #drivingJSRight d riving Joystic}*/
  public double getRightJoystick() {
    return adjustInput(drivingJSRight.getY()) * SubsystemConstants.chassis.kDrivingSpeedModifier.get();
  }

  //--------------------Initializations--------------------
    public OI() {
      
      //----------Joysticks----------
        drivingJSLeft = new Joystick(0);
        drivingJSRight = new Joystick(1);
        buttonJS = new Joystick(2);
      //----------Elevator Buttons----------
      catchButton = new JoystickButton(buttonJS, 1);
      releaseButton = new JoystickButton(buttonJS, 3);
      bottomHatch = new JoystickButton(drivingJSLeft, 12);
        middleHatch= new JoystickButton(drivingJSLeft, 10);
        topHatch = new JoystickButton(drivingJSLeft, 8);
        bottomCargo = new JoystickButton(drivingJSLeft, 11);
        middleCargo = new JoystickButton(drivingJSLeft, 9);
        topCargo = new JoystickButton(drivingJSLeft, 7);
      //----------Gripper Buttons----------
        throwHatch = new JoystickButton(buttonJS, 6);
      //----------Gripper Movement----------
        flipGripper = new JoystickButton(buttonJS, 5);
      //----------Climb----------
        climb = new JoystickButton(buttonJS, 6);
      //----------Camera Buttons---------
        switchToA = new JoystickButton(drivingJSRight, 5);
        switchToB = new JoystickButton(drivingJSRight, 6);
        straighten = new JoystickButton(buttonJS, 9);
      //----------Climbing Movement Testing---------
        ClimbingMovementB = new JoystickButton(buttonJS, 7);
        ClimbingMovementF = new JoystickButton(buttonJS, 8);
      //----------Climbing Frame Testing----------
        ClimbingFrameD = new JoystickButton(buttonJS, 2);
        ClimbingFrameU = new JoystickButton(buttonJS, 4);
        setPistonF = new JoystickButton(buttonJS, 10);
        setPistonR = new JoystickButton(buttonJS, 9);
      //----------Buttons' Binding----------
        bindButtons();
    }


  /**This is the method that makes the buttons cause an action when pressed, and must be ran in the consturctor. <p>
   * It consists of lines of button.whenPressed/whileheld(Command), where button is the button to be pressed, 
   * when pressed or while held is the method to determine the fashion in which it activates 
   * and ends the action and command is the action to be executed when the button is pressed or held.*/
  private void bindButtons() 
  {
    //----------Elevator to Hatch----------
      topHatch.whenPressed(new ElevatorMoveToTarget( SubsystemConstants.Elevator.kTargetSpeedModifier, SubsystemConstants.Elevator.kRocketTopHatchHeight));
      middleHatch.whenPressed(new ElevatorMoveToTarget(SubsystemConstants.Elevator.kTargetSpeedModifier, SubsystemConstants.Elevator.kRocketMiddleHatchHeight));
      bottomHatch.whenPressed(new ElevatorMoveToTarget( SubsystemConstants.Elevator.kTargetSpeedModifier,  SubsystemConstants.Elevator.kRocketBottomHatchHeight));
    //----------Elevator to Cargo----------
      topCargo.whenPressed(new ElevatorMoveToTarget(SubsystemConstants.Elevator.kTargetSpeedModifier, SubsystemConstants.Elevator.kRocketTopCargoHeight));
      middleCargo.whenPressed(new ElevatorMoveToTarget(SubsystemConstants.Elevator.kTargetSpeedModifier, SubsystemConstants.Elevator.kRocketMiddleCargoHeight));
      bottomCargo.whenPressed(new ElevatorMoveToTarget(SubsystemConstants.Elevator.kTargetSpeedModifier, SubsystemConstants.Elevator.kRocketBottomCargoHeight));
    //----------Gripper----------
      catchButton.whileHeld(new GripperIn());
      catchButton.whenReleased(new StopGripper()); //testing - GripperIn's end() did not work 
      releaseButton.whileHeld(new GripperOut());
      releaseButton.whenReleased(new StopGripper()); //testing - GripperOut's end() did not work
      throwHatch.whenPressed(new throwHatch());
    //----------Gripper Movement----------
      flipGripper.whenPressed(new GripperMovementPistons());
    //----------Cameras----------
      switchToA.whenPressed(new SwitchToCameraA()); //Commented since RobotB does not have cameras
      switchToB.whenPressed(new SwitchToCameraB()); //Commented since RobotB does not have cameras
    //----------PID----------
      // straighten.whenPressed(new driveArcadeWithPID()); //Commented since RobotB does not have cameras.
    //--------------------Testing--------------------
      //----------Climbing---------
      //----------Climbing Movement----------
        // ClimbingMovementB.whileHeld(new MoveBasicSubsystem(Robot.climbingMovement, SubsystemConstants.ClimbingMovement.kClimbingSpeed));
        // ClimbingMovementF.whileHeld(new MoveBasicSubsystem(Robot.climbingMovement, SubsystemConstants.ClimbingMovement.kClimbingSpeedForward));
      //----------Climbing Frame----------
        // ClimbingFrameD.whileHeld(new MoveBasicSubsystem(Robot.frame, SubsystemConstants.ClimbingFrame.kFrameMotorSpeedModifier));
        // ClimbingFrameU.whileHeld(new MoveBasicSubsystem(Robot.frame, SubsystemConstants.ClimbingFrame.kFrameMotorSpeedModifierUp));
      //---------Gripper Movement---------  
        setPistonF.whenPressed(new pushPistonF());
        setPistonR.whenPressed(new pushPistonR());
  }
}
