package org.usfirst.frc.team5987.robot.subsystems;

import org.usfirst.frc.team5987.robot.RobotMap;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team5987.robot.commands.*;;

/**
 *@author DorBrekhman
 *@version 0.1
 *
 *TODO add Encoders and set default command
 */

public class DrivingSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	static Victor leftRearMotor;
	static Victor leftFrontMotor;
	static Victor rightRearMotor;
	static Victor rightFrontMotor;
	final static double SPEED_FACTOR = 1; // the speed is multiplied by this factor
	
	public DrivingSubsystem() {
    	// set ports for the victors using the preassigned values of the RobotMap
    	leftRearMotor = new Victor(RobotMap.leftRearMotor);
    	leftFrontMotor = new Victor(RobotMap.leftFrontMotor);
    	rightRearMotor = new Victor(RobotMap.rightRearMotor);
    	rightFrontMotor = new Victor(RobotMap.rightFrontMotor);
    	
    	// set the timeout used for updating the speed
    	leftRearMotor.setExpiration(0.05);
    	leftFrontMotor.setExpiration(0.05);
    	rightRearMotor.setExpiration(0.05);
    	rightFrontMotor.setExpiration(0.05);
    	
    	// the rear motors are inverted because they are placed in the opposite direction in the robot
    	leftRearMotor.setInverted(true);
    	rightRearMotor.setInverted(true);
	}
	
    public void initDefaultCommand() {
    	
    }
    
    public void setLeft(double speed){
    	speed *= SPEED_FACTOR;
    	leftRearMotor.set(speed);
    	leftFrontMotor.set(speed);
    }
    
    public void setRight(double speed){
    	speed *= SPEED_FACTOR;
    	rightRearMotor.set(speed);
    	rightFrontMotor.set(speed);
    }
    
    /**
     * Set speed for both motors (-1 < speed < 1)
     * @param speedL speed of left motors
     * @param speedR speed of right motors
     */
    public void drive(double speedL, double speedR){
    	setLeft(speedL);
    	setRight(speedR);
    }
    
    /**
     * turns the robot in place 
     * @param if positive, turn right, else turns left (range of -1 to 1)
     */
    public void turnInPlace(double speed){
    	setLeft(speed);
    	setRight(-speed);
    }
    /**
     * turn right the robot in place
     * @param speed for turning (-1 < speed < 1)
     */
    public void turnRight(double speed){
    	turnInPlace(speed);
    }
    
    /**
     * turn left the robot in place
     * @param speed for turning (-1 < speed < 1)
     */
    public void turnLeft(double speed){
    	turnInPlace(-speed);
    }
    
    /**
     * move the robot forward
     * @param speed for moving (-1 < speed < 1)
     */
    public void forward(){
    	setLeft(1);
    	setRight(1);
    }
    
    /**
     * move the robot backwards
     * @param speed for moving (-1 < speed < 1)
     */
    public void backwards(){
    	setLeft(-1);
    	setRight(-1);
    }
    
    /**
     * stop the robot (set the speed of all motors to 0)
     */
    public void stop(){
    	setLeft(0);
    	setRight(0);
    }
    
    public static double getLeftEncoder() {
    	
    	return 0;
    }
    
    public static double getRightEncoder() {
    	
    	return 0;
    }
}