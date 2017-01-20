package org.usfirst.frc.team5987.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team5987.robot.RobotMap;
/**
 *
 */
public class GetGearGroupCommand extends CommandGroup {
    
    public  GetGearGroupCommand() {
    	requires(RobotMap.sortingSubsystem);
    	requires(RobotMap.gearpusSubsystem);
    	
    	addParallel(new SetGearLockerCommand(1));
    	addSequential(new TurnClimberCommand(45));
    	addSequential(new SignalHumanPlayerCommand());
    	addSequential(new WaitForGearCommand());
    	addSequential(new TurnClimberCommand(90));
    }
}
