package org.firstinspires.ftc.teamcode.RobotComponents;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
public class Claw {
    private DcMotor vertical;
    private Servo open;
    private Servo extend;
    public Claw (HardwareMap hardwareMap) {
        vertical  = hardwareMap.get(DcMotor.class, "verticalClaw");
        open = hardwareMap.get(Servo.class, "clawOpen");
        extend = hardwareMap.get(Servo.class, "clawExtender");
        vertical.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public double open(boolean open) {
        if (open) {
            this.open.setPosition(1);
        } else {
            this.open.setPosition(0);
        }
        return this.open.getPosition();
    }
    public double lift(boolean up, boolean down) {
        if(up){
            vertical.setTargetPosition(vertical.getTargetPosition()+10);
        }
        if(down){
            vertical.setTargetPosition(vertical.getTargetPosition()-10);
        }
        return vertical.getCurrentPosition();
    }
    public double extend(boolean extend, boolean retract) {
        if(extend){
            this.extend.setPosition(this.extend.getPosition()+0.1);
        }
            if(retract){
            this.extend.setPosition(this.extend.getPosition()-0.1);
        }
        return this.extend.getPosition();
    }
}
