/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.HelperClasses;

import java.util.function.Consumer;
import java.util.function.Supplier;

import com.spikes2212.dashboard.DashBoardController;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * A generic subsystem which uses pistons for movement.
 * It can 
 */
public class PistonSubsystem extends Subsystem {
    private Consumer<Value> pistonSetter;
    public Supplier<Value> state;
    public Supplier<Boolean> isReversed;
    public Supplier<Boolean> isForward;
    public Supplier<Boolean> isOff;

    public PistonSubsystem(Consumer<Value> pistonSetter, Supplier<Value> stateSupplier, Value initState)
    {
        pistonSetter.accept(initState);
        this.pistonSetter = pistonSetter;
        state = stateSupplier;
    }

    public PistonSubsystem(String subsystemName, String name, Consumer<Value> pistonSetter, Supplier<Value> stateSupplier, Value initState)
    {
        pistonSetter.accept(initState);
        this.pistonSetter = pistonSetter;
        state = stateSupplier;
        new DashBoardController().addString (
            String.format("{0} - {1} - Value", subsystemName, name),
            () -> //A supplier which returns a string representation of the current state of the piston.
            {
                if(isReversed())
                {
                    return "Reversed";
                }

                else if(isForward())
                {
                    return "Forward";
                }

                else
                {
                    return "Off";
                }
            });
    }

    public void toggle()
    {
        if(isForward())
        {
            setReverse();
        }

        else
        {
            setForward();
        }
    }

    public void setReverse()
    {
        pistonSetter.accept(Value.kReverse);
    }

    public void setForward()
    {
        pistonSetter.accept(Value.kForward);
    }

    public boolean isReversed()
    {
        return state.get().compareTo(Value.kReverse) == 0;
    }

    public boolean isForward()
    {
        
        return state.get().compareTo(Value.kForward) == 0;
    }

    public boolean isOff()
    {
        return state.get().compareTo(Value.kOff) == 0;
    }

    public void set(Value state)
    {
        pistonSetter.accept(state);
    }

    public void initDefaultCommand()
    {
    
    }

}
