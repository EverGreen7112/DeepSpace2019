/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

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
         * The subsystem that controlls the folding of the gripper.
         * The subsystem contains one speed controller and limit switches for the points where the gripper is folded, and open.
         */
        public static class GripperMovement {
                public static final DigitalInput 
                topMicroswitch = new DigitalInput(RobotMap.gripperMovementTopMicroswitch),
                bottomMicroSwitch = new DigitalInput(RobotMap.gripperMovementBottomMicroswitch);
                public static final SpeedController motor = new WPI_VictorSPX(RobotMap.gripperMovementVictor);              
        }

}
