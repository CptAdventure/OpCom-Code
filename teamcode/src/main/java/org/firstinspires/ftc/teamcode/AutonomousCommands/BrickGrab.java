package org.firstinspires.ftc.teamcode.AutonomousCommands;

import org.firstinspires.ftc.teamcode.RobotComponents.Claw;

public class BrickGrab implements ICommand {
    private ICommand wait = new TimedWaitCommand(500);
    private Claw claw;
    private boolean direction;

    public BrickGrab (Claw claw, boolean direction) { // Direction: Extend when true, Retract when false
        this.claw = claw;
        this.direction = direction;
    }

    @Override
    public boolean Run() {
        claw.extend(direction, !direction);
        if (wait.Run()) {
            claw.extend(0);
            return true;
        }
        return false;
    }
}
