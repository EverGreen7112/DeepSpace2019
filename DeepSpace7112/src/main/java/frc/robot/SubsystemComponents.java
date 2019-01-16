/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;


/**
 * Add your docs here.
 */
public class SubsystemComponents {

    public static class DriveTrain {
            public static final SpeedControllerGroup leftMotorGroup = new SpeedControllerGroup(new WPI_TalonSRX(RobotMap.chassisTalonBL), new WPI_TalonSRX(RobotMap.chassisTalonFL));
            public static final SpeedControllerGroup rightMotorGroup = new SpeedControllerGroup(new WPI_TalonSRX(RobotMap.chassisTalonBR), new WPI_TalonSRX(RobotMap.chassisTalonFR));
    }
    public static class Gripper {
            public static final SpeedController gripperMotorLeft = new VictorSP(RobotMap.gripperMotorLeft);
            public static final SpeedController gripperMotorRight = new WPI_TalonSRX(RobotMap.gripperMotorRight);
            //public static final SpeedControllerGroup gripperMotors = new SpeedControllerGroup(new VictorSP(RobotMap.gripperMotorLeft), new WPI_TalonSRX(RobotMap.gripperMotorRight));
            public static final DigitalInput gripperMicroswitch = new DigitalInput(RobotMap.gripperMicroswitch);
	}
    
        
}
