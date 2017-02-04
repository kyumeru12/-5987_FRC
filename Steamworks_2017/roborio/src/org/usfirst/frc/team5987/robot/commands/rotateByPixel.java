package org.usfirst.frc.team5987.robot.commands;

import org.usfirst.frc.team5987.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Tzvi
 * TODO test
 * This command makes the robot to turn to the boiler or gears 
 */
public class rotateByPixel extends Command {

	double angle;
	boolean angleGear;
	private boolean isLift;

	public rotateByPixel(boolean isLift) {
		this.isLift = isLift;
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {

	}

	protected void execute() {
		if (isLift)
			angle = RobotMap.sdBoardSubsystem.getXdifLift();
		else
			angle = RobotMap.sdBoardSubsystem.getXdifBoiler();
		RobotMap.drivingSubsystem.drive(angle,-angle);
		
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() 
	{
		if(Math.abs(angle) < 0.05)
			return true;
		return false;
	}

	// Called once after isFinished returns true
	protected void end()

	{
		RobotMap.drivingSubsystem.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {

	}
}
