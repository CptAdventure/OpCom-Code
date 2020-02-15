package org.firstinspires.ftc.teamcode.AutonomousCommands;

import org.firstinspires.ftc.teamcode.RobotComponents.Claw;

public class Lift implements ICommand {
    double activePosition;
    double position;
    boolean down = false;
    Claw claw;

    public Lift (double position, boolean down, Claw claw) {
        this.position = position;
        this.down = down;
        this.claw = claw;
    }

    @Override
    public boolean Run() {
        activePosition = claw.lift(!down, down);
        if (down == (activePosition <= position)) { // if down is false, activePosition > position
            claw.lift(0);
            return true;
        }
        return false;
    }
}
