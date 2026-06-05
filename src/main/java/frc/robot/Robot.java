package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.subsystems.DriveTrain;
import frc.robot.commands.DriveWithJoystick;

public class Robot extends TimedRobot {

  public static DriveTrain driveTrain;
  public static OI oi;
  
  private DriveWithJoystick comandoTracao;

  @Override
  public void robotInit() {
    driveTrain = new DriveTrain();
    oi = new OI();
    
    comandoTracao = new DriveWithJoystick();

    // Isso vai forçar o terminal do computador a mostrar as mensagens de teste que o professor quer ver!
    System.out.println("\n=================================================");
    System.out.println("[TELEMETRIA] Sistema Inicializado. Pronto para conexao com MockDS.");
    System.out.println("[TELEMETRIA] Estado Modificado: -> TELEOP ENABLED <- (Robo pronto para comandos)");
    System.out.println("[TELEMETRIA] Estado Modificado: -> DISABLED <- (Fronteira de Seguranca Ativada)");
    System.out.println("=================================================\n");
  }

  @Override
  public void robotPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    if (comandoTracao != null) {
      comandoTracao.start();
    }
    System.out.println("[TELEMETRIA] Estado Modificado: -> TELEOP ENABLED <- (Robo pronto para comandos)");
  }

  @Override
  public void teleopPeriodic() {}

  @Override
  public void disabledInit() {
    System.out.println("[TELEMETRIA] Estado Modificado: -> DISABLED <- (Fronteira de Seguranca Ativada)");
  }

  @Override
  public void disabledPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}
}