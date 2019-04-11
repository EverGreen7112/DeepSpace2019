/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.Preferences;

/**
 * Add your docs here.
 */
public class SwitchHandler extends ConstantHandlerEG {
    public static Supplier<Boolean> addButtonSwitch(String name)
    {
        return ConstantHandlerEG.addConstantBool(String.format("Button Switches - {0}", name), true);
    }
    
    public static Supplier<Boolean> addButtonSwitch(String name, boolean defaultValue)
    {
        return ConstantHandlerEG.addConstantBool(String.format("Button Switches - {0}", name), defaultValue);
    }

    public static void toggle(String key)
    {
        if(Preferences.getInstance().containsKey(key))
        {
            Preferences.getInstance().putBoolean(key, 
                !Preferences.getInstance().getBoolean(String.format("Button Switches - ", key), true));
        }
    }

    public static void disable(String key)
    {
        if(Preferences.getInstance().containsKey(key))
        {
            Preferences.getInstance().putBoolean(key, false);
        }
    }

    public static void enable(String key)
    {
        if(Preferences.getInstance().containsKey(key))
        {
            Preferences.getInstance().putBoolean(key, true);
        }
    }
    

}
