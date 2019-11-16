package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Autonomous(name="gamepad.atRest() test", group="LinearOPMode")
public class atRestTest extends OpMode {
    @Override
    public void init() {
        // Do Nothing
    }

    @Override
    public void loop() {
        telemetry.addData("Gamepad 1 at rest?", gamepad1.atRest());
        telemetry.addData("Gamepad 2 at rest?", gamepad2.atRest());
    }
}
