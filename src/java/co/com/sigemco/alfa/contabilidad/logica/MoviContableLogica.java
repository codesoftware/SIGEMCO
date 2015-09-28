/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.contabilidad.logica;

import co.com.hotel.persistencia.general.EnvioFunction;
import co.com.hotel.utilidades.ManejoLocateCO;
import co.com.sigemco.alfa.contabilidad.dto.MoviContableDao;
import co.com.sigemco.alfa.contabilidad.dto.MoviContableDto;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nicolas
 */
public class MoviContableLogica {

    /**
     * Funcion encargada de realizar la logica para obtener la informacion
     * necesaria para un asiento contable
     *
     * @param mvco_trans String id de la transaccion
     * @return
     */
    public List<MoviContableDto> generaAsientoContable(String mvco_trans) {
        ManejoLocateCO loc = new ManejoLocateCO();
        List<MoviContableDto> rta = null;
        try (EnvioFunction function = new EnvioFunction()) {
            MoviContableDao objDao = new MoviContableDao();
            objDao.setMvco_trans(mvco_trans);
            ResultSet rs = function.enviarSelect(objDao.generaAsientoContable());
            while (rs.next()) {
                if (rta == null) {
                    rta = new ArrayList<MoviContableDto>();
                }
                MoviContableDto aux = new MoviContableDto();
                aux.setSbcu_nombre(rs.getString("sbcu_nombre"));
                aux.setSbcu_codigo(rs.getString("sbcu_codigo"));
                aux.setDebitos(loc.doubleTOMoney(rs.getString("debitos")));
                aux.setCreditos(loc.doubleTOMoney(rs.getString("creditos")));
                rta.add(aux);
            }
        } catch (Exception e) {
            e.printStackTrace();
            rta = null;
        }
        return rta;
    }

    public ArrayList<MoviContableDto> consultaMoviContable(MoviContableDto objDTO) {
        ResultSet rs = null;
        MoviContableDto aux = null;
        ArrayList<MoviContableDto> result = null;
        MoviContableDao objDAO = null;
        try (EnvioFunction funcion = new EnvioFunction()) {
            
            objDAO = poblarDAO(objDTO);
            String filtros = traeFiltros(objDTO);
            rs = funcion.enviarSelect(objDAO.consultaFilros(filtros));
            result = new ArrayList<>();
            while (rs.next()) {
                aux = new MoviContableDto();
                aux.setClas_nombre(rs.getString("clas_nombre"));
                aux.setCuen_nombre(rs.getString("cuen_nombre"));
                aux.setGrup_nombre(rs.getString("grup_nombre"));
                aux.setSbcu_codigo(rs.getString("sbcu_codigo"));
                aux.setSbcu_nombre(rs.getString("sbcu_nombre"));
                aux.setMvco_id_llave(rs.getString("mvco_id_llave"));
                aux.setMvco_lladetalle(rs.getString("mvco_lladetalle"));
                aux.setMvco_mvco(rs.getString("mvco_mvco"));
                aux.setMvco_naturaleza(rs.getString("mvco_naturaleza"));
                aux.setMvco_tercero(rs.getString("mvco_tercero"));
                aux.setMvco_trans(rs.getString("mvco_trans"));
                aux.setMvco_valor(rs.getString("mvco_valor"));
                aux.setMvco_fecha(rs.getString("mvco_fecha"));
                result.add(aux);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

    public MoviContableDao poblarDAO(MoviContableDto objDTO) {
        MoviContableDao objDAO = new MoviContableDao();
//        objDAO.setMvco_id_llave(objDTO.getMvco_id_llave());
//        objDAO.setMvco_lladetalle(objDTO.getMvco_lladetalle());
//        objDAO.setMvco_mvco(objDTO.getMvco_mvco());
//        objDAO.setMvco_naturaleza(objDTO.getMvco_naturaleza());
//        objDAO.setMvco_sbcu(objDTO.getMvco_sbcu());
//        objDAO.setMvco_tercero(objDTO.getMvco_tercero());
//        objDAO.setMvco_tido(objDTO.getMvco_tido());
//        objDAO.setMvco_tipo(objDTO.getMvco_tipo());
//        objDAO.setMvco_trans(objDTO.getMvco_trans());
//        objDAO.setMvco_valor(objDTO.getMvco_valor());
//        objDAO.setClas_clas(objDTO.setClas_clas(null));
        objDAO.setClas_clas(objDTO.getClas_clas());
        objDAO.setCuen_cuen(objDTO.getCuen_cuen());
        objDAO.setGrup_grup(objDTO.getGrup_grup());
        objDAO.setFechaFin(objDTO.getFechaFin());
        objDAO.setFechaIni(objDTO.getFechaIni());
        return objDAO;
    }
    public String traeFiltros(MoviContableDto objDTO) {
        String rta="1 = 1 and sbcu_sbcu = mvco_sbcu and grup_grup = sbcu_grup and cuen_cuen = sbcu_cuen and clas_clas = sbcu_clas";
        try {
            if(objDTO.getClas_clas() != null & !"-1".equalsIgnoreCase(objDTO.getClas_clas())){
               rta+=" and sbcu_clas="+objDTO.getClas_clas();
                if(objDTO.getGrup_grup() != null & !objDTO.getGrup_grup().equalsIgnoreCase("-1") ){
                    rta+=" and sbcu_grup="+objDTO.getGrup_grup();
                    if(objDTO.getCuen_cuen().equalsIgnoreCase("")){
                         rta+=" and sbcu_cuen="+objDTO.getCuen_cuen();
                    }
                }
            }
            if(!"".equalsIgnoreCase(objDTO.getFechaIni().trim())){
                if(!"".equalsIgnoreCase(objDTO.getFechaFin().trim())){                    
                    rta += " and mvco_fecha between to_timestamp('" + objDTO.getFechaIni().trim() + "', 'dd/mm/yyyy') and  to_timestamp('"+ objDTO.getFechaFin().trim() +"', 'dd/mm/yyyy')";
                }else{
                    rta += " and mvco_fecha >  to_timestamp('" + objDTO.getFechaIni().trim() + "', 'dd/mm/yyyy') ";
                }
            }else if(!"".equalsIgnoreCase(objDTO.getFechaFin().trim())){
                rta += " and mvco_fecha <  to_timestamp('" + objDTO.getFechaFin().trim() + "', 'dd/mm/yyyy') ";                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
        
    }
    
}
