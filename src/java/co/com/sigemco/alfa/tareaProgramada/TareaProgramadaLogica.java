/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.tareaProgramada;

import co.com.sigemco.alfa.contabilidad.action.CierreDiarioAction;

/**
 *
 * @author root
 */
public class TareaProgramadaLogica {
    
    public void callCierre(){
        CierreDiarioAction cda = new CierreDiarioAction();
        cda.insertaCierreTarea();
    }
}
