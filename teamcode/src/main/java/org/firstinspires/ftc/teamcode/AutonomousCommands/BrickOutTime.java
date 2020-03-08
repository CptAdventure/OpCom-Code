package org.firstinspires.ftc.teamcode.AutonomousCommands;

import org.firstinspires.ftc.teamcode.RobotComponents.Claw;

public class BrickOutTime implements ICommand {
    private Claw claw;
    private ICommand timer;
    private boolean in;

    public BrickOutTime(Claw claw, int time, boolean in) { // Direction: Extend when true, Retract when false
        this.claw = claw;
        timer = new TimedWaitCommand(time);
        this.in = in;
    }

    @Override
    public boolean Run() {
        claw.extend(!in, in);
        if (timer.Run()) { // if in is false, claw.extend(extend) > position
            claw.extend(0);
            return true;
        }
        return false;
    }
}
