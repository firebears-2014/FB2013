package org.firebears.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Fire one Frisbee at a visible target. 
 */
public class GroupTriggerFireAtTarget extends CommandGroup {


    public GroupTriggerFireAtTarget() {
        addSequential(new TriggerPush());
        addSequential(new TriggerRetract());
    }

}
