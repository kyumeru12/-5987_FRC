package org.usfirst.frc.team5987.robot.commands;

import org.usfirst.frc.team5987.robot.RobotMap;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Command;



/**
*
*@author Gregory Gershanik
*@version v1.0
*
*The class makes the robot turn to the a wanted angle.
*TODO test
*/

public class RotateByAngleCommand extends Command 
{
	double wantedAngle; //the wanted angle - the angle we want to go to .
	double currentAngle; // the angle we are currently in.
	

	RotateByAngleCommand(double wantedAngle) 
	{
		// Use requires() here to declare subsystem dependencies
		requires(RobotMap.drivingSubsystem);
				this.wantedAngle = wantedAngle;
	}

	// Called just before this Command runs the first time
	protected void initialize() 
	{
		currentAngle = RobotMap.navx.getAngle();

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() 
	{
		RobotMap.drivingSubsystem.drive(0.5, 0.5);
		currentAngle = RobotMap.navx.getAngle();
		if (wantedAngle > 0)
			RobotMap.drivingSubsystem.turnRight(0.5); 
		if (wantedAngle < 0)
			RobotMap.drivingSubsystem.turnLeft(0.5);
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
		RobotMap.drivingSubsystem.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() 
	{
		
	}
}