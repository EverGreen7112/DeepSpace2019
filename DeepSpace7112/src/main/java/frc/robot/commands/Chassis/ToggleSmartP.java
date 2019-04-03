package frc.robot.commands.Chassis;

import com.spikes2212.dashboard.ConstantHandler;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.SubsystemComponents;
import frc.robot.SubsystemConstants;

public class ToggleSmartP extends Command {
  public static boolean SmartP;
  // public static boolean finished;

  public ToggleSmartP() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    // finished = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    System.out.println("Toggle SmartP Mode");
    DefaultDrive.smartPMode = !(DefaultDrive.smartPMode);
    if (DefaultDrive.smartPMode)
      DefaultDrive.defenseMode = false;
  }
  
 
  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }
  

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
