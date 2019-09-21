package org.firstinspires.ftc.teamcode.RobotComponents;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;

public class ArmMove {

    private Servo clawRotator = null;
    private Servo clawOpener = null;
    private Servo clawSlider = null;
    private DcMotor lift = null;

    public ArmMove(HardwareMap hardwareMap) {
        clawRotator  = hardwareMap.get(Servo.class, "clawRotator");
        clawOpener = hardwareMap.get(Servo.class, "clawOpener");
        clawSlider = hardwareMap.get(Servo.class, "clawSlider");
        lift = hardwareMap.get(DcMotor.class, "clawLift");

        lift.setDirection(DcMotor.Direction.FORWARD);
    }

}
