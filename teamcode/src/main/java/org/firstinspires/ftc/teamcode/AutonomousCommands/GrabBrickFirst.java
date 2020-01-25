package org.firstinspires.ftc.teamcode.AutonomousCommands;

import org.firstinspires.ftc.teamcode.RobotComponents.Claw;
import org.firstinspires.ftc.teamcode.Utilities.Stopwatch;

public class GrabBrickFirst implements ICommand {
    private Claw claw;
    private boolean upDone;
    private Stopwatch timer;
    private boolean i;

    private final int ABOVE_BRICK = 1000;
    private final int PAST_BRICK = 2500;

    public GrabBrickFirst(Claw claw) {
        this.claw = claw;
        timer = new Stopwatch();
    }

    @Override
    public boolean Run() {
        if (upDone) {
            if (timer.getElapsedTime() >= PAST_BRICK) {
                timer.stop();
                claw.extend(0);
                i=claw.down();
                claw.lift(false, !i);
                return i;
            } else {
                claw.extend(true, false);
                claw.lift(0);
            }
            if (timer.getElapsedTime()==0) {
                timer.start();
            }
        } else {
            upDone = claw.lift(true, false) > ABOVE_BRICK;
        }
        return false;
    }
}
