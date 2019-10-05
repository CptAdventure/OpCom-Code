package org.firstinspires.ftc.teamcode.RobotComponents;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class RobotLifter {

    private DcMotor lifterArm;

    private int lowerPosition;

    public RobotLifter(HardwareMap hardwareMap) {
        lifterArm = hardwareMap.get(DcMotor.class, "RobotLifter");
        lifterArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    //Hopefully their will be limit switches on the robot to base the encoder off of


    /**
     * To Raise the robot you need to lower the arm
     * this will set the motors to go to the correct location, when the function is finished the arm will be moving to
     * the correct location and you will have to wait for the arm to get there
     */
    public void LowerLifterArm(){
        //will look something like
        lifterArm.setTargetPosition(lowerPosition); // will need to set the value
        lifterArm.setPower(0.6); // will need to adjust the speed
    }


    /**
     * To Lower the robot you need to raise the arm
     * this will set the motors to go to the correct location, when the function is finished the arm will be moving to
     * the correct location and you will have to wait for the arm to get there
     */
    public void RaiseArm(){

    }

    /**
     * This will tell you what the arm is doing
      */
     public LifterState GetLifterState(){
        return LifterState.Error;
     }

    /**
     * This is just a special way to describe a state
     * Raised: means that the arm is raised, hence the robot would be on the ground
     * Raising: means that the arm is moving up
     * Lowered: means that arm is lowered, hence if the arm was hooked on the lander then the robot would be off the ground
     * Lowering: means that the arm is moving down
     * Error: there's been an error
     */
    public enum LifterState{
        Raised,
        Raising,
        Lowered,
        Lowering,
        Error
    }
}
