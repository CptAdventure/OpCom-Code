package org.firstinspires.ftc.teamcode.AutonomousCommands;

import org.firstinspires.ftc.teamcode.RobotComponents.Drive;
import org.firstinspires.ftc.teamcode.Utilities.Stopwatch;

public class TimedMoveCommand implements ICommand {
    Stopwatch stopwatch = new Stopwatch();

    @Override
    public boolean Run(double x, double y, double t, float time, Drive drive) {
        if(stopwatch.getElapsedTime()!=0){
            stopwatch.start();
        }
        drive.drive(x,y,t);
        if (stopwatch.getElapsedTime()>=time) {
            return true;
        } else {
            return false;
        }
    }
}
