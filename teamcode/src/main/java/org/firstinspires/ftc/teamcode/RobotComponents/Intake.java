package org.firstinspires.ftc.teamcode.RobotComponents;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    private DcMotor leftMotor;
    private DcMotor rightMotor;
    public Intake(HardwareMap hardwareMap){
        leftMotor = hardwareMap.get(DcMotor.class, "leftIntake");
        rightMotor = hardwareMap.get(DcMotor.class, "rightIntake");
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.FORWARD);
    }
    public double intake (int speed) {
        leftMotor.setPower(1.0 * speed);
        rightMotor.setPower(-1.0 * speed);
        return (leftMotor.getPower()+(rightMotor.getPower()*-1))/2;
    }
}
