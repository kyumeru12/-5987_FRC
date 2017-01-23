package org.usfirst.frc.team5987.robot;

import org.usfirst.frc.team5987.robot.subsystems.*;

import edu.wpi.first.wpilibj.DigitalSource;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	// PWM
	public static int leftFrontMotor = 0;
	public static int leftRearMotor = 1;
	public static int rightRearMotor = 2;
	public static int rightFrontMotor = 3;
	public static int shootingAnglePort = 4;
	public static int leftShooterPort = 5;
	public static int rightShooterPort = 6;
	public static int sortingServoPort = 7;
	public static int carrierMotor = 8;
	public static int climberPort = 9;
	public static int lockerPort = 10;
	
	
	//Digital And Analog 
	
	public static int shooterAngleSensorPort = 0;
	public static int leftShooterChanelA = 1;
	public static int leftShooterChanelB = 2;
	public static int rightShooterChanelA = 3;
	public static int rightShooterChanelB = 4;
	public static int gearLimitSwitch = 5;
	public static int climberPositionPort = 6;
	public static int climbLimitSwitch = 7;
	
	public static DrivingSubsystem drivingSubsystem = new DrivingSubsystem();
	public static GearpusSubsystem gearpusSubsystem = new GearpusSubsystem();
	public static ShootingSubsystem shootingSubsystem = new ShootingSubsystem();
	public static SortingSubsystem sortingSubsystem = new SortingSubsystem();
	public static TransportSubsystem transportSubsystem = new TransportSubsystem();
	public static SDboardSubsystem sdBoardSubsystem = new SDboardSubsystem();
	
		
	
		
}
