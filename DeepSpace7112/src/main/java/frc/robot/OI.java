/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import frc.robot.commands.PID.driveArcadeWithPID;
import frc.robot.commands.Cameras.SwitchToCameraA;
import frc.robot.commands.Cameras.SwitchToCameraB;
import frc.robot.commands.Gripper.GripperPistons;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import com.spikes2212.genericsubsystems.basicSubsystem.commands.MoveBasicSubsystem;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  //--------------------Declerations--------------------

  //----------Joysticks----------

  /**The joystick that controlls the left motors. */
  private Joystick drivingJSLeft;
  /**The Joystick that conntrols the right motors. */
  private Joystick drivingJSRight;
  /**The button joysticks which is used to execute the various commands to the robot - Climbing, catching, releasing and controlling the PID. */
  private Joystick buttonJS;

  //----------Buttons----------
  
  /**The button that is used to catch objects with the gripper. The */
  private Button catchButton;
  /**The button that is used to release objects cought with the gripper. */
  private Button releaseButton;
  private Button switchToA;
  private Button switchToB;
  private Button backButton;
  private Button bottomHatch;
  private Button middleHatch;
  private Button topHatch;
  private Button bottomCargo;
  private Button middleCargo;
  private Button topCargo;

  private double adjustInput(double input){
    return input * Math.abs(input);
  }
  
  public double getLeftJoystick() {
    return adjustInput(drivingJSLeft.getY());
  }
    
  public double getRightJoystick() {
    return adjustInput(drivingJSRight.getY());
  }

  //--------------------Initializations--------------------
  public OI() {
    //----------Joysticks----------
    drivingJSRight = new Joystick(1);
    buttonJS = new Joystick(2);

    //----------Elevator Buttons----------
    bottomHatch = new JoystickButton(drivingJSLeft, 12);
    middleHatch= new JoystickButton(drivingJSLeft, 10);
    topHatch = new JoystickButton(drivingJSLeft, 8);
    bottomCargo = new JoystickButton(drivingJSLeft, 11);
    middleCargo = new JoystickButton(drivingJSLeft, 9);
    topCargo = new JoystickButton(drivingJSLeft, 7);

    //----------Gripper Buttons----------
    catchButton = new JoystickButton(buttonJS, 1);
    releaseButton = new JoystickButton(buttonJS, 3);	
    catchButton.whileHeld(new MoveBasicSubsystem(Robot.gripper, SubsystemConstants.gripper.kGripperInSpeed));
    releaseButton.whileHeld(new MoveBasicSubsystem(Robot.gripper, SubsystemConstants.gripper.kGripperOutSpeed));
    
    //----------Camera Buttons---------
    switchToA = new JoystickButton(drivingJSRight, 5);
    switchToB = new JoystickButton(drivingJSRight, 6);
    backButton = new JoystickButton(buttonJS, 9);
    switchToA.whenPressed(new SwitchToCameraA());
    switchToB.whenPressed(new SwitchToCameraB());
    backButton.whenPressed(new driveArcadeWithPID());
    gripperPistons.whenPressed(new GripperPistons());
  }
}
