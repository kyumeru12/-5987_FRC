package org.usfirst.frc.team5987.robot.subsystems;

import org.usfirst.frc.team5987.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 * Subsystem for the transportation of balls from storage to shooter
 *@author DorBrekhman
 *@version 0.2
 *
 *TODO add default command
 */
public class TransportSubsystem extends Subsystem {
    
    // define devices
	static Victor carrier;
//	static DigitalOutput ballElevator;
//	static DigitalInput carrierReceiver;
//	static DigitalOutput carrierTransmitter;
//	static DigitalInput nearShooterReceiver;
//	static DigitalOutput nearShooterTransmitter;
	
	private static int CARRIER_SPEED_FACTOR = 1;
	
	public TransportSubsystem() {
    	carrier = new Victor(RobotMap.carrierMotor);
//    	ballElevator = new DigitalOutput(RobotMap.ballElevatorMotor);
//    	carrierReceiver = new DigitalInput(RobotMap.carrierReceiver);
//    	nearShooterReceiver = new DigitalInput(RobotMap.nearShooterReceiver);
//    	carrierTransmitter = new DigitalOutput(RobotMap.carrierTransmitter);
//    	nearShooterTransmitter = new DigitalOutput(RobotMap.nearShooterTransmitter);
	}
	
    public void initDefaultCommand() {
    	// set the ports for the devices with RobotMap
    }
    /**
     * set the speed for the conveyor (masoa) that brings the balls to the ball elevator
     * @param speed for the motor (from 0 to 1)
     */
    public static void setCarrierSpeed(double speed){
    	carrier.set(speed * CARRIER_SPEED_FACTOR);
    }
    /**
     * turn or on off the ball elevator that brings the balls to the shooter
     * @param isOn true for turning on
     */
//    public static void setBallElevator(boolean isOn){
//    	ballElevator.set(isOn);
//    }
    /**
     * checks if the receiver that is in the end of the conveyor (masoa) 'sees' the laser of the transmitter
     * @return whether there is a ball in the end of the conveyor (masoa)
     */
//    public static boolean isBallInCarrier(){
//    	return carrierReceiver.get();
//    }
    /**
     * checks if the receiver that is near the shooter 'sees' the laser of the transmitter
     * @return whether there is a ball near the shooter
     */
//    public static boolean isBallNearShooter(){
//    	return nearShooterReceiver.get();
//    }
    /**
     * turn on the transmitter (lasers) of the conveyor (masoa) and the one near the shooter
     * @param isOn if true, turns the transmitters on
     */
//    public static void setTransmitters(boolean isOn){
//    	carrierTransmitter.set(isOn);
//    	nearShooterTransmitter.set(isOn);
//    }
    
}