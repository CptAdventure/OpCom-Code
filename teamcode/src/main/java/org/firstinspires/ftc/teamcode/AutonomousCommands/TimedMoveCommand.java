package org.firstinspires.ftc.teamcode.AutonomousCommands;

import org.firstinspires.ftc.teamcode.RobotComponents.Drive;
import org.firstinspires.ftc.teamcode.Utilities.Stopwatch;

public class TimedMoveCommand implements ICommand {
    private Stopwatch stopwatch = new Stopwatch();
    private double x;
    private double y;
    private double t;
    private long time;
    private Drive drive;
    private final long NOT_STARTED = 0;

    public TimedMoveCommand(double x, double y, double t, long time, Drive drive){
        this.x = x;
        this.y = y;
        this.t = t;
        this.time = time;
        this.drive = drive;
    }

    @Override
    public boolean Run() {
        if(stopwatch.getElapsedTime()==NOT_STARTED){
            stopwatch.start();
        }
        if (stopwatch.getElapsedTime()>=time) {
            drive.drive(0,0,0);
            return true;
        } else {
            drive.drive(x,y,t);
            return false;
        }
    }
}
