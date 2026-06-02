package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

public class OI {
    private final XboxController controle = new XboxController(0);

    public OI() {
    }

    public double getVelocidadeEsquerda() {
        return controle.getRawAxis(1);
    }

    public double getVelocidadeDireita() {
        return controle.getRawAxis(5);
    }
}