package org.firstinspires.ftc.teamcode.RobotComponents;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
public class Claw {
    private DcMotor vertical;
    private Servo open;
    private Servo rotate;
    private CRServo extend;
    private boolean oldOpen;
    private int on = 0;
    private double position = 0;
    public Claw (HardwareMap hardwareMap) {
        vertical  = hardwareMap.get(DcMotor.class, "verticalClaw");
        open = hardwareMap.get(Servo.class, "clawOpen");
        rotate = hardwareMap.get(Servo.class, "clawRotate");
        extend = hardwareMap.get(CRServo.class, "clawExtender");
        vertical.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rotate.scaleRange(-1, 1);
    }
    public double open(boolean open) {
        if (open&&!oldOpen) {
            on++;
        }
        on %= 2;
        this.open.setPosition(on);
        oldOpen = open;
        return this.open.getPosition();
    }
    public double lift(boolean up, boolean down) {
        vertical.setPower(0);
        if(up){
            vertical.setPower(1);
        }
        if(down){
            vertical.setPower(vertical.getPower()-1);
        }
        position += vertical.getPower();
        return position;
    }
    public double extend(boolean extend, boolean retract) {
        this.extend.setPower(0);
        if(extend){
            this.extend.setPower(0.5);
        }
        if(retract){
            this.extend.setPower(this.extend.getPower()-.5);
        }
        return this.extend.getPower();
    }
    public double rotate(double position) {
        rotate.setPosition(position);
        return this.rotate.getPosition();
    }
}
