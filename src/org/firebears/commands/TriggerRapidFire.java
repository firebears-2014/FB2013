package org.firebears.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.firebears.Robot;

public class TriggerRapidFire extends Command {

    static final int PUSHING_STATE = 1;
    static final int RETRACTING_STATE = 2;
    int state = PUSHING_STATE;

    
    
    
    protected void initialize() {
        state = PUSHING_STATE;
        setTimeout(TriggerPush.PUSH_TIME);
    }

    
    
    
    protected void execute() {
        switch (state) {

            case PUSHING_STATE:
                Robot.trigger.TriggerMove(TriggerPush.PUSH_SPEED);
                if (isTimedOut()) {
                    state = RETRACTING_STATE;
                    setTimeout(0.5);
                }
                break;

            case RETRACTING_STATE:
                Robot.trigger.TriggerMove(TriggerRetract.RETRACT_SPEED);
                if (isTimedOut()) {
                    state = PUSHING_STATE;
                    setTimeout(TriggerPush.PUSH_TIME);
                }
                break;

        }
    }

    
    
    
    protected boolean isFinished() {
        return false;
    }

    
    
    
    protected void end() {
        Robot.trigger.TriggerMove(TriggerRetract.RETRACT_SPEED);
    }

    
    
    
    protected void interrupted() {
        end();
    }
}
