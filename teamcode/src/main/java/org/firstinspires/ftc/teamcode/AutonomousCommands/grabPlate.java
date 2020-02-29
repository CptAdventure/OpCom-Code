package org.firstinspires.ftc.teamcode.AutonomousCommands;

import org.firstinspires.ftc.teamcode.RobotComponents.Claw;

public class grabPlate implements ICommand {

    private Claw claw;
    private boolean down, doStuff = false;

    public grabPlate(Claw claw, boolean down){
        this.claw = claw;
        this.down = down;
    }

    @Override
    public boolean Run() {
        if (claw.miniClaw(false) == down) return true;
        doStuff = !doStuff;
        return claw.miniClaw(doStuff) == down;
    }
}
