// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.HIDConstants;
import frc.robot.commands.AutonomousDrive;
import frc.robot.commands.SwerveDriveCommand;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.SwerveModule;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.paths.BarrelRacePath;
import frc.robot.paths.BouncePath;
import frc.robot.paths.FiveMeterPath;
import frc.robot.paths.SlalomPath;
import frc.robot.paths.TrajectoriesExporter;




/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer
{
    // The robot's subsystems and commands are defined here...
    private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

   // private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
    
    private final DriveSubsystem m_robotDrive = new DriveSubsystem(false);

    //private final SwerveModule m_Module = new SwerveModule();

    private final AutonomousDrive m_autonomousDrive = new AutonomousDrive(m_robotDrive);


    // The driver's controller
    XboxController m_driverController = new XboxController(HIDConstants.k_DriverControllerPort);

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer()
    {
        //System.out.println("here!");
 
        var traj = BouncePath.getTrajectory();
        TrajectoriesExporter.exportTrajectoryToCSV(traj, BouncePath.getTrajectoryName());
        TrajectoriesExporter.exportTrajectoryToHumanReadable(traj, BouncePath.getTrajectoryName());

        // TrajectoriesExporter.exportTrajectoryToHumanReadable(ExamplePath2.getTrajectory(), ExamplePath2.getTrajectoryName());
        // System.out.println("now here!");


        // Configure the button bindings
        configureButtonBindings();
 
        m_robotDrive.setDefaultCommand(new SwerveDriveCommand(m_robotDrive, m_driverController));
        //ShuffleboardTab tab = Shuffleboard.getTab("Swerve Drive Tuning");

        m_robotDrive.resetIMU();

        // m_robotDrive.modules[0].setEncoders();
        // m_robotDrive.modules[1].setEncoders();
        // m_robotDrive.modules[2].setEncoders();
        // m_robotDrive.modules[3].setEncoders();
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings()
    {
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand()
    {
        // An ExampleCommand will run in autonomous
        return m_autonomousDrive;
    }
}
