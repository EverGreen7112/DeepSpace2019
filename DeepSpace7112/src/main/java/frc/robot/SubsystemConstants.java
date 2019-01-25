/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;

/**
 * Add your docs here.
 */
public class SubsystemConstants {

    public static interface Elevator{
    public static Supplier<Double> kDistancePerPulse = ConstantHandler.addConstantDouble("Elevator distance per pulse", 1); //temp
    public static Supplier<Double> kRocketBottomHatchHeight = ConstantHandler.addConstantDouble("Rocket bottom hatch hight", 1); //temp
    public static Supplier<Double> kRocketMiddleHatchHeight = ConstantHandler.addConstantDouble("Rocket middle hatch hight", 2); //temp
    public static Supplier<Double> kRocketTopHatchHeight = ConstantHandler.addConstantDouble("Rocket top hatch hight", 3); //temp
    public static Supplier<Double> kElevatorMotorSpeedModifier = ConstantHandler.addConstantDouble("Elevator speed modifier", 0.8); //temp

    public static Supplier<Double> kp = ConstantHandler.addConstantDouble("Elevator - kp", 1); //temp
    public static Supplier<Double> ki = ConstantHandler.addConstantDouble("Elevator - ki", 0.01); //temp
    public static Supplier<Double> kd = ConstantHandler.addConstantDouble("Elevator - kd", 0.1); //temp
    public static Supplier<Double> kTolerance = ConstantHandler.addConstantDouble("Elevator - tolerance", 0.02); //temp
    public static Supplier<Double> kWaitTime = ConstantHandler.addConstantDouble("Elevator - wait time", 1);

    }
}
