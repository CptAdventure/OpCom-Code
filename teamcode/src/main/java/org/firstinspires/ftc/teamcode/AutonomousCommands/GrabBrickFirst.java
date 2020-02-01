package org.firstinspires.ftc.teamcode.AutonomousCommands;

import org.firstinspires.ftc.teamcode.RobotComponents.Claw;
import org.firstinspires.ftc.teamcode.Utilities.Stopwatch;

public class GrabBrickFirst implements ICommand {
    private Claw claw;
    private boolean upDone;
    private boolean start = false;
    private boolean retracted = false;
    private boolean extended = false;

    private final int ABOVE_BRICK = 1750;

    public GrabBrickFirst(Claw claw) {
        this.claw = claw;
    }

    @Override
    public boolean Run() {
        if (!start) {
            claw.lift(-1);
            start = claw.down();
        } else {
            if (upDone) {
                if (extended) {
                    claw.extend(0);
                    if (claw.down()) {
                        claw.lift(0);
                        retracted = claw.extend(false, true, true) < 0;
                        if (retracted){
                            claw.extend(0);
                            return true;
                        }
                        return false;
                    } else {
                        claw.lift(false, true);
                    }
                } else {
                    claw.lift(0);
                    if (!retracted) {
                        claw.extend(false, true, true);
                    } else {
                        extended = claw.extend(true, false, true) > 10;
                    }
                    retracted = 2 <= claw.extended() || retracted;
                }
            } else {
                upDone = claw.lift(true, false) >= ABOVE_BRICK;
            }
        }
        return false;
    }
}
