/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Commands;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class IncreaseZoom extends CommandGroup {
  /**
   * Add your docs here.
   */
  public IncreaseZoom() {
    if(Robot.zoomLevel<4)
      Robot.zoomLevel+=0.5;
  }
}
