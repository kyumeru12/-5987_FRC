package org.usfirst.frc.team5987.robot.subsystems;

import org.usfirst.frc.team5987.robot.RobotMap;


import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 
 *@author Doron Goldenberg
 *@version v1.0
 *
 *It shoot it score!!!
 *
 *TODO get angle and set default command
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
    	leftShootingAngle = new Victor(RobotMap.leftShooterPort);
    	rightShootingAngle = new Victor(RobotMap.rightShooterPort);

        angle = new AnalogInput(RobotMap.shooterAngleSensorPort);
        
        leftShooter = new Victor(RobotMap.leftShooterPort); 
        rightShooter = new Victor(RobotMap.rightShooterPort);
        
        leftSpeed = new Encoder(RobotMap.leftShooterChanelA, RobotMap.leftShooterChanelB, false);
        rightSpeed = new Encoder(RobotMap.rightShooterChanelA, RobotMap.rightShooterChanelB, false);
    }
    
    public void initDefaultCommand() {
    	
    }
    
    public void SetAngleSpeed(double speed) {
    	leftShootingAngle.set(speed);
    	rightShootingAngle.set(speed);
    }
    
    public double getAngle() {
    	//TODO
    	return 0;
    }
    
    public void setSpeed(double speed) {
    	setDifferentSpeed(speed,speed);
    }
    
    public void setDifferentSpeed(double leftSpeed,double rightSpeed) {
    	leftShooter.set(leftSpeed);
    	rightShooter.set(rightSpeed);
    }
    
    public double getSpeed() {
    	return (getLeftSpeed() + getRightSpeed()) / 2;
    }
    
    public double getLeftSpeed() {
    	return leftSpeed.get();
    }
    
    public double getRightSpeed() {
    	return rightSpeed.get();
    }
    
    public double getDeltaSpeeds() {
    	return Math.abs(getLeftSpeed() - getRightSpeed());
    }
}

