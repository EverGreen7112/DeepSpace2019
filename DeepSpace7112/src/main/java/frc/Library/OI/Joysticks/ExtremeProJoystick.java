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
 * A class for <a href="https://www.logitechg.com/en-roeu/products/gamepads/extreme-3d-pro-joystick.html">
 * Logitech's Extreme 3D Pro Joystick</a>, with a map for all of its buttons. <p><p>
 * The Buttons are mapped as such: <p>
 * The buttons on they joystick itself are "top", and the ones on it base are "bottom".
 * The buttons on the left of the driver (with the Y axis pointoing forward) are "left", and the ones to
 * their right are "right".
 * The buttons on closer to the driver are "back" and the ones further away from them are "forward".
 */
public class ExtremeProJoystick extends Joystick {
    
    public Button trigger;
    public Button thumb;
    public Button topLeftBack;
    public Button topRightBack;
    public Button topLeftForward;
    public Button topRightForward;
    public Button bottomLeftForward;
    public Button bottomRightForward;
    public Button bottomLeftMiddle;
    public Button bottomRightMiddle;
    public Button bottomLeftBack;
    public Button bottomRightBack;

    public ExtremeProJoystick(int port)
    {
        super(port);
        
        trigger = new JoystickButton(this, 1);
        thumb = new JoystickButton(this, 2);
        topLeftBack = new JoystickButton(this, 3);
        topRightBack = new JoystickButton(this, 4);
        topLeftForward = new JoystickButton(this, 5);
        topRightForward = new JoystickButton(this, 6);
        bottomLeftForward = new JoystickButton(this, 7);
        bottomRightForward = new JoystickButton(this, 8);
        bottomLeftMiddle = new JoystickButton(this, 9);
        bottomRightMiddle = new JoystickButton(this, 10);
        bottomLeftBack = new JoystickButton(this, 11);
        bottomRightBack = new JoystickButton(this, 12);
    }

}
