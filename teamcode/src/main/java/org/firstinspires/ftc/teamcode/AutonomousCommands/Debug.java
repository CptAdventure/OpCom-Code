package org.firstinspires.ftc.teamcode.AutonomousCommands;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Debug implements ICommand {
    private String label;
    private Object message;
    private Telemetry t;

    public Debug (String label, Object message, Telemetry t) {
        this.label = label;
        this.message = message;
        this.t = t;
    }

    @Override
    public boolean Run() {
        t.addData(label, message);
        return true;
    }
}
