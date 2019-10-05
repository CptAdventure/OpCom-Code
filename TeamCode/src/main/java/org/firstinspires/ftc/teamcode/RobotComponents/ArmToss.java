package org.firstinspires.ftc.teamcode.RobotComponents;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ArmToss {

        public ArmToss(HardwareMap robot, String name){
            /* Set up Arn */
            tossMotor = robot.get(DcMotor.class, name);
            tossMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            tossMotor.setPower(1);
        }
        private DcMotor tossMotor;
        private armTossStage tossStage = armTossStage.readyToToss;
        private final int error = 50;

        public double calculatePulses(double degreesToRotate, long pulses){
            return (degreesToRotate / 360) * pulses;
        }


    /**
     * Tosses the arm
     * @param toss Are you tossing?
     * @param position The position to toss to
     * @return Returns the boolean false unless it is tossing, in which it will return the boolean true
     */
        public boolean run(boolean toss, int position) {

            if(toss && tossStage == armTossStage.readyToToss) {
                tossStage = armTossStage.tossing; // start tossing
                tossMotor.setTargetPosition((int) Math.round(calculatePulses(position, 1440)));
                return true;
            } else if (tossStage == armTossStage.tossing && tossMotor.getCurrentPosition() >= position - error && !toss) {
                tossStage = armTossStage.returningToReady;
                tossMotor.setTargetPosition(0);
                return true;
            } else if (tossStage == armTossStage.returningToReady && tossMotor.getCurrentPosition() <= error ) {
                tossStage = armTossStage.readyToToss;
                return false;
            } else if (tossStage == armTossStage.tossing && toss) {
                return true;
            } else {
                return false;
            }
        }

        private enum armTossStage {
            readyToToss,
            tossing,
            returningToReady
    }
}
