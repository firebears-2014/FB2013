/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.firebears.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.firebears.Robot;
import org.firebears.RobotMap;

/**
 *
 * @author paul
 */
public class TriggerRetract extends Command {

    double RETRACT_TIME = RobotMap.RETRACT_TIME;//Stops with jag limit switch, time includes shooter spinner recovery time
    final static double RETRACT_SPEED = RobotMap.RETRACT_SPEED;
    final static double STOP = RobotMap.STOP;

    public TriggerRetract(double t) {
        requires(Robot.trigger);
        //RETRACT_TIME = t;
    }

    public TriggerRetract() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.trigger);
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first RETRACT_TIME
    protected void initialize() {
        setTimeout(RETRACT_TIME);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.trigger.TriggerMove(RETRACT_SPEED);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.trigger.TriggerMove(STOP);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        Robot.trigger.TriggerMove(STOP);
    }
}
