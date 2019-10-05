package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotComponents.ArmToss;
import org.firstinspires.ftc.teamcode.RobotComponents.BallScooper;
import org.firstinspires.ftc.teamcode.RobotComponents.Drive;

@TeleOp(name="TeleOpTest", group="Iterative Opmode")
public class TeleOpTest extends OpMode {

    private Drive drive;
    private BallScooper ballScooper;
    private ArmToss armTosser;


    @Override
    public void init() {
        drive = new Drive(hardwareMap);
        ballScooper = new BallScooper(hardwareMap);
        armTosser = new ArmToss(hardwareMap, "tossMotor");
    }

    @Override
    public void loop() {
        drive.drive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);

        armTosser.run(gamepad1.a, 1440 / 4);
        //this is running front motor at half speed.



    }
}