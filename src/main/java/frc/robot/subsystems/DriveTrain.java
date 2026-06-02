package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark; 
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
    
    private final Spark motorEsquerdo = new Spark(0);
    private final Spark motorDireito = new Spark(1);

    public DriveTrain() {
    }

    public void andar(double esq, double dir) {
        motorEsquerdo.set(esq);
        motorDireito.set(-dir); 
    }

    @Override
    protected void initDefaultCommand() {
    }
}   