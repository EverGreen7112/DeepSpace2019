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
import com.spikes2212.genericsubsystems.drivetrains.TankDrivetrain;
import com.spikes2212.utils.CamerasHandler;


import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.Library.Subsystems.BasicSubsystems.Classes.BasicSubsystemWithSwitch;
import frc.Library.Subsystems.PistonSubsystems.Classes.PistonSubsystem;
import frc.robot.commands.Chassis.DefaultDrive;
import frc.robot.commands.Elevator.ElevatorDefault;

/** This class represents the actual robot - it's joysticks, commands and subsystems. It also contains
 * the methods ran whenever it gain communication and whenever it's enabled.
 * <p> 
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation.
 * <p>
 * changing the name of this class or the package after
 * creating this project, requires updating the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot implements SubsystemMethods, SubsystemConstants, RobotMap {
  /**The instance of OI - cotaining all the joysticks and their buttons,
   * and the methods to get their input.*/
  public static OI oi;
  
  /**The robot's wheels subsystems, comprised of two pairs of motors, one for the right wheels
   * and one for the left. */
  public static TankDrivetrain drivetrain;
  public static BasicSubsystem elevator;
  public static BasicSubsystem elevatorEncoder;
  public static BasicSubsystem elevatorClimb;
  public static BasicSubsystem gripper;
  public static BasicSubsystem frame;
  public static BasicSubsystem climbingMovement;
  
  public static PistonSubsystem tounge;
  public static PistonSubsystem hatch;
  public static PistonSubsystem gripperMovement;
  
  public static CamerasHandler cameraHandler;

  public static Compressor compressor;

  public static DashBoardController dbc;
  double test;

  @Override
  /**The function ran whenever communications with the robot is regained. To rerun it after communication
   * has already been gained, one may press the restart robot code button in the driver station.<p>.
   * It initializes all the subsystems, joysticks and buttons (the last two through an OI object),
   * and configures the sensors and subsystems. */
  public void robotInit() {
    //----------Sensor Configs----------
      initConfig(); //Configures the elevator - inverts the motors and sets the distance per pulse.
      
      test = 0;
      
      compressor = new Compressor();  
      compressor.start();
      compressor.setClosedLoopControl(true);

      cameraHandler = new CamerasHandler(CameraConsts.kWidth.get(), CameraConsts.kHeight.get(), 
        CameraPorts.front, CameraPorts.side);
        cameraHandler.setExposure(CameraConsts.kExposure.get());
    //----------BasicSubsystems----------
      drivetrain = new TankDrivetrain(
        SubsystemComponents.Chassis.leftMotorGroup::set, 
        SubsystemComponents.Chassis.rightMotorGroup::set);
      
      gripper = new BasicSubsystemWithSwitch (
        SubsystemComponents.Gripper.motors::set, 
        new Limitless(), //new MaxLimit(SubsystemComponents.Gripper::isCargoCaught), //Commented due to the MaxVoltage elevator constant not bring determined yet, making testing the gripper with this limit impossible
        "Gripper");

      elevator  = new BasicSubsystemWithSwitch(
        SubsystemComponents.Elevator.motors::set, 
        new Limitless(),
        "Elevator");
      
      tounge = new PistonSubsystem(
        SubsystemComponents.Gripper.toungePiston::set, 
        SubsystemComponents.Gripper.toungePiston::get, 
        "Hatch System - Tounge Pistons");
      hatch = new PistonSubsystem(
        SubsystemComponents.Gripper.PushPiston::set, 
        SubsystemComponents.Gripper.PushPiston::get, 
        "Hatch System - Push Pistons");
      gripperMovement = new PistonSubsystem(
        SubsystemComponents.GripperMovement.MovementPiston::set,
        SubsystemComponents.GripperMovement.MovementPiston::get,
        "Gripper Movement Pistons");
    //----------Class Constructors----------
      dbc = new DashBoardController();
    //----------DefaultCommands----------
      drivetrain.setDefaultCommand(new DefaultDrive());
      elevator.setDefaultCommand(new ElevatorDefault());
    //----------Shuffleboard data----------
      // (Currently Unneccesary values commented)
      //-----Elevator-----
        // dbc.addNumber("Laser elevator height", SubsystemComponents.Elevator::getElevatorHeightByLaser);
        // dbc.addNumber("Encoder elevator height", SubsystemComponents.Elevator::getElevatorHeightByEncoder);
        // dbc.addNumber("getElevatorHeight()", SubsystemComponents.Elevator::getElevatorHeight);
        // dbc.addBoolean("Sensors are Functioning", SubsystemComponents.Elevator.sensorsFunctionSupplier); //Currently MoveToTarget is not used, and therefore the height is not used.
        // dbc.addNumber("Elevator Speed", oi::getBTJoystick); //Left Speed
        // dbc.addNumber("Elevator Locked Speed", () -> ElevatorDefault.lockedSpeed); //Left Speed
        // dbc.addBoolean("elevator switched", () -> ElevatorDefault.switchHit); //Was the encoder reset since RobotInit()?
        // dbc.addNumber("Laser Percentage", () -> SubsystemComponents.Elevator.getHeightPercentageByLaser());
        // dbc.addNumber("Laser Value", () -> SubsystemComponents.Elevator.lazerSensor.getValue());
        // dbc.addNumber("name", this::getTest);
        // dbc.addBoolean("Elevator default toggled", () -> MoveElevatorToTarget.defaultToggled);
      //-----Gripper-----
        // dbc.addNumber("gripper speed", gripper::getSpeed); //Gripper Speed
      //-----Chassis-----
        // dbc.addNumber("Chassis Left Speed", () -> oi.getLeftJoystick() * SubsystemConstants.Chassis.kDrivingSpeedModifier.get());
        // dbc.addNumber("Chassis Right Speed", () -> oi.getRightJoystick() * SubsystemConstants.Chassis.kDrivingSpeedModifier.get());
        // dbc.addNumber("Chassis Mean Speed", this::getChassisSpeed);
      //-----PID-----
        // dbc.addNumber("PID X value", () -> ImageProccessingSuppliers.twoReflectivesCenter.get());
        // dbc.addNumber("PID reflective 0 X value ", () -> ImageProccessingSuppliers.Reflective0.centerXSupplier.get());
        // dbc.addNumber("PID reflective 1 X value ", () -> ImageProccessingSuppliers.Reflective1.centerXSupplier.get());
        // dbc.addBoolean("PID reflective 0 seen", () -> ImageProccessingSuppliers.Reflective0.isUpdated.get());
        // dbc.addBoolean("PID reflective 1 seen", () -> ImageProccessingSuppliers.Reflective1.isUpdated.get());
      //-----Smart P-----
        // dbc.addBoolean("Smart P", () -> DefaultDrive.smartPMode);
        // dbc.addNumber("Smart P Fix", () -> DefaultDrive.fixSupplier.get());
        // dbc.addNumber("Smart P SetPoint", () -> DefaultDrive.setPoint.get());
        // dbc.addBoolean("smart P", () -> DefaultDrive.smartPMode);
        // dbc.addNumber("Smart P Left Speed", () -> DefaultDrive.leftSmartSpeed);
        // dbc.addNumber("Smart P Right Speed", () -> DefaultDrive.rightSmartSpeed);
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
    enableConfig();
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    dbc.update();
  }

  @Override
  public void teleopInit() {
    enableConfig();
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
    return ((OI.getLeftJoystick() * ChassisConsts.kDrivingSpeedModifier.get())
     + (OI.getRightJoystick() * ChassisConsts.kDrivingSpeedModifier.get()))
     /2;
  }

  public void initConfig()
  {
    ElevatorMethods.initConfig();
  }

  public void enableConfig()
  {
    ElevatorMethods.enableConfig();
    GripperMovementMethods.enableConfig();
    HatchSystemMethods.enableConfig();
    ChassisMethods.enableConfig();
  }

}
