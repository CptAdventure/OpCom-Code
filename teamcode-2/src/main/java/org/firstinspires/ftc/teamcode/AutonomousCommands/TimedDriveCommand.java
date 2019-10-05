package org.firstinspires.ftc.teamcode.AutonomousCommands;

import org.firstinspires.ftc.teamcode.RobotComponents.Drive;
import org.firstinspires.ftc.teamcode.Utilities.Stopwatch;

// thsi command will drive the robot at a given speed for a given time
public class TimedDriveCommand implements ICommand {

    private Drive drive;

    private double x;
    private double y;
    private double r;
    private long time;

    private boolean hasStarted = false;
    private Stopwatch stopwatch;

    //hopefully this will make sense now, time is in milliseconds
    public TimedDriveCommand(Drive drive, double x, double y, double r, long time) {
        this.drive = drive;
        this.x = x;
        this.y = y;
        this.r = r;
        this.time = time;

        stopwatch = new Stopwatch();
    }

    @Override
    public boolean Run() {
        // if the command hasn't started then start the stopwatch
        if (! hasStarted){
            hasStarted = true;
            stopwatch.start();
        }

        // if the time has elapsed then stop the drive and return true
        if (stopwatch.getElapsedTime() > time) {
            drive.drive(0,0,0);
            return true;
        }

        // else tell the drive to drive with the speed specified when creating the command
        drive.drive(x, y, r);
        return false;
    }
}
