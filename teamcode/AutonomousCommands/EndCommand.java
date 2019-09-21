package org.firstinspires.ftc.teamcode.AutonomousCommands;

// used as the last command in the list to prevent list out of bound errors
// there is a challenge in the AutoTest op to figure a way to get rid of this
public class EndCommand implements ICommand {

    // will always do nothing and return false
    @Override
    public boolean Run() {
        return false;
    }
}
