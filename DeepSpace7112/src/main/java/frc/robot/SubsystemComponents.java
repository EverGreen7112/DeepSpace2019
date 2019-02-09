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

/**
 * Add your docs here.
 */
public class SubsystemComponents {
        
        public static class DriveTrain {
                public static final SpeedControllerGroup leftMotorGroup = new SpeedControllerGroup(new WPI_TalonSRX(RobotMap.chassisTalonBL), new WPI_TalonSRX(RobotMap.chassisTalonFL));
                public static final SpeedControllerGroup rightMotorGroup = new SpeedControllerGroup(new WPI_TalonSRX(RobotMap.chassisTalonBR), new WPI_TalonSRX(RobotMap.chassisTalonFR));
        }
        
        /**
         * The subsystem that controlls the shaft that is used to raise the back of the robot during the climbing process.
         * The subsystem contains one speed controller and 2 limit switches that indicate the topmost and bottommost points that the shaft goes.
         */
        public static class ClimbingShaft{
                public static final SpeedController Motor = new WPI_TalonSRX(RobotMap.shaftTalon);
                public static final DigitalInput bottomLimiter = new DigitalInput(RobotMap.shaftBottomLimiter);
                public static final DigitalInput topLimiter = new DigitalInput(RobotMap.shaftTopLimiter);
    }
}
