// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.WPI_Pigeon2;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Drivetrain extends SubsystemBase {
  /** Pneumatics */
  // private static Compressor m_compressor = new Compressor(PneumaticsModuleType.CTREPCM); // Compressor
  // public static DoubleSolenoid m_shift = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.P_HIGHGEAR, Constants.P_LOWGEAR); // Shifter Solenoid
  public static boolean LowGear = false; // Low Gear
  private NetworkTableEntry currentEntry; // Compressor Current Draw
  private NetworkTableEntry shiftEntry; // Shifter state
  /** Drivetrain */
  private final WPI_TalonSRX frontRight = new WPI_TalonSRX(Constants.FR_TALONSRX); // Front Right Motor
  private final WPI_TalonSRX backRight = new WPI_TalonSRX(Constants.BR_TALONSRX); // Back Right Motor
  private final WPI_TalonSRX frontLeft = new WPI_TalonSRX(Constants.FL_TALONSRX); // Front Left Motor
  private final WPI_TalonSRX backLeft = new WPI_TalonSRX(Constants.BL_TALONSRX); // Back Left Motor
  private final MotorControllerGroup rightMotors = new MotorControllerGroup(frontRight, backRight); // Right Motors
  private final MotorControllerGroup leftMotors = new MotorControllerGroup(frontLeft, backLeft); // Left Motors
  private final DifferentialDrive m_drive = new DifferentialDrive(leftMotors, rightMotors); // Differential Drive
  /* Pigeon 2.0 */
  // private final WPI_Pigeon2 m_pigeon = new WPI_Pigeon2(Constants.P_PIGEON); // Pigeon2.0
  private NetworkTableEntry pitchEntry;
  // Current Limits (TalonSRX)
  void setTalonLimit(WPI_TalonSRX talon) {
    talon.configPeakCurrentLimit(35, 10); // Limit the peak output to 35 Amps
    talon.configPeakCurrentDuration(200, 10); // Limit the peak current duration
    talon.configContinuousCurrentLimit(30, 10); // 30 Amp Draw
    talon.enableCurrentLimit(true); // Enable the Current Limit
  }
  /** Creates a new Drivetrain. */
  public Drivetrain() {
    rightMotors.setInverted(true);
    setTalonLimit(frontRight);
    setTalonLimit(backRight);
    setTalonLimit(frontLeft);
    setTalonLimit(backLeft);
    // m_shift.set(Value.kForward);
    NetworkTableInstance inst = NetworkTableInstance.getDefault();
    NetworkTable table = inst.getTable("Pigeon2");
    pitchEntry = table.getEntry("Pitch");
    currentEntry = table.getEntry("Compressor Current");
    shiftEntry = table.getEntry("Shifter Status");
  }

  // Shift Gears Command
  public CommandBase ShiftGears() {
    return runOnce(
      () -> {
        shift();
      });
  }

  // Shift Gears Method
  public void shift() {
    // m_shift.toggle();
    LowGear = !LowGear;
  }

  // Get Shifter Status
  public boolean getShiftStatus() {
    return LowGear;
  }
  // Get Compressor Current
  // public double getCurrent() {
    // return m_compressor.getCurrent();
  // }

  // Get Compressor Operation State
  // public boolean getCompressorState() {
  //   return m_compressor.isEnabled();
  // }

  // Get Compressor Pressure Switch State
  // public boolean getPressureSwitch() {
  //   return m_compressor.getPressureSwitchValue();
  // }

  public void arcadeDrive(double fwd, double rot) {
    m_drive.arcadeDrive(fwd, rot);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // double pitch = m_pigeon.getPitch();
    // pitchEntry.setDouble(pitch);
    boolean shiftStatus = getShiftStatus();
    shiftEntry.setBoolean(shiftStatus);
    // double current = m_compressor.getCurrent();
    // currentEntry.setDouble(current);
  }
}
