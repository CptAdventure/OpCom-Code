package org.firstinspires.ftc.teamcode.RobotComponents;

import android.support.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    private DcMotor leftMotor;
    private DcMotor rightMotor;
    public Intake(@NonNull HardwareMap hardwareMap){
        leftMotor = hardwareMap.get(DcMotor.class, "leftIntake");
        rightMotor = hardwareMap.get(DcMotor.class, "rightIntake");
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.FORWARD);
    }
    public double intake (boolean run) {
        if (run) {
            leftMotor.setPower(1.0);
            rightMotor.setPower(-1.0);
        } else {
            leftMotor.setPower(0.0);
            rightMotor.setPower(0.0);
        }
        return (leftMotor.getPower()+(rightMotor.getPower()*-1))/2;
    }
}
