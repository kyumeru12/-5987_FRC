package org.usfirst.frc.team5987.robot.commands;


import org.usfirst.frc.team5987.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *@author DorBrekhman
 *Change the shooting speed of both motors to the wanted constant speed for shooting
 *TODO test
 */

public class ChangeShootSpeedConstCommand extends Command {
	// CONSTANTS
	public double ACCURATE_SHOOT_SPEED = 1000; // the wanted speed for the motors (in which the ball's speed measurements were taken)
	public double MAX_SPEED_DIFF = 1000; // used to convert the speed difference into the motor's speed
	public double ACCEPTABLE_DELTA_SPEED = 10; // the difference speed (between the current and wanted) which is close enough to the wanted 
	
	private double leftSpeedDiff;
	private double rightSpeedDiff;
    public ChangeShootSpeedConstCommand() {
        // Use requires() here to declare subsystem dependencies
		requires(RobotMap.shootingSubsystem);
		requires(RobotMap.sdBoardSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }
    
    public static double limitSpeed(double speed){
    	if(speed > 1)
    		return 1.0;
    	if(speed < 1)
    		return -1.0;
    	return speed;
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// Set the left motor speed to the difference between the wanted angle and current one divided by a factor 
    	leftSpeedDiff = ACCURATE_SHOOT_SPEED -RobotMap.shootingSubsystem.getLeftSpeed();
    	double speedL = limitSpeed(leftSpeedDiff / MAX_SPEED_DIFF);
    	RobotMap.shootingSubsystem.setLeftSpeed(speedL);
    	SmartDashboard.putNumber("SpeedL", speedL);

    	// Set the left motor speed to the difference between the wanted angle and current one divided by a factor 
    	rightSpeedDiff = ACCURATE_SHOOT_SPEED - RobotMap.shootingSubsystem.getRightSpeed();
    	double speedR = limitSpeed(rightSpeedDiff / MAX_SPEED_DIFF);
    	RobotMap.shootingSubsystem.setRightSpeed(speedR);
    	
    	// show the data in the SmartDashboard
    	RobotMap.sdBoardSubsystem.setLeftSpeed(RobotMap.shootingSubsystem.getLeftSpeed());
    	RobotMap.sdBoardSubsystem.setRightSpeed(RobotMap.shootingSubsystem.getRightSpeed()); 
    	
    	Timer.delay(0.05);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	// exit command if the speed is close enough to the wanted speed in both motors
    	boolean rightDiffOK = Math.abs(rightSpeedDiff) > ACCEPTABLE_DELTA_SPEED;
    	boolean leftDiffOK = Math.abs(leftSpeedDiff) > ACCEPTABLE_DELTA_SPEED;
    	
        return rightDiffOK && leftDiffOK;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
