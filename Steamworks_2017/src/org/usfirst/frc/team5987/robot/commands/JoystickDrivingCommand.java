package org.usfirst.frc.team5987.robot.commands;


import org.usfirst.frc.team11111.robot.Robot;
import org.usfirst.frc.team11111.robot.subsystems.DrivingSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *@author DorBrekhman
 *@version 0.1
 */
public class JoystickDrivingCommand extends Command {
	
	private double leftSpeed;
	private double rightSpeed;
	
    public JoystickDrivingCommand(double leftSpeed, double rightSpeed) {
    	requires(Robot.drivingSubsystem);
    	this.leftSpeed = leftSpeed;
    	this.rightSpeed = rightSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	DrivingSubsystem.drive(leftSpeed, rightSpeed);
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