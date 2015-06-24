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
public class LoaderGoToPosition extends Command {

    String m_position;

    public LoaderGoToPosition() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.loaderClimber);

    }

    public LoaderGoToPosition(String sPosition) {//the button will provide the position
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        m_position = sPosition;
        System.out.println(m_position);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        //Robot.loaderClimber.enablePID();//do we need this?
        System.out.println("LoaderGoToPosition: " + m_position);
        Robot.loaderClimber.goToPosition(m_position);//parameter of button is preference reference
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.loaderClimber.anglegood();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
