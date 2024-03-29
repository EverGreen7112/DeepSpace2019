/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.PID;

import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcadeWithPID;
import com.spikes2212.utils.PIDSettings;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.ImageProccessingSuppliers;
import frc.robot.Robot;
import frc.robot.SubsystemConstants;

/**
 * This is the robot's command to straighten itself - 
 * it often needs to stay on a single, straight line,
 * and this command uses a <a href="https://bit.ly/2H6fnUT">PID loop</a>
 * to automatically drive the robot back to the line every time it deviates from it.
*/
public class TogglePID extends CommandGroup {
  public static boolean looped;
  public TogglePID() {
    if(!looped)
    {
      looped = false;
      addSequential(new DriveArcadeWithPID (
        Robot.drivetrain, //The subsystem the PID will strighten - the DriveTrain
        ImageProccessingSuppliers.center, //The PID source - the center of the objects seen on the camera.
        ImageProccessingSuppliers.center.pidGet(), //The set point - the  thing the robot will try to move towards, here, the center of the 
        SubsystemConstants.PID.kMovement.get(), //The amount of forward movement. 
        new PIDSettings ( //The settings for the PID system (its constants):
          SubsystemConstants.PID.kP.get(), //The proportional constant of the PID system
          SubsystemConstants.PID.kI.get(), //The Integral constant of the PID system.
          SubsystemConstants.PID.kD.get(), //The Derivative constant of the PID system.
          SubsystemConstants.PID.kTolerance.get(), //The Tolerance constant of the PID system.
          SubsystemConstants.PID.kWaitTime.get() //The Wait Time constant of the PID system.
        ),
        SubsystemConstants.PID.kOutputRange.get(), //The range of values the PID system can output.
        false)); //Wheter or not the system is contionous (whether or not it resets to 0 after a full round).
    }
    
    else
    {
      looped = true;
    }
  }
}
