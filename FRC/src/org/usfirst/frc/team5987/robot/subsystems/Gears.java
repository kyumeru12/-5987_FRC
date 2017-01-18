package org.usfirst.frc.team5987.robot.subsystems;

import org.usfirst.frc.team5987.robot.commands.GearsCommands;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Gears extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public static DigitalInput gearsLS = new DigitalInput(1); //The LimitSwitch.
	public static AnalogPotentiometer PTN = new AnalogPotentiometer(1); //The Potentiometer.
	public double getRoation(double rotation)
	{
		return PTN.get();
	}

	public static boolean isGear() {
		if (gearsLS.get()) //checks if a gear is collected.
			return true;	//returns true if collected.
		else
			return false;  //returns false if not.
	}

	public static double getWantedAngle()
	{
		return SmartDashboard.getNumber("AngleName");
		
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new GearsCommands());
	}
}
