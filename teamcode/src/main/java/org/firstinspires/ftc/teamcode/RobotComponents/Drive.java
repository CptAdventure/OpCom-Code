package org.firstinspires.ftc.teamcode.RobotComponents;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
public class Drive {
    private DcMotor forwardLeftMotor = null;
    private DcMotor forwardRightMotor = null;
    private DcMotor rearLeftMotor = null;
    private DcMotor rearRightMotor = null;
    public Drive(HardwareMap hardwareMap){
        forwardLeftMotor  = hardwareMap.get(DcMotor.class, "forwardLeftMotor");
        forwardRightMotor = hardwareMap.get(DcMotor.class, "forwardRightMotor");
        rearLeftMotor = hardwareMap.get(DcMotor.class, "rearLeftMotor");
        rearRightMotor = hardwareMap.get(DcMotor.class, "rearRightMotor");
        forwardLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        forwardRightMotor.setDirection(DcMotor.Direction.REVERSE);
        rearLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        rearRightMotor.setDirection(DcMotor.Direction.FORWARD);
    }
    public void drive(double x, double y, double r){
        double forwardLeftPower  =  y - x - r;
        double forwardRightPower = -y - x - r;
        double rearLeftPower     =  y + x - r;
        double rearRightPower    = -y + x - r;
        forwardLeftMotor.setPower(forwardLeftPower);
        forwardRightMotor.setPower(forwardRightPower);
        rearLeftMotor.setPower(rearLeftPower);
        rearRightMotor.setPower(rearRightPower);
    }
    public void testFrontLeft() { forwardLeftMotor.setPower(0.5); }
    public void testFrontRight() { forwardRightMotor.setPower(0.5); }
    public void testRearLeft() { rearLeftMotor.setPower(0.5); }
    public void testRearRight() { rearRightMotor.setPower(0.5); }
}
