package org.usfirst.frc.team5987.robot;

import org.usfirst.frc.team5987.robot.NAVX.Navx;
import org.usfirst.frc.team5987.robot.NAVX.Navx.PinType;
import org.usfirst.frc.team5987.robot.subsystems.DrivingSubsystem;
import org.usfirst.frc.team5987.robot.subsystems.GearpusSubsystem;
import org.usfirst.frc.team5987.robot.subsystems.SDboardSubsystem;
import org.usfirst.frc.team5987.robot.subsystems.ShootingSubsystem;
import org.usfirst.frc.team5987.robot.subsystems.SortingSubsystem;
import org.usfirst.frc.team5987.robot.subsystems.TransportSubsystem;

import edu.wpi.first.wpilibj.SPI;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	public static Navx navx = new Navx(SPI.Port.kMXP);
	
	// PWM
	public static int leftFrontMotor = 0;
	public static int leftRearMotor = 1;
	public static int rightRearMotor = 2;
	public static int rightFrontMotor = 3;
	public static int shootingLeftAnglePort = 4;
	public static int shootingRightAnglePort = 5;
	public static int leftShooterPort = 6;
	public static int rightShooterPort = 7;
	public static int sortingServoPort = 8;
	public static int carrierMotor = 9;
	
	//NAVX PWM
	
	public static int climberLPort= navx.getChannelFromPin(PinType.PWM, 0);
	public static int climberRPort=navx.getChannelFromPin(PinType.PWM, 1);
	public static int lockerPort = navx.getChannelFromPin(PinType.PWM, 2);
	
	
	//Digital And Analog 
	
	public static int shooterAngleSensorPort = 0;
	public static int leftShooterChanelA = 1;
	public static int leftShooterChanelB = 2;
	public static int rightShooterChanelA = 3;
	public static int rightShooterChanelB = 4;
	public static int isGearsPort = 5;
	public static int climberPositionPort = 6;
	public static int climbLimitSwitch = 7;
	
	public static DrivingSubsystem drivingSubsystem = new DrivingSubsystem();
	public static GearpusSubsystem gearpusSubsystem = new GearpusSubsystem();
	public static ShootingSubsystem shootingSubsystem = new ShootingSubsystem();
	public static SortingSubsystem sortingSubsystem = new SortingSubsystem();
	public static TransportSubsystem transportingSubsystem = new TransportSubsystem();
	public static SDboardSubsystem sdBoardSubsystem = new SDboardSubsystem();

			
		
	
		
}
