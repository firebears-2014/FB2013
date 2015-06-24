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
public class ShootElevatorClearDAPref extends Command{
    
    public ShootElevatorClearDAPref() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.shootElevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.shootElevator.clearCalPreferences();
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
