package org.firstinspires.ftc.teamcode.AutonomousCommands;

import org.firstinspires.ftc.teamcode.RobotComponents.Color;
import org.firstinspires.ftc.teamcode.RobotComponents.Drive;
import org.firstinspires.ftc.teamcode.Utilities.Stopwatch;

import java.util.ArrayList;

public class moveLToBrick implements ICommand {
    private Stopwatch stopwatch = new Stopwatch();
    private Drive drive;
    private ICommand moveUp;
    private Color color;
    private boolean direction;
    private ArrayList<ICommand> listOfCommands = new ArrayList();
    private final int ACTIVATED = 100;
    public moveLToBrick(Drive drive, Color color, boolean direction){
        this.drive = drive;
        this.color = color;
        this.direction = direction;
        moveUp = new TimedMoveCommand(direction?1:-1,0,0,1000, drive);
    }

    @Override
    public boolean Run() {
        if (moveUp.Run()) {
            if(color.getYellow()>=ACTIVATED) {
                drive.drive(0,0,0);
                return true;
            }
            drive.drive((direction?1:-1)*0.25,0,0);
        }
        return false;
    }
}
