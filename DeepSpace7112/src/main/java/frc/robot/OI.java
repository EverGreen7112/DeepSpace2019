/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


import edu.wpi.first.wpilibj.Joystick;
import com.spikes2212.genericsubsystems.basicSubsystem.commands.MoveBasicSubsystem;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  //--------------------Declerations--------------------

  //----------Joysticks----------
  private Joystick drivingJSLeft;
  private Joystick drivingJSRight;
  private Joystick buttonsJoystick;

  private Joystick buttonJoystick;

  //----------Buttons----------
  
  private Button catchButton;
  private Button releaseButton;

		private double adjustInput(double input){
			return input * Math.abs(input);
    }
    
    public double getLeftJoystick() {
			return -adjustInput(drivingJSLeft.getY()) * SubsystemConstants.chassis.kDrivingSpeedModifier.get();
		}
		
		public double getRightJoystick() {
			return adjustInput(drivingJSRight.getY()) * SubsystemConstants.chassis.kDrivingSpeedModifier.get();
		}
	
	
	
		public OI() {
			drivingJSLeft = new Joystick(0);
			drivingJSRight = new Joystick(1);	  
			buttonJoystick = new Joystick(2);
			catchButton = new JoystickButton(buttonJoystick, 2);
			releaseButton = new JoystickButton(buttonJoystick, 4);	

		    catchButton.whileHeld(new MoveBasicSubsystem(Robot.gripper, SubsystemConstants.gripper.gripperInSpeed));
		    releaseButton.whileHeld(new MoveBasicSubsystem(Robot.gripper, SubsystemConstants.gripper.gripperOutSpeed));
		}	
	

}
