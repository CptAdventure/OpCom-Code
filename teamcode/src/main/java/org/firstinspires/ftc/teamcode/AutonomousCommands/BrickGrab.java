package org.firstinspires.ftc.teamcode.AutonomousCommands;

import org.firstinspires.ftc.teamcode.RobotComponents.Claw;

public class BrickGrab implements ICommand {
    private ICommand wait = new TimedWaitCommand(50);
    private Claw claw;
    private boolean direction;
    private boolean waited;

    public BrickGrab (Claw claw, boolean direction) {
        this.claw = claw;
        this.direction = direction;
    }

    @Override
    public boolean Run() {
        waited = wait.Run();
        claw.extend((direction)&&waited, (!direction)&&waited);
        return waited;
    }
}
