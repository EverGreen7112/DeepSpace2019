/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.spikes2212.genericsubsystems.basicSubsystem.commands.MoveBasicSubsystem;

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
  private Joystick buttonJS; 

  //----------Buttons----------  
  private Button A;
  private Button Y;
  
  //--------------------Constructors--------------------

	public OI(){

		  //----------Joysticks----------
		drivingJSLeft = new Joystick(0);
		drivingJSRight = new Joystick(1);
		buttonJS = new Joystick(2);

		  //----------Buttons----------
		A = new JoystickButton(buttonJS, 2);
		Y = new JoystickButton(buttonJS, 4);

		bindButtons();
	}

	//--------------------Methods--------------------
	
	private void bindButtons(){

		//Lowers the climbing shaft until reaching the bottom microswitch
		A.whileHeld(new MoveBasicSubsystem(Robot.shaft, -SubsystemConstants.ClimbingShaft.shaftMotorSpeedModifier.get()));
		//Raises the climbing shaft until reaching the top microswitch
		Y.whileHeld(new MoveBasicSubsystem(Robot.shaft, SubsystemConstants.ClimbingShaft.shaftMotorSpeedModifier.get()));
	}
	
  	// receives input, returns the adjusted input for better sensitivity
	private double adjustInput(double input){
			return input * Math.abs(input);
	}
	
    
    public double getLeftJoystick() {
			return -adjustInput(drivingJSLeft.getY()) * SubsystemConstants.chassis.kDrivingSpeedModifier.get();
		}
		
		public double getRightJoystick() {
			return adjustInput(drivingJSRight.getY()) * SubsystemConstants.chassis.kDrivingSpeedModifier.get();
		}
	

}
