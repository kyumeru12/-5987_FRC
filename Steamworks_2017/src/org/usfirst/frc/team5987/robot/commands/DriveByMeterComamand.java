package org.usfirst.frc.team5987.robot.commands;

import org.usfirst.frc.team5987.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveByMeterComamand extends CommandGroup {
    
    public  DriveByMeterComamand(double xDistence, double yDistence) {
    	requires(RobotMap.driveSubsystem);
    	double distence = xDistence*xDistence+yDistence*yDistence;
    	while (distence > 0.1) {
    		distence -= (RobotMap.driveSubsystem.getLeftEncoder()+RobotMap.driveSubsystem.getRightEncoder())/2;
    		addSequential(new RotateCommand(Math.tan(yDistence/xDistence)));
            addSequential(new DriveByDistence(distence));
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
