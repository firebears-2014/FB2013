/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.firebears.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author paul
 */
public class GroupTriggerFire4 extends CommandGroup {
    
    public GroupTriggerFire4() {
        
        // Spin up
        addSequential(new ShooterSetSpinSpeed(1.0));
        
        // Shoot
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
