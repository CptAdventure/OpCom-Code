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
    private Intake intake;
    private double driveX;
    private double driveY;
    @Override
    public void init() {
        drive = new Drive(hardwareMap);
        claw = new Claw(hardwareMap);
        intake = new Intake(hardwareMap);
    }
    @Override
    public void loop() {
        telemetry.update();
        roation=gamepad1.right_stick_x==0?0:gamepad1.right_stick_x;
        driveX=gamepad1.left_stick_x==0?0:gamepad1.left_stick_x;
        driveY=gamepad1.left_stick_y==0?0:gamepad1.left_stick_y;
        drive.drive(driveX,driveY,roation);
        telemetry.addData("Movement (x,y,r)",driveX+","+driveY+","+ roation);
        telemetry.addData("Claw Gripper Position", claw.open(gamepad2.right_bumper));
        telemetry.addData("Claw Lift Position (Down?)", Double.toString(claw.lift(-gamepad2.right_stick_y*.9))
                +'('+claw.down()+')');
        telemetry.addData("Claw Extender Position (End?)", claw.extend(gamepad2.left_stick_x*.9) + "(" +
                (claw.extended()%2!=claw.extended()?(claw.extended()==3?"Error":"Close"):(claw.extended()==1?" Far ":"None "))+")");
        telemetry.addData("Claw Rotation (Degrees)", claw.rotate(gamepad2.left_bumper));
        telemetry.addData("Intake Speed", intake.intake((gamepad1.right_bumper?1:0)*(gamepad1.b?-1:1)));
    }
}