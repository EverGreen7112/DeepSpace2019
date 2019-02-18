/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.awt.*;

public class GUI extends Frame{
  /**
   * Add your docs here.
   */
  public GUI() {
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.

    //-----Set buttons for each section-----
    Button chassis = new Button("Change Chassis Constants");
    Button PID = new Button("Change PID Constants");
    Button cameras = new Button("Change Camera Constants");
    Button elevator = new Button("Change Elevator Constants");
    Button gripper = new Button("Change Gripper Constants");
    Button climbingShaft = new Button("Change Climbing Shaft Constants");
    Button climbingMovement = new Button("Change Climbing Movement Constants");

    //-----Set button locations-----
    chassis.setBounds(10, 10, 20, 10);
    PID.setBounds(25, 10, 20, 10);
    cameras.setBounds(10, 25, 20, 10);
    elevator.setBounds(25, 25, 20, 10);
    gripper.setBounds(10, 40, 20, 10);
    climbingShaft.setBounds(25, 40, 20, 10);
    climbingMovement.setBounds(10, 55, 20, 10);

    //-----Add buttons into frame-----
    add(chassis);
    add(PID);
    add(cameras);
    add(elevator);
    add(gripper);
    add(climbingShaft);
    add(climbingMovement);

    //-----Frame parameters-----
    setSize(300, 300);
    setLayout(null);
    setVisible(true);
  }
}
