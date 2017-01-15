package org.usfirst.frc.team5987.robot.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team5987.robot.commands.ClimbingCommand;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 * @author A Cool Author
 * @version 9000.1 TODO DD EXPLins
 */
// T H I S P R O J E C T I S B R O U G H T TO U B Y G A L A X I A ! ! ! ! ! ! !
// !
public class ClimbingSubsystem extends Subsystem {
	// Configuring the motor and the limit switch designed for this subsystem
	public static Victor galaxiaClimbingMotor = new Victor(1);
	public static DigitalInput limitSwitch = new DigitalInput(1);

	public static void setSpeed(double speed)// function that is designed to
												// change the victor speed
												// acording to a value decided
												// when calling this function
	{
		galaxiaClimbingMotor.set(speed);
	}

	public static boolean isTop() {
		if (limitSwitch.get() == true)// check's if the limit switch is pressed
										// return true if so.
			return true;
		else
			return false;
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new ClimbingCommand());

	}
}
