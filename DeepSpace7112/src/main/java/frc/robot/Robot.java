/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.spikes2212.dashboard.DashBoardController;
import com.spikes2212.genericsubsystems.basicSubsystem.BasicSubsystem;
import com.spikes2212.genericsubsystems.basicSubsystem.commands.MoveBasicSubsystem;
import com.spikes2212.genericsubsystems.basicSubsystem.utils.limitationFunctions.MinLimit;
import com.spikes2212.genericsubsystems.basicSubsystem.utils.limitationFunctions.MaxLimit;
import com.spikes2212.genericsubsystems.basicSubsystem.utils.limitationFunctions.Limitless;
import com.spikes2212.genericsubsystems.drivetrains.TankDrivetrain;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTank;
import com.spikes2212.utils.CamerasHandler;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Scheduler;

/** This is the code ran (together with the OI) when activating the robot - 
 * it includes the decleration, intialization and confguration of the Subsystems.
 * <p> 
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation.
 * <p>
 * changing the name of this class or the package after
 * creating this project, requires updating the build.gradle file in the
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
  
  public static Compressor compressor;

  public static CamerasHandler cameraHandler;
  private DashBoardController dbc;

  @Override
  /**The function ran when the robot is activated.*/
  public void robotInit() {
    //---------Sensor Configs----------
    SubsystemComponents.Elevator.setupSensors();
    SubsystemComponents.Gripper.LockPiston.set(Value.kReverse);
    SubsystemComponents.Gripper.PushPiston.set(Value.kReverse);
    SubsystemComponents.GripperMovement.piston.set(Value.kForward);

    cameraHandler = new CamerasHandler (
      SubsystemConstants.cameras.kCameraWidth.get(), 
      SubsystemConstants.cameras.kCameraHeight.get(), 
      RobotMap.cameraA);
    cameraHandler.setExposure(SubsystemConstants.cameras.kCameraExposure.get());
    
    compressor = new Compressor();
    compressor.start();
    compressor.setClosedLoopControl(true);

    //----------BasicSubsystems----------
      drivetrain = new TankDrivetrain(SubsystemComponents.DriveTrain.leftMotorGroup::set, SubsystemComponents.DriveTrain.rightMotorGroup::set);
      // gripper = new BasicSubsystem(SubsystemComponents.Gripper.motors::set, new MinLimit (
      // SubsystemComponents.Gripper::isCargoCaught)); //Commented due to the sensors not beinng commented yet, making the gripper is limitless system.
      gripper = new BasicSubsystem(SubsystemComponents.Gripper.motors::set, new Limitless()); //testing
      elevator = new BasicSubsystem(SubsystemComponents.Elevator.motors::set, new MaxLimit (
        () -> (SubsystemComponents.Elevator.encoder.getDistance() >= SubsystemConstants.Elevator.kEncoderMaxHeight.get()))); 
        //^^^Maximum Limit by the encoder - if it transmits that the elevator has surpussed our determained maximum height, it'll stop the movement
      shaft = new BasicSubsystem(SubsystemComponents.ClimbingShaft.motor::set, new MinLimit(
        SubsystemComponents.ClimbingShaft.bottomLimiter::get)); //Minimum Limit - if the shaft presses the switch , stop the shaft. The robot is built such that the switch will be pressed when the shaft reaces the determained minimum height. 
      climbingMovement = new BasicSubsystem(SubsystemComponents.ClimbingMovement.motor::set, new Limitless());
      
    //----------Class Constructors----------
      oi = new OI();
      dbc = new DashBoardController(); 
    
    //----------DefaultCommands----------
    drivetrain.setDefaultCommand(new DriveTank(drivetrain, oi::getLeftJoystick, oi::getRightJoystick));
    //elevator.setDefaultCommand(new MoveBasicSubsystem(elevator, oi::getBTJoystick));
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
    dbc.update();
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
    Scheduler.getInstance().run();
    dbc.update();

  }
}
