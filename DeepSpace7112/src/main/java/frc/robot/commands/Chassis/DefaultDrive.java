/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Chassis;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.ImageProccessingSuppliers;
import frc.robot.Robot;
import frc.robot.SubsystemComponents;
import frc.robot.SubsystemConstants;

public class DefaultDrive extends Command {
  public static boolean defenseMode;
  public static boolean smartPMode;
  public static double motorSmartInput;
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
      double setPoint = ImageProccessingSuppliers.center.pidGet();
      double smartAdjust = calculatePercentage(setPoint);
      Robot.drivetrain.tankDrive(
        0.3 * ((Robot.oi.getLeftJoystick() * 
          SubsystemConstants.Chassis.kDrivingSpeedModifier.get()) + smartAdjust), // The input for the motor.
        0.3 * ((Robot.oi.getRightJoystick() *
          SubsystemConstants.Chassis.kDrivingSpeedModifier.get()))); // Includes joystick + alignment correction.
    }

    else {
      // System.out.println("Normal Drive");
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

  public double calculatePercentage(double x)
  {
    if(x==320) //W
    {
      return 0;
    }

    double numerator = (Math.pow(x-320, 2))*(100-SubsystemConstants.SmartP.kA.get());
    double denominator = Math.pow(320, 2);
    return numerator/denominator;
  }
}
