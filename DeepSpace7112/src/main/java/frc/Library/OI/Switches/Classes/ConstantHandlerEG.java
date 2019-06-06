/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.Library.OI.Switches.Classes;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;

import edu.wpi.first.wpilibj.Preferences;

/**
 * A class to expand upon spikesllib's {@link ConstantHandler}, which contains support only for String,
 * int, and double constants. This class adds support for boolean constants, as well.
 */
public class ConstantHandlerEG extends ConstantHandler {
    
    public static Map<String, Boolean> boolMap = new HashMap<String, Boolean>();
    /**
     * Puts a boolean variable with input name and value in the prefrences section of the shuffleboard,
     * such that it can be changed there to affect the execution, and returns a supplier of the input value.
     * @param name - The name of the variable in the shuffleboard.
     * @param value - The default value of the variabel
     * @return
     */
    public static Supplier<Boolean> addConstantBool(String name, boolean value)
    {
        if(!Preferences.getInstance().containsKey(name))
        {
            Preferences.getInstance().putBoolean(name, value);
        }

        boolMap.put(name, value);

        return () -> Preferences.getInstance().getBoolean(name, value);
    }


    /**Resets all the  */
    public static void reset()
    {
        ConstantHandler.reset();
        for (String key : boolMap.keySet()) {
            Preferences.getInstance().putBoolean(key, boolMap.get(key));
        }

    }
}
