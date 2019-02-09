/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


import com.spikes2212.genericsubsystems.drivetrains.TankDrivetrain;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTank;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
  public static BasicSubsystem gripper;
  public static BasicSubsystem test;
  public DashBoardController dbc;
  SendableChooser<Command> chooser = new SendableChooser<>();

  @Override
  public void robotInit() {
    drivetrain = new TankDrivetrain(SubsystemComponents.DriveTrain.leftMotorGroup::set, SubsystemComponents.DriveTrain.rightMotorGroup::set);
    SubsystemComponents.Gripper.createMotorGroup();
    initDashboard();
    gripper = new BasicSubsystem(SubsystemComponents.Gripper.Motors::set, new MinLimit(
      () -> SubsystemComponents.Gripper.lazerSensor.getVoltage() > SubsystemConstants.gripper.kVoltageLimit));
    dbc = new DashBoardController();
    dbc.addBoolean("optic sensor", () -> SubsystemComponents.Gripper.lazerSensor.getVoltage() > SubsystemConstants.gripper.kVoltageLimit);
    oi = new OI();
    drivetrain.setDefaultCommand(new DriveTank(drivetrain, oi::getLeftJoystick, oi::getRightJoystick));
        
  }

  private void initDashboard(){
    SmartDashboard.putData("Roll In", new MoveBasicSubsystem(Robot.gripper, SubsystemConstants.gripper.gripperInSpeed));
		SmartDashboard.putData("Roll Out", new MoveBasicSubsystem(Robot.gripper, SubsystemConstants.gripper.gripperOutSpeed));
    SmartDashboard.putData("Stop gripper", new MoveBasicSubsystem(Robot.gripper, 0));
  }

  @Override
  public void robotPeriodic() {

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
