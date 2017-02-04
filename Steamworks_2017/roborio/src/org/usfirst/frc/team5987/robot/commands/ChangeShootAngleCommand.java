package org.usfirst.frc.team5987.robot.commands;

import org.usfirst.frc.team5987.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *@author Doron
 *@version 1.0
 *
 *TODO updtae params
 *
 *set the shooters' angle
 */
public class ChangeShootAngleCommand extends Command {

	double deltaAngle;
	double velocity;
	double maxAngle;
	double minAngle;
	double wantedAngle;
	boolean set;

	public ChangeShootAngleCommand(double wantedAngle, boolean set) {
		requires(RobotMap.shootingSubsystem);
		requires(RobotMap.sdBoardSubsystem);

		this.wantedAngle = wantedAngle;
		this.set = set;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		maxAngle = 10.0/360;
		minAngle = 1.0/360;
		deltaAngle = (RobotMap.shootingSubsystem.getAngle() - wantedAngle);
		RobotMap.shootingSubsystem.SetAngleSpeed(deltaAngle / maxAngle);
		deltaAngle = (RobotMap.shootingSubsystem.getAngle() - wantedAngle);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double currentAngle =RobotMap.shootingSubsystem.getAngle();
		if (!set) {
			deltaAngle = (currentAngle - wantedAngle); // Mei-										// invert
			velocity = deltaAngle / maxAngle;
			RobotMap.shootingSubsystem.SetAngleSpeed(velocity);
			deltaAngle = (currentAngle - wantedAngle);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (set || Math.abs(RobotMap.shootingSubsystem.getAngle() - wantedAngle) < minAngle) {
			return true;
		}
		return false;
	}

	// Called once after isFinished returns true=
	protected void end() {
		if (!set)
			RobotMap.shootingSubsystem.SetAngleSpeed(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}