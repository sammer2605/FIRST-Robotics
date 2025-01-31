// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class BeginBalance extends CommandBase {
  private boolean isFinished = false;
  /** Creates a new DriveForward. */
  public BeginBalance() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_drive); // Add the DriveTrain Subsystem as a requirement
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (!RobotContainer.m_drive.onSlope()){
    RobotContainer.m_drive.tankDriveVolts(Constants.AUTOSPEED, Constants.AUTOSPEED); // Drive forward at 4 volts
    return;
    }
    if (RobotContainer.m_drive.onSlope()){
      RobotContainer.m_drive.arcadeDrive(0,0);
      isFinished = true;
      return;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    isFinished = true;
    RobotContainer.m_drive.tankDriveVolts(0, 0); // Stop the robot when the command ends
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isFinished;
  }
}
