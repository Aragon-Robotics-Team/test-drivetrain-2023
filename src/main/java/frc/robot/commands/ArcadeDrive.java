// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class ArcadeDrive extends CommandBase {

  private static final class Config{
    public static final int kRightStickZ = 3;
    public static final int kLeftStickY = 1;
    public static final double kSpeedMultiplier = 0.4;
    public static final double kTurnMultiplier = 0.4;
  }
  private Drivetrain m_drivetrain;
  private Joystick m_joystick;

  /** Creates a new ArcadeDrive. */
  public ArcadeDrive(Drivetrain drivetrain, Joystick joystick) {
    m_drivetrain = drivetrain;
    m_joystick = joystick;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double m_speed = m_joystick.getRawAxis(Config.kRightStickZ)*Config.kSpeedMultiplier;
    double m_turn = m_joystick.getRawAxis(Config.kLeftStickY)*Config.kTurnMultiplier;
    m_drivetrain.getDrive().arcadeDrive(m_speed, m_turn, true);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.getDrive().arcadeDrive(0,0,true);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
