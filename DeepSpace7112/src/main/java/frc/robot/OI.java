/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.Commands.SwitchToCameraA;
import frc.robot.Commands.SwitchToCameraB;

import com.spikes2212.utils.CamerasHandler;

import edu.wpi.first.wpilibj.Joystick;
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
  private Button switchToA;
  private Button switchToB;

	public OI()
	{
		drivingJSRight = new Joystick(1);
		switchToA = new JoystickButton(drivingJSRight, 5);
		switchToB = new JoystickButton(drivingJSRight, 6);
		switchToA.whenPressed(new SwitchToCameraA());
		switchToB.whenPressed(new SwitchToCameraB());
	}

  	// receives input, returns the adjusted input for better sensitivity
	private double adjustInput(double input) {
		return input * Math.abs(input);
    }
    
    public double getLeftJoystick() {
		return adjustInput(drivingJSLeft.getY());
	}
		
	public double getRightJoystick() {
		return adjustInput(drivingJSRight.getY());
	}


}
