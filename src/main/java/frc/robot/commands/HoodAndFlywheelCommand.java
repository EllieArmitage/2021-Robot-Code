// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class HoodAndFlywheelCommand extends CommandBase {
  /** Creates a new ActuateHoodCommand. */
  ShooterSubsystem m_shooter;
  Boolean m_isFinished;

  public HoodAndFlywheelCommand(ShooterSubsystem shooter) 
  {
    m_shooter = shooter;
    addRequirements(shooter);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize()
  {
    m_isFinished = false;
    m_shooter.setHoodMotor(m_shooter.hoodSetSetpoint(0.2));//m_shooter.hoodAngleFinder());

    //m_shooter.setHoodMotor(m_shooter.hoodAngleFinder());
    //m_shooter.runFlyWheel();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
   m_shooter.setHoodMotor(m_shooter.hoodSetSetpoint(0.2));//m_shooter.hoodAngleFinder());
   //m_shooter.runFlyWheel();
   SmartDashboard.putNumber("Flywheel Velocity", m_shooter.getShooterVelocity());
   SmartDashboard.putNumber("Hood Encoder", m_shooter.getAbsEncoder());

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted)
  {
    m_isFinished = true;
    m_shooter.forceStopHoodMotor();
    //m_shooter.stopFlyWheel();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_isFinished;
  }
}
