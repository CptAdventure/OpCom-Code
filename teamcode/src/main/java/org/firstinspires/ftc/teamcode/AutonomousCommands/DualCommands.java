package org.firstinspires.ftc.teamcode.AutonomousCommands;

public class DualCommands implements ICommand {

    private ICommand A, B;
    private boolean a;

    public DualCommands (ICommand A, ICommand B) {
        this.A = A;
        this.B = B;
    }

    @Override
    public boolean Run() {
        a = A.Run();
        return B.Run() && a;
    }
}
