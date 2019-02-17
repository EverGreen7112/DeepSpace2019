/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import frc.robot.commands.driveArcadeWithPID;
import frc.robot.commands.Cameras.SwitchToCameraA;
import frc.robot.commands.Cameras.SwitchToCameraB;

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
  private Joystick drivingJSLeft;
  private Joystick drivingJSRight;
  private Joystick buttonJS;

  //----------Buttons----------
  
  private Button catchButton;
  private Button releaseButton;
  private Button switchToA;
  private Button switchToB;
  private Button backButton;


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
    //----------Gripper Buttons----------
    catchButton = new JoystickButton(buttonJS, 2);
    releaseButton = new JoystickButton(buttonJS, 4);	
    catchButton.whileHeld(new MoveBasicSubsystem(Robot.gripper, SubsystemConstants.gripper.kGripperInSpeed));
    releaseButton.whileHeld(new MoveBasicSubsystem(Robot.gripper, SubsystemConstants.gripper.kGripperOutSpeed));
    //----------Camera Buttons---------
    switchToA = new JoystickButton(drivingJSRight, 5);
    switchToB = new JoystickButton(drivingJSRight, 6);
    backButton = new JoystickButton(buttonJS, 9);
    switchToA.whenPressed(new SwitchToCameraA());
    switchToB.whenPressed(new SwitchToCameraB());
    backButton.whenPressed(new driveArcadeWithPID());
  }
		
		
			

}
