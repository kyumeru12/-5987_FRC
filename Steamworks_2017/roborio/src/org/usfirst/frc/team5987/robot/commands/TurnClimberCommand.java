
package org.usfirst.frc.team5987.robot.subsystems;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5987.robot.Robot;
import org.usfirst.frc.team5987.robot.RobotMap;

/**
 * @author Doron
 */
public class TurnClimberCommand extends Command {

	private double wantedAngle;
	private double speed;
	private double maxAngle;

	public TurnClimberCommand(double angle) {
		// Use requires() here to declare subsystem dependencies
		requires(RobotMap.gearpusSubsystem);
		this.wantedAngle = angle; // Tau Zdainuuuuu!!!!!!!!!1
	}

	public TurnClimberCommand() {
		// Use requires() here to declare subsystem dependencies
		requires(RobotMap.gearpusSubsystem);
		wantedAngle = -1.0;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		
		maxAngle = 0.2;
		

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (wantedAngle < 0.0)
			RobotMap.gearpusSubsystem.setClimberSpeed(-1.0);
		else
			driveToAngle();
		Timer.delay(0.05);
	}

	public void driveToAngle() {
		speed = (wantedAngle - RobotMap.gearpusSubsystem.getClimberPosition()) / maxAngle;
		if (speed > 1.0)
			speed = 1.0;
		if (speed < -1.0)
			speed = -1.0;
		SmartDashboard.putNumber("Speed", speed);
		RobotMap.gearpusSubsystem.setClimberSpeed(speed);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (wantedAngle < 0.0)
			return RobotMap.gearpusSubsystem.isTop();
		else if (Math.abs(wantedAngle - RobotMap.gearpusSubsystem.getClimberPosition()) < 0.01)
			return true;
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		RobotMap.gearpusSubsystem.setClimberSpeed(0.0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
