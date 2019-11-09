package org.firstinspires.ftc.teamcode.OpModes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.RobotComponents.Claw;
import org.firstinspires.ftc.teamcode.RobotComponents.Drive;
import org.firstinspires.ftc.teamcode.RobotComponents.Intake;

@TeleOp(name="Concept Design OpMode", group = "LinearOpMode")
public class oldOpModes extends OpMode {
    private Drive drive;
    private Claw claw;
    private double roation;
    private float BUMPER_SENSITIVITY=.5f;
    private Intake intake;
    @Override
    public void init() {
        drive = new Drive(hardwareMap);
        claw = new Claw(hardwareMap);
        intake = new Intake(hardwareMap);
    }
    @Override
    public void loop() {
        telemetry.update();
        if(gamepad1.right_bumper){roation +=BUMPER_SENSITIVITY;}
        if(gamepad1.left_bumper){roation -=BUMPER_SENSITIVITY;}
        drive.drive(gamepad1.left_stick_x,gamepad1.left_stick_y, roation);
        telemetry.addData("Movement (x,y,r)",gamepad1.left_stick_x+","+gamepad1.left_stick_y+","+ roation);
        telemetry.addData("Claw Gripper Position", claw.open(gamepad2.right_bumper));
        telemetry.addData("Claw Lift Position", claw.lift(gamepad2.dpad_up,gamepad2.dpad_down));
        telemetry.addData("Claw Extender Position", claw.extend(gamepad2.dpad_left,gamepad2.dpad_right));
        telemetry.addData("Intake Speed", intake.intake(gamepad2.a));
    }
}
