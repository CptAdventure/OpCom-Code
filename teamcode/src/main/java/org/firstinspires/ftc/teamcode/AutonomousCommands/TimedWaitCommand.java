package org.firstinspires.ftc.teamcode.AutonomousCommands;

import org.firstinspires.ftc.teamcode.Utilities.Stopwatch;

public class TimedWaitCommand implements ICommand {
    private Stopwatch stopwatch = new Stopwatch();
    private long time;
    private final long NOT_STARTED = 0;

    public TimedWaitCommand(long time){
        this.time = time;
    }

    @Override
    public boolean Run() {
        if(stopwatch.getElapsedTime()==NOT_STARTED){
            stopwatch.start();
        }
        return stopwatch.getElapsedTime()>=time;
    }
}
