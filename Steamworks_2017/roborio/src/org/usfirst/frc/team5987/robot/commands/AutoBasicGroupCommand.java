package org.usfirst.frc.team5987.robot.commands;

import org.usfirst.frc.team5987.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *@author Doron
 *@version 1.0v
 *
 *this is a generic auto command, use this to create your own autonomous command!
 */
public class AutoBasicGroupCommand extends CommandGroup {
    
	/**
	 * 
	 * @param initX the first point the robot drive to
	 * @param initY the first point the robot drive to
	 * @param isGear is the robot doing gears in auto mode
	 * @param shootingX the second point the robot drive to, if not necessary change to 0
	 * @param shootingY the second point the robot drive to, if not necessary change to 0
	 * @param isShoot is the robot shooting in auto mode
	 */
    public AutoBasicGroupCommand(double initX, double initY, boolean isGear, double shootingX, double shootingY, boolean isShoot) {
    	
    	requires(RobotMap.drivingSubsystem);
    	requires(RobotMap.gearpusSubsystem);
    	requires(RobotMap.transportingSubsystem);
    	requires(RobotMap.sdBoardSubsystem);
    	requires(RobotMap.shootingSubsystem);
    	
        addSequential(new Driveing4DaWinCommand(initX,initY));
        
        if (isGear) {
        	addSequential(new NavigateToLiftCommand());
        	addSequential(new PutGearGroupCommand());
        }
        
        addSequential(new Driveing4DaWinCommand(shootingX,shootingY));
        
        while (isShoot) {
        	addSequential(new shootGroupCommand());
        }
    }
    
    /**
     * 
     * @param initX the first point the robot drive to
     * @param initY the first point the robot drive to
     * 
     * the robot wont score any gears and wont shoot any balls with this constructor.
     */
    public AutoBasicGroupCommand(double initX, double initY) {
        this(initX,initY,false,0,0,false);
    }
    
    /**
     * 
     * @param initX the first point the robot drive to
	 * @param initY the first point the robot drive to
	 * @param isGear is the robot doing gears in auto mode
     * @param isShoot is the robot shooting in auto mode
     * 
     * the robot wont drive a second time with this constructor.
	 */
    public AutoBasicGroupCommand(double initX, double initY,boolean isGear, boolean isShoot) {
        this(initX,initY,isGear,0,0,isShoot);
    }

}
