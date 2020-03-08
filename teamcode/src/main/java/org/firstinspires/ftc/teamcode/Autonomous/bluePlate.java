package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.AutonomousCommands.DualCommands;
import org.firstinspires.ftc.teamcode.AutonomousCommands.EndCommand;
import org.firstinspires.ftc.teamcode.AutonomousCommands.ICommand;
import org.firstinspires.ftc.teamcode.AutonomousCommands.MoveToWall;
import org.firstinspires.ftc.teamcode.AutonomousCommands.TimedMoveCommand;
import org.firstinspires.ftc.teamcode.AutonomousCommands.TimedWaitCommand;
import org.firstinspires.ftc.teamcode.AutonomousCommands.grabPlate;
import org.firstinspires.ftc.teamcode.RobotComponents.Claw;
import org.firstinspires.ftc.teamcode.RobotComponents.Drive;

import java.util.ArrayList;

@Autonomous(name="Red Base", group="OpMode")
public class bluePlate extends OpMode {
    private ArrayList<ICommand> listOfCommands = new ArrayList();
    private ICommand commandToRun;
    private Drive drive;
    private Claw claw;
    private int i, j;

    @Override
    public void init() {
        drive = new Drive(hardwareMap);
        claw = new Claw(hardwareMap);

        // Commands
        // listOfCommands.add(new GoDownToBottom(claw));
        listOfCommands.add(new TimedMoveCommand(-1, 0, 0, 250, drive));
        listOfCommands.add(new MoveToWall(drive, hardwareMap, true, 20));
        listOfCommands.add(new TimedWaitCommand(250));
        listOfCommands.add(new DualCommands( new TimedWaitCommand(1000), new grabPlate(claw, true)));
        listOfCommands.add(new TimedMoveCommand(0, -0.75, 0.365, 3000, drive));
        listOfCommands.add(new DualCommands( new TimedMoveCommand(0, 1, 0, 1000, drive), new grabPlate(claw, false)));
        listOfCommands.add(new TimedMoveCommand(-1, 0, 0, brickMoveData.WALL_TIME, drive));
        listOfCommands.add(new TimedMoveCommand(0, -1, 0, 1250, drive));
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