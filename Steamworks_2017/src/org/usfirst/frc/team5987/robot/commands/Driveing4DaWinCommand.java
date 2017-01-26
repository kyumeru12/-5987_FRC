
package org.usfirst.frc.team5987.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team5987.robot.Robot;
import org.usfirst.frc.team5987.robot.RobotMap;

/**
 * @author Doron
 * @version 0.1v alpha
 * 
 *          driving toward a point smoothly (One Command to rule them all )
 */
public class Driveing4DaWinCommand extends Command {

	private double x; // A x value in axis
	private double y; // A y value in axis

	private double wantedAngle; // our wanted angle
	private double distance; // distance from the target

	private double initLeftEncode;
	private double initRightEncode;

	private double currentLeftEncode;
	private double currentRightEncode;

	private final double K = 1; // The constant value of distance between the
								// wheels, in meter

	private double radius;

	private double previousAngle;
	private double currentAngle;

	private double maxDistance; // From this distance the robot will start to
								// decrease his speed
	private double maxAngle;// From this angle the robot will start to decrease
							// his speed

	private double minSpeed;// When the robot reach this speed below he will
							// stop

	/**
	 * 
	 * @param x
	 *            A x value in axis, in meter
	 * @param y
	 *            A y value in axis, in meter
	 * 
	 *            The constructor define the target by the x,y. Our robot is in
	 *            (0,0) position.
	 */
	public Driveing4DaWinCommand(double x, double y) {
		requires(Robot.exampleSubsystem);
		this.x = x;
		this.y = y;
	}

	/**
	 * 
	 * @param angle
	 * @return the angle in radians
	 * 
	 */
	public double angleToRadian(double angle) {
		return angle * Math.PI / 180;
	}

	/**
	 * 
	 * @param radian
	 * @return the angle in degrees
	 */
	public double radianToAngle(double radian) {
		return radian * 180 / Math.PI;
	}

	/**
	 * update the wanted angle based on targets position The formula is for
	 * converting point into a vector
	 */
	public void updateWantedAngle() {
		double devision;

		if (x == 0) // just in case we devide by 0...
			devision = y * 1000000000000000000.0;
		else
			devision = y / x;

		wantedAngle = radianToAngle(Math.atan(devision));
	}

	/**
	 * update the distance based on targets position The formula is for
	 * converting point into a vector
	 */
	public void updateDistance() {
		distance = Math.sqrt(x * x + y * y);
	}

	/**
	 * update the radius based on the delta distance we have already went through every call for an update 
	 */
	public void updateRadius() {
		double dRightEncode = currentRightEncode - initRightEncode;
		double dLeftEncode = currentLeftEncode - initLeftEncode;
		if (dRightEncode == dLeftEncode)
			return;
		double rightradius = dLeftEncode * K / (dRightEncode - dLeftEncode);
		radius = rightradius + 0.5 * K;
	}
	
	/**
	 * update targets position based on our distance from the robot to the  target
	 */
	public void moveTarget() {
		x -= radius * (Math.cos(angleToRadian(previousAngle)) - Math.sin(angleToRadian(90 - currentAngle)));
		y -= radius * (Math.cos(angleToRadian(90 - currentAngle)) - Math.sin(angleToRadian(previousAngle)));
	}

	/**
	 * update calls every fuking update
	 */
	public void update() {
		currentLeftEncode = RobotMap.drivingSubsystem.getLeftEncoder();
		currentRightEncode = RobotMap.drivingSubsystem.getLeftEncoder();
		currentAngle = RobotMap.navx.getAngle();
		updateRadius();
		moveTarget();
		updateWantedAngle();
		updateDistance();
		initLeftEncode = currentLeftEncode;
		initRightEncode = currentRightEncode;
		previousAngle = currentAngle;
	}

	/**
	 * set the motor speed based on his distance and his angle
	 */
	public void setSpeed() {
    	double rightSpeed = lim(strightSpeed() + angleSpeed());
    	double leftSpeed = lim(strightSpeed() - angleSpeed());
    	RobotMap.drivingSubsystem.drive(rightSpeed,leftSpeed)
    }

	/**
	 * 
	 * @param num
	 * @return if num < -1 || num > 1 set to -1, 1 
	 */
	public double lim(double num) {
		if (num > 1)
			return 1;
		if (num < -1)
			return -1;
		return num;
	}

	/**
	 * 
	 * @return limited speed based on distance 
	 * for explanation for the formula see the readme at git hub
	 */
	public double straightSpeed() {
		return lim(distance / maxDistance) * Math.sin(angleToRadian(previousAngle));
	}
	/**
	 * 
	 * @return limited speed based on angle 
	 * for explanation for the formula see the readme at git hub
	 */
	public double angleSpeed() {
		return lim((wantedAngle - previousAngle) / maxAngle);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		initLeftEncode = RobotMap.drivingSubsystem.getLeftEncoder();
		initRightEncode  = RobotMap.drivingSubsystem.getLeftEncoder();
		previousAngle = RobotMap.navx.getAngle();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		update();
		setSpeed();
		Timer.delay(0.05);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (straightSpeed() + angleSpeed() < minSpeed && strightSpeed() - angleSpeed() < minSpeed) //if speed is low meaning we reached the target
			return true;
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		RobotMap.drivingSubsystem.drive(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
