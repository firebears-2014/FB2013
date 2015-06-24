package org.firebears.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Simplified autonomous mode. Shott three times. No vision.
 *
 * @author Keith
 */
public class GroupAutonomousShootingShort extends CommandGroup {

    public GroupAutonomousShootingShort() {

        // Fire all frisbees into the target.

        addSequential(new ShooterSetSpinSpeed(1.0));

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
