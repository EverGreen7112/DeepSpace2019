/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.Library.Subsystems.BasicSubsystems.Classes;

import java.util.function.Consumer;
import java.util.function.Predicate;

import com.spikes2212.genericsubsystems.basicSubsystem.BasicSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import frc.Library.Exceptions;
import frc.Library.OI.Switches.Classes.Switch;
import frc.Library.OI.Switches.Classes.SwitchHandler;

/**
 * 
 */
public class BasicSubsystemWithSwitch extends BasicSubsystem implements Exceptions {
    Switch switchOn;
    public BasicSubsystemWithSwitch(Consumer<Double> speedConsumer, Predicate<Double> canMove, String name)
    {
        super(name, speedConsumer, canMove);
        switchOn = SwitchHandler.addSwitch("Subsystem Switches - " + name, true);
    }

    public BasicSubsystemWithSwitch(Consumer<Double> speedConsumer, Predicate<Double> canMove, boolean switchDefault, String name)
    {
        super(name, speedConsumer, canMove);
        switchOn = SwitchHandler.addSwitch("Subsystem Switches - " + name, switchDefault);
    }


    @Override
    public void move(double speed) throws IlleagalSpeedInputException {
        if(switchOn.get())
        {
            if(speed < -1)
            {
                throw new IlleagalSpeedInputException(getName() + " Subsystem's speed supplier returns values lower than -1");
            }
            
            else if(speed > 1)
            {
                throw new IlleagalSpeedInputException(getName() + " Subsystem's speed supplier returns values higher than 1");
            }
            
            else
            {
                super.move(speed);
            }
        }
    }

    @Override
    public void setDefaultCommand(Command defaultCommand) {
        if(switchOn.get())
        {
            super.setDefaultCommand(defaultCommand);
        }
    }

    @Override
    public void initDefaultCommand() {
        if(switchOn.get())
        {
            super.initDefaultCommand();
        }
    }
}
