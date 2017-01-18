package org.usfirst.frc.team5987.robot.commands;

import org.usfirst.frc.team5987.robot.subsystems.DrivingSubsystem;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GoToGearCommand extends Command {

	double angleToGear;
	boolean angleGear;

	public GoToGearCommand()
	{
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() 
	{
		angleToGear = SmartDashboard.getNumber("angleToFrontGear");
			DrivingSubsystem.turnRight(0.5f);

	}
	protected void execute()
	{
		if (angleToGear > 0)
			DrivingSubsystem.turnRight(0.5);
		if (angleToGear < 0)
			DrivingSubsystem.turnLeft(0.5);
	}
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() 
	{
		
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
Contact GitHub 