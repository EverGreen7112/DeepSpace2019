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
  private Joystick buttonsJoystick;

  private Button A;
  private Button Y;

  public OI(){
	  buttonsJoystick = new Joystick(2);

	  A = new JoystickButton(buttonsJoystick, 2);
	  Y = new JoystickButton(buttonsJoystick, 4);

	  A.whileHeld(new MoveBasicSubsystem(Robot.elevator, - SubsystemConstants.Elevator.kElevatorMotorSpeedModifier.get()));
	  Y.whileHeld(new MoveBasicSubsystem(Robot.elevator, SubsystemConstants.Elevator.kElevatorMotorSpeedModifier));

  }

  	// receives input, returns the adjusted input for better sensitivity
		private double adjustInput(double input){
			return input * Math.abs(input);
    }
    
    public double getLeftJoystick() {
			return adjustInput(drivingJSLeft.getY());
		}
		
		public double getRightJoystick() {
			return adjustInput(drivingJSRight.getY());
		}


}
