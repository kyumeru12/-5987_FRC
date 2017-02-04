package org.usfirst.frc.team5987.robot.commands;

import org.usfirst.frc.team5987.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;
/**
 *@author doron
 *
 *isnt it ovaise... (this command get the Gear...)
 *
 *TODO add drive to lift
 */
public class GetGearGroupCommand extends CommandGroup {
    
    public  GetGearGroupCommand() {
    	requires(RobotMap.sortingSubsystem);
    	requires(RobotMap.gearpusSubsystem);
    	
    	addParallel(new SetGearLockerCommand(true));
    	addSequential(new TurnClimberCommand(45));
    	//addSequential(new SignalHumanPlayerCommand());
    	addSequential(new WaitForGearCommand());
    	addSequential(new TurnClimberCommand(90));
    }
}
