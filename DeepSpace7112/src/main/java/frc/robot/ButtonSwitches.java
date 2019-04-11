/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.function.Supplier;

/**
 * Add your docs here.
 */
public class ButtonSwitches {
    public static interface Gripper
    {
        public static Supplier<Boolean>
            gripperIn = ConstantHandlerEG.addConstantBool("Gripper In Enable", true),
            gripperOut = ConstantHandlerEG.addConstantBool("Gripper Out enable", true),
            togglePistons = ConstantHandlerEG.addConstantBool("Toggle Push Pistons Enabled", true);
    }

    public static interface GripperMovement
    {
        public static Supplier<Boolean> 
            flipGripper = ConstantHandlerEG.addConstantBool("Flip Gripper Button Enabled", true); 
    }

    public static interface Elevator
    {
        public static Supplier<Boolean>
            topHatch = ConstantHandlerEG.addConstantBool("Top Hatch Button Enabled", true),
            middleHatch = ConstantHandlerEG.addConstantBool("Middle Hatch Button Enabled", true),
            bottomHatch = ConstantHandlerEG.addConstantBool("Bottom Hatch Button Enabled", true),
            topCargo = ConstantHandlerEG.addConstantBool("Top Cargo Button Enabled", true),
            middleCargo = ConstantHandlerEG.addConstantBool("Middle Cargo Button Enabled", true),
            bottomCargo = ConstantHandlerEG.addConstantBool("Bottom Button Enabled", true),
            lockSpeed = ConstantHandlerEG.addConstantBool("SpeedLock Button Enabled", true);
    }

    public static interface Chassis
    {
        public static Supplier<Boolean>
            fastAdjust = ConstantHandlerEG.addConstantBool("Fast Adjust Button Enabled", true),
            slowAdjust = ConstantHandlerEG.addConstantBool("Slow Adjust Button Enabled", true);
    }

    public static interface smartP
    {
        public static Supplier<Boolean> 
            toggle = ConstantHandlerEG.addConstantBool("Toggle Smart P Button Enabled", true);
    }
    


}
