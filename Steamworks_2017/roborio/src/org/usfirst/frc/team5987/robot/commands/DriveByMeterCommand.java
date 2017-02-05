package org.usfirst.frc.team5987.robot.commands;

import org.usfirst.frc.team5987.robot.RobotMap;
import org.usfirst.frc.team5987.robot.commands.RotateByAngleCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveByMeterCommand extends CommandGroup {
    
    public  DriveByMeterCommand(double xDistance, double yDistance) {
    	requires(RobotMap.drivingSubsystem);
    	double distance = xDistance * xDistance + yDistance * yDistance;
    	while (distance > 0.1) {
    		distance -= (RobotMap.drivingSubsystem.getLeftEncoder() + RobotMap.drivingSubsystem.getRightEncoder()) / 2;
    		addSequential(new RotateByAngleCommand(Math.tan(yDistance / xDistance)));
            addSequential(new ChangeSpeedByLeftDistCommand(distance));
    	}
        
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}