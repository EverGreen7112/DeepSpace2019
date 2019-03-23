/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.PID.driveArcadeWithPID;
import frc.robot.commands.Gripper.GripperIn;
import frc.robot.PortMaps.ButtonMap;
import frc.robot.PortMaps.JoystickMap;
import frc.robot.commands.Chassis.ToggleDefense;
import frc.robot.commands.Chassis.ToggleSmartP;
import frc.robot.commands.Cameras.SwitchToCameraA;
import frc.robot.commands.Cameras.SwitchToCameraB;
import frc.robot.commands.Climbing.Climb;
import frc.robot.commands.Elevator.MoveElevatorToTarget;
import frc.robot.commands.Elevator.ToggleSpeedLock;
import frc.robot.commands.Gripper.GripperOut;
import frc.robot.commands.Gripper.StopGripper;
import frc.robot.commands.Gripper.TogglePushPistons;
import frc.robot.commands.GripperMovement.FoldGripper;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

import java.util.function.Supplier;

import com.spikes2212.genericsubsystems.basicSubsystem.commands.MoveBasicSubsystem;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTank;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  //--------------------Decleratins--------------------

  //----------Joysticks----------
  /**The joystick that controlls the left motors' wheels. */
  private Joystick drivingJSLeft;
  /**The Joystick that conntrols the right motors' wheels. */
  private Joystick drivingJSRight;
  /**The button joystick which is used to execute the various commands to the robot - Climbing, catching, releasing and controlling the PID. */
  public Joystick buttonJS;

  //----------Buttons----------
  /**The button that is used to catch objects with the gripper. The */
  public Button catchButton;
  public Button cycle;
  /**The button that is used to release objects cought with the gripper. When it's released, the gripper catches back an object not fully released.*/
  public Button releaseButton;
  /**The button to switch the StreamViewer on the shuffleboard to view the Camera from port 0.  */
  public Button switchToA;
  /**The button to switch the StreamViewer on the shuffleboard to view the Camera from port 1.  */
  public Button switchToB;
  /**The button to straighten the robot (make it fix deviation from a painted line.) */
  public Button straighten;
  /**The button to move the gripper to the bottom hatch of the rocket or to the hatch of the cargo ship. */
  public Button bottomHatch;
  /**The button to move the gripper to the middle hatch of the rocket. */
  public Button middleHatch;
  /**The button to move the gripper to the top hatch of the rocket. */
  public Button topHatch;
  /**The button to move the gripper to the bottom cargo of the rocket or to the cargo of the cargo ship. */
  public Button bottomCargo;
  /**The button to move the gripper to the middle cargo of the rocket. */
  public Button middleCargo;
  /**The button to move the gripper to the top hatch of the rocket. */
  public Button topCargo;
  public Button lockSpeed;
  /**The button to move forward the back wheels for the climbing movement<p>
   * -FOR TESTING-
   */
  public Button ClimbingMovementF;
  /**The button to move backwards the back wheels for the climbing movement<p>
   * -FOR TESTING-
   */
  public Button ClimbingMovementB;
  /**The button to make the robot climb the level 3 hab. It must only be pressed after the gripper is laid on the level. */
  public Button climb;
  /**The button to flip the gripper from "ball mode" to "hatch mode"*/
  public Button flipGripper;
  /**The button to move the climbing frame up */
  public Button ClimbingFrameU;
  /**The button to move the climbing frame down */
  public Button ClimbingFrameD;
  public Button togglePushPistons;
  public Button setPistonF;
  public Button setPistonR;
  public Button toggleDefense;
  public Button slowAdjust;
  public Button fastAdjust;


  /**The method to adjust the Driving Joysticks' value, turning the speed by value into a curve instead of a line - 
   * instead of each movement of the joystick increasing the speed equally, the furthest you move it the more
   * each movement increases the speed.
   * @param input - the joystick's axis value input to be adjusted
   * @return The adjusted input
    */
  private double adjustInput(double input) {
    return input * Math.abs(input);
  }
  
  /**return the Y axis of the {@link #buttonJS Button Joytick's} left joystick, used to move the elevator, adjusted to move more slowly to increase safety.    */
  public double getBTJoystickLeft() {
    return -buttonJS.getRawAxis(1) * 0.55;
  }

  /**return the Y axis of the {@link #buttonJS Button Joystick's} right joystick, used to move the elevator at faster speeds. */
  public double getBTJoystickRight() {
    return buttonJS.getRawAxis(3) * 0.9;
  }

  /**@return the {@link #adjustInput(double) adjusted} current Y axis of the {@link #drivingJSLeft left driving Joystic}*/
  public double getLeftJoystick() {
    return -adjustInput(drivingJSLeft.getY()) * 1.07; //Left deviation
  }

    
  /**@return the {@link #adjustInput(double) adjusted} current Y axis of the {@link #drivingJSRight d riving Joystic}*/
  public double getRightJoystick() {
    return adjustInput(drivingJSRight.getY());
  }

  //--------------------Initializations--------------------
    public OI() {
      
      //----------Joysticks----------
        drivingJSLeft = new Joystick(0);
        drivingJSRight = new Joystick(1);
        buttonJS = new Joystick(2);
      //----------Chassis----------
        toggleDefense = new JoystickButton(drivingJSLeft, ButtonMap.Chassis.toggleDefenseButton.get());
        slowAdjust = new JoystickButton(drivingJSRight, ButtonMap.Chassis.slowAdjustButton.get());
        fastAdjust = new JoystickButton(drivingJSRight, ButtonMap.Chassis.fastAdjustButton.get());
      //----------Elevator Buttons----------
        lockSpeed = new JoystickButton(buttonJS, ButtonMap.Elevator.setStall.get());
        //-----Move To Hatch-----
          bottomHatch = new JoystickButton(drivingJSLeft, JoystickMap.DrivingJoystick.bottomRightBack);
          middleHatch= new JoystickButton(drivingJSLeft, JoystickMap.DrivingJoystick.bottomRightMiddle);
          topHatch = new JoystickButton(drivingJSLeft, JoystickMap.DrivingJoystick.bottomRightFront);
        //-----Move To Cargo-----
          bottomCargo = new JoystickButton(drivingJSLeft, );
          middleCargo = new JoystickButton(drivingJSLeft, JoystickMap.DrivingJoystick.bottomLeftMiddle);
          topCargo = new JoystickButton(drivingJSLeft, JoystickMap.DrivingJoystick.bottomLeftFront);
        //Commented since stall would be too difficult with the autonomus rise.
      //----------Gripper Buttons----------
        catchButton = new JoystickButton(buttonJS, ButtonMap.gripper.catchPort.get());
        releaseButton = new JoystickButton(buttonJS, ButtonMap.gripper.releasePort.get());
        togglePushPistons = new JoystickButton(buttonJS, ButtonMap.gripper.togglePushPistons.get());
        setPistonF = new JoystickButton(buttonJS, ButtonMap.gripper.setPistonForward.get());
        setPistonR = new JoystickButton(buttonJS, ButtonMap.gripper.setPistonReverse.get());
      //----------Gripper Movement----------
        flipGripper = new JoystickButton(buttonJS, ButtonMap.GripperMovement.flipGripper.get());
      //----------Climb----------
        // climb = new JoystickButton(buttonJS, 6); //commented due to lack of climbing
      //----------Camera Buttons----------
        // switchToA = new JoystickButton(drivingJSRight, ButtonMap.Cameras.switchToA.get());
        // switchToB = new JoystickButton(drivingJSRight, ButtonMap.Cameras.switchToB.get());
      //----------PID----------
        straighten = new JoystickButton(drivingJSLeft, ButtonMap.PID.straighten.get()); //needs testing.

      /*//----------Climbing Movement Testing---------
        ClimbingMovementB = new JoystickButton(buttonJS, 7);
        ClimbingMovementF = new JoystickButton(buttonJS, 8);
      //----------Climbing Frame Testing----------
        ClimbingFrameD = new JoystickButton(buttonJS, 2);
        ClimbingFrameU = new JoystickButton(buttonJS, 4);*/ //commented due to lack of climbing
      //----------Buttons' Binding----------
        bindButtons();
    }


  /**This is the method that makes the buttons cause an action when pressed, and must be ran in the consturctor. <p>
   * It consists of lines of button.whenPressed/whileheld(Command), where button is the button to be pressed, 
   * when pressed or while held is the method to determine the fashion in which it activates 
   * and ends the action and command is the action to be executed when the button is pressed or held.*/
  private void bindButtons() 
  {
    // ----------Chassis----------
    
    toggleDefense.whenPressed(new ToggleDefense());
    slowAdjust.whileHeld(new DriveTank(Robot.drivetrain, 
     () -> Robot.oi.getLeftJoystick() * SubsystemConstants.Chassis.kSlowSpeedModifier.get(),
     () -> Robot.oi.getRightJoystick() * SubsystemConstants.Chassis.kSlowSpeedModifier.get()));
    fastAdjust.whileHeld(new DriveTank(Robot.drivetrain,
     () -> Robot.oi.getLeftJoystick() * SubsystemConstants.Chassis.kFastSpeedModifier.get(),
     () -> Robot.oi.getRightJoystick() * SubsystemConstants.Chassis.kFastSpeedModifier.get()));
    // fastAdjust.whileHeld(new MoveBasicSubsystem(Robot.drivetrain, () -> 2));
    //----------Elevator----------
      lockSpeed.whenPressed(new ToggleSpeedLock());
    //----------Elevator to Hatch----------
    //  topHatch.whenPressed(new MoveElevatorToTarget(SubsystemConstants.Elevator.kTargetSpeedModifier, SubsystemConstants.Elevator.kRocketTopHatchHeight));
    //  middleHatch.whenPressed(new MoveElevatorToTarget(SubsystemConstants.Elevator.kTargetSpeedModifier, SubsystemConstants.Elevator.kRocketMiddleHatchHeight));
    //  bottomHatch.whenPressed(new MoveElevatorToTarget( SubsystemConstants.Elevator.kTargetSpeedModifier,  SubsystemConstants.Elevator.kRocketBottomHatchHeight));
      topHatch.whenPressed(new MoveElevatorToTarget(SubsystemConstants.Elevator.kTargetSpeedModifier, SubsystemConstants.Elevator.kRocketTopHatchVoltage));
      middleHatch.whenPressed(new MoveElevatorToTarget(SubsystemConstants.Elevator.kTargetSpeedModifier, SubsystemConstants.Elevator.kRocketMiddleHatchVoltage));
      bottomHatch.whenPressed(new MoveElevatorToTarget( SubsystemConstants.Elevator.kTargetSpeedModifier,  SubsystemConstants.Elevator.kRocketBottomHatchVoltage));
    // // //----------Elevator to Cargo----------
    //   topCargo.whenPressed(new MoveElevatorToTarget(SubsystemConstants.Elevator.kTargetSpeedModifier, SubsystemConstants.Elevator.kRocketTopCargoHeight));
    //   middleCargo.whenPressed(new MoveElevatorToTarget(SubsystemConstants.Elevator.kTargetSpeedModifier, SubsystemConstants.Elevator.kRocketMiddleCargoHeight));
    //   bottomCargo.whenPressed(new MoveElevatorToTarget(SubsystemConstants.Elevator.kTargetSpeedModifier, SubsystemConstants.Elevator.kRocketBottomCargoHeight));
    topCargo.whenPressed(new MoveElevatorToTarget(SubsystemConstants.Elevator.kTargetSpeedModifier, SubsystemConstants.Elevator.kRocketTopCargoVoltage));
    middleCargo.whenPressed(new MoveElevatorToTarget(SubsystemConstants.Elevator.kTargetSpeedModifier, SubsystemConstants.Elevator.kRocketMiddleCargoVoltage));
    bottomCargo.whenPressed(new MoveElevatorToTarget(SubsystemConstants.Elevator.kTargetSpeedModifier, SubsystemConstants.Elevator.kRocketBottomCargoVoltage)); 
    //----------Gripper----------
     //  catchButton.whileHeld(new MoveBasicSubsystem(Robot.gripper, SubsystemConstants.gripper.kGripperInSpeed)); //commented due to not working.
     // releaseButton.whileHeld(new MoveBasicSubsystem(Robot.gripper, SubsystemConstants.gripper.kGripperOutSpeed)); //commented due to not working.
      catchButton.whileHeld(new GripperIn());
      catchButton.whenReleased(new StopGripper()); //testing - GripperIn's end() did not work 
      releaseButton.whileHeld(new GripperOut());
      releaseButton.whenReleased(new StopGripper()); //testing - GripperOut's end() did not work
      togglePushPistons.whenPressed(new TogglePushPistons());
    //----------Gripper Movement----------
      flipGripper.whenPressed(new FoldGripper());
      // setPistonF.whenPressed(new pushPistonF());
      // setPistonR.whenPressed(new pushPistonR());
    //----------Cameras----------
      // switchToA.whenPressed(new SwitchToCameraA()); //Commented since RobotB does not have cameras
      // switchToB.whenPressed(new SwitchToCameraB()); //Commented since RobotB does not have cameras
    // ----------PID----------
      straighten.whenPressed(new ToggleSmartP()); //Commented since RobotB does not have cameras.
    //--------------------Testing--------------------
      //----------Climbing---------
      //----------Climbing Movement---------- 
        // ClimbingMovementB.whileHeld(new MoveBasicSubsystem(Robot.climbingMovement, SubsystemConstants.ClimbingMovement.kClimbingSpeed));
        // ClimbingMovementF.whileHeld(new MoveBasicSubsystem(Robot.climbingMovement, SubsystemConstants.ClimbingMovement.kClimbingSpeedForward));
      //----------Climbing Frame----------
        // ClimbingFrameD.whileHeld(new MoveBasicSubsystem(Robot.frame, SubsystemConstants.ClimbingFrame.kFrameMotorSpeedModifier));
        // ClimbingFrameU.whileHeld(new MoveBasicSubsystem(Robot.frame, SubsystemConstants.ClimbingFrame.kFrameMotorSpeedModifierUp));
      //---------Gripper Movement---------  
  }
}
