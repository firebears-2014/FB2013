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
public class ShootElevatorJogAngle extends Command {

    double m_jogAmount;

    public ShootElevatorJogAngle() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.shootElevator);
    }

    public ShootElevatorJogAngle(double jogAmount) {
        m_jogAmount = jogAmount;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.shootElevator.jogAngle(m_jogAmount);
    }

    // Called repeatedly when this Command is scheduled to run

    protected void execute() {
        Robot.shootElevator.jogAngle(m_jogAmount);
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
