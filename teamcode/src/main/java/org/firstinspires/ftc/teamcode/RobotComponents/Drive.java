package org.firstinspires.ftc.teamcode.RobotComponents;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

// class to control the drive system
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

        forwardLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        forwardRightMotor.setDirection(DcMotor.Direction.FORWARD);
        rearLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        rearRightMotor.setDirection(DcMotor.Direction.FORWARD);
    }

    /**
     * Does the work when driving
     * @param x The amount you want to go up or down
     * @param y The amount you want to go left or right
     * @param r The amount you want to rotate
     */
    public void drive(double x, double y, double r){

        double forwardLeftPower = y - x - r;
        double forwardRightPower = -y - x -r;
        double rearLeftPower = -y + x - r;
        double rearRightPower = y + x - r;

        forwardLeftMotor.setPower(forwardLeftPower);
        forwardRightMotor.setPower(forwardRightPower);
        rearLeftMotor.setPower(rearLeftPower);
        rearRightMotor.setPower(rearRightPower);
    }
}
