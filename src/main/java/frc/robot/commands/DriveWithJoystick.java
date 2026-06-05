package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveWithJoystick extends Command {

    public DriveWithJoystick() {
        requires(Robot.driveTrain);
    }

    @Override
    protected void initialize() {
        Robot.driveTrain.andar(0, 0);
    }

    @Override
    protected void execute() {
        double esq = Robot.oi.getVelocidadeEsquerda();
        double dir = Robot.oi.getVelocidadeDireita();
        Robot.driveTrain.andar(esq, dir);
    }

    @Override
    protected boolean isFinished() {
        return false; 
    }

    @Override
    protected void end() {
        Robot.driveTrain.andar(0, 0);
    }

    @Override
    protected void interrupted() {
        end();
    }
}