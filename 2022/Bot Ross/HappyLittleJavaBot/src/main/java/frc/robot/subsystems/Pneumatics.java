// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Pneumatics extends SubsystemBase {
  // Shifter Solenoid
  public static DoubleSolenoid shifter = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.HIGHGEAR, Constants.LOWGEAR);
  public static boolean isHighGear = false;

  /** Creates a new Pneumatics. */
  public Pneumatics() {
    shifter.set(Value.kForward); // Set to Low Gear by default
  }

  public void shift() {
    shifter.toggle();
    isHighGear = !isHighGear;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
