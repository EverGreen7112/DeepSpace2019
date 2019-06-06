/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.Library.OI.Joysticks.ExtremeProJoystick;
import frc.Library.OI.Joysticks.F310GamePad;
import frc.Library.OI.Joysticks.GroupButtonBindings;

/**
 * This class contains all of the joysticks and their buttons, and the methods to access them.
 */
public class OI implements CommandList {
    /**The {@link ExtremeProJoystick Extreme 3D Pro Joystick} used for controlling the 
     * left wheels of the robot.*/
    private static ExtremeProJoystick drivingJSLeft;
    /**The {@link ExtremeProJoystick Extreme 3D Pro Joystick} used for controlling the 
     * right wheels of the robot.*/
    private static ExtremeProJoystick drivingJSRight;
    /**The {@link F310GamePad} used for controlling the robot's elevator, gripper, and hatch system. */
    private static F310GamePad buttonJS;

    /*The code ran when this class is loaded - it intitilizes all the joysticks and (through their 
     * own contructors) their buttons, and binds each button to execute the required commands by the 
     * specific conditions.*/
    static
    {
        //----------Joystick initializations----------
            drivingJSLeft = new ExtremeProJoystick(0);
            drivingJSRight = new ExtremeProJoystick(1);
            buttonJS = new F310GamePad(2);
        //----------Chassis----------
            drivingJSRight.trigger.whileHeld(ChassisCommands.fastDrive);
            drivingJSRight.thumb.whileHeld(ChassisCommands.slowDrive);
            drivingJSLeft.trigger.whileHeld(ChassisCommands.toggleSmartP);
        //----------Elevator----------
            GroupButtonBindings.whenPressed(ElevatorCommands.moveToBottomHatch, 
                buttonJS.LT, drivingJSRight.bottomRightBack);            
            drivingJSRight.bottomRightMiddle.whenPressed(ElevatorCommands.moveToMiddleHatch);
            drivingJSRight.bottomRightForward.whenPressed(ElevatorCommands.moveToTopHatch);

            GroupButtonBindings.whenPressed(ElevatorCommands.moveToBottomCargo, 
                buttonJS.RT, drivingJSRight.bottomLeftBack);            
            drivingJSRight.bottomLeftMiddle.whenPressed(ElevatorCommands.moveToMiddleCargo);
            drivingJSLeft.bottomLeftForward.whenPressed(ElevatorCommands.moveToTopCargo);

            buttonJS.BACK.whenPressed(ElevatorCommands.lockSpeed);
        //----------Gripper----------
            buttonJS.B.whileHeld(GripperCommands.gripperCatch);
            buttonJS.X.whileHeld(GripperCommands.gripperRelease);
        //----------Hatch System----------
            GroupButtonBindings.whenPressed(HatchSystemCommands.toggleHatch, 
                buttonJS.Y, buttonJS.A);
            GroupButtonBindings.whenPressed(HatchSystemCommands.toggleTounge, 
                buttonJS.LB, buttonJS.RB);
    }

    /**
     * @return The inverse value of the {@link #buttonJS button joystick's} second axis
     * (forward-back).*/
    public static double getBTJoystick()
    {
        return -buttonJS.getRawAxis(1);
    }

    /**
     * @return The inverse value of the {@link #drivingJSLeft left driving joystick}'s curreny Y 
     * (forward-back) axis value, slightly adjusted, as the 0 point is not finenly tuned. 
     */
    public static double getLeftJoystick()
    {
        return -curve(drivingJSLeft.getY()) * 1.07;
    }

    /**@return The {@link #drivingJSRight right driving joystick}'s Y (forward-back) axis value. */
    public static double getRightJoystick()
    {
        return curve(drivingJSRight.getY());
    }

    /**
     * Adjust the number to the parabola graph rather than the linear graph the joysticks give:<p>
     * By default, each equal-distance movement of the joysticks increases its value by an identical 
     * amount.This method adjusts this values such that the further the joysticks go, the more their
     * movements are worth - the inputs exponent rather than increase at a fixed rate. <p>
     * This is done to make increasing or decreasing the robot's speed easier and faster. If control
     * of a joystick with curved input is significantly hard, it is recommended to remove the effects
     * of this method. 
     * @param input - The linear input to be made exponential.
     * @return The input adjusted to be exponential.
     */
    public static double curve(double input)
    {
        return input * Math.abs(input);
    }

}
