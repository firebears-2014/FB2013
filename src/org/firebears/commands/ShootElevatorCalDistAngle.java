/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.firebears.commands;

import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
import org.firebears.Robot;
import org.firebears.subsystems.Camera;

/**
 *
 * @author paul
 */
public class ShootElevatorCalDistAngle extends Command {

    Camera camera = Robot.camera;
    private ParticleAnalysisReport[] particleList = null;
    
    DriverStationLCD driverLCD = null;
    DriverStationLCD.Line displayLine = DriverStationLCD.Line.kUser2;

    public ShootElevatorCalDistAngle() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.shootElevator);
        driverLCD = DriverStationLCD.getInstance();
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        float Dist = 12;
        particleList = camera.getAllParticles();
        if (camera.hasPicture()) {
            Dist = (float) camera.getDistance();
            Robot.shootElevator.saveCalDistAngle(Dist);
           //driverLCD.println(displayLine, 2, "Distance = " + Dist + "    ");
        }
        else{
        driverLCD.println(displayLine, 2, "NO DISTANCE INFO, NOT REGISTERED!!!");
        }
        driverLCD.updateLCD();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
