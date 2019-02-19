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
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;


/**
 * Add your docs here.
 */
public class SubsystemComponents {
    
    /**The DriveTrain is the part that controls the robot's wheels.
     * It consists of two sets of motors - two left ones and two right ones.
     */
    public static class DriveTrain {
        public static final SpeedControllerGroup leftMotorGroup = new SpeedControllerGroup (
            new WPI_TalonSRX(RobotMap.chassisTalonBL), new WPI_TalonSRX(RobotMap.chassisTalonFL));
        public static final SpeedControllerGroup rightMotorGroup = new SpeedControllerGroup (
            new WPI_TalonSRX(RobotMap.chassisTalonBR), new WPI_TalonSRX(RobotMap.chassisTalonFR));
    }

    /**
    * The elevator subsystem consists of 2 speed controllers inside a speed controller group
    * one encoder on the shaft of the motors
    * one microswitch which determines the maximum length the elevator can go mechanicly
    * one optic switch to reset the encoder (refrence point)
    */
    public static class Elevator {
        public static final SpeedControllerGroup motors = new SpeedControllerGroup(new WPI_TalonSRX(RobotMap.elevatorTalonL), new WPI_TalonSRX(RobotMap.elevatorTalonR));
        public static final Encoder encoder = new Encoder(RobotMap.elevatorEncoderA, RobotMap.elevatorEncoderB);
        public static final DigitalInput microswitch = new DigitalInput(RobotMap.elevatorMicroswitch);
        public static final DigitalInput opticSwitch = new DigitalInput(RobotMap.elevatorOpticSwitch);
        public static final AnalogInput lazerSensor = new AnalogInput(RobotMap.elevatorLazerDistanceSensor);

        public static double getElevatorHeightByLazer(){
                return (SubsystemConstants.Elevator.kElevatorMaxHeight.get() / 20) * lazerSensor.getValue();
        }

        public static double getElevatorHeight(){
            if(getElevatorHeightByLazer() < SubsystemConstants.Elevator.kElevatorMaxHeight.get()){
                if(encoder.getDistance() != 0 && encoder.getDistance() > SubsystemConstants.Elevator.kElevatorEncoderMinHeight.get()
                && encoder.getDistance() < SubsystemConstants.Elevator.kElevatorEncoderMaxHeight.get()){
                    return (getElevatorHeightByLazer() + encoder.getDistance()) / 2;
                }
                else return getElevatorHeightByLazer();
            }
            else 
                if(encoder.getDistance() != 0 && encoder.getDistance() > SubsystemConstants.Elevator.kElevatorEncoderMinHeight.get()
                    && encoder.getDistance() < SubsystemConstants.Elevator.kElevatorEncoderMaxHeight.get()){
                        return encoder.getDistance();
                }
            else
                return -1;
        }
}

    /**
    * The Gripper subsystem consists of 2 speed controllers inside a speed controller group, the right motor is inverted
    * The subsystem contains one analog proximity lazer based sensor.
    */
    public static class Gripper {
            private static final SpeedController motorL = new WPI_VictorSPX(RobotMap.gripperMotorLeft);
            private static final SpeedController motorR = new WPI_VictorSPX(RobotMap.gripperMotorRight);
            public static final DoubleSolenoid gripperLeftPiston = new DoubleSolenoid(1, 2); //Left gripper piston setting the port
            public static final DoubleSolenoid gripperRightPiston = new DoubleSolenoid(3, 4); //Right griper piston setting the port
        

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
             * @return true if a cargo is inside the gripper, false otherwise.
             */
            public static boolean isCargoCaught() {
                return lazerSensor.getVoltage() >= SubsystemConstants.gripper.kVoltageLimit.get();
            }
    }
    
    public static class GripperMovement{
        public static final DoubleSolenoid gripperMovementLeftPiston = new DoubleSolenoid(5, 6); //Left gripper movement piston setting the port
        public static final DoubleSolenoid gripperMovementRightPiston = new DoubleSolenoid(7, 8); //Right gripper movement piston setting the port    
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
       
    /**
     * The subsystem that controlls the movement of the robot during the climbing process.
     * The subsystem contains one speed controller.
     */
    public static class ClimbingMovement {
            public static final SpeedController Motor = new WPI_TalonSRX(RobotMap.climbingMovementTalon);
    }

}
