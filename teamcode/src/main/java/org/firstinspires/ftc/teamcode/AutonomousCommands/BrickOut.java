package org.firstinspires.ftc.teamcode.AutonomousCommands;

import org.firstinspires.ftc.teamcode.RobotComponents.Claw;

public class BrickOut implements ICommand {
    private ICommand wait = new TimedWaitCommand(250);
    private Claw claw;
    private float position;
    private boolean in;

    public BrickOut(Claw claw, float position, boolean in) { // Direction: Extend when true, Retract when false
        this.claw = claw;
        this.position = position;
        this.in = in;
    }

    @Override
    public boolean Run() {
        if (in == (claw.extend(!in, in) <= position)) { // if in is false, claw.extend(extend) > position
            claw.extend(0);
            return true;
        }
        return false;
    }
}
