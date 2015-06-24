/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.firebears.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.firebears.Robot;

/**
 *
 * @author paul
 */
public class LoaderSavePosition extends Command {

    String m_positiion;

//    public LoaderSavePosition() {
//        // Use requires() here to declare subsystem dependencies
//        requires(Robot.loaderClimber);
//    }

    public LoaderSavePosition(String sPosition) {
        requires(Robot.loaderClimber);
        // Use requires() here to declare subsystem dependencies
        m_positiion = sPosition;
         System.out.println("LoaderSavePosition " + m_positiion);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
             System.out.println("Initialize " + m_positiion);
        Robot.loaderClimber.storeLoaderAngle(m_positiion);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //System.out.println("execute " + m_positiion);
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
