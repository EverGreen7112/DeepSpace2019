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
                ConstantHandler.addConstantInt("Button Ports - gripper catch  (X)", JoystickMap.ButtonJoystick.X);
            /**B*/
            public static  Supplier<Integer> releasePort = 
                ConstantHandler.addConstantInt("Button Ports - gripper release button port (B)", JoystickMap.ButtonJoystick.B);
            /**RB*/
            public static Supplier<Integer> openHatch = 
                ConstantHandler.addConstantInt("Button Ports - open hatch (Y)", JoystickMap.ButtonJoystick.Y);
            /**RB*/
            public static Supplier<Integer> closeHatch = 
                ConstantHandler.addConstantInt("Buttton Ports - close hatch  (A)", JoystickMap.ButtonJoystick.A);
            /**RB*/
            public static Supplier<Integer> openTounge = 
                ConstantHandler.addConstantInt("Button Ports - open tounge (RB)", JoystickMap.ButtonJoystick.RB);
            /**RB*/
            public static Supplier<Integer> closeTounge = 
                ConstantHandler.addConstantInt("Button Ports - close tounge (LB)", JoystickMap.ButtonJoystick.LB);
    }

    public static interface GripperMovement
    {
        /**{@link OI#buttonJS} - RJ*/
        public static Supplier<Integer> flipGripper = 
            ConstantHandler.addConstantInt("Flip Gripper button port (RJ)", JoystickMap.ButtonJoystick.rightJoystick);
    }

    public static interface Cameras
    {
        /**{@link OI#switchToA} Driving TopFrontLeft (5)*/
        public static Supplier<Integer> switchToA =
            ConstantHandler.addConstantInt("Switch to camera A button port (Left Joystick 5)",
            JoystickMap.DrivingJoystick.topFrontLeft);
        /**{@link OI#switchToB} Driving TopBackRight (6)*/
        public static Supplier<Integer> switchToB =
            ConstantHandler.addConstantInt("Switch to camera B button port (Left Joystick 6)",
            JoystickMap.DrivingJoystick.topBackRight);
    }

    public static interface PID
    {
        /**LB -  {@link OI#straighten} */
        public static Supplier<Integer> straighten =
            ConstantHandler.addConstantInt("PID loop button port (Left Driving Trigger)", JoystickMap.DrivingJoystick.trigger); //Button currently taken
    }

    public static interface Elevator
    {
        public static Supplier<Integer> lockSpeed =
            ConstantHandler.addConstantInt("Stall mode button port (back)", JoystickMap.ButtonJoystick.back);
        public static Supplier<Integer> stopAutoElevator = 
            ConstantHandler.addConstantInt("Toggle ElevatorDefault button port (start)", JoystickMap.ButtonJoystick.start);
        
        public static interface MoveToHatch 
        {
            public static Supplier<Integer> topHatch =
                ConstantHandler.addConstantInt("Top Hatch button port (Left Joystick 8)", JoystickMap.DrivingJoystick.bottomRightFront);
            public static Supplier<Integer> middleHatch =
                ConstantHandler.addConstantInt("TMiddle Hatch button port (Left Joystick 10)", JoystickMap.DrivingJoystick.bottomRightMiddle);
            public static Supplier<Integer> bottomHatchDRV =
                ConstantHandler.addConstantInt("Bottom Hatch driving joystick port (Left Joystick 12)", JoystickMap.DrivingJoystick.bottomRightBack);
            public static Supplier<Integer> bottomHatchBT =
                ConstantHandler.addConstantInt("Bottom Hatc button joystck port (LT)", JoystickMap.ButtonJoystick.LT);
        }

        public static interface MoveToCargo
        {
            public static Supplier<Integer> topCargo =
                ConstantHandler.addConstantInt("Top Cargo button port (Left Joystick 7)", JoystickMap.DrivingJoystick.bottomLeftFront);
            public static Supplier<Integer> middleCargo =
                ConstantHandler.addConstantInt("Middle Cargo button port (Left Joystick 9)", JoystickMap.DrivingJoystick.bottomLeftMiddle);
            public static Supplier<Integer> bottomCargoDRV =
                ConstantHandler.addConstantInt("Bottom Cargo driving joystick port (Left Joystick 11)", JoystickMap.DrivingJoystick.bottomLeftBack);
            public static Supplier<Integer> bottomCargoBT =
                ConstantHandler.addConstantInt("Bottom Cargo Driving Joystick port (RT)", JoystickMap.ButtonJoystick.RT);
        }
    }

    public static interface Chassis 
    {
        public static Supplier<Integer> toggleDefenseButton = ConstantHandler.addConstantInt("Defense toggle button port (Left Thumb)",JoystickMap.DrivingJoystick.thumb); //temp
        public static Supplier<Integer> slowAdjustButton = ConstantHandler.addConstantInt("Adjust to slow button port (Right Thumb)", JoystickMap.DrivingJoystick.thumb);
        public static Supplier<Integer> fastAdjustButton = ConstantHandler.addConstantInt("Adjust to fast button port (Right Driving Trigger)", JoystickMap.DrivingJoystick.trigger);
    }
    
    /*
    Left:
        * Button Joystick:
            * A (2)
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
