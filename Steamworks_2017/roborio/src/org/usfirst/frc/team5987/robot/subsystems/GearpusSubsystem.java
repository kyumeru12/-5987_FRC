
package org.usfirst.frc.team5987.robot.subsystems;

import org.usfirst.frc.team5987.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Doron && Tzvi but doron is guilty
 */
public class GearpusSubsystem extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private static int lockPosition = 0;

	private static Victor climberLeft; // PWM
	private static Victor climberRight; // PWM

	private static AnalogInput climberPosition; // Analog
	private static Servo locker; // PWM

	private static DigitalInput climbLimitSwitch = new DigitalInput(RobotMap.climbLimitSwitch); // DIO

	public GearpusSubsystem() {
		climberLeft = new Victor(RobotMap.climberLPort);
		climberRight = new Victor(RobotMap.climberRPort);

		climberRight.setInverted(true);

		climberPosition = new AnalogInput(RobotMap.climberPositionPort);
		locker = new Servo(RobotMap.lockerPort);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
	}
	/**
	*@parmeters speed the wanted speed for the motor
	*the functions set the speed for the climber motors
	*/
	public void setClimberSpeed(double speed) {
		climberLeft.set(speed);
		climberRight.set(speed);

	}
	/**
	*@return This function returns the current angle of the climber in tau (2PI = TAU)
	*TODO move the smartdashboard to SD subsystem
	*/
	public double getClimberPosition() {
		SmartDashboard.putNumber("Pontialmeter", climberPosition.getValue());
		SmartDashboard.putNumber("Tau", ((((4052.0 / 4055.0) * climberPosition.getValue())) % 405.2)/405.2);
		return (((4052.0 / 4055.0) * climberPosition.getValue()) % 405.2)/405.2;
	}

	
	/*
	*@parameters position set position to the servo   
	*/
	public void setLockerPosition(double position) {
		locker.set(position);
	}
	
	/**
	*this function get the current from the servo
	*/
	public double getLockerPosition() {
		return locker.get();
	}
	/**
	*@return this function returns the current state of the limit switch 
	*/
	public static boolean isTop() {
		return !climbLimitSwitch.get();
	}
	/**
	*@return this function returns if the current state of the limit switch is locked
 	*/
	public static boolean isGearLocked() {
		return RobotMap.gearpusSubsystem.getLockerPosition() == lockPosition;

	}

}
