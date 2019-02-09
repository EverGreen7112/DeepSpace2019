/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

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
            public static void createMotorGroup(){
                motorR.setInverted(true);
                Motors = new SpeedControllerGroup(motorL,motorR);
            }


            public static final AnalogInput lazersensor = new AnalogInput(RobotMap.gripperAnalogLazerSensor);
            
            /**
             * 
             * @return true if a cargo is inside the gripper, false otherwise
             */
            public static boolean isCargoCought(){ //WIP
                return false;
            }
	}
}