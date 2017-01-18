package org.usfirst.frc.team5987.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team5987.robot.Robot;
import org.usfirst.frc.team5987.robot.RobotMap;

/**
 *
 */
public class SootingSpeedCommand extends Command {

	private double speed;
	
    public SootingSpeedCommand(double speed) {
        // Use requires() here to declare subsystem dependencies
        requires(RobotMap.shootingSubsystem);
        
        this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.shootingSubsystem.SetSpeed(speed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
Contact GitHub 