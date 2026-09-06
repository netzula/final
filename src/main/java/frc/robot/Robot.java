// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.controls.VelocityVoltage;


//NO queremos el set enonces ponemos, porque trabaja con -1 a 1 y queremos 2.5



//este es el motor
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;





// Control de xbox
import  edu.wpi.first.wpilibj.XboxController;


/**
 * The methods in this class are called automatically corresponding to each mode, as described in
 * the TimedRobot documentation. If you change the name of this class or the package after creating
 * this project, you must also update the Main.java file in the project.
 */
public class Robot extends TimedRobot {

  
  private Command m_autonomousCommand;

  //la clase debe ir privada porque es algo que no se puede mostrar.

  //estos son los cuatros motores del chasis
  //motores del lado izq
  private final TalonFX rueda1iz = new TalonFX(1);
  private final TalonFX rueda2de = new TalonFX(2);


  //motores del lado derecho
  private final TalonFX rueda3iz = new TalonFX(3);
  private final TalonFX rueda4de = new TalonFX(4);


  //declaramos el modo autonnomo que es el tiempo
  private final Timer autoTimer = new Timer();
  
  //Aqui declaramos que el voltage 
  private final PositionVoltage reqPositionVoltage = new PositionVoltage(0);
  private final TalonFX motor = new TalonFX(9);
  private final VelocityVoltage flywheelsVoltage = new VelocityVoltage(0);
  private final TalonFX angulador = new TalonFX(10);


  private final TalonFX indexwheel = new TalonFX(13);


  //estos son los flywheel
  private final TalonFX flywheel1 = new TalonFX(11);
  private final TalonFX flywheel2 = new TalonFX(12);


  //relleno
  private static final String kDefaultAuto = "Default";
  private static final String kcustomAuto = "My auto";
  private final SendableChooser<String> m_Chooser = new SendableChooser<>();




  private final RobotContainer m_robotContainer;

  //Control del xbox
  XboxController control = new XboxController(0);

  //velocidad flywheels y el del boton b
  double rpm = 2500.0;
  double rps = rpm/ 60.0;

  //los rps no los ocupamos pero por si acaso, para una ayuda visual
  //el rpm para la fase 5
  double rpm2 = 1000.0;
  double rps2 = rpm2 / 60.0;

  //rpm del boton Y
  double rpm3 = 1800.0;
  double rps3 = rpm3 / 60.0;

  //modo desatasco
  double rpm4 = -800.0;
  double rps4 = rpm4 / 60.0;
  


 
  
  //ROTACION
  double rotacionGrados = 25.0;
  double anguloVuelta = rotacionGrados / 360.0;

  //ANGULACIÓN
  double anguloGrados = 30.0;
  
  //ROTACIONTELEOP y angulos
  double rotacionGrados2= 15.0;
   double rotacionVuelta2= rotacionGrados2/ 360.0;
     double anguloGrados2= 20.0;
     double rotacionGrados3= 15.0;
   double rotacionVuelta3= rotacionGrados3/ 360.0;
        double anguloGrados3= 30.0;

  
  


  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  public Robot() {
 
   
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
    m_Chooser.setDefaultOption(kcustomAuto, kDefaultAuto);
    SmartDashboard.putData("Auto choices", m_Chooser);
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();
    //Inicio del temporizador que va comenzar desde cero
    autoTimer.reset();
    autoTimer.start();


    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      CommandScheduler.getInstance().schedule(m_autonomousCommand);
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {

    //fase 1
    if (autoTimer.get() >=0.0 && autoTimer.get() < 2.5){

       rueda1iz.setVoltage(2.5);
       rueda2de.setVoltage(2.5);
       rueda3iz.setVoltage(2.5);
       rueda4de.setVoltage(2.5);
      
    }

      //fase 2

      else if (autoTimer.get() >= 2.5 && autoTimer.get() < 3.5){
        rueda1iz.setVoltage(6);
          rueda2de.setVoltage (-6);
          //lado derecha
           rueda3iz.setVoltage(6);
           rueda4de.setVoltage(-6);
           //flywheels
           motor.setVoltage(0);
           angulador.setVoltage(0);

      }


    // fase 3
     else if (autoTimer.get() >= 3.5 && autoTimer.get() < 7.5) {
      //como no podiamos poner las coordenadas exactas hicimos una aproximación con voltaje, hasta donde podia llega.    
      rueda1iz.setVoltage(6); 
       rueda2de.setVoltage(-6); 
        rueda3iz.setVoltage(6);  
        rueda4de.setVoltage(-6);
    

      //2500 rpm
      flywheel1.setControl(flywheelsVoltage.withVelocity(rpm));
      flywheel2.setControl(flywheelsVoltage.withVelocity(rpm));
     }

      //fase 4
      else if (autoTimer.get() >= 7.5 && autoTimer.get() < 10.5) {
       rueda1iz.setVoltage(3);
       rueda2de.setVoltage(-3);

       //CHECA LOS VOLTIOS

       rueda3iz.setVoltage(3);
       rueda4de.setVoltage(-3);
       
       motor.setVoltage(0);
       angulador.setVoltage(0);
      }
      // FASE 5: 10.5 - 15.0 segundos
else if (autoTimer.get() >= 10.5 && autoTimer.get() < 15.0) {

  // CHASIS DETENIDO
  rueda1iz.setVoltage(0);
  rueda2de.setVoltage(0);
  rueda3iz.setVoltage(0);
  rueda4de.setVoltage(0);

  // ROTACIÓN DE LA TORRETA A 25°
  motor.setControl(
      reqPositionVoltage.withPosition(anguloVuelta)
  );

  // ANGULACIÓN A 30°
  angulador.setControl(
      reqPositionVoltage.withPosition(anguloVuelta)
  );

  // INDEX A 1000 RPM
  indexwheel.setControl(
      indexwheel.withVelocity(rps2)
  );
}


      //FASE 6

      
      else if (autoTimer.get() >= 15.0 && autoTimer.get() < 20.0) {
        //CHASIS APAGADO
        rueda1iz.setVoltage(0);
        rueda2de.setVoltage(0);
        rueda3iz.setVoltage(0);
        rueda4de.setVoltage(0);

        //TORRETA YA HASTA AHÍ
        flywheel1.setVoltage(0);
        flywheel2.setVoltage(0);
      
        //INDEX YA BYE
        indexwheel.setVoltage(0);

        //ANGULO 
       motor.setVoltage(0);
        angulador.setVoltage(0);
      }
    
    




    }





  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic(){
    //Xbox pasa al teleoperado 
  if (control.getYButton()){

    //15 de motor y 20 de angulacion 
    flywheel1.set(rpm3);
    flywheel2.set(rpm3);
    motor.setControl(reqPositionVoltage.withPosition(anguloVuelta));
     angulador.setControl(reqPositionVoltage.withPosition(anguloGrados)); 
  } 

     if (control.getBButton()) {
    //ocupa 25 de motor y 30 de angulacion
    flywheel1.set(rpm);
    flywheel2.set(rpm);
    
    
    //DESATASCO 
    if (control.getRightTriggerAxis() < 1){   
       flywheel1.set(rpm4);      
       flywheel2.set(rpm4);}


    if (control.getLeftY()>0.1){
       rueda1iz.set(control.getLeftY());
       rueda2de.set(control.getLeftY());   
       rueda4de.set(control.getLeftY());  
       rueda3iz.set(control.getLeftY());}

      else if (control.getLeftY()<-0.1){
        rueda1iz.set(control.getLeftY());
        rueda2de.set(control.getLeftY());
        rueda4de.set(control.getLeftY());
        rueda3iz.set(control.getLeftY());

      }
      else {
         rueda1iz.set(0);
         rueda2de.set(0);
         rueda4de.set(0);
         rueda3iz.set(0);
     }
     }
      
     
     if (control.getLeftX() > 0.1) {
       rueda1iz.set(control.getLeftX());
        rueda3iz.set(control.getLeftX());
        rueda2de.set(-control.getLeftX());
        rueda4de.set(-control.getLeftX());}

       else if (control.getLeftX() < -0.1) {
    rueda1iz.set(-control.getLeftX());
    rueda3iz.set(-control.getLeftX());
    rueda2de.set(control.getLeftX());
    rueda4de.set(control.getLeftX());}
    else {
    rueda1iz.set(0);
    rueda3iz.set(0);
    rueda2de.set(0);
    rueda4de.set(0);


  
  }



   }

  

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}




}
