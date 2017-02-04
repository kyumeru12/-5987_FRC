package org.usfirst.frc.team5987.robot.commands;

import org.usfirst.frc.team5987.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Doron
 *TODO Check default rpm,test Command
 */
public class ChangeShootSpeedByRPMCommand extends Command {

	private double RPM;

	public ChangeShootSpeedByRPMCommand(double RPM) {
		// Use requires() here to declare subsystem dependencies
		requires(RobotMap.shootingSubsystem);

		this.RPM = RPM;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		RobotMap.shootingSubsystem.setSpeed(1);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
		
		if (RobotMap.shootingSubsystem.getLeftSpeed() > RPM) {
			RobotMap.shootingSubsystem.setLeftSpeed(RobotMap.shootingSubsystem.getLeftPWMSpeed() - 0.01);

		} else if (RobotMap.shootingSubsystem.getRightSpeed() > RPM) {
			RobotMap.shootingSubsystem.setRightSpeed(RobotMap.shootingSubsystem.getRightPWMSpeed() - 0.01);

		}
		Timer.delay(0.05);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (RobotMap.shootingSubsystem.getRightSpeed() <= RPM && RobotMap.shootingSubsystem.getLeftSpeed() <= RPM)
			return true;
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