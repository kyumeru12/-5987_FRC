//package org.usfirst.frc.team5987.robot.subsystems;
//
//
//import edu.wpi.first.wpilibj.AnalogPotentiometer;
//import edu.wpi.first.wpilibj.DigitalInput;
//import edu.wpi.first.wpilibj.command.Subsystem;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//
///**
// *@deprecated unitet by GearpusSubsystem
// */
//public class Gears extends Subsystem {
//
//	// Put methods for controlling this subsystem
//	// here. Call these from Commands.
//	public static DigitalInput gearsLS = new DigitalInput(1);
//	public static AnalogPotentiometer PTN = new AnalogPotentiometer(1);
//	public double getRoation(double rotation)
//	{
//		return PTN.get();
//	}
//	public static double Xdif;
//	public static boolean isGear() {
//		if (gearsLS.get()) //checks if a gear is collected.
//			return true;	//returns true if collected.
//		else
//			return false;  //returns false if not.
//	}
//
//	public void initDefaultCommand() {
//		// Set the default command for a subsystem here.
//	}
//	public static double getXdif(){
//		Xdif = SmartDashboard.getNumber("Xdifferece",-9001);
//		return Xdif;
//	}
//
//	public static double getDist() {
//		double Dist = SmartDashboard.getNumber("distToGear",0);
//		return Dist;
//	}
//}
