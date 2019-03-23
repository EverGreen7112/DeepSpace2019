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
            ConstantHandler.addConstantInt("Gripper catch button port (X)", JoystickMap.ButtonJoystick.X);
        /**B*/
        public static  Supplier<Integer> releasePort = 
            ConstantHandler.addConstantInt("Gripper release button port (B)", JoystickMap.ButtonJoystick.B);
        /**RB*/
        public static Supplier<Integer> throwHatch = 
            ConstantHandler.addConstantInt("Gripper throw hatch button port (RB)", JoystickMap.ButtonJoystick.RB);
        /**Y*/
        public static Supplier<Integer> togglePushPistons =
            ConstantHandler.addConstantInt("Toggle push pistons button port (Y)", JoystickMap.ButtonJoystick.Y);
        
        //--------------------Testing--------------------
            /**{@link OI#buttonJ
             * S} - START*/
            public static Supplier<Integer> setPistonForward = 
                ConstantHandler.addConstantInt("Set gripper pistons forward port (START)", JoystickMap.ButtonJoystick.start);
            /**{@link OI#buttonJS} - BACK */
            public static Supplier<Integer> setPistonReverse = 
                ConstantHandler.addConstantInt("Set gripper pistons reverse port (BACK)", JoystickMap.ButtonJoystick.back);
    }

    public static interface GripperMovement
    {
        /**{@link OI#buttonJS} - LB*/
        public static Supplier<Integer> flipGripper = 
            ConstantHandler.addConstantInt("Flip Gripper button port (LB)", JoystickMap.ButtonJoystick.LB);
    }

    public static interface Cameras
    {
        /**{@link OI#switchToA}*/
        public static Supplier<Integer> switchToA =
            ConstantHandler.addConstantInt("Switch to camera A button port (Left Joystick 5)", JoystickMap.DrivingJoystick.topfrontLeft);
        /**{@link OI#switchToB}*/
        public static Supplier<Integer> switchToB =
            ConstantHandler.addConstantInt("Switch to camera B button port (Left Joystick 6)", JoystickMap.DrivingJoystick.topBackRight);
    }

    public static interface PID
    {
        /**LB -  {@link OI#straighten} */
        public static Supplier<Integer> straighten =
            ConstantHandler.addConstantInt("PID loop button port (Left Driving Triggrt)", JoystickMap.DrivingJoystick.trigger); //Button currently taken
    }

    public static interface Elevator
    {
        //--------------------Testing--------------------
            public static Supplier<Integer> setStall =
                ConstantHandler.addConstantInt("Stall mode button port (RT)", JoystickMap.ButtonJoystick.RT);
            public static Supplier<Integer> topHatch =
                ConstantHandler.addConstantInt("Top Hatch button port (Left Joystick 8)", JoystickMap.DrivingJoystick.bottomRightFront);
            public static Supplier<Integer> middleHatch =
                ConstantHandler.addConstantInt("TMiddle Hatch button port (Left Joystick 10)", JoystickMap.DrivingJoystick.bottomRightMiddle);
            public static Supplier<Integer> bottomHatch =
                ConstantHandler.addConstantInt("Bottom Hatch button port (Left Joystick 12)", JoystickMap.DrivingJoystick.bottomRightBack);
    }

    public static interface Chassis 
    {
        public static Supplier<Integer> toggleDefenseButton = ConstantHandler.addConstantInt("Defense toggle button port (Left Trigger)",JoystickMap.DrivingJoystick.trigger); //temp
        public static Supplier<Integer> slowAdjustButton = ConstantHandler.addConstantInt("Adjust to slow button port (Right Thumb)", JoystickMap.DrivingJoystick.thumb);
        public static Supplier<Integer> fastAdjustButton = ConstantHandler.addConstantInt("Adjust to fasr buttonn port (Right Driving Trigger)", JoystickMap.DrivingJoystick.trigger);
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
