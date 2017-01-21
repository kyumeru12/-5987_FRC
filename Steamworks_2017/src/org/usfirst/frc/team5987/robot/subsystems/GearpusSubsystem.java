
package org.usfirst.frc.team5987.robot.subsystems;

import org.usfirst.frc.team5987.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearpusSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private static int lockPosition = 0;
	private static Spark climber;
	private static AnalogInput climberPosition;
	private static Servo locker;
	private static DigitalInput gearLimitSwitch = new DigitalInput(RobotMap.gearLimitSwitch);
	private static DigitalInput climbLimitSwitch = new DigitalInput(RobotMap.climbLimitSwitch);
	
//	public static pussy;
	
	public GearpusSubsystem() {
		climber = new Spark(RobotMap.climberPort);
		climberPosition = new AnalogInput(RobotMap.climberPositionPort);
		locker = new Servo(RobotMap.lockerPort);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    }
    
    public void setClimberSpeed(double speed) {
    	climber.set(speed);
    	
    }
    
    public double getClimberPosition() {
    	return climberPosition.getValue();
    }

    public void setLockerPosition(double position) {
    	locker.set(position);
    }
    
    public double getLockerPosition() {
    	return locker.get();
    }
    
	public static boolean isGear() {
		return gearLimitSwitch.get();
	}
	
	public static boolean isTop() {
		return climbLimitSwitch.get();
	}
	
	public static boolean isGearLocked()
	{
		return RobotMap.gearpusSubsystem.getLockerPosition() == lockPosition; 
				
	}
}

