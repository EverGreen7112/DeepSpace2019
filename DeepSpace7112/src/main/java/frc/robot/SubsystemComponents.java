/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import static org.junit.Assume.assumeNoException;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;


/**
 * This class contains the objects for each of the Robot's motors and sensors, 
 * divided into interfaces matching the subsystem they're a part of.
 * <p>
 * It is used to keep all the components organized and easily changeable.
 * <p>
 * This is where the components are called from in the initialization of the subsystems, 
 * in the {@link Robot Robot class}. </p>
 */
public class SubsystemComponents {
    
    /**The DriveTrain is the part that controls the robot's wheels.
     * It consists of two sets of motors, a pair for each side of the robot - two left ones and two right ones.
     */
    public static class DriveTrain {
        public static final SpeedControllerGroup leftMotorGroup = new SpeedControllerGroup (
            new WPI_VictorSPX(RobotMap.chassisVictorBL), new WPI_VictorSPX(RobotMap.chassisVictorFL));
        public static final SpeedControllerGroup rightMotorGroup = new SpeedControllerGroup (
            new WPI_VictorSPX(RobotMap.chassisVictorBR), new WPI_VictorSPX(RobotMap.chassisVictorFR));
    }

    /**
    * The elevator subsystem consists of 2 speed controllers inside a speed controller group
    * one encoder on the shaft of the motors
    * one microswitch which determines the maximum length the elevator can go mechanicly
    * one optic switch to reset the encoder (refrence point)
    
    */
    public static class Elevator {
        public static final  SpeedControllerGroup motors = new SpeedControllerGroup(new WPI_VictorSPX(RobotMap.elevatorMotorF), new WPI_VictorSPX(RobotMap.elevatorMotorB));
        public static final Encoder encoder = new Encoder(RobotMap.elevatorEncoderA, RobotMap.elevatorEncoderB);
        public static final DigitalInput opticSwitch = new DigitalInput(RobotMap.elevatorOpticSwitch);
        public static final AnalogInput lazerSensor = new AnalogInput(RobotMap.elevatorLazerDistanceSensor);
        /**The boolean for whether or not the encoder was rset yet, because before the first time, it gives incorrect value. */
        public static boolean encoderWasReset;
        public static class ElevatorOutOfRangeException extends Exception {
            public ElevatorOutOfRangeException(String message)
            {
                super(message);       
            }
        }
        
        /**The configuration of the elevator's sensors, which must be ran before its Subsystem is created.
         * It inverts the motor, sets the encoder's Distance per pulse by SubsystemConstants, ans sets that the encoder was not reset yet.
         */
        public static void setupSensors() {
            motors.setInverted(true);
            encoder.setDistancePerPulse(SubsystemConstants.Elevator.kDistancePerPulse.get());
            encoderWasReset = false;
        }

        public static double getElevatorHeightByEncoder(){
            return encoder.getDistance() + SubsystemConstants.Elevator.kEncoderBonusHeight.get();
        }

        public static double getElevatorHeightByLazer(){
                return (SubsystemConstants.Elevator.kMaxHeight.get() / 20) * lazerSensor.getValue();
        }
        
        /**Checks the height of the encoder by the lazer sensor and the encoder, and returns it by the sesnor(s) that make most sense. */
        public static double getElevatorHeight(){
            try
            {
                if(getElevatorHeightByLazer() < SubsystemConstants.Elevator.kMaxHeight.get()) { //If the lazer sensor shows that the elevator height is permitted:
                    if(SubsystemComponents.Elevator.encoderWasReset //If the encoder shows the the height is possible and it was reset at least once (as before it's values re invalid):
                    && getElevatorHeightByEncoder() != 0 
                    && getElevatorHeightByEncoder() > SubsystemConstants.Elevator.kEncoderMinHeight.get()
                    && getElevatorHeightByEncoder() < SubsystemConstants.Elevator.kEncoderMaxHeight.get())
                        return (getElevatorHeightByLazer() + getElevatorHeightByEncoder()) / 2; //If both of the sensors show a possible height, return the mean of both of their values.
                    else return getElevatorHeightByLazer(); //If only the lazer sesor returns a possible height, return only its value. 
                }
                else 
                    if(getElevatorHeightByEncoder() != 0 //If the encoder shows the elevator's height is possible: 
                    && getElevatorHeightByEncoder() > SubsystemConstants.Elevator.kEncoderMinHeight.get()
                    && getElevatorHeightByEncoder() < SubsystemConstants.Elevator.kEncoderMaxHeight.get())
                        return getElevatorHeightByEncoder(); //If only the encoder shows a possible height, return only its value.
                else
                    throw new ElevatorOutOfRangeException("Elevator sensors send impossible information."); //If none of the sensors show possible values
            }

            catch(ElevatorOutOfRangeException e)
            {
                //Add message
                return -1;
            }
        }
    }

    /**
     * The Gripper subsystem consists of 2 speed controllers inside a speed controller group, the right motor is inverted
     * The subsystem contains one analog proximity lazer based sensor.*/
    public static class Gripper {
            private static final WPI_VictorSPX motorL = new WPI_VictorSPX(RobotMap.gripperMotorLeft);
            private static final SpeedController motorR = new WPI_VictorSPX(RobotMap.gripperMotorRight);
            // public static final DoubleSolenoid gripperLeftPiston = new DoubleSolenoid(1, 2); //Left gripper piston setting the port
            // public static final DoubleSolenoid gripperRightPiston = new DoubleSolenoid(3, 4); //Right griper piston setting the port
            public static final SpeedControllerGroup motors = new SpeedControllerGroup(motorL,motorR);

        /**
         * The method is required to be called before the gripper subsystem is created.
         * The method inverts the right motor, then creates the speedControllerGroup for the gripper.
         */
        
        //public static final DoubleSolenoid leftPiston = new DoubleSolenoid (
           // RobotMap.gripperMovementLeftPistonF, RobotMap.gripperMovementLeftPistonR); //Commented since RobotB does not have solenoids/
        //public static final DoubleSolenoid rightPiston = new DoubleSolenoid (
            //RobotMap.gripperMovementRightPistonF, RobotMap.gripperMovementRightPistonR); //Commented since RobotB does not have solenoids/
            
        public static final AnalogInput lazerSensor = new AnalogInput(RobotMap.gripperAnalogLazerSensor);
            
        /**
         * @return true if a cargo is inside the gripper, false otherwise.
         */
        public static boolean isCargoCaught() {
            return lazerSensor.getVoltage() >= SubsystemConstants.gripper.kVoltageLimit.get();
        }
    }
    
    public static class GripperMovement {
       //DoubleSolenoid leftPiston = new DoubleSolenoid (
            //RobotMap.gripperMovementLeftPistonF, RobotMap.gripperMovementLeftPistonR); //Commented since RobotB does not have solenoids/
      //DoubleSolenoid rightPiston = new DoubleSolenoid (
           // RobotMap.gripperMovementRightPistonF, RobotMap.gripperMovementRightPistonR); //Commented since RobotB does not have solenoids/
    }
                
    /**
     * The subsystem that controlls the shaft that is used to raise the back of the robot during the climbing process.
     * The subsystem contains one speed controller and 2 limit switches that indicate the topmost and bottommost points that the shaft goes.
     */
    public static class ClimbingShaft{
                public static final WPI_VictorSPX motor = new WPI_VictorSPX(RobotMap.shaftTalon);
                public static final DigitalInput bottomLimiter = new DigitalInput(RobotMap.shaftBottomLimiter);
    }
       
    /**
     * The subsystem that controlls the movement of the robot during the climbing process.
     * The subsystem contains one speed controller.
     */
    public static class ClimbingMovement {
            public static final SpeedController motor = new WPI_VictorSPX(RobotMap.climbingMovementMotor);
    }

}
