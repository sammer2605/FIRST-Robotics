// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class SpinBalance extends CommandBase {
  Timer t;
  /** Creates a new Balance. */
  public SpinBalance() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    t = new Timer();
    t.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.m_drive.tankDriveVolts(2,-2);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    t.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return t.hasElapsed(1.5);
  }
}
