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
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    }
    
    public void SetAngleSpeed(double speed) {
    	shootingAngle.set(speed);
    }
    
    public double getAngle() {
    	//TODO
    	return 0;
    }
    
    public void SetSpeed(double speed) {
    	leftShooter.set(speed);
    	rightShooter.set(speed);
    }
    
    public double getSpeed() {
    	return (leftSpeed.get() + rightSpeed.get()) / 2;
    }
    
    public double getDelta() {
    	return Math.abs(leftSpeed.get() + rightSpeed.get());
    }
}

