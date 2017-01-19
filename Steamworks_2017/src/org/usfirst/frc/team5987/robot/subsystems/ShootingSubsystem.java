package org.usfirst.frc.team5987.robot.subsystems;

import org.usfirst.frc.team5987.robot.RobotMap;

import com.ctre.CANTalon;

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
    
    CANTalon shootingAngle;

    AnalogInput angle;
    
    Victor leftShooter;
    Victor rightShooter;
    
    Encoder leftSpeed;
    Encoder rightSpeed;
    
    public ShootingSubsystem() {
    	CANTalon shootingAngle = new CANTalon(RobotMap.shootingAnglePort);

        AnalogInput angle = new AnalogInput(RobotMap.shooterAngleSensorPort);
        
        Victor leftShooter = new Victor(RobotMap.leftShooterPort); 
        Victor rightShooter = new Victor(RobotMap.rightShooterPort);
        
        Encoder leftSpeed = new Encoder(RobotMap.leftShooterChanelA,RobotMap.leftShooterChanelB,false);
        Encoder rightSpeed = new Encoder(RobotMap.rightShooterChanelA,RobotMap.rightShooterChanelB,false);
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(command);
    	
    }
    
    public void SetAngleSpeed(double speed) {
    	shootingAngle.set(speed);
    }
    
    public double getAngle() {
    	//TODO
    	return 0;
    }
    
    public void setSpeed(double speed) {
    	setDifrentSpeed(speed,speed);
    }
    
    public void setDifrentSpeed(double leftSpeed,double rightSpeed) {
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

