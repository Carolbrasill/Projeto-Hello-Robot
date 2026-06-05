<p align="center">
  <img src="https://vectorseek.com/wp-content/uploads/2023/08/WorldSkills-Logo-Vector.svg-.png" alt="WorldSkills Logo" width="250"/>
</p>

<h1 align="center"> WorldsSkills - #23 Robótica Móvel </h1>

# Projeto Hello Robot utilizando o VSCode FRC 2020 - Controle de Tração e Telemetria

Este projeto foi desenvolvido em Java utilizando o ecossistema WPILib (versão 2020) para o controle de um robô da FRC. O foco da atividade foi a implementação de um sistema de tração por joystick e a configuração de mensagens de telemetria para monitoramento dos estados do robô.

## 🛠️ Estrutura do Código

O projeto segue a arquitetura baseada em **Comandos e Subsistemas** (*Command-Based*) da WPILib:

### 1. Robot.java
Arquivo principal que gerencia o ciclo de vida do robô. Modificado para garantir o monitoramento e o fluxo de execução dos comandos.
* **`robotInit()`**: Inicializa os subsistemas (`DriveTrain`), o mapeamento de entradas (`OI`) e o comando de tração.
* **`teleopInit()`**: Ativa o comando de controle por joystick assim que o modo manual é habilitado.
* **`disabledInit()`**: Garante a segurança parando processos quando o robô é desativado.

### 2. DriveTrain.java (Subsystems)
Responsável pelo mapeamento físico e controle dos motores do robô.
* Utiliza dois controladores de velocidade **Spark** conectados nas portas PWM `0` (Esquerdo) e `1` (Direito).
* Implementa o método `andar(double esq, double dir)` aplicando a inversão correta de polaridade no motor direito (`-dir`) para manter o deslocamento retilíneo correto.
* Configurado com o `initDefaultCommand()` para acionar automaticamente o comando de joystick.

### 3. DriveWithJoystick.java (Commands)
Gerencia a lógica de execução do controle de movimentação.
* No método `execute()`, requisita continuamente as velocidades calculadas do lado esquerdo e direito através do arquivo de mapeamento `OI.java`.
* Repassa esses valores em tempo real para o método `Robot.driveTrain.andar(esq, dir)`.

---

## 📺 Sistema de Telemetria

Para fins de validação em ambientes de simulação e monitoramento de logs, foram estruturadas mensagens textuais padronizadas que são impressas diretamente no console/terminal durante a transição de estados:

* **Inicialização:** `[TELEMETRIA] Sistema Inicializado. Pronto para conexao com MockDS.`
* **Modo Teleoperado:** `[TELEMETRIA] Estado Modificado: -> TELEOP ENABLED <- (Robo pronto para comandos)`
* **Modo Desabilitado:** `[TELEMETRIA] Estado Modificado: -> DISABLED <- (Fronteira de Seguranca Ativada)`

---

## 🚀 Comandos Úteis para Execução

Caso necessite compilar ou enviar o projeto novamente através do terminal, utilize os seguintes comandos do Gradle Wrapper:

* **Compilar o projeto:**
    ```powershell
    ./gradlew build
    ```
* **Enviar o código para a RoboRIO real:**
    ```powershell
    ./gradlew deploy
    ```
* **Iniciar simulação via Desktop (Modo Sem Interface Gráfica):**
    ```powershell
    ./gradlew simulateJava --args="--nogui"
    ```

---

## 📅 Diário de Bordo & Atualizações Diárias

### 🔹 Etapa 1: Implementação da Lógica Base
* Desenvolvimento inicial da estrutura Command-Based. Mapeamento dos motores `Spark(0)` e `Spark(1)` e criação do método `andar()`.
* Vinculação da telemetria no arquivo principal do robô (`Robot.java`).

### 🔹 Etapa 2: Debug de Sintaxe e Ambiente (Erros do PowerShell)
* **Problema:** Tentativa de execução do simulador utilizando barras invertidas (`\gradlew simulateJava`), gerando erro de comando não reconhecido no PowerShell.
* **Solução:** Correção da sintaxe do Gradle Wrapper para o padrão Unix/PowerShell local usando `./gradlew simulateJava`. O código compilou perfeitamente (`BUILD SUCCESSFUL`).
* **Tratamento de Trava do Simulador:** Devido a bloqueios locais na máquina do laboratório que impediam a abertura da interface do MockDS, a validação foi adaptada para leitura de logs diretamente no terminal e forçada via parâmetros `--nogui`.

### 🔹 Etapa 3: Amarração de Agendamento (Default Command)
* **Problema:** Os motores não respondiam ao joystick em modo Teleoperado porque o subsistema `DriveTrain` não tinha um gatilho automático de chamada.
* **Solução:** Implementação do método `setDefaultCommand(new DriveWithJoystick());` dentro de `initDefaultCommand()`. Com isso, a WPILib passou a agendar o loop de leitura do controle por padrão.

### 🔹 Etapa 4: Resolução de Conflitos do Git e Envio ao GitHub
* **Problema:** Erro de repositório remoto duplicado (`error: remote origin already exists`) ao tentar reconfigurar o Git após uma desconfiguração local na máquina.
* **Solução:** Limpeza e sincronização forçada das branches locais e remotas apontando para a branch padrão `main`.
* **Autenticação:** Finalização do processo de envio contornando o travamento de credenciais do terminal via autenticação do navegador ("Git Ecosystem"). Código e documentação consolidados na nuvem via `git push -u origin main --force`.