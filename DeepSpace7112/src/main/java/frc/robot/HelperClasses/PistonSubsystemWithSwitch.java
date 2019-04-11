/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.HelperClasses;

import java.util.function.Consumer;
import java.util.function.Supplier;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.ConstantHandlerEG;

/**
 * Add your docs here.
 */
public class PistonSubsystemWithSwitch extends PistonSubsystem{
    Supplier<Boolean> switchOn;

    PistonSubsystemWithSwitch(String subsystemName, String name, Consumer<Value> pistonSetter, Supplier<Value> stateSupplier, Value initState)
    {
      super(subsystemName, name, pistonSetter, stateSupplier, initState);
      switchOn = ConstantHandlerEG.addConstantBool(String.format("Subsystem Switches - {0} - {1)",subsystemName,name), true);
    }

    @Override
    public void toggle() {
        if(switchOn.get())
        {
           super.toggle();
        }
    }

    @Override
    public void setReverse() {
        if(switchOn.get())
        {
            super.setReverse();
        }
    }

    @Override
    public void setForward() {
        if(switchOn.get())
        {
            super.setForward();
        }
    }

    @Override
    public void set(Value state) {
        if(switchOn.get())
        {
            super.set(state);
        }
    }

    
}
