/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.firebears.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.firebears.Robot;
import org.firebears.RobotMap;
import org.firebears.subsystems.Lights;

/**
 *
 * @author paul
 */
public class TriggerContinousWhileHeld extends Command {

    final static int STARTUP_STATE = 0;
    final static int PUSH_STATE = 1;
    final static int RETRACT_STATE = 2;
    final static int SPINUP_STATE = 4;
    double PUSH_TIME = RobotMap.PUSH_TIME;
    double RETRACT_TIME = RobotMap.RETRACT_TIME;
    double RETRACT_SPEED = RobotMap.RETRACT_SPEED;
    double PUSH_SPEED = RobotMap.PUSH_SPEED;
    double STOP = RobotMap.STOP;
    int state;
    double timeout;
    
    TriggerRetract retractCommand = new TriggerRetract();

    public TriggerContinousWhileHeld() {
        requires(Robot.shooter);
        requires(Robot.trigger);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        state = STARTUP_STATE;
//        System.out.println("initialize"); 
        timeout = timeSinceInitialized() + 0.7;

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

        switch (state) {
            
            case STARTUP_STATE:
                Robot.lights.lightProgram(Lights.SHOOTER_WINDUP);
//                System.out.println("\tstartup\t" + timeout);
                Robot.shooter.setSpeed(1.0);
                if (timeSinceInitialized() > timeout) {
                    state = SPINUP_STATE;
                    timeout = timeSinceInitialized() + 1.0;
                }
                break;
                
            case SPINUP_STATE:
                Robot.lights.lightProgram(Lights.SHOOTER_WINDUP);
                if (timeSinceInitialized() > timeout) {
                    state = PUSH_STATE;
                    timeout = timeSinceInitialized() + PUSH_TIME + .08;
                }
                break;
                
            case PUSH_STATE:
                Robot.lights.lightProgram(Lights.SHOOT);
                Robot.trigger.TriggerMove(PUSH_SPEED);
//                System.out.println("\tpush\t" + timeout);
                if (timeSinceInitialized() > timeout) {
                    Robot.trigger.TriggerMove(STOP);
                    state = RETRACT_STATE;
                    timeout = timeSinceInitialized() + RETRACT_TIME;
                }
                break;
                
            case RETRACT_STATE:
                Robot.trigger.TriggerMove(RETRACT_SPEED);
//                 System.out.println("\tretract\t" + timeout);
                if (timeSinceInitialized() > timeout) {
                    state = PUSH_STATE;
                    timeout = timeSinceInitialized() + PUSH_TIME + .08;
                    Robot.trigger.TriggerMove(0);
                }
        }
    }
        // Make this return true when this Command no longer needs to run execute()
    

    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
//        System.out.println("end");
        Robot.shooter.setSpeed(0.2);
        retractCommand.start();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
//        System.out.println("INTERUPTED");
        end();
    }
}
