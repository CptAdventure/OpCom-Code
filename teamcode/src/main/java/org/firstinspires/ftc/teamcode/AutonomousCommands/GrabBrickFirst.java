package org.firstinspires.ftc.teamcode.AutonomousCommands;

import org.firstinspires.ftc.teamcode.RobotComponents.Claw;
import org.firstinspires.ftc.teamcode.Utilities.Stopwatch;

public class GrabBrickFirst implements ICommand {
    private Claw claw;
    private boolean upDone;
    private Stopwatch timer;
    private Stopwatch timer2;
    private boolean start = false;
    private boolean retracted = false;

    private final int ABOVE_BRICK = 2000;
    private final int PAST_BRICK = 2500;

    public GrabBrickFirst(Claw claw) {
        this.claw = claw;
        timer = new Stopwatch();
        timer2 = new Stopwatch();
    }

    @Override
    public boolean Run() {
        if (!start) {
            claw.lift(-1);
            start = claw.down();
        } else {
            if (upDone) {
                if (timer.getElapsedTime() >= PAST_BRICK) {
                    timer.stop();
                    claw.extend(0);
                    if (claw.down()) {
                        if (timer2.getElapsedTime() < 1) timer2.start();
                        claw.lift(0);
                        retracted = claw.extend(false, true, true) < 3;
                        if (retracted){
                            claw.extend(0);
                            return true;
                        }
                        return false;
                    } else {
                        claw.lift(false, true);
                    }
                } else {
                    if (!retracted) {
                        claw.extend(false, true, true);
                    } else {
                        claw.extend(true, false, true);
                        claw.lift(0);
                    }
                    retracted = 2 <= claw.extended();
                }
                if (timer.getElapsedTime() == 0) timer.start();
            } else {
                upDone = claw.lift(true, false) >= ABOVE_BRICK;
            }
        }
        return false;
    }
}
