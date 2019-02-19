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
import frc.robot.commands.Gripper.GripperRelease;
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
  /**The button that is used to release objects cought with the gripper. When it's released, the gripper catches back an object not fully released.*/
  private Button releaseButton;
  /**The button to switch the StreamViewer on the shuffleboard to view the Camera from port 0.  */
  private Button switchToA;
  /**The button to switch the StreamViewer on the shuffleboard to view the Camera from port 1.  */
  private Button switchToB;
  /**The button to strighten the robot (make it fix deviation from a painted line.) */
  private Button strighten;


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
    drivingJSLeft = new Joystick(0);
    drivingJSRight = new Joystick(1);
    buttonJS = new Joystick(2);
    //----------Gripper Buttons----------
    catchButton = new JoystickButton(buttonJS, 2);
    releaseButton = new JoystickButton(buttonJS, 4);	
    catchButton.whileHeld(new MoveBasicSubsystem(Robot.gripper, SubsystemConstants.gripper.kGripperInSpeed));
    releaseButton.whileHeld(new GripperRelease());
    //----------Camera Buttons---------
    switchToA = new JoystickButton(drivingJSRight, 5);
    switchToB = new JoystickButton(drivingJSRight, 6);
    strighten = new JoystickButton(buttonJS, 9);
    switchToA.whenPressed(new SwitchToCameraA());
    switchToB.whenPressed(new SwitchToCameraB());
    strighten.whenPressed(new driveArcadeWithPID());
  }
}
