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
