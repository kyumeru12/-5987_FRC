
package org.usfirst.frc.team5987.robot.commands;

import org.usfirst.frc.team5987.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
/**
 *
 */
public class ChangeSpeedByLeftDistCommand extends Command {

	private double distence;
	private double maxDistance;
	private double minDistance;
	
    public ChangeSpeedByLeftDistCommand(double distance) {
        // Use requires() here to declare subsystem dependencies
        requires(RobotMap.drivingSubsystem);

        this.distence = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	maxDistance = 1.5;
    	double speed = distence/maxDistance;
    	if (speed > 1)
    		speed = 1;
    	RobotMap.drivingSubsystem.drive(speed,speed);
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
