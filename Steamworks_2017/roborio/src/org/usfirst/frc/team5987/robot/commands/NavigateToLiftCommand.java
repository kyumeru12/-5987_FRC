package org.usfirst.frc.team5987.robot.commands;
import org.usfirst.frc.team5987.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *@author A Cool Author
 *@version 9000.1
 */
//Gets the X difference from the raspberry pi and turns accordingly
public class NavigateToLiftCommand extends Command {

    public NavigateToLiftCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double speedL;
		double speedR;
		double Xdif = RobotMap.sdBoardSubsystem.getXdifLift(); //getting the distance from the edge of the camera from Python (value from 1 to -1)
		double dist = RobotMap.sdBoardSubsystem.getDistLift(); //get the distance between the target and the robot
		double KTurn = SmartDashboard.getNumber("K Turn",0); // a variable decided by the smartDashboard to change the angles of turning (value from 1 to 0)
		double startingDist = dist;// getting the distance between the target and the robot but this one doesn't change from the moment the command started
		
		while(dist<0.05){
			Xdif = RobotMap.sdBoardSubsystem.getXdifLift(); //updating the 'Xdif' every loop
			dist = RobotMap.sdBoardSubsystem.getDistLift(); //getting the *current* distance from the target
			KTurn = RobotMap.sdBoardSubsystem.getKTurn(); //for Debugging if the variable is changed mid-testing
			if(!(Xdif==-9001)){
				if(Xdif>0){// while turning left
					speedL = (dist / startingDist) * (-Xdif) * KTurn;//    calculations for the speed of the left side wheels of the robot 
					speedR = (dist / startingDist) * (speedL / 2) * KTurn;// calculations for the speed of the right side wheels of the robot
					RobotMap.drivingSubsystem.drive(speedL, speedR); //turning the robot according to the calculations above
				}else if(Xdif<0){//while turning right
					speedR = (dist / startingDist) * (Xdif) * KTurn;//    calculations for the speed of the right side wheels of the robot
					speedL = (dist / startingDist) * (speedR / 2) * KTurn;//calculations for the speed of the left side wheels of the robot
					RobotMap.drivingSubsystem.drive(speedL, speedR);//turning the robot according to the calculations above
				}
			}
    	}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}