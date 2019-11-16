package org.firstinspires.ftc.teamcode.RobotComponents;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
public class Claw {
    private DcMotor vertical;
    private Servo open;
    private Servo extend;
    private boolean oldOpen;
    private int on = 0;
    public Claw (HardwareMap hardwareMap) {
        vertical  = hardwareMap.get(DcMotor.class, "verticalClaw");
        open = hardwareMap.get(Servo.class, "clawOpen");
        extend = hardwareMap.get(Servo.class, "clawExtender");
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
            vertical.setPower(0.1);
        }
        if(down){
            vertical.setPower(-0.1);
        }
        return vertical.getCurrentPosition();
    }
    public double extend(boolean extend, boolean retract) {
        if(extend){
            this.extend.setPosition(this.extend.getPosition()+0.01);
        }
            if(retract){
            this.extend.setPosition(this.extend.getPosition()-0.01);
        }
        return this.extend.getPosition();
    }
}
