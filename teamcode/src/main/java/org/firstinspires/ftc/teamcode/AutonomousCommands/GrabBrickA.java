package org.firstinspires.ftc.teamcode.AutonomousCommands;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Autonomous.brickMoveData;
import org.firstinspires.ftc.teamcode.RobotComponents.Claw;

public class GrabBrickA implements ICommand {
    private Claw claw;
    private boolean upDone;
    private boolean start = false;
    private boolean retracted = false;
    private boolean extended = false;
    private boolean pulse = false;
    private Telemetry t;

    public GrabBrickA(Claw claw, Telemetry t) {
        this.claw = claw;
        this.t = t;
    }

    @Override
    public boolean Run() {
        if (!start) { // This makes sure the lift is down
            claw.lift(-1);
            start = claw.down();
        } else {
            if (upDone) {
                if (extended) {
                    if (claw.down()) {
                        claw.lift(0); // Stop lowering
                        claw.extend(false, true);
                        retracted = claw.extendVal() < brickMoveData.BRICK; // This integer is approx. brick width
                        if (retracted){
                            claw.extend(0); // Stop retracting
                            return true; // Return (next)
                        }
                        return false; // Return (continue)
                    } else {
                        claw.extend(0); // Stop extending
                        claw.lift(false, true); // Lower
                    }
                } else {
                    claw.lift(0); // Stop lifting
                    if (!retracted) {
                        retracted = 2 <= claw.extended(); // When the inner sensor is on
                        claw.extend(false, true); // Retract (to the innermost value)
                    } else {
                        claw.extend(true, false); // Extend to the
                        extended = (claw.extendVal() > brickMoveData.PAST_BRICK) || ((claw.extended() % 2) == 1); // value of 12.5
                    }
                }
            } else {
                upDone = claw.lift(true, false) >= brickMoveData.ABOVE_BRICK; // When above the brick, set upDone
            }
        }
        t.addData("DEBUG1", claw.extendVal()); // Debug Values
        t.addData("DEBUG2", claw.extended());
        return false; // Return (continue)
    }
}
