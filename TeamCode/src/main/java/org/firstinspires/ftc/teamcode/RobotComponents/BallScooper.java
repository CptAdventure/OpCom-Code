package org.firstinspires.ftc.teamcode.RobotComponents;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

// this is a rough outline of the class to control the ball mechanisms will need to be worked on
public class BallScooper {

    private DcMotor scooperMotor;
    private DcMotor LunchMotor;

    public BallScooper(HardwareMap hardwareMap) {
        this.scooperMotor = hardwareMap.get(DcMotor.class, "ScooperMotor");
        LunchMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LunchMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }
    /**
     * this is the part that has the flipper things in front and runs the conveyor
      */
    public void RunScooper(double speed){
        scooperMotor.setPower(speed);
        // maybe theres more that could be added here, sensors???
    }


}
