/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcadeWithPID;
import com.spikes2212.utils.PIDSettings;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.ImageProccessingSuppliers;
import frc.robot.Robot;
import frc.robot.SubsystemConstants;

public class GetPIDArcadeDrive extends CommandGroup {
  /**
   * Initilizes the Joystick and the PID program
   */
  public GetPIDArcadeDrive() {
        addSequential(new DriveArcadeWithPID(
          Robot.drivetrain, //The DriveTrain Subsystem
           ImageProccessingSuppliers.CENTER, //The PID source
           SubsystemConstants.cameras.kSetPoint, //The set point 
           ImageProccessingSuppliers.kMovement.get(), //The amount of forward movement. 
           new PIDSettings( //The settings for the PID system:
             ImageProccessingSuppliers.kP.get(), //The proportional part of the PID system
             ImageProccessingSuppliers.kI.get(), //The Integral part of the PID system.
             ImageProccessingSuppliers.kD.get(), //The Derivative part of the PID system.
             ImageProccessingSuppliers.kTolerance.get(), //The Tolerance constant of the PID system.
             ImageProccessingSuppliers.kWaitTime.get() //The Wait Time constant of the PID system.
            ),
           SubsystemConstants.cameras.kCameraOutputRange, //The range of values the PID system can output.
           false) //Wheter or not the system is contionous (whether or not it resets to 0 after a full round).
        );

  }
}
