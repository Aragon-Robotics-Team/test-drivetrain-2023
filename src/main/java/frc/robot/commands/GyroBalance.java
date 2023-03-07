// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class GyroBalance extends CommandBase {
  private static final class Config{
    public static final double kSpeedMultiplier = 0.3;
  }
  private Drivetrain m_drivetrain;

  private Timer m_timer;

  private double m_initPosition;
  /** Creates a new GyroBalance. */
  public GyroBalance(Drivetrain drivetrain) {
    m_drivetrain = drivetrain;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_initPosition = m_drivetrain.getGyroAngle();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_timer.hasElapsed(7)){
    if (m_drivetrain.getGyroAngle() - m_initPosition >= 0.75){
      //Drive towards game piece hub
      m_drivetrain.getDrive().arcadeDrive(1*Config.kSpeedMultiplier, 0);
      SmartDashboard.putString("Gyro", "Tilted Backwards");
    }
    else if (m_drivetrain.getGyroAngle() - m_initPosition <= -0.75){
      //Drive away from game piece hub
      m_drivetrain.getDrive().arcadeDrive(-1*Config.kSpeedMultiplier, 0);
      SmartDashboard.putString("Gyro", "Tilted Forwards");
    }
  }
}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.getDrive().arcadeDrive(0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_drivetrain.getGyroAngle() - m_initPosition <= 0.75 && m_drivetrain.getGyroAngle() - m_initPosition >= -0.75;
  }
}