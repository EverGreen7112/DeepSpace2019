/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.spikes2212.dashboard.DashBoardController;
import com.spikes2212.genericsubsystems.basicSubsystem.BasicSubsystem;
import com.spikes2212.genericsubsystems.basicSubsystem.utils.limitationFunctions.Limitless;
import com.spikes2212.genericsubsystems.basicSubsystem.utils.limitationFunctions.MaxLimit;
import com.spikes2212.genericsubsystems.basicSubsystem.utils.limitationFunctions.TwoLimits;
import com.spikes2212.genericsubsystems.drivetrains.TankDrivetrain;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTank;
import com.spikes2212.utils.CamerasHandler;

import frc.robot.commands.Elevator.ElevatorEncoderReset;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

import com.spikes2212.dashboard.DashBoardController;
import com.spikes2212.genericsubsystems.basicSubsystem.BasicSubsystem;
import com.spikes2212.genericsubsystems.basicSubsystem.commands.MoveBasicSubsystem;
import com.spikes2212.genericsubsystems.basicSubsystem.utils.limitationFunctions.MinLimit;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static OI oi;
  public static TankDrivetrain drivetrain;
  public static BasicSubsystem elevator;
  public static BasicSubsystem elevatorEncoder;
  private DashBoardController dbc;
  public static BasicSubsystem gripper;
  public static BasicSubsystem test;
  SendableChooser<Command> chooser = new SendableChooser<>();
  public static CamerasHandler cameraHandler;


  @Override
  public void robotInit() {
    drivetrain = new TankDrivetrain(SubsystemComponents.DriveTrain.leftMotorGroup::set, SubsystemComponents.DriveTrain.rightMotorGroup::set);
    elevator = new BasicSubsystem(SubsystemComponents.Elevator.motors::set, new MaxLimit(SubsystemComponents.Elevator.microswitch::get));
    SubsystemComponents.Elevator.encoder.setDistancePerPulse(SubsystemConstants.Elevator.kDistancePerPulse.get());
    elevator.setDefaultCommand(new ElevatorEncoderReset());
    drivetrain.setDefaultCommand(new DriveTank(drivetrain, oi::getLeftJoystick, oi::getRightJoystick));
    oi = new OI();
    dbc = new DashBoardController();
    SubsystemComponents.Gripper.createMotorGroup();
    gripper = new BasicSubsystem(SubsystemComponents.Gripper.Motors::set, new MinLimit(
      () -> SubsystemComponents.Gripper.lazerSensor.getVoltage() > SubsystemConstants.gripper.kVoltageLimit));
    dbc = new DashBoardController();
    dbc.addBoolean("optic sensor", () -> SubsystemComponents.Gripper.lazerSensor.getVoltage() > SubsystemConstants.gripper.kVoltageLimit);
    drivetrain.setDefaultCommand(new DriveTank(drivetrain, oi::getLeftJoystick, oi::getRightJoystick));
        
  }

  private void initDashboard(){
  }

  @Override
  public void robotPeriodic() {
    dbc.update();
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
    
  }

  @Override
  public void autonomousInit() {

  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
   
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    dbc.update();

  }

  @Override
  public void testPeriodic() {
  }

	
 
}
