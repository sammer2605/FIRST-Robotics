// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // User Controllers are defined here...
  public static XboxController xbox = new XboxController(Constants.CONTROLLER); // Xbox Controller (Or Joystick)
  // The robot's subsystems are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  public static DriveTrain m_drive = new DriveTrain(); // DriveTrain
  // Autonomous Routine Chooser
  private SendableChooser<Command> m_autoChoice;

  // The robot's commands are defined here...
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // Set default commands on subsystems
    m_drive.setDefaultCommand(new arcadeDrive());

    buildDriverTab();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}

  private void buildDriverTab(){
    ShuffleboardTab driveTab = Shuffleboard.getTab("Drive");
    m_autoChoice = new SendableChooser<Command>();
    m_autoChoice.addOption("Forward", new DriveForward());
    m_autoChoice.addOption("Backward", new DriveBackward());
    m_autoChoice.addOption("Both", new DFARSHAR());
    m_autoChoice.addOption("Self Destruct", new SelfDestruct());
    m_autoChoice.setDefaultOption("Both", new DFARSHAR());
    driveTab.add("Autonomous Chooser", m_autoChoice).withWidget(BuiltInWidgets.kComboBoxChooser).withPosition(0, 0).withSize(2, 1);
  
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoChoice.getSelected();
  }
}
