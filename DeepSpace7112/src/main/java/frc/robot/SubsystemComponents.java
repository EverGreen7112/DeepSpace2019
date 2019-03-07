/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.awt.event.MouseWheelEvent;
import java.util.function.Supplier;

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
            new WPI_TalonSRX(RobotMap.chassisVictorBR), new WPI_TalonSRX(RobotMap.chassisVictorFR));
    }

    /**
    * The elevator subsystem consists of 2 speed controllers inside a speed controller group
    * one encoder on the frame of the motors
    * one microswitch which determines the maximum length the elevator can go mechanicly
    * one optic switch to reset the encoder (refrence point)
    
    */
    public static class Elevator {
        
        public static double getStallSpeed()
        { 
            return //0.2;
                SubsystemConstants.Elevator.kStallMaxMultiplier.get() *
                (SubsystemComponents.Elevator.getElevatorHeight()/SubsystemConstants.Elevator.kEncoderMaxHeight.get()); 
        }
  //^^^ Gets the speed needed at the elevator's maximum height, and multiplies it by the percentage rose so far.
        public static final  SpeedControllerGroup motors = new SpeedControllerGroup (
            new WPI_VictorSPX(RobotMap.elevatorMotorA), new WPI_VictorSPX(RobotMap.elevatorMotorB));
        public static final Encoder encoder = new Encoder(RobotMap.elevatorEncoderA, RobotMap.elevatorEncoderB);
        public static final DigitalInput opticSwitch = new DigitalInput(RobotMap.elevatorOpticSwitch);
        public static final AnalogInput lazerSensor = new AnalogInput(RobotMap.elevatorLazerDistanceSensor);
        /**The boolean for whether or not the encoder was reset yet, because before the first time, it gives incorrect value. */
        public static boolean encoderWasReset = false;
        /**Whether or not the sensors are working properly. 
         * If not, a message will be sent to the drivers , who will have to use the elevator manually.*/
        public static boolean sensorsFunction = true;
        /**The supplier for {@link #sensorsFunction whether or not the sensors work prperly.} */
        public static Supplier<Boolean> sensorsFunctionSupplier = () -> sensorsFunction;
        public static String blockString = "";
        public static Supplier<String> messageSupplier = () -> blockString;
        public static class ElevatorOutOfRangeException extends Exception {
            public ElevatorOutOfRangeException(String message)
            {
                super(message);       
            }
        }
        
        /**The configuration of the elevator's sensors, which must be ran before its Subsystem is created.
         * It inverts the motor, sets the encoder's Distance per pulse by SubsystemConstants, and sets that the encoder was not reset yet.*/
        public static void setupSensors() {
            motors.setInverted(true);
            encoder.setDistancePerPulse(SubsystemConstants.Elevator.kDistancePerPulse.get());
            encoderWasReset = false;
        }

        public static double getElevatorHeightByEncoder(){
            return encoder.getDistance() + SubsystemConstants.Elevator.kEncoderBonusHeight.get();
        }

        public static double getElevatorHeightByLazer() {
            return (7/0.02)*(lazerSensor.getValue()/600.0) * 100.0;
        }
        
        /**Checks the height of the encoder by the lazer sensor and the encoder, and returns it by the sesnor(s) that make most sense. */
        public static double getElevatorHeight(){
        blockString = "before try block";
            try
            {
                blockString = "in try block";
                if(getElevatorHeightByLazer() < SubsystemConstants.Elevator.kMaxHeight.get()) { //If the lazer sensor shows that the elevator height is permitted:
                    blockString = "in LaserIsPossible block";
                    if(SubsystemComponents.Elevator.encoderWasReset //If the encoder shows the the height is possible and it was reset at least once (as before it's values re invalid):
                    && getElevatorHeightByEncoder() != 0 
                    && getElevatorHeightByEncoder() > SubsystemConstants.Elevator.kEncoderMinHeight.get()
                    && getElevatorHeightByEncoder() < SubsystemConstants.Elevator.kEncoderMaxHeight.get()) {
                        blockString = "Laser and encoder are possible block";
                        return (getElevatorHeightByLazer() + getElevatorHeightByEncoder()) / 2; }//If both of the sensors show a possible height, return the mean of both of their values.
                    else {
                        blockString = "only Laser possible";
                        return getElevatorHeightByLazer(); //If only the lazer sesor returns a possible height, return only its value. 
                   }
                }
                else 
                    blockString = "laser impossibe";
                    if(getElevatorHeightByEncoder() != 0 //If the encoder shows the elevator's height is possible: 
                    && getElevatorHeightByEncoder() > SubsystemConstants.Elevator.kEncoderMinHeight.get()
                    && getElevatorHeightByEncoder() < SubsystemConstants.Elevator.kEncoderMaxHeight.get()
                    && encoderWasReset) {
                        blockString = "laser impossible, encoder not.";
                        return getElevatorHeightByEncoder(); //If only the encoder shows a possible height, return only its value.
                    }
                    else
                    {
                        sensorsFunction = false;
                        throw new ElevatorOutOfRangeException("Elevator sensors send impossible information."); //If none of the sensors show possible values
                    }
            }

            catch(ElevatorOutOfRangeException e)
            {
                return -1;
            }
        }
    }

    /**
     * The Gripper subsystem consists of 2 speed controllers inside a speed controller group, the right motor is inverted
     * The subsystem contains one analog proximity lazer based sensor.*/
    public static class Gripper {
            public static final WPI_VictorSPX motorR = new WPI_VictorSPX(RobotMap.gripperMotorRight);
            public static SpeedControllerGroup motors;        
        public static final AnalogInput lazerSensor = new AnalogInput(RobotMap.gripperAnalogLazerSensor);
            
        /**
         * @return true if a cargo is inside the gripper, false otherwise.
         */
        public static boolean isCargoCaught() {
            return lazerSensor.getVoltage() >= SubsystemConstants.gripper.kVoltageLimit.get();
        }
    }
    
    public static class GripperMovement {
        public static final DoubleSolenoid LockPiston = new DoubleSolenoid (
            RobotMap.gripperMovementLockPistonF, RobotMap.gripperMovementLockPistonR);
        public static final DoubleSolenoid PushPiston = new DoubleSolenoid (
            RobotMap.gripperMovementPushPistonF, RobotMap.gripperMovementPushPistonR);
        public static final DoubleSolenoid MovementPiston = new DoubleSolenoid(
            RobotMap.gripperMovementPistonF, RobotMap.gripperMovementPistonR);
    }
                
    /**
     * The subsystem that controlls the frame that is used to raise the back of the robot during the climbing process.
     * The subsystem contains one speed controller and 2 limit switches that indicate the topmost and bottommost points that the frame goes.
     */
    public static class ClimbingFrame{
                public static final WPI_VictorSPX motor = new WPI_VictorSPX(RobotMap.frameTalon);
                public static final DigitalInput bottomLimiter = new DigitalInput(RobotMap.frameBottomLimiter);
    }
       
    /**
     * The subsystem that controlls the movement of the robot during the climbing process.
     * The subsystem contains one speed controller.
     */
    public static class ClimbingMovement {
            public static final SpeedController motor = new WPI_VictorSPX(RobotMap.climbingMovementMotor);
    }
}
