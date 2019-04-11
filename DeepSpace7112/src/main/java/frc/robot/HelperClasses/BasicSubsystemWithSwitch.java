/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.HelperClasses;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

import com.spikes2212.genericsubsystems.basicSubsystem.BasicSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.ConstantHandlerEG;

/**
 * Add your docs here.
 */
public class BasicSubsystemWithSwitch extends BasicSubsystem{
    Supplier<Boolean> switchOn;
    public BasicSubsystemWithSwitch(String name, Consumer<Double> speedConsumer, Predicate<Double> canMove)
    {
        super(name, speedConsumer, canMove);
        switchOn = ConstantHandlerEG.addConstantBool("Subsystem Switches - " + name, true);
    }

    public BasicSubsystemWithSwitch(String name, Consumer<Double> speedConsumer, Predicate<Double> canMove, boolean switchDefault)
    {
        super(name, speedConsumer, canMove);
        switchOn = ConstantHandlerEG.addConstantBool("Subsystem Switches - " + name, switchDefault);
    }

    @Override
    public void move(double speed) {
        if(switchOn.get())
        {
            super.move(speed);
        }
    }

    @Override
    public void setDefaultCommand(Command defaultCommand) {
        if(switchOn.get())
        {
            super.setDefaultCommand(defaultCommand);
        }
    }

}
