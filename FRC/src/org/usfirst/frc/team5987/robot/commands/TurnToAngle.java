package org.usfirst.frc.team5987.robot.commands;

import org.usfirst.frc.team5987.robot.RobotMap;
import org.usfirst.frc.team5987.robot.subsystems.DrivingSubsystem;
import org.usfirst.frc.team5987.robot.subsystems.Gears;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Command;



/**
*
*@author Gregory Gershanik
*@version v1.0
*
*The class makes the robot turn to the angle needed.
*/

public class TurnToAngle extends Command 
{
	double wantedAngle; //the wanted angle - the angle we want to go to .
	double currentAngle; // the angle we are currently in.
	public static AHRS ahrs; //defining the ahrs from the NAvX library.

	public TurnToAngle() 
	{
		// Use requires() here to declare subsystem dependencies
		requires(RobotMap.D0);
		ahrs = new AHRS(SPI.Port.kMXP);
	}

	// Called just before this Command runs the first time
	protected void initialize() 
	{
		wantedAngle = Gears.getWantedAngle();
		currentAngle = ahrs.getAngle();

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() 
	{
		DrivingSubsystem.drive(0.5, 0.5);
		currentAngle = ahrs.getAngle();
		if (wantedAngle > 0)
			DrivingSubsystem.turnRight(0.5); 
		if (wantedAngle < 0)
			DrivingSubsystem.turnLeft(0.5);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		if (wantedAngle == currentAngle)
			return true;
		else
			return false;
	}

	// Called once after isFinished returns true
	protected void end() 
	{
		
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() 
	{
		
	}
}
