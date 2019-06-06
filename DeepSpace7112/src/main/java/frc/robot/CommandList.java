/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.spikes2212.genericsubsystems.basicSubsystem.commands.MoveBasicSubsystem;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTank;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.Library.OI.Switches.Commands.ToggleSwitch;
import frc.Library.Subsystems.BasicSubsystems.Commands.MoveBasicSubsystemWithSwitch;
import frc.Library.Subsystems.DriveTrain.Commands.DriveTankWithSwitch;
import frc.Library.Subsystems.PistonSubsystems.Commands.SetPistonSubsystem;
import frc.Library.Subsystems.PistonSubsystems.Commands.TogglePistonSubsystem;
import frc.robot.commands.Chassis.DefaultDrive;
import frc.robot.commands.Elevator.MoveElevatorToRocket;
import frc.robot.commands.Elevator.ToggleSpeedLock;
import frc.robot.commands.Elevator.MoveElevatorToRocket.Objective;
import frc.robot.commands.GripperMovement.FlipGripper;

/**
 * Add your docs here.
 */
public interface CommandList {
    public static interface GripperCommands
    {
        /**The command to catch cargo - causes the gripper's wheels to spin inwards, making everything 
         * near move unto the gripper.*/
        public static final MoveBasicSubsystem gripperCatch = new MoveBasicSubsystemWithSwitch(Robot.gripper, SubsystemConstants.GripperConsts.kGripperInSpeed, "Gripper - Command Switches - Catch");
        /**The command to release the cargo - causes the gripper's wheels to spin outwards, causing anything
         * caught in the gripper to spin out of it with momentum.*/
        MoveBasicSubsystem gripperRelease = new MoveBasicSubsystemWithSwitch(Robot.gripper, SubsystemConstants.GripperConsts.kGripperOutSpeed, "Gripper - Command Switches - Release"); 
    }

    public static interface HatchSystemCommands
    {
        /**The command to toggle the push pistons of the hatch system */
        TogglePistonSubsystem toggleHatch = new TogglePistonSubsystem(Robot.hatch, "Hatch System - Push Pistons - Toggle Command");
        TogglePistonSubsystem toggleTounge = new TogglePistonSubsystem(Robot.tounge, "Hatch System - Tounge Pistons - Toggle Command");
        
        SetPistonSubsystem openHatch = new SetPistonSubsystem(Robot.hatch, () -> Value.kForward, "Hatch System - Push Pistons - Open Command");
        SetPistonSubsystem closeHatch = new SetPistonSubsystem(Robot.hatch, () -> Value.kReverse, "Hatch System - Push Pistons - Close Command");

        SetPistonSubsystem openTounge = new SetPistonSubsystem(Robot.hatch, () -> Value.kForward, "Hatch System - Tounge Pistons - Open Command");
        SetPistonSubsystem closeTounge = new SetPistonSubsystem(Robot.hatch, () -> Value.kReverse, "Tounge Command");
    }

    public static interface GripperMovementCommands
    {
        FlipGripper foldGripper = new FlipGripper();
    }

    /**The commands executed by the elevator: the targeted movements to each level of the rocket, and the 
     * toggling of its SpeedLock mode.*/
    public static interface ElevatorCommands
    {
        MoveElevatorToRocket moveToBottomHatch = new MoveElevatorToRocket(Objective.kBottomHatch);
        MoveElevatorToRocket moveToMiddleHatch = new MoveElevatorToRocket(Objective.kMiddleHatch);
        MoveElevatorToRocket moveToTopHatch = new MoveElevatorToRocket(Objective.kTopHatch);
        MoveElevatorToRocket moveToBottomCargo = new MoveElevatorToRocket(Objective.kBottomCargo);
        MoveElevatorToRocket moveToMiddleCargo = new MoveElevatorToRocket(Objective.kMiddleCargo);
        MoveElevatorToRocket moveToTopCargo = new MoveElevatorToRocket(Objective.kBottomCargo);

        ToggleSpeedLock lockSpeed = new ToggleSpeedLock();
    }

    public static interface ChassisCommands
    {
        DriveTank fastDrive = new DriveTankWithSwitch(
            Robot.drivetrain, 
            () -> OI.getLeftJoystick() * SubsystemConstants.ChassisConsts.kFastSpeedModifier.get(),
            () -> OI.getRightJoystick() * SubsystemConstants.ChassisConsts.kFastSpeedModifier.get(),
            "Chassis - Command Switches - Fast Drive");

        DriveTank slowDrive = new DriveTankWithSwitch(
            Robot.drivetrain, 
            () -> OI.getLeftJoystick() * SubsystemConstants.ChassisConsts.kSlowSpeedModifier.get(),
            () -> OI.getRightJoystick() * SubsystemConstants.ChassisConsts.kSlowSpeedModifier.get(),
            "Chassis - Command Switches - Fast Drive");

        ToggleSwitch toggleSmartP = new ToggleSwitch("Chassis - Command Switches - Toggle Smart P", DefaultDrive.smartPMode);
    }
}
