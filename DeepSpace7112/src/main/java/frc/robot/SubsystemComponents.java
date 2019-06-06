/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.spikes2212.genericsubsystems.basicSubsystem.BasicSubsystem;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import frc.robot.RobotMap;


/**
 * This class contains the objects for each of the Robot's motors and sensors, 
 * divided into interfaces matching the subsystem they're a part of.
 * <p>
 * It is used to keep all the components organized and easily changeable.
 * <p>
 * This is where the components are called from in the initialization of the {@link BasicSubsystem} 
 * objects, in the {@link Robot Robot class}. </p>
 */
public interface SubsystemComponents extends RobotMap {
    
    /**The DriveTrain is the part that controls the robot's wheels.
     * It consists of two sets of motors, a pair for each side of the robot - two left ones and two right ones.
     */
    public static interface Chassis {
        /**The pair of motors that control the left wheels of the robot. */
        public static final SpeedControllerGroup leftMotorGroup = new SpeedControllerGroup (
            new WPI_VictorSPX(MotorPorts.chassisBackLeft), new WPI_VictorSPX(MotorPorts.chassisFrontLeft));
        /**The pair og motors that control the right wheels of the robot. */
        public static final SpeedControllerGroup rightMotorGroup = new SpeedControllerGroup (
            new WPI_VictorSPX(MotorPorts.chassisBackRight), new WPI_VictorSPX(MotorPorts.chassisFrontRight));
    }

    /**
    * The elevator subsystem consists of 2 speed controllers inside a speed controller group
    * one encoder on the frame of the motors
    * one microswitch which determines the maximum length the elevator can go mechanicly
    * one optic switch to reset the encoder (refrence point)    
    */
    public static interface Elevator {

        public static WPI_VictorSPX 
            motorA = new WPI_VictorSPX(MotorPorts.ElevatorA),
            motorB = new WPI_VictorSPX(MotorPorts.ElevatorB);
        public static SpeedControllerGroup motors = new SpeedControllerGroup(motorA, motorB);
        /**The motors that controls the elevator's movement */
        public static final Encoder encoder = new Encoder(EncoderPorts.elevatorA, EncoderPorts.elevatorB);
        public static final DigitalInput opticSwitch = new DigitalInput(SwitchPorts.elevator);
        public static final AnalogInput lazerSensor = new AnalogInput(AnalogPorts.elevator);
    }

    /**
     * The Gripper subsystem consists of 2 speed controllers inside a speed controller group, the right motor is inverted
     * The subsystem contains one analog proximity lazer based sensor.*/
    public static class Gripper {
            public static final WPI_VictorSPX motorR = new WPI_VictorSPX(MotorPorts.gripperRight);
            public static final WPI_VictorSPX motorL = new WPI_VictorSPX(MotorPorts.gripperLeft);
            public static SpeedControllerGroup motors = new SpeedControllerGroup(motorR, motorL);;        
            public static final AnalogInput lazerSensor = new AnalogInput(AnalogPorts.gripper);
            public static final DoubleSolenoid PushPiston = new DoubleSolenoid (
                PistonsPorts.pushForward,
                PistonsPorts.pushReverse);
            public static final DoubleSolenoid toungePiston = new DoubleSolenoid (
                PistonsPorts.toungeForward,
                PistonsPorts.toungeReverse);
        /**
         * @return true if a cargo is inside the gripper, false otherwise.
         */
        public static boolean isCargoCaught() {
            return lazerSensor.getVoltage() >= SubsystemConstants.GripperConsts.kVoltageLimit.get();
        }
    }
    
    public static class GripperMovement {
        public static final DoubleSolenoid MovementPiston = new DoubleSolenoid(
            PistonsPorts.gripperMovementForward, PistonsPorts.gripperMovementReverse);
    }
}
