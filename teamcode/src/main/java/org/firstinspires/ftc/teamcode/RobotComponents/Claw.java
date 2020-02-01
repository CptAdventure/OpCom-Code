package org.firstinspires.ftc.teamcode.RobotComponents;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DigitalChannel;

import static java.lang.System.nanoTime;

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
    private double clawPosition = 0;
    private double oldTime;

    private final int MAX_HEIGHT_VALUE = 4750;

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
        oldTime = nanoTime();
    }
    public double open (boolean open) {
        if (open&&!oldOpen) { // oldOpen will be false for one loop before being set to true by a lower piece of code
            on+=.4; // open && !oldOpen i/o list: 0,0: 0; 1,0: 1; 1,1: 0; 0,1: 0;
        }
        on%=.8; // Go between values 0 and 0.4
        this.open.setPosition(on + .4); // 0 -> 0.4, 0.4 -> 0.8
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
        clawPosition += ((nanoTime() - oldTime) != 0) ? this.extend.getPower() / (nanoTime() - oldTime) * 100000000 : 0; // Crude position from here to return
        oldTime = nanoTime();
        if(cfSwitch.getState()) {
            clawPosition = 17.5;
        }
        if (ccSwitch.getState()) {
            clawPosition = 0;
        }
        return clawPosition; // Return estimated position
    }
    public double extend (boolean extend, boolean retract, boolean override) { // Same as previous, but override switches
        this.extend.setPower(0);
        if (extend){
            this.extend.setPower(0.5);
        }
        if (retract){
            this.extend.setPower(-.5);
        }
        clawPosition += ((nanoTime() - oldTime) != 0) ? this.extend.getPower() / (nanoTime() - oldTime) * 100000000 : 0;
        oldTime = nanoTime();
        if(cfSwitch.getState()) {
            clawPosition = 17.5;
        }
        if (ccSwitch.getState()) {
            clawPosition = 0;
        }
        return clawPosition;
    }
    public double extend (double extendPower) { // Number input (Joystick)
        extend.setPower(extendPower); // Set power
        if ((extend.getPower() > 0) && !cfSwitch.getState()){
            extend.setPower(0); // Don't go too far
        }
        if ((extend.getPower() < 0) && !ccSwitch.getState()){
            extend.setPower(0); // Don't go too close
        }
        clawPosition += ((nanoTime() - oldTime) != 0) ? this.extend.getPower() / (nanoTime() - oldTime) * 100000000 : 0; // Crude position from here on down
        oldTime = nanoTime();
        if (cfSwitch.getState()) {
            clawPosition = 17.5;
        }
        if (ccSwitch.getState()) {
            clawPosition = 0;
        }
        return clawPosition; // Return estimated position
    }
    public int extended() { // Get extension system: 0 = none, 1 = far, 2 = close, 3 = both (error)
        return (cfSwitch.getState()?1:0)+(ccSwitch.getState()?2:0);
    }
    public double rotate(boolean open) { // Rotation. Hopefully self explanitory.
        p = p ^ (open&&!oldRotate); // Hat is XOR
        this.rotate.setPosition(p?1:0);
        oldRotate = open;
        return this.rotate.getPosition();
    }
    public boolean capStone(boolean open) { // Hopefully also self explanitory
        stone.setPosition(prevDeployed?0.25:0);
        prevDeployed = open || prevDeployed;
        return prevDeployed;
    }
}
