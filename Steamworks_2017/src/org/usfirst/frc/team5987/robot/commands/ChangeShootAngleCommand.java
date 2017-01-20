package org.usfirst.frc.team5987.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team5987.robot.Robot;
import org.usfirst.frc.team5987.robot.RobotMap;

/**
 *
 */
public class ChangeShootAngleCommand extends Command {

	double deltaAngle;
	double velocity;
	double maxAngle;
	double minAngle;
	double wantedAngle;
	
    public ChangeShootAngleCommand(double wantedAngle) {
        requires(RobotMap.shootingSubsystem);
        requires(RobotMap.networkTable);
        
        this.wantedAngle = wantedAngle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	maxAngle = 10;
    	minAngle = 1;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	deltaAngle = (RobotMap.shootingSubsystem.getAngle() - wantedAngle);//Mei-be invert
    	velocity = deltaAngle/maxAngle;
    	RobotMap.shootingSubsystem.SetAngleSpeed(velocity);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Math.abs(RobotMap.shootingSubsystem.getAngle() - wantedAngle) < minAngle)
    		return true;
        return false;
    }

    // Called once after isFinished returns true=
    protected void end() {
    	RobotMap.shootingSubsystem.SetAngleSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}