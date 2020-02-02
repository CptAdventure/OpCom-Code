package org.firstinspires.ftc.teamcode.AutonomousCommands;

import org.firstinspires.ftc.teamcode.RobotComponents.Claw;

public class BrickGrab implements ICommand {
    private ICommand wait = new TimedWaitCommand(250);
    private Claw claw;
    private boolean direction;

    public BrickGrab (Claw claw, boolean direction) {
        this.claw = claw;
        this.direction = direction;
    }

    @Override
    public boolean Run() {
        if (direction) {
            claw.extend(true, false);
        }
        if (!direction) {
            claw.extend(false, true);
        }
        if (wait.Run()) {
            claw.extend(0);
            return true;
        }
        return false;
    }
}
