/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.PortMaps;

import com.spikes2212.dashboard.ConstantHandler;

/**
 * Add your docs here.
 */
public class JoystickMap {
    public static interface ButtonJoystick
    {
        public static final int
            X = ConstantHandler.addConstantInt("Button Joystick X port", 1).get(),
            A = ConstantHandler.addConstantInt("Button Joystick A port", 2).get(),
            B = ConstantHandler.addConstantInt("Button Joystick B port", 3).get(),
            Y = ConstantHandler.addConstantInt("Button Joystick Y port", 4).get(),
            LB = ConstantHandler.addConstantInt("Button Joystick LB port", 5).get(),
            RB = ConstantHandler.addConstantInt("Button Joystick RB port", 6).get(),
            LT = ConstantHandler.addConstantInt("Button Joystick LT port", 7).get(),
            RT = ConstantHandler.addConstantInt("Button Joystick RT port", 8).get(),
            back = ConstantHandler.addConstantInt("Button Joystick BACK port", 9).get(),
            start = ConstantHandler.addConstantInt("Button Joystick START port", 10).get(),
            leftJoystick = ConstantHandler.addConstantInt("Button Joystick left joystick button port", 11).get(),
            rightJoystick = ConstantHandler.addConstantInt("Button joystick right joytsick button port", 12).get();
    }

    public static interface DrivingJoystick
    {
        public static final int
            trigger = ConstantHandler.addConstantInt("Driving Joystick Trigger button port", 1).get(),
            thumb = ConstantHandler.addConstantInt("Driving Joysticks Thumb  button port", 2).get(),
            topBackLeft =
                ConstantHandler.addConstantInt("Driving Joystick Top Back Left button port", 3).get(),
            topBackRight =
                ConstantHandler.addConstantInt("Driving Joystick Top Back Right button port", 4).get(),
            topfrontLeft = 
                ConstantHandler.addConstantInt("Driving Joystick Top front Left button port", 5).get(),
            topfrontRight = 
                ConstantHandler.addConstantInt("Driving Joystick Top front Right button port", 6).get(),
            bottomLeftFront =
                ConstantHandler.addConstantInt("Driving Joystick Bottom Left Front button port", 7).get(),
            bottomRightFront =
                ConstantHandler.addConstantInt("Driving Joystick bottom front right button port", 8).get(),
            bottomLeftMiddle =
                ConstantHandler.addConstantInt("Driving Joystick bottom left middle button port", 9).get(),
            bottomRightMiddle =
                ConstantHandler.addConstantInt("Driving joystick bottom right middle button port", 10).get(),
            bottomLeftBack =
                ConstantHandler.addConstantInt("Driving Joystick bottom left back port", 11).get(),
            bottomRightBack =
                ConstantHandler.addConstantInt("Driving Joysticl Bottom Right Back button port", 12).get();

    } 
}
