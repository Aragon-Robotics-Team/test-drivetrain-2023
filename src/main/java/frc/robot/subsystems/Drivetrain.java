// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {

  private static final class Config{
    //For left: ID = 13, port is 1
    public static final int kLeftMotorID = 13;
    //For right: ID = 14, port is 0
    public static final int kRightMotorID = 14;
  }

  private CANSparkMax m_leftMotor = new CANSparkMax(Config.kLeftMotorID, MotorType.kBrushless);
  private CANSparkMax m_rightMotor = new CANSparkMax(Config.kRightMotorID, MotorType.kBrushless);

  private DifferentialDrive m_drive = new DifferentialDrive(m_leftMotor, m_rightMotor);

  private ADXRS450_Gyro m_gyro  = new ADXRS450_Gyro();

  /** Creates a new Drivetrain. */
  public Drivetrain() {}

  public DifferentialDrive getDrive(){
    return m_drive;
  }

  public double getGyroAngle(){
    return m_gyro.getAngle();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
