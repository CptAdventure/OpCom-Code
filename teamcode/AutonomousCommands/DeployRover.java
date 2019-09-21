package org.firstinspires.ftc.teamcode.AutonomousCommands;

import org.firstinspires.ftc.teamcode.RobotComponents.RobotLifter;


//this command currently will just lower the robot to the ground
public class DeployRover implements ICommand {

    private RobotLifter robotLifter;

    public DeployRover(RobotLifter robotLifter) {
        this.robotLifter = robotLifter;
    }


    @Override
    public boolean Run() {
        // tells the robotLifter code to lower the robot
        robotLifter.RaiseArm();

        // here is how the command waits for the robot to actually make it to the ground
        // checks with the robot lifter to get where it is
        // if the lifter arm has been raised then it is finished
        //else then it returns false meaning it needs to wait
       if (robotLifter.GetLifterState() == RobotLifter.LifterState.Raised){
           return true;
       } else {
           return false;
       }

    }
}
