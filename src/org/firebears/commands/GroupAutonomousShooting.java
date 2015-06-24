package org.firebears.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Autonomous command.  Best position to start would be halfway into the 
 * back of the pyramid.  This command should move forward and shoot all
 * frisbees into the top target.
 * 
 * @author Keith
 */
public class GroupAutonomousShooting extends CommandGroup {
    
        public boolean saveFiles = true;
    
    public GroupAutonomousShooting() {
        
        // Move forward into the pyramid.
        addSequential(new ChassisDriveToDistance(18));
        
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
