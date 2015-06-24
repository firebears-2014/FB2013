/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.firebears.commands;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *  Command for testing the lights.  
 *  This command sets one digital output to a given value.
 */
public class LightTest extends Command {
    
    final DigitalOutput line;
    final boolean value;
    
    public LightTest(DigitalOutput l, boolean b) {
        this.line = l;
        this.value = b;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        this.line.set(this.value);
        System.out.println("::: " + line + " " + value);
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
        end();
    }
}
