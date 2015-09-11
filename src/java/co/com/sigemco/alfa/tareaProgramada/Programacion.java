/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.tareaProgramada;

import java.util.Date;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author root
 */
public class Programacion {

    private Scheduler horario;

    private void crearProgramacion () {
        try {
            SchedulerFactory factoria = new StdSchedulerFactory();
            horario = factoria.getScheduler();
            horario.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void iniciarTarea() {
        if (this.horario == null) {
            this.crearProgramacion();
        }
        //Informacion sobre la tarea
        //La informacion que se pide un nombre, a que grupo pertenece
        //y a que clase que implemente de Job va a ser llamado
        JobDetail job = new JobDetail("TareaAutomatizar", null, TareaInvocar.class) {
        };
        //Creamos un trigger y le decimos cada cuanto queremos que se invoque la tarea o Job
        //Tenemos los metodos como los siguientes
        //TriggerUtils.makeSecondlyTrigger(tiempo) --> Para invocar una tarea cada cierto segundos
        //TriggerUtils.makeMinutelyTrigger(tiempo) --> Para invocar una tarea cada cierto minutos
        //TriggerUtils.makeHourlyTrigger(tiempo) --> Para invocar una tarea cada cierta hora
        //Entre otros mas que pueden revisar en la documentacion
        //Trigger trigger = TriggerUtils.makeSecondlyTrigger(30);
        //Trigger trigger = TriggerUtils.makeDailyTrigger(2, 0);
        Trigger trigger = TriggerUtils.makeDailyTrigger(3, 0);
        //En que momento va a iniciar la tarea
        trigger.setStartTime(new Date());
        //El nombre del trigger que debe ser unico
        trigger.setName("CierreDiario");
        try {
            ((StdScheduler) this.horario).scheduleJob(job, trigger);
        } catch (SchedulerException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
