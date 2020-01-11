package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.AutonomousCommands.EndCommand;
import org.firstinspires.ftc.teamcode.AutonomousCommands.GoDownToBottom;
import org.firstinspires.ftc.teamcode.AutonomousCommands.ICommand;
import org.firstinspires.ftc.teamcode.AutonomousCommands.TimedMoveCommand;
import org.firstinspires.ftc.teamcode.AutonomousCommands.TimedWaitCommand;
import org.firstinspires.ftc.teamcode.RobotComponents.Claw;
import org.firstinspires.ftc.teamcode.RobotComponents.Drive;

import java.util.ArrayList;

@Autonomous(name="Close Autonomous", group="LinearOpMode")
public class earlyAuto extends OpMode {
    private ArrayList<ICommand> listOfCommands = new ArrayList();
    private ICommand commandToRun;
    private Drive drive;
    private Claw claw;
    @Override
    public void init() {
        drive = new Drive(hardwareMap);
        claw = new Claw(hardwareMap);

        // Commands
        //listOfCommands.add(new GoDownToBottom(claw));
        listOfCommands.add(new TimedMoveCommand(0,1,0,100, drive));
        listOfCommands.add(new TimedWaitCommand(100));
        listOfCommands.add(new TimedMoveCommand(0,-1,0,50, drive));
        listOfCommands.add(new TimedWaitCommand(100));
        listOfCommands.add(new TimedMoveCommand(1,0,0,1000, drive));
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