package org.usfirst.frc.team5987.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team5987.robot.subsystems.InputOutput; // TO-DO : CHANGE NAME


/**
 * Navigate to gear, then put it in the lift.
 * Works from different distances and angles (~0.5 -> ~3 METER, +- ~30 DEGRESS from center)
 *@author DorBrekhman
 *@version 0.1
 */
public class GearGroupCommand extends CommandGroup {
    static double MAX_ANGLE_TO_TURN = 20; // max angle for turning to front of the lift
    static double MIN_DIST_TO_TURN = 0.5; // min distance in METER for turning to front of the lift
    
    public  GearGroupCommand() {
    	// get angle and distance (to front gear) from smartDashboard
    	angle = InputOutput.getAngleToGear(); // TO-DO : CHANGE NAME
    	distance = InputOutput.getDistToGear(); // TO-DO : CHANGE NAME
    	// check if you need to make a turn (if the angle is small, you can just align to the lift)
    	if (angle > MAX_ANGLE_TO_TURN || distance < MIN_DIST_TO_TURN){
    		// turn, drive to distance from lift, turn to center lift
    		addSequential(new GoToFrontGear()); // TO-DO : CHANGE NAME
    	}
    	// uses Xdifference (x value from center of the lift)
    	addSequential(new AlignToGear()); // TO-DO : CHANGE NAME
    	// put the gear in the lift
    	addSequential(new PutGear()); // TO-DO : CHANGE NAME
    }
}
