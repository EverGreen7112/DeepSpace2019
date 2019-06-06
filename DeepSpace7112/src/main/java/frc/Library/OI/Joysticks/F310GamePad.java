/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.Library.OI.Joysticks;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * Add your docs here.
 */
public class F310GamePad extends Joystick {
    
    public Button X;
    public Button A;
    public Button B;
    public Button Y;
    public Button LB;
    public Button RB;
    public Button LT;
    public Button RT;
    public Button BACK;
    public Button START;
    public Button leftJoystick;
    public Button rightJoystick;

    public F310GamePad(int port)
    {
        super(port);
        
        X = new JoystickButton(this, 1);
        A = new JoystickButton(this, 2);
        B = new JoystickButton(this, 3);
        Y = new JoystickButton(this, 4);
        LB = new JoystickButton(this, 5);
        RB = new JoystickButton(this, 6);
        LT = new JoystickButton(this, 7);
        RT = new JoystickButton(this, 8);
        BACK = new JoystickButton(this, 9);
        START = new JoystickButton(this, 10);
        leftJoystick = new JoystickButton(this, 11);
        rightJoystick = new JoystickButton(this, 12);
    }

}
