package org.firstinspires.ftc.teamcode.RobotComponents;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private DcMotor inMotor;
    public Intake(HardwareMap hardwareMap){
        leftMotor = hardwareMap.get(DcMotor.class, "leftIntake");
        rightMotor = hardwareMap.get(DcMotor.class, "rightIntake");
        inMotor = hardwareMap.get(DcMotor.class, "innerIntake");
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        inMotor.setDirection(DcMotor.Direction.REVERSE);
    }
    public double intake (int speed) {
        leftMotor.setPower(1.0 * speed);
        rightMotor.setPower(-1.0 * speed);
        inMotor.setPower(0.75 * speed); // Slower
        return (leftMotor.getPower()+(rightMotor.getPower()/-1)+(inMotor.getPower()/0.75))/3;
    }
}
