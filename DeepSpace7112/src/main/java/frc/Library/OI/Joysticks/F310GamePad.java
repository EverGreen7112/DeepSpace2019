/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.Library.OI.Joysticks;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.Library.FunctionalInterfaces.Adjuster;


/**
 * A class for <a href="https://www.logitechg.com/en-roeu/products/gamepads/f310-gamepad.html">
 * Logitech's F310 Gamepad</a>, with all of its buttons as members, so there is no need to 
 * initilize or keep track of individual buttons. <p><p>
 * Noth that this class inherits {@link AdjustedJoystick}, so it can be constructed with an
 * {@link Adjuster} which will automatically adjust its getRawAxis values.
 */
public class F310GamePad extends AdjustedJoystick {
    
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

    /**Constructs and returns a new F310 Gamepad joystick, connected to input port.
     * @param port - the port the gamepad is connected to.
     */
    public F310GamePad(int port)
    {
        super(port);
        init();
    }

    /**
     * Constructs and returns a new F310 Gamepad joystick, connected to input port, and adjusts its
     * axis' values according to the input adjuster.
     * @param port - the port the gamepad is connected to.
     * @param adjuster - the function that should adjust the joysticks' values.
     */
    public F310GamePad(int port, Adjuster<Double> adjuster)
    {
        super(port, adjuster);
        init();
    }

    private void init()
    {
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
