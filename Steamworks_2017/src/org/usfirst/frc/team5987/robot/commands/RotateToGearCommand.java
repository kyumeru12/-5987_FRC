package org.usfirst.frc.team5987.robot.commands;

import org.usfirst.frc.team5987.robot.RobotMap;
import org.usfirst.frc.team5987.robot.subsystems.DrivingSubsystem;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RotateToGearCommand extends Command {

	double angleToGear;
	boolean angleGear;

	public RotateToGearCommand()
	{
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() 
	{
		angleToGear = RobotMap.sdBoardSubsystem.getXdifLift;
		RobotMap.drivingSubsystem.turnRight(0.5f);

	}
	protected void execute()
	{
		if (angleToGear > 0)
			RobotMap.drivingSubsystem.turnRight(0.5);
		if (angleToGear < 0)
			RobotMap.drivingSubsystem.turnLeft(0.5);
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