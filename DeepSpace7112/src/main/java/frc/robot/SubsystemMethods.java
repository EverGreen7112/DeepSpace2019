/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.Library.OI.Switches.Classes.SwitchHandler;
import frc.robot.CommandList.ElevatorCommands;
import frc.robot.SubsystemConstants.ElevatorConsts;
import frc.robot.commands.Chassis.DefaultDrive;
import frc.robot.commands.Elevator.ElevatorDefault;
import frc.robot.commands.Elevator.MoveElevatorToRocket;

/**
 * Add your docs here.
 */
public interface SubsystemMethods {
    public static class ElevatorMethods implements Exceptions, SubsystemComponents.Elevator, ElevatorCommands, ElevatorConsts
    {
        /**The configuration of the elevator's sensors, which must be ran before its {@link BasicSubsystem} instance
         * is created in {@link Robot#robotInit()}. It inverts the motor, sets the encoder's Distance 
         * per pulse by {@link SubsystemConstants} and sets that the encoder was not reset yet.*/
        public static void initConfig() {
            motors.setInverted(true);
            encoder.setDistancePerPulse(SubsystemConstants.ElevatorConsts.Sensors.kDistancePerPulse.get());
            ElevatorDefault.encoderWasReset = false;
        }

        public static void enableConfig()
        {
            SwitchHandler.disable(ElevatorDefault.speedLock);
        }

        /**
         * @return - The elevator's height as determined by the laser sensor's value.
         */
        private static double getElevatorHeightByLaser() { 
            //By the laser's linear function ax + b, as we calaculated.
            return 
             Laser.kLaserFunctionSlope.get() //a
             * lazerSensor.getVoltage() // * x
             + Laser.kLaserFunctionB.get(); // + b
        }


        
        public static double getElevatorHeightByEncoder(){
            return encoder.getDistance() + SubsystemConstants.ElevatorConsts.ElevatorLimitHeights.kBonusEncoderHeight.get();
        }

        public static double getElevatorHeight()
        {
            try
            {
                if(getElevatorHeightByLaser() < SubsystemConstants.ElevatorConsts.ElevatorLimitHeights.kMaxHeight.get()) { 
                    if( //IF
                    ElevatorDefault.encoderWasReset //The encoder was set at least once
                    && getElevatorHeightByEncoder() != 0 //AND the encoder's output is not 0
                    && getElevatorHeightByEncoder() > SubsystemConstants.ElevatorConsts.ElevatorLimitHeights.kEncoderMinHeight.get() //AND it does not output lower than it should be able to
                    && getElevatorHeightByEncoder() < SubsystemConstants.ElevatorConsts.ElevatorLimitHeights.kEncoderMaxHeight.get()) //AND it does not output higher than it should be able to
                    {
                        SwitchHandler.enable(MoveElevatorToRocket.sensorsFunction);
                        return (getElevatorHeightByLaser() + getElevatorHeightByEncoder()) / 2; }//If both of the sensors show a possible height, return the mean of both of their values.
                    else {
                        return getElevatorHeightByLaser(); //If only the lazer sesor returns a possible height, return only its value. 
                   }
                }
                else 
                    if(getElevatorHeightByEncoder() != 0 //If the encoder shows the elevator's height is possible: 
                    && getElevatorHeightByEncoder() > SubsystemConstants.ElevatorConsts.ElevatorLimitHeights.kEncoderMinHeight.get()
                    && getElevatorHeightByEncoder() < SubsystemConstants.ElevatorConsts.ElevatorLimitHeights.kEncoderMaxHeight.get()
                    && ElevatorDefault.encoderWasReset) {
                        return getElevatorHeightByEncoder(); //If only the encoder shows a possible height, return only its value.
                    }

                    else
                    {
                        throw new ElevatorOutOfRangeException("Elevator sensors send impossible information."); //If none of the sensors show possible values
                    }
            }

            catch(ElevatorOutOfRangeException e)
            {
                SwitchHandler.disable(MoveElevatorToRocket.sensorsFunction); //Disable the automatic move commands - Their distanc esupplier outpiuuts wrong values, and so would cause a malfunction.
                return -1;
            }
        }
    }

    public class GripperMovementMethods
    {
        public static void enableConfig()
        {
            Robot.gripperMovement.setForward();
        }
    }

    public class HatchSystemMethods
    {
        public static void enableConfig()
        {
            SubsystemComponents.Gripper.PushPiston.set(Value.kForward);
            SubsystemComponents.Gripper.toungePiston.set(Value.kReverse);
        }
    }

    public class ChassisMethods
    {
        public static void enableConfig()
        {
            SwitchHandler.disable(DefaultDrive.smartPMode);
        }
    }
}
