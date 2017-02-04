package org.usfirst.frc.team5987.robot.commands;

import org.usfirst.frc.team5987.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Doron
 * 
 * set the shooters' speed equals to one another
 */
public class ChangeShootSpeedCommand extends Command {

	private double speed;

	public ChangeShootSpeedCommand(double speed) {
		// Use requires() here to declare subsystem dependencies
		requires(RobotMap.shootingSubsystem);

		this.speed = speed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		RobotMap.shootingSubsystem.setSpeed(speed);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
		
		if (RobotMap.shootingSubsystem.getLeftSpeed() > RobotMap.shootingSubsystem.getRightSpeed()) {
			RobotMap.shootingSubsystem.setLeftSpeed(RobotMap.shootingSubsystem.getLeftPWMSpeed() - 0.01);

		} else if (RobotMap.shootingSubsystem.getLeftSpeed() < RobotMap.shootingSubsystem.getRightSpeed()) {
			RobotMap.shootingSubsystem.setRightSpeed(RobotMap.shootingSubsystem.getRightPWMSpeed() - 0.01);

		}
		Timer.delay(0.05);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (RobotMap.shootingSubsystem.getDeltaSpeeds() < 50) {
			return true;
		}
		if (RobotMap.shootingSubsystem.getRightPWMSpeed() < 0 || RobotMap.shootingSubsystem.getLeftPWMSpeed() > 0) {
			RobotMap.shootingSubsystem.setSpeed(0);
			return true;
		}
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