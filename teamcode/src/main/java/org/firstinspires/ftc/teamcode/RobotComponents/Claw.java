package org.firstinspires.ftc.teamcode.RobotComponents;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DigitalChannel;

public class Claw {
    private DcMotor vertical;
    private Servo open;
    private Servo stone;
    private Servo rotate;
    private CRServo extend;
    private boolean oldOpen;
    private boolean oldRotate;
    private boolean oldUp;
    private float on = .5f;
    private boolean prevDeployed = false;
    private boolean p = false;
    private DigitalChannel liftSwitch;
    private DigitalChannel ccSwitch;
    private DigitalChannel cfSwitch;

    private final int MAX_HEIGHT_VALUE = 7500;

    public Claw (HardwareMap hardwareMap) {
        vertical = hardwareMap.get(DcMotor.class, "verticalClaw");
        open = hardwareMap.get(Servo.class, "clawOpen");
        rotate = hardwareMap.get(Servo.class, "clawRotate");
        stone = hardwareMap.get(Servo.class, "capStone");
        extend = hardwareMap.get(CRServo.class, "clawExtender");
        liftSwitch = hardwareMap.get(DigitalChannel.class, "bottomLift");
        ccSwitch = hardwareMap.get(DigitalChannel.class, "closeExtension");
        cfSwitch = hardwareMap.get(DigitalChannel.class, "farExtension");
        vertical.setTargetPosition(0);
        vertical.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        vertical.setDirection(DcMotor.Direction.REVERSE);
        vertical.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        extend.setDirection(CRServo.Direction.REVERSE);
        liftSwitch.setMode(DigitalChannel.Mode.INPUT);
        ccSwitch.setMode(DigitalChannel.Mode.INPUT);
        cfSwitch.setMode(DigitalChannel.Mode.INPUT);
    }
    public double open (boolean open) {
        if (open&&!oldOpen) {
            on+=.5;
        }
        on%=1;
        this.open.setPosition(on+.25);
        oldOpen = open;
        return this.open.getPosition();
    }
    public double lift (boolean up, boolean down) {
        vertical.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        if ((up||oldUp) && vertical.getCurrentPosition()<MAX_HEIGHT_VALUE) {
            vertical.setPower(1);
            if (down) {
                vertical.setPower(0);
            }
        } else if (liftSwitch.getState()) {
            vertical.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            oldUp = up;
            return 0;
        } else if (down) {
            vertical.setPower(-0.625);
        } else {
            vertical.setPower(0);
        }
        oldUp = up;
        return vertical.getCurrentPosition();
    }
    public double lift (double liftPower) {
        vertical.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        vertical.setPower(Math.max(liftPower, -0.625));
        if (liftPower==0) vertical.setPower(0);
        if (vertical.getCurrentPosition()>MAX_HEIGHT_VALUE) vertical.setPower(Math.min(liftPower, 0));
        if (liftSwitch.getState()&&liftPower<0) {
            vertical.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            return 0;
        }
        return vertical.getCurrentPosition();
    }
    public boolean down() {
        return liftSwitch.getState();
    }
    public double extend (boolean extend, boolean retract) {
        this.extend.setPower(0);
        if (extend && cfSwitch.getState()){
            this.extend.setPower(0.5);
        }
        if (retract && ccSwitch.getState()){
            this.extend.setPower(this.extend.getPower()-.5);
        }
        return this.extend.getPower();
    }
    public double extend (boolean extend, boolean retract, boolean override) {
        this.extend.setPower(0);
        if (extend){
            this.extend.setPower(0.5);
        }
        if (retract){
            this.extend.setPower(-.5);
        }
        return this.extend.getPower();
    }
    public double extend (double extendPower) {
        extend.setPower(extendPower);
        if (extend.getPower()>0 && cfSwitch.getState()){
            extend.setPower(0);
        }
        if ((this.extend.getPower() < 0) && ccSwitch.getState()){
            this.extend.setPower(0);
        }
        return this.extend.getPower();
    }
    public int extended() {
        return (cfSwitch.getState()?1:0)+(ccSwitch.getState()?2:0);
    }
    public double rotate(boolean open) {
        if (open&&!oldRotate) {
            p=!p;
        }
        this.rotate.setPosition(p?1:0);
        oldRotate = open;
        return this.rotate.getPosition();
    }
    public boolean capStone(boolean open) {
        stone.setPosition(prevDeployed?0.25:0);
        prevDeployed = open || prevDeployed;
        return prevDeployed;
    }
}
