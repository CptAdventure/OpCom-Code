package org.firstinspires.ftc.teamcode.AutonomousCommands;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RobotComponents.Claw;
import org.firstinspires.ftc.teamcode.Utilities.Stopwatch;

public class GrabBrickFirst implements ICommand {
    private Claw claw;
    private boolean upDone;
    private boolean start = false;
    private boolean retracted = false;
    private boolean extended = false;
    private boolean pulse = false;
    private Telemetry t;

    private final int ABOVE_BRICK = 1750;

    public GrabBrickFirst(Claw claw, Telemetry t) {
        this.claw = claw;
        this.t = t;
    }

    @Override
    public boolean Run() {
        if (!start) {
            claw.lift(-1);
            start = claw.down();
        } else {
            if (upDone) {
                if (extended) {
                    if (claw.down()) {
                        claw.lift(0);
                        if (pulse) {
                            claw.extend(false, true);
                            pulse = false;
                        }
                        retracted = claw.extendVal() < 5;
                        if (retracted){
                            claw.extend(0);
                            return true;
                        }
                        return false;
                    } else {
                        if(!pulse) {
                            claw.extend(0);
                            claw.lift(false, true);
                            pulse = true;
                        }
                    }
                } else {
                    claw.lift(0);
                    if (!retracted) {
                        retracted = 2 <= claw.extended();
                        claw.extend(false, true);
                    } else {
                        if (pulse) {
                            claw.extend(true, false);
                            pulse = false;
                        }
                        extended = claw.extendVal() > 15;
                    }
                }
            } else {
                if(!pulse) {
                    upDone = claw.lift(true, false) >= ABOVE_BRICK;
                    pulse = true;
                }
            }
        }
        return false;
    }
}
