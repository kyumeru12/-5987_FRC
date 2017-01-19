package org.usfirst.frc.team5987.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GetGearGroupCommand extends CommandGroup {
    
    public  GetGearGroupCommand() {
    	requires(sortingSubsystem);
    	requires(gearpusSubsystem);
    	
    	addParallel(new LockGearCommand(1));
    	addSequential(new TurnClimberCommand(45));
    	addSequential(new SignalHumanPlayerCommand());
    	addSequential(new WaitForGearCommand());
    	addSequential(new TurnClimberCommand(90));
    }
}
