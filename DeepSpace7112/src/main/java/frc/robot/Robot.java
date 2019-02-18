/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.spikes2212.dashboard.DashBoardController;
import com.spikes2212.genericsubsystems.basicSubsystem.BasicSubsystem;
import com.spikes2212.genericsubsystems.basicSubsystem.utils.limitationFunctions.MinLimit;
import com.spikes2212.genericsubsystems.basicSubsystem.utils.limitationFunctions.MaxLimit;
import com.spikes2212.genericsubsystems.basicSubsystem.utils.limitationFunctions.TwoLimits;
import com.spikes2212.genericsubsystems.basicSubsystem.utils.limitationFunctions.Limitless;
import com.spikes2212.genericsubsystems.drivetrains.TankDrivetrain;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTank;
import com.spikes2212.utils.CamerasHandler;

import frc.robot.commands.Elevator.ElevatorEncoderReset;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.commands.GUI;

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
  public static BasicSubsystem gripper;
  public static BasicSubsystem shaft;
  public static BasicSubsystem climbingMovement;
  
  public static CamerasHandler cameraHandler;
  private DashBoardController dbc;


  @Override
  public void robotInit() {
    //---------Sensor Configs----------
    SubsystemComponents.Gripper.createMotorGroup();
    SubsystemComponents.Elevator.encoder.setDistancePerPulse(SubsystemConstants.Elevator.kDistancePerPulse.get());
    cameraHandler = new CamerasHandler (
      SubsystemConstants.cameras.kCameraWidth.get(), 
      SubsystemConstants.cameras.kCameraHeight.get(), 
      RobotMap.cameraA,
      RobotMap.cameraB);
    cameraHandler.setExposure(SubsystemConstants.cameras.kCameraExposure.get());
    
    //----------BasicSubsystems----------
    drivetrain = new TankDrivetrain(SubsystemComponents.DriveTrain.leftMotorGroup::set, SubsystemComponents.DriveTrain.rightMotorGroup::set);
    gripper = new BasicSubsystem(SubsystemComponents.Gripper.Motors::set, new MinLimit(
      SubsystemComponents.Gripper::isCargoCaught));
    elevator = new BasicSubsystem(SubsystemComponents.Elevator.motors::set, new MaxLimit(
      SubsystemComponents.Elevator.microswitch::get));
    shaft = new BasicSubsystem(SubsystemComponents.ClimbingShaft.Motor::set, new TwoLimits(
      SubsystemComponents.ClimbingShaft.bottomLimiter::get, SubsystemComponents.ClimbingShaft.topLimiter::get));
    climbingMovement = new BasicSubsystem(SubsystemComponents.ClimbingMovement.Motor::set, new Limitless());

    //----------DefaultCommands----------
    elevator.setDefaultCommand(new ElevatorEncoderReset());
    drivetrain.setDefaultCommand(new DriveTank(drivetrain, oi::getLeftJoystick, oi::getRightJoystick));
    
    //----------Class Constructors----------
    oi = new OI();
    dbc = new DashBoardController(); 

    //----------GUI----------
    GUI gui = new GUI();
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
