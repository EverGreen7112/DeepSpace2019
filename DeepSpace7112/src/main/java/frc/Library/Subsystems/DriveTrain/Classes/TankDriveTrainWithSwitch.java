/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.Library.Subsystems.DriveTrain.Classes;

import java.util.function.Consumer;

import com.spikes2212.genericsubsystems.drivetrains.TankDrivetrain;

import frc.Library.Exceptions;
import frc.Library.OI.Switches.Classes.Switch;
import frc.Library.OI.Switches.Classes.SwitchHandler;

/**
 * Add your docs here.
 */
public class TankDriveTrainWithSwitch extends TankDrivetrain implements Exceptions{
    Switch switchOn;
    public TankDriveTrainWithSwitch(Consumer<Double> controlLeft, Consumer<Double> ControlRight, 
        String name)
    {
        super(controlLeft, ControlRight);
        switchOn = SwitchHandler.addSwitch(name);
    }

    public TankDriveTrainWithSwitch(Consumer<Double> controlLeft, Consumer<Double> ControlRight, 
        String name, boolean defaultValue)
    {
        super(controlLeft, ControlRight);
        switchOn = SwitchHandler.addSwitch(name, defaultValue);
    }

    @Override
    public void setLeft(double speedLeft) {
        if(switchOn.get())
        {
            
            if(speedLeft < -1)
            {
                throw new IlleagalSpeedInputException(getName() + " Subsystem's speed supplier returns values lower than -1");
            }
            
            else if(speedLeft > 1)
            {
                throw new IlleagalSpeedInputException(getName() + " Subsystem's speed supplier returns values higher than 1");
            }
            
            else
            {
                super.setLeft(speedLeft);
            }
        }
    }

    @Override
    public void setRight(double speedRight) {
        if(switchOn.get())
        {
            
            if(speedRight < -1)
            {
                throw new IlleagalSpeedInputException(getName() + " Subsystem's speed supplier returns values lower than -1");
            }
            
            else if(speedRight > 1)
            {
                throw new IlleagalSpeedInputException(getName() + " Subsystem's speed supplier returns values higher than 1");
            }
            
            else
            {
                super.setRight(speedRight);
            }
        }
    }
}
