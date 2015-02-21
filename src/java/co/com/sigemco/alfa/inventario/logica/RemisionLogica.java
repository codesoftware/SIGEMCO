/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.logica;

import co.com.hotel.dto.Sede;
import co.com.hotel.logica.sede.Adm_SedeLogica;
import co.com.hotel.persistencia.general.EnvioFunction;
import co.com.sigemco.alfa.inventario.dao.RemisionDao;
import co.com.sigemco.alfa.inventario.dto.ReferenciaDTO;
import co.com.sigemco.alfa.inventario.dto.RemisionDto;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Clase encargada de la logica de las remisones del sistema
 *
 * @author nicolas
 */
public class RemisionLogica {

    /**
     * Funcion encargada de realizar la logica de insersion de una remision
     *
     * @return
     */
    public String insertaRemision(RemisionDto objDto) {
        String rta = "";
        RemisionDao objDao = null;
        try (EnvioFunction function = new EnvioFunction()) {
            objDto.setFiltros("N");
            objDao = poblarDao(objDto);
            System.out.println("Sql: " + objDao.insert());
            if (function.enviarUpdate(objDao.insert())) {
                rta = "Ok";
            } else {
                rta = "Error al insertar";
            }
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error RemisionLogica.insertaRemision " + e;
        }
        return rta;
    }

    /**
     * Funcion encargada de migrar la informacion de un objeto Dto a un objeto
     * dao
     *
     * @param objDto Objeto Data Transfer Object dto
     * @return RemisionDao Objeto Data Acces object
     */
    public RemisionDao poblarDao(RemisionDto objDto) {
        RemisionDao rta = null;
        try {
            rta = new RemisionDao();
            rta.setRmce_rmce(objDto.getRmce_rmce());
            rta.setRmce_refe(objDto.getRmce_refe());
            rta.setRmce_imei(objDto.getRmce_imei());
            rta.setRmce_iccid(objDto.getRmce_iccid());
            rta.setRmce_valor(objDto.getRmce_valor());
            rta.setRmce_comision(objDto.getRmce_comision());
            rta.setRmce_tppl(objDto.getRmce_tppl());
            rta.setRmce_fcve(objDto.getRmce_fcve());
            rta.setRmce_fcsl(objDto.getRmce_fcsl());
            rta.setRmce_fcen(objDto.getRmce_fcen());
            rta.setRmce_tius_ent(objDto.getRmce_tius_ent());
            rta.setRmce_tius_sal(objDto.getRmce_tius_sal());
            rta.setRmce_codigo(objDto.getRmce_codigo());
            rta.setRmce_sede(objDto.getRmce_sede());
            rta.setRmce_pagado(objDto.getRmce_pagado());
            rta.setRmce_estado(objDto.getRmce_estado());
            rta.setValorBeteween(objDto.getValorBeteween());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion encargada de realizar la logica de la consulta general por
     * filtros de las remisiones
     *
     * @param objTo
     * @return
     */
    public List<RemisionDto> consultaGeneralXFiltros(RemisionDto objTo) {
        List<RemisionDto> rta = null;
        RemisionDao objDao = null;
        objDao = poblarDao(objTo);
        try (EnvioFunction function = new EnvioFunction()) {
            ResultSet rs = function.enviarSelect(objDao.consultaGeneralXFiltros());
            while (rs.next()) {
                if (rta == null) {
                    rta = new ArrayList<RemisionDto>();
                }
                RemisionDto aux = new RemisionDto();
                aux.setRmce_rmce(rs.getString("rmce_rmce"));
                aux.setRmce_refe(rs.getString("rmce_refe"));
                aux.setRmce_imei(rs.getString("rmce_imei"));
                aux.setRmce_iccid(rs.getString("rmce_iccid"));
                aux.setRmce_valor(rs.getString("rmce_valor"));
                aux.setRmce_comision(rs.getString("rmce_comision"));
                aux.setRmce_tppl(rs.getString("rmce_tppl"));
                aux.setRmce_fcve(rs.getString("rmce_fcve"));
                aux.setRmce_fcsl(rs.getString("rmce_fcsl"));
                aux.setRmce_fcen(rs.getString("rmce_fcen"));
                aux.setRmce_tius_ent(rs.getString("rmce_tius_ent"));
                aux.setRmce_tius_sal(rs.getString("rmce_tius_sal"));
                aux.setRmce_codigo(rs.getString("rmce_codigo"));
                aux.setRmce_sede(rs.getString("rmce_sede"));
                aux.setRmce_estado(rs.getString("rmce_estado"));
                aux.setRmce_pagado(rs.getString("rmce_pagado"));
                aux.setRmce_comdev(rs.getString("rmce_comdev"));
                //Logica para obtener la descripcion de la referencia
                ReferenciaLogica logicaRef = new ReferenciaLogica();
                ReferenciaDTO objRef = new ReferenciaDTO();
                objRef.setRefe_refe(aux.getRmce_refe());
                objRef = logicaRef.traeReferenciaEspecifica(objRef);
                logicaRef = null;
                aux.setRmce_refe(objRef.getRefe_desc());
                //Logica para obtener el nombre de la sede
                Adm_SedeLogica logicaSede = new Adm_SedeLogica();
                Sede sedeAux = logicaSede.consultarSedeEspecifico(aux.getRmce_sede());
                aux.setRmce_sede(sedeAux.getSede_nombre());
                logicaSede = null;
                aux.setFiltros("S");
                rta.add(aux);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    public RemisionDto consultaCelularEspecifico(RemisionDto objTo) {
        RemisionDao objDao = null;
        objDao = poblarDao(objTo);
        RemisionDto aux = new RemisionDto();
        try (EnvioFunction function = new EnvioFunction()) {
            ResultSet rs = function.enviarSelect(objDao.consultaEspecifica());

            while (rs.next()) {
                aux.setRmce_rmce(rs.getString("rmce_rmce"));
                aux.setRmce_refe(rs.getString("rmce_refe"));
                aux.setRmce_imei(rs.getString("rmce_imei"));
                aux.setRmce_iccid(rs.getString("rmce_iccid"));
                aux.setRmce_valor(rs.getString("rmce_valor"));
                aux.setRmce_comision(rs.getString("rmce_comision"));
                aux.setRmce_tppl(rs.getString("rmce_tppl"));
                aux.setRmce_fcve(rs.getString("rmce_fcve"));
                aux.setRmce_fcsl(rs.getString("rmce_fcsl"));
                aux.setRmce_fcen(rs.getString("rmce_fcen"));
                aux.setRmce_tius_ent(rs.getString("rmce_tius_ent"));
                aux.setRmce_tius_sal(rs.getString("rmce_tius_sal"));
                aux.setRmce_codigo(rs.getString("rmce_codigo"));
                aux.setRmce_sede(rs.getString("rmce_sede"));
                aux.setRmce_estado(rs.getString("rmce_estado"));
                aux.setRmce_pagado(rs.getString("rmce_pagado"));
                aux.setRmce_comdev(rs.getString("rmce_comdev"));
                aux.setFiltros("N");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return aux;
    }

    /**
     * Funcion encargada de realizar la logica para encontrar los equipos
     * celulares proximos a vencer
     *
     * @return
     */
    public List<RemisionDto> encuentraEquiposProximosAVencer() {
        List<RemisionDto> rta = null;
        RemisionDao objDao = new RemisionDao();
        try (EnvioFunction function = new EnvioFunction()) {
            ResultSet rs = function.enviarSelect(objDao.remisionesProximasAVencer());
            while (rs.next()) {
                if (rta == null) {
                    rta = new ArrayList<RemisionDto>();
                }
                RemisionDto aux = new RemisionDto();
                aux.setRmce_rmce(rs.getString("rmce_rmce"));
                aux.setRmce_refe(rs.getString("refe_desc"));
                aux.setRmce_fcve(rs.getString("rmce_fcve"));
                aux.setRmce_sede(rs.getString("sede_nombre"));
                aux.setRmce_iccid(rs.getString("rmce_iccid"));
                aux.setRmce_imei(rs.getString("rmce_imei"));
                aux.setFiltros("S");
                rta.add(aux);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    public String actualizaCelularEspecifico(RemisionDto objTo) {
        RemisionDao objDao = null;
        objDao = poblarDao(objTo);
        try (EnvioFunction function = new EnvioFunction()) {
            if (function.enviarUpdate(objDao.actualizaEspecifica())) {
                return "CELULAR ACTUALIZADO CORRECTAMENTE";
            } else {
                return "NO PUDO ACTUALIZAR EL CELULAR";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR AL ACTUALIZAR CELULAR";
        }
    }
    
    /**
     * Funcion encargada de devolver un  celular
     * @param rmce_rmce
     * @param rmce_comdev
     * @return 
     */
    
    public String devolverCelular(String rmce_rmce, String rmce_comdev){
         RemisionDao objDao = new RemisionDao();
         try(EnvioFunction function = new EnvioFunction()) {
            
            objDao.setRmce_comdev(rmce_rmce);
            objDao.setRmce_rmce(rmce_rmce);
             if (function.enviarUpdate(objDao.devuelveCelular())) {
                return "CELULAR DEVUELTO CORRECTAMENTE";
            } else {
                return "NO PUDO DEVOLVER EL CELULAR EL CELULAR";
            }
        } catch (Exception e) {
            
            e.printStackTrace();
            return "ERROR DEVOLVIENDO CELULAR";
        }
        
    }
}
