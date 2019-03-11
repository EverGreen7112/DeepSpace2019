/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.PortMaps;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.OI;

/**
 * Add your docs here.
 */
public class ButtonMap {
    public static interface gripper
    {
        /**X*/
        public static Supplier<Integer> catchPort = 
            ConstantHandler.addConstantInt("Gripper catch button port (X)", JoystickMap.X);
        /**B*/
        public static  Supplier<Integer> releasePort = 
            ConstantHandler.addConstantInt("Gripper release button port (B)", JoystickMap.B);
        /**RB*/
        public static Supplier<Integer> throwHatch = 
            ConstantHandler.addConstantInt("Gripper throw hatch button port (RB)", JoystickMap.RB);
        /**Y*/
        public static Supplier<Integer> togglePushPistons =
            ConstantHandler.addConstantInt("Toggle push pistons button port (Y)", JoystickMap.Y);
        
        //--------------------Testing--------------------
            /**{@link OI#buttonJ
             * S} - START*/
            public static Supplier<Integer> setPistonForward = 
                ConstantHandler.addConstantInt("Set gripper pistons forward port (START)", JoystickMap.start);
            /**{@link OI#buttonJS} - BACK */
            public static Supplier<Integer> setPistonReverse = 
                ConstantHandler.addConstantInt("Set gripper pistons reverse port (BACK)", JoystickMap.back);
    }

    public static interface GripperMovement
    {
        /**{@link OI#buttonJS} - LB*/
        public static Supplier<Integer> flipGripper = 
            ConstantHandler.addConstantInt("Flip Gripper button port (LB)", JoystickMap.LB);
    }

    public static interface Cameras
    {
        /**{@link OI#switchToA}*/
        public static Supplier<Integer> switchToA =
            ConstantHandler.addConstantInt("Switch to camera A button port (Left Joystick 5)", 5);
        /**{@link OI#switchToB}*/
        public static Supplier<Integer> switchToB =
            ConstantHandler.addConstantInt("Switch to camera B button port (Left Joystick 6)", 6);
    }

    public static interface PID
    {
        /**{@link OI#straighten} */
        // public static Supplier<Integer> straighten =
        //     ConstantHandler.addConstantInt("PID loop button port (LB)", JoystickMap.LB); //Button currently taken
    }

    public static interface Elevator
    {
        //--------------------Testing--------------------
            public static Supplier<Integer> setStall =
                ConstantHandler.addConstantInt("Stall mode button port (RT)", JoystickMap.RT);
    }

    public static interface Movement 
    {
        public static Supplier<Integer> toggleDefense = 
            ConstantHandler.addConstantInt("Defense movement button port (LT)", JoystickMap.LT);
    }
    
    /*
    Left:
        * Button Joystick:
            * A (2)
            * LT (7)
            * RT (8)
            * Left Joystick (11)
            * Right Joystick (12)
        
        * Left Driving Joystick:
            * 1
            * 2
            * 3
            * 4
            * Testing/Unused
              {
                  * 7
                  * 8
                  * 9
                  * 10
                  * 11
                  * 12
              }


    Taken:
        * Button Joystick:
            * X (1)
            * B (3)
            * Y (4)
            * LB (5)
            * RB (6)
            * BACK (9)
            * START (10)
        
        * Left Driving Joystick:
            * 5
            * 6
            * Testing/Unused
            {
                * 7
                * 8
                * 9
                * 10
                * 11
                * 12
            }

        * Right Driving Joystick:
            *None
     */
}
