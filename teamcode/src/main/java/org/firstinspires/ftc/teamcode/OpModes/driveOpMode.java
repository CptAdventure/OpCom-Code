package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotComponents.Drive;

@TeleOp(name="2020 Drive OpMode", group = "LinearOpMode")
public class driveOpMode extends OpMode {
    private Drive drive;
    //private Claw claw;
    private double roation;
    //private Intake intake;
    private double driveX;
    private double driveY;
    @Override
    public void init() {
        drive = new Drive(hardwareMap);
        //claw = new Claw(hardwareMap);
        //intake = new Intake(hardwareMap);
    }
    @Override
    public void loop() {
        telemetry.update();
        if(gamepad1.right_stick_x==0){roation=0;}else {roation=gamepad1.right_stick_x;}
        if(gamepad1.left_stick_x==0){driveX=0;} else {driveX=gamepad1.left_stick_x;}
        if(gamepad1.left_stick_y==0){driveY=0;} else {driveY=gamepad1.left_stick_y;}
        drive.drive(driveX,driveY,roation);
        telemetry.addData("Movement (x,y,r)",driveX+","+driveY+","+ roation);
    }
}
