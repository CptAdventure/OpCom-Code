package org.firstinspires.ftc.teamcode.RobotComponents;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DigitalChannel;

import static java.lang.System.nanoTime;

public class Claw {
    private DcMotor vertical;
    private Servo open, stone, rotate, base, base2;
    private CRServo extend;
    private boolean oldOpen, oldRotate, oldUp, oldMini, miniPosition = false, prevDeployed = false, p = false;
    private float on = .5f, clawPosition = 0, oldTime;
    private DigitalChannel liftSwitch, ccSwitch, cfSwitch;

    private final int MAX_HEIGHT_VALUE = 4750;

    public Claw (HardwareMap hardwareMap) {
        vertical = hardwareMap.get(DcMotor.class, "verticalClaw");
        open = hardwareMap.get(Servo.class, "clawOpen");
        base = hardwareMap.get(Servo.class, "clawMini1");
        base2 = hardwareMap.get(Servo.class, "clawMini2");
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
        oldTime = nanoTime();
    }
    public double open (boolean open) {
        if (open&&!oldOpen) { // oldOpen will be false for one loop before being set to true by a lower piece of code
            on+=.4; // open && !oldOpen i/o list: 0,0: 0; 1,0: 1; 1,1: 0; 0,1: 0;
        }
        on%=.8; // Go between values 0 and 0.4
        this.open.setPosition(on + .2); // 0 -> 0.2, 0.4 -> 0.6
        oldOpen = open; // One-pulse
        return this.open.getPosition(); // Return the position
    }
    public double lift (boolean up, boolean down) { // Boolean-input (Autonomous)
        vertical.setMode(DcMotor.RunMode.RUN_USING_ENCODER); // Prevent 0-sticking
        if ((up||oldUp) && vertical.getCurrentPosition()<MAX_HEIGHT_VALUE) { // < MAX_HEIGHT_VALUE: soft-limit
            vertical.setPower(1); // Go up
            if (down) {
                vertical.setPower(0); // Up & Down removal
            }
        } else if (liftSwitch.getState()) { // At bottom and not going down
            vertical.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); // Reset encoder
            oldUp = up; // See below for details
            return 0; // Prevent glitching of vertical.getCurrentPosition
        } else if (down) {
            vertical.setPower(-0.625); // Go down
        } else {
            vertical.setPower(0); // Don't move
        }
        oldUp = up; // De-stutter
        return vertical.getCurrentPosition(); // Return the position
    }
    public double lift (double liftPower) { // Number-input (Joysticks)
        vertical.setMode(DcMotor.RunMode.RUN_USING_ENCODER); // Prevent 0-sticking
        vertical.setPower(Math.max(liftPower, -0.625)); // Max down speed: -0.625
        if (liftPower==0) vertical.setPower(0); // If not moving, don't move (bug in app code)
        if (vertical.getCurrentPosition()>MAX_HEIGHT_VALUE) vertical.setPower(Math.min(liftPower, 0)); // Soft-limits, allow down
        if (liftSwitch.getState()&&liftPower<0) {
            vertical.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); // Reset encoder
            return 0; // Prevent glitches
        }
        return vertical.getCurrentPosition(); // Return the position
    }
    public boolean down() { // Self explanitory function: lift down?
        return liftSwitch.getState();
    }
    public double extend (boolean extend, boolean retract) { // Boolean input (autonomous)
        this.extend.setPower(0); // Set to zero pre-emptively
        if (extend && !cfSwitch.getState()) {
            this.extend.setPower(0.5); // If extendable and extension wanted extend
        }
        if (retract && !ccSwitch.getState()) {
            this.extend.setPower(this.extend.getPower() - .5); // If retractable and retraction wanted, reduce power to retract/neutralize
        }
        if ((nanoTime() - oldTime) != 0) {
            clawPosition += ((nanoTime() - oldTime) * (this.extend.getPower())) / 100000000;
        }
        oldTime = nanoTime();
        return clawPosition; // Return estimated position
    }
    @Deprecated
    public double extend (boolean extend, boolean retract, boolean override) { // Same as previous, but override switches
        this.extend.setPower(0);
        if (extend){
            this.extend.setPower(0.5);
        }
        if (retract){
            this.extend.setPower(-.5);
        }
        if ((nanoTime() - oldTime) != 0) {
            clawPosition += ((nanoTime() - oldTime) * (this.extend.getPower())) / 100000000;
        }
        oldTime = nanoTime();
        return clawPosition; // Return estimated position
    }
    public double extend (double extendPower) { // Number input (Joystick)
        extend.setPower(extendPower); // Set power
        if ((extend.getPower() > 0) && !cfSwitch.getState()){
            extend.setPower(0); // Don't go too far
        }
        if ((extend.getPower() < 0) && !ccSwitch.getState()){
            extend.setPower(0); // Don't go too close
        }
        if ((nanoTime() - oldTime) != 0) {
            clawPosition += ((nanoTime() - oldTime) * (this.extend.getPower())) / 100000000;
        }
        oldTime = nanoTime();
        return clawPosition; // Return estimated position
    }
    public double extendVal() { // Crude position gathering
        if ((nanoTime() - oldTime) != 0) {
            clawPosition += ((nanoTime() - oldTime) * (this.extend.getPower())) / 100000000;
        }
        oldTime = nanoTime();
        return clawPosition; // Return estimated position
    }
    public boolean resetExtendVal() {
        clawPosition = 0;
        return true;
    }
    public int extended() { // Get extension system: 0 = none, 1 = far, 2 = close, 3 = both (error)
        return (cfSwitch.getState()?1:0)+(ccSwitch.getState()?2:0);
    }
    public double rotate(boolean open) { // Rotation. Hopefully self explanitory.
        p = p ^ (open&&!oldRotate);
        this.rotate.setPosition(p?1:0);
        oldRotate = open;
        return this.rotate.getPosition();
    }
    public boolean capStone(boolean open) { // Hopefully also self explanitory
        stone.setPosition(prevDeployed?0.25:0);
        prevDeployed = open || prevDeployed;
        return prevDeployed;
    }
    public boolean miniClaw (boolean change) { // ? tutorial: the colon decides the value ( true : false ), boolean in question mark
        if (change && !oldMini) {  miniPosition = !miniPosition; } // ?s can be stacked with parenthesis
        base.setPosition(!miniPosition ? 0 : 0.96875);
        base2.setPosition(!miniPosition ? 0.96875 : 0.09375);
        oldMini = change;
        return miniPosition;
    }
}
