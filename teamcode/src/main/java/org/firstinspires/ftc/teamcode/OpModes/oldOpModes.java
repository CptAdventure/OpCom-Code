package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotComponents.Drive;

@TeleOp(name="Concept Design OpMode", group = "LinearOpMode")
public class oldOpModes extends OpMode {
    private Drive drive;
    private double a;
    private float DPAD_SENSITIVITY=.5f;

    @Override
    public void init() {
        drive = new Drive(hardwareMap);
    }

    @Override
    public void loop() {
        if(gamepad1.dpad_right){a+=DPAD_SENSITIVITY;}
        if(gamepad1.dpad_left){a-=DPAD_SENSITIVITY;}
        drive.drive(gamepad1.left_stick_x,gamepad1.left_stick_y,a);
    }
}
