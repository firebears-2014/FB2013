/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.firebears.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
import org.firebears.Robot;
import org.firebears.subsystems.Camera;

/**
 *
 * @author paul
 */
public class ShootElevatorGoToAngle extends Command {

    Camera camera = Robot.camera;
    private ParticleAnalysisReport[] particleList = null;
    double m_dist = 0;

    public ShootElevatorGoToAngle() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.shootElevator);
        requires(Robot.camera);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        //Robot.shootElevator.enablePID();
        particleList = camera.getAllParticles(); 
       if(camera.hasPicture()){
           m_dist = camera.getDistance();
           Robot.shootElevator.setSetpointShootAngle(m_dist);
       }else{
          
           System.out.println("No picture seen");
       }
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
