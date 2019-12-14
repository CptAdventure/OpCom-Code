package org.firstinspires.ftc.teamcode.AutonomousCommands;

import org.firstinspires.ftc.teamcode.RobotComponents.Claw;

public class GoDownToBottom implements ICommand {

    Claw claw;

    public GoDownToBottom(Claw claw) {
        this.claw = claw;
    }

    @Override
    public boolean Run() {
        claw.lift(false,true);
        return claw.down();
    }
}
