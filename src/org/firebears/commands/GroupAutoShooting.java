package org.firebears.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Autonomous command.  Best position to start would be halfway into the 
 * back of the pyramid.  This command should move forward and shoot all
 * frisbees into the top target.
 * 
 * @author Keith
 */
public class GroupAutoShooting extends CommandGroup {
    
        public boolean saveFiles = false;
    
    public GroupAutoShooting() {
        
        // Take a picture and point towards the target
        addSequential(new GroupAutoShootSetup(saveFiles));
        
        // Fire all frisbees into the target.
//        addSequential(new TriggerFireAtTarget());
        
        addSequential(new TriggerPush());
        addSequential(new TriggerRetract());
                
        addSequential(new TriggerPush());
        addSequential(new TriggerRetract());
                
        addSequential(new TriggerPush());
        addSequential(new TriggerRetract());
                
        addSequential(new TriggerPush());
        addSequential(new TriggerRetract());
        
        // Spin down
        addSequential(new ShooterSetSpinSpeed(0.2));
    }
}
