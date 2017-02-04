package org.usfirst.frc.team5987.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SDboardSubsystem extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	PowerDistributionPanel pdp = new PowerDistributionPanel();
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	
	private static double getDouble(String name){
		double Xdif = SmartDashboard.getNumber(name, -9001); // ACoolName was here
		if (!(Xdif == -9001)) {
			return Xdif;
		} else {
			// if there is no connection and the value is -9001 (Will
			DriverStation.reportError("SMART_DASHBOARD_ERROR: Unable to get " + name + "from smart dashboard", true);		// never happen cause Xdif is between 1 and -1) then it
			return 0;
		}
	}
	
	public double getXdifLift() {// get's the distance between the camera (and the
								// robot) and the lift marks on the field
		return getDouble("Gears X Difference"); // ACoolName was here
	}

	public static void setLeftSpeed(double speed) {
		SmartDashboard.putNumber("Left Side Wheels", speed);
	}

	public static void setRightSpeed(double speed) {
		SmartDashboard.putNumber("Right Side Wheels", speed);
	}

	public static void setGear(boolean b) {
		SmartDashboard.putBoolean("Gear Is In Place", b);
	}

	public static double getDistLift() {
		return getDouble("Distance From Lift");
	}
	
	public static boolean isLiftInFieldView() {
		boolean isGear = SmartDashboard.getBoolean("Gear In Field View", false);
		return isGear;
	}
	
	public static boolean isBoilerInFieldView() {
		boolean isGear = SmartDashboard.getBoolean("Boiler In Field View", false);
		return isGear;
	}
	
	public void getPDP() {
		for (int i = 0; i < 15; i++) {
			double current = pdp.getCurrent(i);
			SmartDashboard.putNumber("PDP Current: " + i, current);
		}
	}
	public double getKTurn(){
		return getDouble("K Turn");
	}
}
