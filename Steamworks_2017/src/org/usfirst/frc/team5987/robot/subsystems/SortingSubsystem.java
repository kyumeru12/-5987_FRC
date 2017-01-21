package org.usfirst.frc.team5987.robot.subsystems;

import org.usfirst.frc.team5987.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *@author Doron
 *@version 1.1v
 *
 *TODO add default command
 */
public class SortingSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	private Servo sortingServo;
	public static DigitalInput gears;
	
	public SortingSubsystem() {
		sortingServo = new Servo(RobotMap.sortingServoPort);
		gears = new DigitalInput(RobotMap.gearLimitSwitch);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    }
    
    public void setServoPosition(double position) {
    	sortingServo.set(position);
    }
    
    public double getServoPosition() {
    	return sortingServo.get();
    }
    
    public static boolean isGear() {
		return gears.get();
	}
    
    //Approximate will take around 1.25x10^19 years
    public boolean zhoo() {
    	int[] toSort = new int[32];
    	for (int i = 0; i < toSort.length; i++) {
    		toSort[i] = (int) (Math.random()*toSort.length);
    	}
    	while(!isSorted(toSort))
    		suffle(toSort);
    	return true;
    }
    
    public boolean isSorted(int[] toSort) {
    	for (int i = 0; i < toSort.length - 1; i++)
    		if (toSort[i] > toSort[i+1])
    			return false;
    	return true;
    }
    
    public void suffle(int[] toSort) {
    	for (int i = 0; i < toSort.length; i++) {
    		int index1 = (int) (Math.random()*toSort.length);
    		int index2 = (int) (Math.random()*toSort.length);
    		int tmp = toSort[index1];
    		toSort[index1] = toSort[index2];
    		toSort[index2] = tmp;
    	}
    }
}