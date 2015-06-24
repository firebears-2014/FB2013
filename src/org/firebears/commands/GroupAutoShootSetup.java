package org.firebears.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.firebears.Robot;
import org.firebears.subsystems.Lights;

/**
 * Take a picture and prepare the robot for shooting, but doesn't yet fire the
 * trigger. <p>
 *
 * @author Keith
 */
public class GroupAutoShootSetup extends CommandGroup {

    public boolean saveFiles = false;
    public double shooterSpinSpeed = 1.0;

    public GroupAutoShootSetup() {
        this(false);
    }

    public GroupAutoShootSetup(boolean save) {
        super("AutoShootSetup");
        this.saveFiles = save;

        // Take one picture and attempt to find a target.
        addSequential(new CameraTakePicture(saveFiles));

        // Turn the robot towards the target, if a target is visible.
        // Continues executing until we are facing the target.
        addParallel(new ChassisTurnToTarget());

        // Raise the elevator to the target, if a target is visible.
        // Continues executing(based on the PIDController) until on target.
        addParallel(new ShootElevatorGoToAngle());

        // Sets the spinner setpoint in the shooter's PIDController.
        addParallel(new ShooterSetSpinSpeed(shooterSpinSpeed));
    }
    
       protected void initialize() {
           Robot.lights.lightProgram(Lights.AUTO_TARGETING);
    }
}
