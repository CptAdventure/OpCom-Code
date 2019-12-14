package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.AutonomousCommands.EndCommand;
import org.firstinspires.ftc.teamcode.AutonomousCommands.ICommand;
import org.firstinspires.ftc.teamcode.AutonomousCommands.TimedMoveCommand;
import org.firstinspires.ftc.teamcode.RobotComponents.Drive;

import java.util.ArrayList;

@Autonomous(name="Drive Forward", group="LinearOpMode")
public class earlyAuto extends OpMode {
    private ArrayList<ICommand> listOfCommands = new ArrayList();
    private ICommand commandToRun;
    private Drive drive;
    @Override
    public void init() {
        drive = new Drive(hardwareMap);

        // Commands
        listOfCommands.add(new TimedMoveCommand(1,0,0,1000, drive));
        listOfCommands.add(new EndCommand());
    }
    @Override
    public void loop() {
        if(commandToRun.Run()) {
            listOfCommands.remove(0);
        }
    }
}