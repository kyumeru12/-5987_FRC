package org.usfirst.frc.team5987.robot;

import org.usfirst.frc.team5987.robot.subsystems.ClimbingSubsystem;
import org.usfirst.frc.team5987.robot.subsystems.DrivingSubsystem;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap
{
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	public static int leftRearMotor;
	public static int leftFrontMotor;
	public static int rightRearMotor;
	public static int rightFrontMotor;
	// If you are using multiple modules, make sure to define both the port
	// numbe and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	public ClimbingSubsystem climbing;
	public static DrivingSubsystem D0;

	public RobotMap() 
	{
		climbing = new ClimbingSubsystem();
		D0 = new DrivingSubsystem();
	}

}
