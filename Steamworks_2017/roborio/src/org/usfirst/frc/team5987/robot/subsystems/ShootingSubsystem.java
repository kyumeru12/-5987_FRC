package org.usfirst.frc.team5987.robot.subsystems;

import org.usfirst.frc.team5987.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * @author Doron Goldenberg
 * @version v1.0
 *
 *          It shoot it score!!!
 *
 *          TODO get angle and set default command
 */
public class ShootingSubsystem extends Subsystem {

	Victor leftShootingAngle;
	Victor rightShootingAngle;

	AnalogInput angle;

	Victor leftShooter;
	Victor rightShooter;

	Encoder leftSpeed;
	Encoder rightSpeed;

	public ShootingSubsystem() {
		leftShootingAngle = new Victor(RobotMap.shootingLeftAnglePort);
		rightShootingAngle = new Victor(RobotMap.shootingRightAnglePort);

		angle = new AnalogInput(RobotMap.shooterAngleSensorPort);

		leftShooter = new Victor(RobotMap.leftShooterPort);
		rightShooter = new Victor(RobotMap.rightShooterPort);

		leftSpeed = new Encoder(RobotMap.leftShooterChanelA, RobotMap.leftShooterChanelB, false);
		rightSpeed = new Encoder(RobotMap.rightShooterChanelA, RobotMap.rightShooterChanelB, false);

		leftSpeed.setDistancePerPulse(1);
		rightSpeed.setDistancePerPulse(1);

		rightShooter.setInverted(true);
		rightShootingAngle.setInverted(true);

	}

	public void initDefaultCommand() {

	}

	public void SetAngleSpeed(double speed) {
		leftShootingAngle.set(speed);
		rightShootingAngle.set(speed);
	}

	public double getAngle() {
		final double kMax=3628.0;
		final double kMin=300.0;
		SmartDashboard.putNumber("Pontiometer", angle.getValue());
		SmartDashboard.putNumber("Tau_Shooting", (angle.getValue()-kMin)/kMax);
		return (angle.getValue()-kMin)/kMax; 
	}

	public void setSpeed(double speed) {
		setLeftSpeed(speed);
		setRightSpeed(speed);
	}

	public void setLeftSpeed(double speed) {
		leftShooter.set(speed);
		SmartDashboard.putNumber("left pwm speed", leftShooter.get());
	}

	public void setRightSpeed(double speed) {
		rightShooter.set(speed);
		SmartDashboard.putNumber("right pwm speed", rightShooter.get());
	}

	public double getLeftSpeed() {
		SmartDashboard.putNumber("left speed", Math.abs(leftSpeed.getRate()));
		return Math.abs(leftSpeed.getRate());
	}

	public double getRightSpeed() {
		SmartDashboard.putNumber("right speed", Math.abs(rightSpeed.getRate()));
		return Math.abs(rightSpeed.getRate());
	}

	public double getLeftPWMSpeed() {
		SmartDashboard.putNumber("left pwm speed", leftShooter.get());
		return leftShooter.get();
	}

	public double getRightPWMSpeed() {
		SmartDashboard.putNumber("right pwm speed", rightShooter.get());
		return rightShooter.get();
	}

	public double getDeltaSpeeds() {
		SmartDashboard.putNumber("Delta", Math.abs(getLeftSpeed() - getRightSpeed()));
		return Math.abs(getLeftSpeed() - getRightSpeed());
	}
}
