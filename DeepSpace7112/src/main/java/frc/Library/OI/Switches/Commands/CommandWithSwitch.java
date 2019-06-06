/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.Library.OI.Switches.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.Library.OI.Switches.Classes.Switch;
import frc.Library.OI.Switches.Classes.SwitchHandler;

/**
 * This command utilizes the {@link Switch} and {@link SwitchHandler} classes and creates 
 * a {@link Command} with a switch on the shuffleoard's prefrences section, named in construction. <p>
 * This can used to disable the command (for example for testing) or quit it while it's executing 
 * (e.g in case of a malfunction). <P> 
 * The switch can be turned on and off both manually on the shuffleboard itself, and from within the code, 
 * via the {@link SwitchHandler}. <p>
 * @author Atai Ambus, #7112
 */
public class CommandWithSwitch extends Command {
  /**A boolean supplier with aname value and a matching switch on the Shuffleboard's prefrence. 
   * <p>
   * <b>Suggested use</b>: Make that if this switch is off, this command will quit autoatically if this is
   * executing, and that {@link Command#start()} will ran no real code when called. (such that 
   * {@link Command#initialize()}, 
   * {@link Command#execute()}, 
   * {@link Command#end()} and 
   * {@link Command#interrupted()} 
   * will be conditioned by the switch returning true, and that {@link #isFinished()}
   */
  protected Switch switchOn;

  /**
   * Adds a switch on the shuffleboard - If it's off, the command will quit if it's executing, and
   * will only execute for one iteration if false. Note that it <i>is</i> possible to prevent the command
   * from execting at all, by simply puting your execute code in an {@code if(switchOn.get())} block. This, however,
   * must be done manually in the subclass' overridden execute() method.
   * @param name - the name for the switch, which will also be the key to accesing it through the {@link SwitchHandler}.
   */
  public CommandWithSwitch(String name) {
    switchOn = SwitchHandler.addSwitch(name);
  }

  public CommandWithSwitch(String name, boolean defaultValue) {
    switchOn = SwitchHandler.addSwitch(name, defaultValue);
  }


  /**Ends the command and runs {@link Command#end()} when this returns true. <p>
   * <ul>
   * <li></b>intended use</b> is to end the command if the switch is 
   * turned off. This both ensures execute() runs only once if this command is disabled, and that if if it 
   * is turned off while executing it will end.</li>
   * <li> <b>To implement this in your subclass:</b> add {@code || super.isFinished()} to your isFinished() 
   * conditions, or {@code || !switchOn()} if using the former will add other unnecessary conditions. </li> </ul>
   * @return true if this command's switch is off, false if it is on.*/
  @Override
  protected boolean isFinished() {
    return !switchOn.get();
  }

  public void disable()
  {
    SwitchHandler.disable(switchOn);
  }

  public void enable()
  {
    SwitchHandler.enable(switchOn);
  }
}
