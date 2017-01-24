package org.usfirst.frc.team5987.robot.subsystems;

import org.usfirst.frc.team5987.robot.RobotMap;
import org.usfirst.frc.team5987.robot.commands.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *tioud
 *@author student
 *@version 1.0v
 *
 *I want to be the vary beast like no one ever was,
 *to catch them is my real test to train them is my pass.
 *I will travel across the land searching for a while,
 *POKEMON to understand the power that inside!!!
 *POKEMON;
 *
 *------------------------------------------------------------------------------------------
 *
 *	#include <stdio.h>
 *
 *	#define NINE 1 + 8
 *	#define SIX 5 + 1
 *
 *	public int main() {
 *
 *		printf("the answer of %d multiply by %d equals to %d!!!\n", NINE, SIX, NINE * SIX);
 *
 *		return 1;
 *	}
 *
 *------------------------------------------------------------------------------------------
 *
 *	console:
 *	
 *		the answer of 9 multiply by 6 equals to 42!!!
 *
 *------------------------------------------------------------------------------------------
 */
public class PutGearGroupCommand extends CommandGroup {
    
    public  PutGearGroupCommand() {
    	
    	//requires(RobotMap.drivingSubsystem);
    	requires(RobotMap.gearpusSubsystem);
    	
    	addSequential(new SetGearLockerCommand(false));
    	addSequential(new TurnClimberCommand(1));
    	addParallel(new TurnClimberCommand(1/16));
    	addSequential(new SetGearLockerCommand(true));

    }
}
