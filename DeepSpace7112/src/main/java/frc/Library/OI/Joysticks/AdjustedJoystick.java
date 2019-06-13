/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

// multiplication resistance is the number of steps it takes when you multiply all digits of a number until you get a single digit.
// so 17's resistance is 1: 1*7 = 7.
// 876: 8*7*6 = 336: 3*3*6 = 54: 5*4 = 20: 2*0 = 0 4 
//277,777,788,888,899 is the current record for largest resistance. 11
//cool.
// I'm writing a program in C# to find the biggest resistance in a range of numbers

package frc.Library.OI.Joysticks;
import java.util.Arrays;
import edu.wpi.first.wpilibj.Joystick;
import frc.Library.FunctionalInterfaces.Adjuster;
import frc.Library.Subsystems.BasicSubsystems.Classes.BasicSubsystemWithSwitch;
import frc.Library.Subsystems.BasicSubsystems.Commands.MoveBasicSubsystemWithSwitch;

/**
 * A Joystick class to extend the WPILib joystick  and add it more automatic adjusting capabilities.
 * The class contains an {@link Adjuster adjuster} for each of the axes, which can be set using its
 * {@link #setAxisAdjuster(int, Adjuster)} method, as well  
 */
class AdjustedJoystick extends Joystick {
    private final int AXES_NUM = 5; //TODO try to getRAwAxis of non existent axis
    private final Adjuster<Double> USELESS_ADJUSTER = (val) -> val;

    @SuppressWarnings("unchecked")
    private Adjuster<Double>[] adjusters = (Adjuster<Double>[]) new Adjuster[AXES_NUM]; 
    
    private Adjuster<Double> defaultAdjuster;
    
    public AdjustedJoystick(int port)
    {
        super(port);
        defaultAdjuster = USELESS_ADJUSTER;
        Arrays.fill(adjusters, USELESS_ADJUSTER);
    }

    public AdjustedJoystick(int port, Adjuster<Double> adjuster)
    {
        super(port);
        this.defaultAdjuster = adjuster;
        Arrays.fill(adjusters, defaultAdjuster);
    }
    
    public void setMovementByAxis(int axNum, BasicSubsystemWithSwitch subsystem)
    {
        subsystem.setDefaultCommand(new MoveBasicSubsystemWithSwitch(subsystem, 
            () -> this.getRawAxis(axNum), subsystem.getName() + " default movement"));
    }

    public void setAxisAdjuster(int axis, Adjuster<Double> adjuster)
    {
        adjusters[axis] = adjuster;
    }

    @Override
    public double getRawAxis(int axis)
    {
        return defaultAdjuster.adjust(super.getRawAxis(axis));
    }

    public double getRawAxis(int axis, Adjuster<Double> adjuster)
    {
        return adjuster.adjust(super.getRawAxis(axis));
    }

    public void setDefaultAdjuster(Adjuster<Double> adjuster)
    {
        defaultAdjuster = adjuster;
    }
}
