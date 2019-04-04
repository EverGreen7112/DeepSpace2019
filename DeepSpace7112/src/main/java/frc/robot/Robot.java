/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import javax.imageio.plugins.tiff.GeoTIFFTagSet;

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
import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.PortMaps.RobotMap;
import frc.robot.SubsystemComponents.GripperMovement;
import frc.robot.commands.Chassis.DefaultDrive;
import frc.robot.commands.Elevator.ElevatorDefault;
import frc.robot.commands.Elevator.MoveElevatorToTarget;
import frc.robot.commands.GripperMovement.FoldGripper;
import frc.robot.commands.GripperMovement.GripperDefault;

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
  double test;

  @Override
  /**The function ran when the robot is activated.*/
  public void robotInit() {
    //----------Sensor Configs----------
      // SubsystemComponents.Gripper.motorR.setInverted(true);
      // SubsystemComponents.Gripper.motorR.setNeutralMode(NeutralMode.Brake);
      // SubsystemComponents.Gripper.motorL.setNeutralMode(NeutralMode.Brake);
      SubsystemComponents.Gripper.motors = new SpeedControllerGroup(SubsystemComponents.Gripper.motorR, SubsystemComponents.Gripper.motorL);
      SubsystemComponents.Elevator.setupSensors(); //Configures the elevator - inverts the motors and sets the distance per pulse.
      SubsystemComponents.Gripper.PushPiston.set(Value.kForward);
      SubsystemComponents.GripperMovement.MovementPiston.set(Value.kForward);
      SubsystemComponents.Gripper.toungePiston.set(Value.kReverse);
      DefaultDrive.defenseMode = false;
      DefaultDrive.smartPMode = false;
      ElevatorDefault.speedLock = false;
      test = 0;
      DefaultDrive.defenseMode = false;
      DefaultDrive.smartPMode = false;

    cameraHandler = new CamerasHandler ( //configures the cameras - puts the cameras' video on the shuffleboard, and creates a CameraHandler for easy manipulation of it.
      SubsystemConstants.cameras.kCameraWidth.get(), 
      SubsystemConstants.cameras.kCameraHeight.get(), 
      RobotMap.cameraB, RobotMap.cameraA);
      // cameraHandler.setExposure(SubsystemConstants.cameras.kCameraExposure.get()); //Configures the camera handler - sets the appropriate expusure.

      compressor = new Compressor();  
      compressor.start();
      compressor.setClosedLoopControl(true);

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
      dbc = new DashBoardController();
      oi = new OI();
    //----------DefaultCommands----------
      drivetrain.setDefaultCommand(new DefaultDrive());
      elevator.setDefaultCommand(new ElevatorDefault());
      gripper.setDefaultCommand(new GripperDefault());
    //----------Shuffleboard data----------
      // (Currently Unneccesary values commented)
      dbc.addNumber("Laser elevator height", SubsystemComponents.Elevator::getElevatorHeightByLaser);
      dbc.addNumber("Encoder elevator height", SubsystemComponents.Elevator::getElevatorHeightByEncoder);
      dbc.addNumber("getElevatorHeight()", SubsystemComponents.Elevator::getElevatorHeight);
      // dbc.addBoolean("Sensors are Functioning", SubsystemComponents.Elevator.sensorsFunctionSupplier); //Currently MoveToTarget is not used, and therefore the height is not used.
      dbc.addNumber("Elevator Speed", oi::getBTJoystickLeft); //Left Speed
      dbc.addNumber("Elevator Locked Speed", () -> ElevatorDefault.lockedSpeed); //Left Speed
      dbc.addBoolean("elevator switched", () -> ElevatorDefault.switchHit); //Was the encoder reset since RobotInit()?
      // dbc.addNumber("gripper speed", gripper::getSpeed); //Gripper Speed
      // dbc.addNumber("Chassis Left Speed", () -> oi.getLeftJoystick() * SubsystemConstants.Chassis.kDrivingSpeedModifier.get());
      // dbc.addNumber("Chassis Right Speed", () -> oi.getRightJoystick() * SubsystemConstants.Chassis.kDrivingSpeedModifier.get());
      // dbc.addNumber("Chassis Mean Speed", this::getChassisSpeed);
      // dbc.addBoolean("Defense Mode", () -> DefaultDrive.defenseMode);
      // dbc.addNumber("PID X value", () -> ImageProccessingSuppliers.twoReflectivesCenter.get());
      // dbc.addNumber("PID reflective 0 X value ", () -> ImageProccessingSuppliers.Reflective0.centerXSupplier.get());
      // dbc.addNumber("PID reflective 1 X value ", () -> ImageProccessingSuppliers.Reflective1.centerXSupplier.get());
      // dbc.addBoolean("PID reflective 0 seen", () -> ImageProccessingSuppliers.Reflective0.isUpdated.get());
      // dbc.addBoolean("PID reflective 1 seen", () -> ImageProccessingSuppliers.Reflective1.isUpdated.get());
      // dbc.addNumber("Laser Percentage", () -> SubsystemComponents.Elevator.getHeightPercentageByLaser());
      // dbc.addBoolean("Smart P", () -> DefaultDrive.smartPMode);
      // dbc.addNumber("Laser Value", () -> SubsystemComponents.Elevator.lazerSensor.getValue());
      // dbc.addNumber("name", this::getTest);
      // dbc.addBoolean("Elevator default toggled", () -> MoveElevatorToTarget.defaultToggled);
      // dbc.addNumber("Smart P Fix", () -> DefaultDrive.fixSupplier.get());
      // dbc.addNumber("Smart P SetPoint", () -> DefaultDrive.setPoint.get());
      // dbc.addBoolean("smart P", () -> DefaultDrive.smartPMode);
      // dbc.addNumber("Smart P Left Speed", () -> DefaultDrive.leftSmartSpeed);
      // dbc.addNumber("Smart P Right Speed", () -> DefaultDrive.rightSmartSpeed);
      dbc.addNumber("speeeeed", oi::getBTJoystickRight);
  }

  @Override
  public void robotPeriodic() {
    dbc.update();
  }

  public double getTest()
  {
    test++;
    return test;
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
    SubsystemComponents.Gripper.PushPiston.set(Value.kForward);
    SubsystemComponents.GripperMovement.MovementPiston.set(Value.kForward);
    SubsystemComponents.Gripper.toungePiston.set(Value.kReverse);
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    dbc.update();
  }

  @Override
  public void teleopInit() {
    SubsystemComponents.Gripper.PushPiston.set(Value.kForward);
    SubsystemComponents.GripperMovement.MovementPiston.set(Value.kForward);
    SubsystemComponents.Gripper.toungePiston.set(Value.kForward);
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
    return (oi.getLeftJoystick()*SubsystemConstants.Chassis.kDrivingSpeedModifier.get()
     + oi.getRightJoystick()*SubsystemConstants.Chassis.kDrivingSpeedModifier.get())
     /2;
  }

}
