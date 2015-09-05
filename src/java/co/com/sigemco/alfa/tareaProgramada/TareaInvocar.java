/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.tareaProgramada;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author root
 */
public class TareaInvocar  implements Job{
    	public void execute(JobExecutionContext jec)throws JobExecutionException{
		TareaProgramadaLogica tarea = new TareaProgramadaLogica();
		try {
			System.out.println("Ejecutando Tarea..");
			tarea.callCierre();
			System.out.println("Tarea Ejecutada..");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
		}
	}
    
}
