/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Elevator; 

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import frc.Library.OI.Switches.Classes.Switch;
import frc.Library.OI.Switches.Classes.SwitchHandler;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.SubsystemComponents;
import frc.robot.SubsystemComponents.Elevator;

public class ElevatorDefault extends Command implements Elevator, SubsystemComponents {
  
  /**The boolean for whether or not the encoder was reset yet, because before the first time, 
   * it gives incorrect value. */
  public static boolean encoderWasReset = false;

  public ElevatorDefault() {
    requires(Robot.elevator);
  }

  public static Switch speedLock =  SwitchHandler.addSwitch("Elevator - Modes - SpeedLock", false);
  public static boolean switchHit = false;
  public static double lockedSpeed = 0;

  @Override
  protected void initialize() {
    super.initialize();
  }

  @Override
  protected void execute() {
    
    if(speedLock.get()) 
    {
      System.out.println("Stalling Elevator: " + lockedSpeed);
      Robot.elevator.move(lockedSpeed);
    }

    else
    {
      System.out.println("Moving Elevator: " + OI.getBTJoystick());

      if(OI.getBTJoystick() < 0 && !switchHit && SubsystemComponents.Elevator.opticSwitch.get())
      {
        encoder.reset();
        switchHit = true;
        encoderWasReset = true;
      }

      if(OI.getBTJoystick() > 0)
      {
        switchHit = false;
      }

      if(Math.abs(OI.getBTJoystick()) > 0.13) //If the elevator's joystick is moves
      {
        Gripper.PushPiston.set(Value.kReverse); //Close the hatch - 
        // if it were open the elevator would hit itr when it moved. 
        Robot.elevator.move(OI.getBTJoystick()); //and move the elevator.
      }

      else
      {
        Robot.elevator.move(0);
      }

    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }
}
