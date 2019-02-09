/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;


/**
 * Add your docs here.
 */
public class SubsystemComponents {

    public static class DriveTrain {
        public static final SpeedControllerGroup leftMotorGroup = new SpeedControllerGroup(new WPI_TalonSRX(RobotMap.chassisTalonBL), new WPI_TalonSRX(RobotMap.chassisTalonFL));
        public static final SpeedControllerGroup rightMotorGroup = new SpeedControllerGroup(new WPI_TalonSRX(RobotMap.chassisTalonBR), new WPI_TalonSRX(RobotMap.chassisTalonFR));
        }

        /**
    * The elevator subsystem consists of 2 speed controllers inside a speed controller group
    * one encoder on the shaft of the motors
    * one microswitch which determines the maximum length the elevator can go mechanicly
    * one optic switch to reset the encoder (refrence point)
    */
    public static class Elevator{
        public static final SpeedControllerGroup motors = new SpeedControllerGroup(new WPI_TalonSRX(RobotMap.elevatorTalonL), new WPI_TalonSRX(RobotMap.elevatorTalonR));
        public static final Encoder encoder = new Encoder(RobotMap.elevatorEncoderA, RobotMap.elevatorEncoderB);
        public static final DigitalInput microswitch = new DigitalInput(RobotMap.elevatorMicroswitch);
        public static final DigitalInput opticSwitch = new DigitalInput(RobotMap.elevatorOpticSwitch);
    }


    /**
    * The Gripper subsystem consists of 2 speed controllers inside a speed controller group, the right motor is inverted
    * The subsystem contains one analog proximity lazer based sensor.
    */
    public static class Gripper {
            private static final SpeedController motorL = new WPI_VictorSPX(RobotMap.gripperMotorLeft);
            private static final SpeedController motorR = new WPI_VictorSPX(RobotMap.gripperMotorRight);
            

            public static SpeedControllerGroup Motors;

            /**
             * The method is required to be called before the gripper subsystem is created.
             * The method inverts the right motor, then creates the speedControllerGroup for the gripper.
             */
            public static void createMotorGroup() {
                motorR.setInverted(true);
                Motors = new SpeedControllerGroup(motorL,motorR);
            }
            
            public static final AnalogInput lazerSensor = new AnalogInput(RobotMap.gripperAnalogLazerSensor);
            
            /**
             * 
             * @return true if a cargo is inside the gripper, false otherwise
             */
            public static boolean isCargoCought(){ //WIP
                return false;
            }
	}
}
