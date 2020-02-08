package org.firstinspires.ftc.teamcode.QuickFunctions;

import org.firstinspires.ftc.teamcode.RobotComponents.Claw;

public class ButtonEject {

    private Claw claw;
    private boolean half = false;
    private boolean going = false;

    public ButtonEject ( Claw claw ) {
        this.claw = claw;
    }

    public boolean run ( boolean start ) {
        going = going || start;
        if (going) {
            if (!half) {
                claw.extend(true, false);
                if (claw.extendVal() >= 7.5) {
                    claw.rotate(true);
                } else {
                    claw.rotate(false);
                }
                if ((claw.extended() % 2) == 1) {
                    claw.open(true);
                    claw.open(false);
                    half = true;
                }
            } else {
                claw.extend(false, true);
                if (claw.extendVal() <= 7.5) {
                    claw.rotate(true);
                } else {
                    claw.rotate(false);
                }
                if (claw.extended()>=2) {
                    claw.open(true);
                    claw.open(false);
                    half = false;
                    going = false;
                }
            }
        }
        return going;
    }

}
