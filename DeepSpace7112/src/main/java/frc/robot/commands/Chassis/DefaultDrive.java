/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Chassis;

import java.util.function.Supplier;

import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcadeWithPID;
import com.spikes2212.utils.PIDSettings;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.ImageProccessingSuppliers;
import frc.robot.Robot;
import frc.robot.SubsystemComponents;
import frc.robot.SubsystemConstants;

public class DefaultDrive extends Command {
  public static boolean defenseMode;
  public static boolean smartPMode;
  public static boolean pidMode;
  // public static boolean adjusted;
  public static double motorSmartInput;
  public static double leftSmartSpeed;
  public static double rightSmartSpeed;
  public static Supplier<Double> setPoint = () -> ImageProccessingSuppliers.center.pidGet();
  public static Supplier<Double> fixSupplier = () -> {
    double smartAdjust = calculatePercentage(setPoint.get()/2);
    if (setPoint.get() < 320) 
    {
      smartAdjust = -smartAdjust;
    }
    // smartAdjust = setPoint.get() < 320 ? -smartAdjust : smartAdjust; //commented to check if an if statement will be better
    return smartAdjust;}; //half for every motor.
  public DefaultDrive() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.drivetrain);
    }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    // adjusted = false;
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
      // double smartAdjust = fixSupplier.get()/2;
      double smartAdjust = fixSupplier.get();
      // if(setPoint.get() == 320 || adjusted)
      // {
      //   adjusted = true;
      //   smartAdjust = 0;
      // } 

      leftSmartSpeed = 
          Robot.oi.getLeftJoystick() 
        * SubsystemConstants.Chassis.kDrivingSpeedModifier.get()
        + smartAdjust
        // + SubsystemConstants.SmartP.kSpeedModifier.get()
        ;
      rightSmartSpeed = 
          Robot.oi.getRightJoystick() 
        * SubsystemConstants.Chassis.kDrivingSpeedModifier.get() 
        + smartAdjust
        // + SubsystemConstants.SmartP.kSpeedModifier.get()
        ;
      Robot.drivetrain.tankDrive (leftSmartSpeed, rightSmartSpeed);
      // The input for the right motor: includes Y axis movement and adjustments from the smart input.
      // Solely for the purpose of Y axis movement
      System.out.println("smartP allignment");
      System.out.println("Adjustment: " + smartAdjust);
      System.out.println("Left Speed: " + leftSmartSpeed);
      System.out.println("Right Speed: " + rightSmartSpeed);
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

  /**
   * Caculate the percentage of power by the function of Percentage to give to he engines for 
   * the Smart P fix by the position of the center point in pixels - 
   * A parabola with a vertex of (320, a), where a is the max amount of power possible to give 
   * to the engines without moving the robot, so every additional error would actully move for the fix,
   * [320 is the center of te camera, where the center should be],
   * and which has the points (0, m) and (640, m), where m is the percentage to give to the engines at
   * maximum error (which 0 and 640 are).
   * @param centerPosition - The position of the center point in pixels.
   * @return - The percentage required to give to the engines in order to fix the error
   * between the robot's position and the center.
   */
  public static double calculatePercentage(double centerPosition) //x = centerPosition 
  {
    double numerator = ((Math.pow(centerPosition-320, 2))) 
      * (SubsystemConstants.SmartP.kMaxDeviationFix.get()
        - SubsystemConstants.SmartP.kA.get());
    double denominator = (Math.pow(320, 2))*100;
    double fraction = (numerator/denominator)/7;
    return fraction + SubsystemConstants.SmartP.kA.get();
  }
}
