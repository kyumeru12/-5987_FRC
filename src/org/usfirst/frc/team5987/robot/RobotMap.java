package org.usfirst.frc.team5987.robot;

import org.usfirst.frc.team5987.robot.subsystems.ClimbingSubsystem;
import org.usfirst.frc.team5987.robot.subsystems.ShootingSubsystem;

import edu.wpi.first.wpilibj.DigitalSource;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	public static final ShootingSubsystem shootingSubsystem = new ShootingSubsystem();

	public static final Subsystem networkTable = null;
	
	public static int shootingAnglePort = 0;
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
	public static int leftShooterPort;
	public static int rightShooterPort;
	public static DigitalSource leftShooterChanelA;
	public static DigitalSource leftShooterChanelB;
	public static DigitalSource rightShooterChanelA;
	public static DigitalSource rightShooterChanelB;
	public static int shooterAngleSensorPort;
	public static int SortingServoPort;
	public static int leftFrontMotor = 0;
	public static int rightFrontMotor = 1;
	public static int leftRearMotor = 2;
	public static int rightRearMotor = 3;
	public ClimbingSubsystem climbing;
	public RobotMap(){
		climbing = new ClimbingSubsystem();
		leftFrontMotor = 0;
		rightFrontMotor = 1;
		leftRearMotor = 2;
		rightRearMotor = 3;
	}
    	// If you are using multiple modules, make sure to define both the port
    	// number and the module. For example you with a rangefinder:
    	// public static int rangefinderPort = 1;
		// public static int rangefinderModule = 1;
}
