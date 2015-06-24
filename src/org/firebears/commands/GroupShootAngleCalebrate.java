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
public class GroupShootAngleCalebrate extends CommandGroup {
    
    public GroupShootAngleCalebrate() {
        addSequential(new CameraTakePicture());
        addSequential(new ShootElevatorCalDistAngle());
    }
}
