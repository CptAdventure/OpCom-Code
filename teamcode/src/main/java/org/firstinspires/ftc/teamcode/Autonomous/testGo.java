package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.AutonomousCommands.BrickGrab;
import org.firstinspires.ftc.teamcode.AutonomousCommands.CrossToBrickEnd;
import org.firstinspires.ftc.teamcode.AutonomousCommands.EndCommand;
import org.firstinspires.ftc.teamcode.AutonomousCommands.GrabBrickFirst;
import org.firstinspires.ftc.teamcode.AutonomousCommands.ICommand;
import org.firstinspires.ftc.teamcode.AutonomousCommands.MoveToWall;
import org.firstinspires.ftc.teamcode.AutonomousCommands.TimedMoveCommand;
import org.firstinspires.ftc.teamcode.AutonomousCommands.TimedWaitCommand;
import org.firstinspires.ftc.teamcode.RobotComponents.Claw;
import org.firstinspires.ftc.teamcode.RobotComponents.Drive;

import java.util.ArrayList;

@Autonomous(name="Test: Going Up To Bricks", group="OpMode")
public class testGo extends OpMode {
    private ArrayList<ICommand> listOfCommands = new ArrayList();
    private ICommand commandToRun;
    private Drive drive;
    private Claw claw;
    private int i;

    @Override
    public void init() {
        drive = new Drive(hardwareMap);
        claw = new Claw(hardwareMap);

        // Commands
        // listOfCommands.add(new GoDownToBottom(claw));
        listOfCommands.add(new MoveToWall(drive, hardwareMap, true));
        listOfCommands.add(new TimedWaitCommand(100));
        listOfCommands.add(new CrossToBrickEnd(drive, hardwareMap, true));
        listOfCommands.add(new TimedWaitCommand(100));
        listOfCommands.add(new TimedMoveCommand(-0.25, 0, 0, 100, drive));
        listOfCommands.add(new GrabBrickFirst(claw));
        listOfCommands.add(new BrickGrab(claw, false));
        for (i=-1; ++i==3;) {
            listOfCommands.add(new TimedMoveCommand(1, 0, 0, 1000, drive));
            listOfCommands.add(new BrickGrab(claw, true));
            listOfCommands.add(new TimedMoveCommand(-1, 0, 0, 1100, drive));
            listOfCommands.add(new BrickGrab(claw, false));
        }
        listOfCommands.add(new TimedMoveCommand(-0.5, 0, 0, 500, drive));
        listOfCommands.add(new EndCommand());
        commandToRun = listOfCommands.remove(0);
    }
    @Override
    public void loop() {
        if(commandToRun.Run()) {
            commandToRun = listOfCommands.remove(0);
        }
    }
}