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

@Autonomous(name="Blue B Brick", group="OpMode")
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
        listOfCommands.add(new MoveToWall(drive, hardwareMap, true, 500));
        listOfCommands.add(new TimedWaitCommand(500));
        listOfCommands.add(new CrossToBrickEnd(drive, hardwareMap, false));
        listOfCommands.add(new TimedWaitCommand(500));
        listOfCommands.add(new TimedMoveCommand(0.625, 0, 0, 400, drive));
        listOfCommands.add(new GrabBrickFirst(claw, telemetry));
        for (i=-1; ++i!=3;) {
            listOfCommands.add(new TimedMoveCommand(0, -0.5, 0, 500, drive));
            listOfCommands.add(new TimedWaitCommand(500));
            listOfCommands.add(new TimedMoveCommand(-0.5, 0, 0, 2000, drive));
            listOfCommands.add(new BrickGrab(claw, true));
            listOfCommands.add(new TimedWaitCommand(250));
            listOfCommands.add(new TimedMoveCommand(0.5, 0, 0, 2000, drive));
            listOfCommands.add(new TimedWaitCommand(500));
            listOfCommands.add(new TimedMoveCommand(0, 0.5, 0, 500, drive));
            listOfCommands.add(new TimedWaitCommand(500));
            listOfCommands.add(new TimedMoveCommand(0.625, 0, 0, 600, drive));
            listOfCommands.add(new BrickGrab(claw, false));
            listOfCommands.add(new TimedWaitCommand(250));
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