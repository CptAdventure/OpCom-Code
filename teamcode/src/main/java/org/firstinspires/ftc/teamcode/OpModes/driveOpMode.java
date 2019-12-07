package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

//import org.firstinspires.ftc.teamcode.RobotComponents.Claw;
import org.firstinspires.ftc.teamcode.RobotComponents.Drive;
import org.firstinspires.ftc.teamcode.RobotComponents.Intake;

@TeleOp(name="ONLY Drive OpMode", group = "LinearOpMode")
public class driveOpMode extends OpMode {
    private Drive drive;
    //private Claw claw;
    private double roation;
    private float BUMPER_SENSITIVITY=.5f;
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
        if(gamepad1.left_stick_y==0){roation=0;}else {roation=gamepad1.left_stick_y;}
        if(gamepad1.left_stick_x==0){driveX=0;} else {driveX=gamepad1.left_stick_x;}
        if(gamepad1.right_stick_y==0){driveY=0;} else {driveY=gamepad1.right_stick_y;}
        drive.drive(driveX,driveY,roation);
        telemetry.addData("Movement (x,y,r)",driveX+","+driveY+","+ roation);
        /*telemetry.addData("Claw Gripper Position", claw.open(gamepad2.right_bumper));
        telemetry.addData("Claw Lift Position", claw.lift(gamepad2.dpad_up,gamepad2.dpad_down));
        telemetry.addData("Claw Extender Position", claw.extend(gamepad2.dpad_left,gamepad2.dpad_right));*/
        //telemetry.addData("Intake Speed", intake.intake(gamepad1.a));
    }
}
