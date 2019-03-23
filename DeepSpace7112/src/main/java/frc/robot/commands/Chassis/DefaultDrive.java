/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Chassis;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.ImageProccessingSuppliers;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.SubsystemConstants;
import frc.robot.SubsystemConstants.Chassis;

public class DefaultDrive extends Command {
  public static boolean defenseMode;
  public static boolean smartPMode;
  public static double rightMotorSmartInput;
  public static double leftMotorSmartInput;
  public DefaultDrive() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.drivetrain);
    }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // if(defenseMode) {
    //   System.out.println("Defense Drive");
    //   Robot.drivetrain.tankDrive(Robot.oi.getLeftJoystick() * SubsystemConstants.Chassis.kSlowSpeedModifier.get(), Robot.oi.getRightJoystick() * SubsystemConstants.Chassis.kSlowSpeedModifier.get());
    // }
    // else
     if(smartPMode) {
      System.out.println("smartP allignment");
      double setPoint = ImageProccessingSuppliers.center.pidGet(); // The current X that we need to correct.
      rightMotorSmartInput = (0.5 + ((Math.pow(setPoint - 320, 2)) / 1024)) * // The input we will give to the right motor (not including the joystick) to correct the alignment.
        ((setPoint - 320) / Math.abs(setPoint - 320));                        // Uses the function: y = (a + ((Math.pow(x - 320, 2) / 1024)) * ((x - 320) / Math.abs(x - 320))
      leftMotorSmartInput = -rightMotorSmartInput; // The input we'll give the left motor (excluding the joystick) to correct the alignment. Is the opposite of the right input to turn in place.
      Robot.drivetrain.tankDrive((Robot.oi.getRightJoystick() * SubsystemConstants.Chassis.kDrivingSpeedModifier.get()) + leftMotorSmartInput, // The input for the motor.
       (Robot.oi.getRightJoystick() * SubsystemConstants.Chassis.kDrivingSpeedModifier.get()) + rightMotorSmartInput);                         // Includes joystick + alignment correction.
    }
    else {
      System.out.println("Normal Drive");
    Robot.drivetrain.tankDrive(Robot.oi.getLeftJoystick() * SubsystemConstants.Chassis.kDrivingSpeedModifier.get(), Robot.oi.getRightJoystick() * SubsystemConstants.Chassis.kDrivingSpeedModifier.get());
  }
}

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
