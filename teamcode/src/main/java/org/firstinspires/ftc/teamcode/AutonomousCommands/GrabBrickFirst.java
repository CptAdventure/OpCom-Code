package org.firstinspires.ftc.teamcode.AutonomousCommands;

import org.firstinspires.ftc.teamcode.RobotComponents.Claw;
import org.firstinspires.ftc.teamcode.Utilities.Stopwatch;

public class GrabBrickFirst implements ICommand {
    private Claw claw;
    private boolean upDone;
    private Stopwatch timer;
    private Stopwatch timer2;
    private boolean i;

    private final int ABOVE_BRICK = 1500;
    private final int PAST_BRICK = 2500;

    public GrabBrickFirst(Claw claw) {
        this.claw = claw;
        timer = new Stopwatch();
        timer2 = new Stopwatch();
    }

    @Override
    public boolean Run() {
        if (upDone) {
            if (timer.getElapsedTime() >= PAST_BRICK) {
                timer.stop();
                claw.extend(0);
                i=claw.down();
                claw.lift(false, !i);
                if (i) {
                    if(timer2.getElapsedTime() >= 1000) {
                        claw.extend(false, true, true);
                    } else {
                        claw.extend(0);
                        return false;
                    }
                    if (timer2.getElapsedTime()==0) {
                        timer2.start();
                    }
                }
            } else {
                claw.extend(true, false, true);
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
