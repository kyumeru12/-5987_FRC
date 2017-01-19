
package org.usfirst.frc.team5987.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team5987.robot.Robot;
import org.usfirst.frc.team5987.robot.RobotMap;

/**
 *@author Doron	
 *@version v1.0
 *TODO Explain
 */
public class DriveByDistence extends Command {

	private double distence;
	private double maxDistence;
	private double minDistence;
	
    public DriveByDistence(double distence) {
        // Use requires() here to declare subsystem dependencies
        requires(RobotMap.driveSubsystem);

        this.distence = distence;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	maxDistence = 1.5; //in METERS 
    	double speed = distence/maxDistence;
    	if (speed > 1)
    		speed = 1;
    	RobotMap.driveSubsystem.drive(speed,speed);
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
	    end();
    }
}
