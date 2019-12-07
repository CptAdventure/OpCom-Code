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
    private float on = 0;
    public Claw (HardwareMap hardwareMap) {
        vertical  = hardwareMap.get(DcMotor.class, "verticalClaw");
        open = hardwareMap.get(Servo.class, "clawOpen");
        rotate = hardwareMap.get(Servo.class, "clawRotate");
        extend = hardwareMap.get(CRServo.class, "clawExtender");
        vertical.setTargetPosition(0);
        vertical.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        vertical.setDirection(DcMotor.Direction.REVERSE);
        vertical.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        extend.setDirection(CRServo.Direction.REVERSE);
    }
    public double open(boolean open) {
        if (open&&!oldOpen) {
            on+=.3;
        }
        on%=.6;
        this.open.setPosition(on+.2);
        oldOpen = open;
        return this.open.getPosition();
    }
    public double lift(boolean up, boolean down) {
        vertical.setPower(0);
        if(up){
            vertical.setPower(1);
        }
        if(down){
            vertical.setPower(-0.25);
            if(up){
                vertical.setPower(0);
            }
        }
        return vertical.getCurrentPosition();
    }
    public void resetlift() {
        vertical.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        vertical.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
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
        rotate.setPosition(Math.abs(position)%2-.1);
        return this.rotate.getPosition();
    }
}
