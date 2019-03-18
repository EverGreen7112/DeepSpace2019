/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.PortMaps.RobotMap;
import frc.robot.SubsystemComponents.GripperMovement;
import frc.robot.commands.Chassis.DefaultDrive;
import frc.robot.commands.Elevator.ElevatorDefault;
import frc.robot.commands.GripperMovement.FoldGripper;

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
  public static BasicSubsystem elevatorClimb;
  public static BasicSubsystem gripper;
  public static BasicSubsystem frame;
  public static BasicSubsystem climbingMovement;
  public static CamerasHandler cameraHandler;

  public static Compressor compressor;

  public static DashBoardController dbc;

  @Override
  /**The function ran when the robot is activated.*/
  public void robotInit() {
    //----------Sensor Configs----------
      // SubsystemComponents.Gripper.motorR.setInverted(true);
      // SubsystemComponents.Gripper.motorR.setNeutralMode(NeutralMode.Brake);
      // SubsystemComponents.Gripper.motorL.setNeutralMode(NeutralMode.Brake);
      SubsystemComponents.Gripper.motors = new SpeedControllerGroup(SubsystemComponents.Gripper.motorR, SubsystemComponents.Gripper.motorL);
      SubsystemComponents.Elevator.setupSensors(); //Configures the elevator - inverts the motors and sets the distance per pulse.
      SubsystemComponents.Gripper.PushPiston.set(Value.kReverse);
      SubsystemComponents.GripperMovement.MovementPiston.set(Value.kReverse);
      DefaultDrive.defenseMode = false;

    cameraHandler = new CamerasHandler ( //configures the cameras - puts the cameras' video on the shuffleboard, and creates a CameraHandler for easy manipulation of it.
      SubsystemConstants.cameras.kCameraWidth.get(), 
      SubsystemConstants.cameras.kCameraHeight.get(), 
      RobotMap.cameraA);
    cameraHandler.setExposure(SubsystemConstants.cameras.kCameraExposure.get()); //Configures the camera handler - sets the appropriate expusure.

      compressor = new Compressor(); //Commented because RobotB does not have working pneomatics.
      compressor.start(); //Commented because RbotB does not have working pneomatics.
      compressor.setClosedLoopControl(true); //Commented because RobotB does not have pneomtics.

    //----------BasicSubsystems----------
      drivetrain = new TankDrivetrain(SubsystemComponents.Chassis.leftMotorGroup::set, SubsystemComponents.Chassis.rightMotorGroup::set);
      
      gripper = new BasicSubsystem(SubsystemComponents.Gripper.motors::set, new MaxLimit(
        SubsystemComponents.Gripper::isCargoCaught)); //Commented due to the MaxVoltage elevator constant not bring determined yet, making testing the gripper with this limit impossible. 
      
    //  gripper = new BasicSubsystem(SubsystemComponents.Gripper.motors::set, new Limitless()); //testing
      
      // elevator = new BasicSubsystem(SubsystemComponents.Elevator.motors::set, new MaxLimit (
      //   () -> (SubsystemComponents.Elevator.encoder.getDistance() >= SubsystemConstants.Elevator.kEncoderMaxHeight.get()))); 
        //^^^Maximum Limit by the encoder - if it transmits that the elevator has surpussed our determained maximum height, it'll stop the movement
      
        elevator  = new BasicSubsystem(SubsystemComponents.Elevator.motors::set, new Limitless());
      elevatorClimb = new BasicSubsystem(SubsystemComponents.Elevator.motors::set, new MinLimit(SubsystemComponents.ClimbingFrame.bottomLimiter::get));
    
      frame = new BasicSubsystem(SubsystemComponents.ClimbingFrame.motor::set, new MinLimit(
        SubsystemComponents.ClimbingFrame.bottomLimiter::get)); //Minimum Limit - if the frame presses the switch , stop the frame. The robot is built such that the switch will be pressed when the frame reaces the determained minimum height. 
      
      //frame = new BasicSubsystem(SubsystemComponents.Climbingframe.motor::set, new Limitless());
      climbingMovement = new BasicSubsystem(SubsystemComponents.ClimbingMovement.motor::set, new Limitless());
      
    //----------Class Constructors----------
      oi = new OI();
      dbc = new DashBoardController(); 
    
    //----------DefaultCommands----------
      drivetrain.setDefaultCommand(new DriveTank(drivetrain, oi::getLeftJoystick, oi::getRightJoystick));
      // drivetrain.setDefaultCommand(new defaultDrive());
      elevator.setDefaultCommand(new ElevatorDefault());
    //----------Shuffleboard data----------
      dbc.addNumber("Lazer elevator height", SubsystemComponents.Elevator::getElevatorHeightByLazer);
      dbc.addNumber("Encoder elevator height", SubsystemComponents.Elevator::getElevatorHeightByEncoder);
      dbc.addNumber("Total elevator height", SubsystemComponents.Elevator::getElevatorHeight);
      // dbc.addBoolean("Sensors are Functioning", SubsystemComponents.Elevator.sensorsFunctionSupplier); //Currently MoveToTarget is not used, and therefore the height is not used.
      dbc.addNumber("Elevator Speed", oi::getBTJoystickLeft);
      dbc.addNumber("lazer voltage", SubsystemComponents.Elevator.lazerSensor::getVoltage);
      dbc.addBoolean("elevator switched", () -> SubsystemComponents.Elevator.encoderWasReset);
      dbc.addNumber("gripper speed", gripper::getSpeed);
      dbc.addNumber("Chassis current speed modifier", () -> SubsystemComponents.Chassis.currentSpeed);
      dbc.addNumber("Chassis Left Speed", oi::getBTJoystickLeft);
      dbc.addNumber("Chassis Right Speed", oi::getRightJoystick);
      dbc.addNumber("Chassis Mean Speed", this::getChassisSpeed);
      dbc.addNumber("Elevator JS", oi::getBTJoystickLeft);
      dbc.addBoolean("Defense Mode", () -> DefaultDrive.defenseMode);
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

  public double getChassisSpeed()
  {
    return (oi.getLeftJoystick() + oi.getRightJoystick())/2;
  }

}
