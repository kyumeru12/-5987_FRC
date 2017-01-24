package org.usfirst.frc.team5987.robot.commands;

import org.usfirst.frc.team5987.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class WaitForGearCommand extends Command {

    public WaitForGearCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(RobotMap.sortingSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	SmartDashboard.putBoolean("isGear1", RobotMap.sortingSubsystem.isGear());
    	Timer.delay(2);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	SmartDashboard.putBoolean("isGear2", RobotMap.sortingSubsystem.isGear());
    	return RobotMap.sortingSubsystem.isGear();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
