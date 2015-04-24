/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.logica;

import co.com.hotel.persistencia.general.EnvioFunction;
import co.com.sigemco.alfa.inventario.dao.ConteoProdDao;
import co.com.sigemco.alfa.inventario.dao.DetalleConteoDao;
import co.com.sigemco.alfa.inventario.dto.ConteoProdDto;
import co.com.sigemco.alfa.inventario.dto.DetalleConteoDto;
import com.google.gson.Gson;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author nicolas
 */
public class ConteoProdLogica {

    /**
     * Funcion encargada de realizar la logica para la insercion del registro de
     * un conteo de inventario nuevo
     *
     * @param objDto
     * @return
     */
    public String insertaCreacionConteo(ConteoProdDto objDto) {
        String rta = "";
        ConteoProdDao objDao = null;
        try (EnvioFunction function = new EnvioFunction()) {
            objDao = poblarDao(objDto);
            boolean valida = function.enviarUpdate(objDao.insertaConteo());
            if (valida) {
                rta = "Ok";
            } else {
                rta = "Error";
            }

        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error";
        }
        return rta;
    }

    public List consultaGeneralConteos(ConteoProdDto objDto) {
        ConteoProdDao objDao = null;
        List<ConteoProdDto> rta = null;
        try (EnvioFunction function = new EnvioFunction()) {
            objDao = poblarDao(objDto);
            ResultSet rs = function.enviarSelect(objDao.consultaGeneral());
            while (rs.next()) {
                if (rta == null) {
                    rta = new ArrayList<ConteoProdDto>();
                }
                ConteoProdDto aux = new ConteoProdDto();
                aux.setCopr_copr(rs.getString("copr_copr"));
                aux.setCopr_estado(rs.getString("copr_estado"));
                aux.setCopr_tius(rs.getString("copr_tius"));
                aux.setCopr_fecha(rs.getString("copr_fecha"));
                aux.setCopr_sede(rs.getString("copr_sede"));
                aux.setCopr_fec_ini(rs.getString("copr_fec_ini"));
                aux.setCopr_fec_fin(rs.getString("copr_fec_fin"));
                aux.setCopr_desc(rs.getString("copr_desc"));
                aux.setSede_nombre(rs.getString("sede_nombre"));
                rta.add(aux);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion encargada de realizar la logica para buscar un conteo por su Id
     *
     * @param objDto
     * @return
     */
    public ConteoProdDto consultaConteosId(ConteoProdDto objDto) {
        ConteoProdDao objDao = null;
        ConteoProdDto rta = null;
        try (EnvioFunction function = new EnvioFunction()) {
            objDao = poblarDao(objDto);
            ResultSet rs = function.enviarSelect(objDao.consultaConteoXId());
            while (rs.next()) {
                if (rta == null) {
                    rta = new ConteoProdDto();
                }
                rta.setCopr_copr(rs.getString("copr_copr"));
                rta.setCopr_estado(rs.getString("copr_estado"));
                rta.setCopr_tius(rs.getString("copr_tius"));
                rta.setCopr_fecha(rs.getString("copr_fecha"));
                rta.setCopr_sede(rs.getString("copr_sede"));
                rta.setCopr_fec_ini(rs.getString("copr_fec_ini"));
                rta.setCopr_fec_fin(rs.getString("copr_fec_fin"));
                rta.setCopr_desc(rs.getString("copr_desc"));
                rta.setSede_nombre(rs.getString("sede_nombre"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion encargada de poblar un objeto Dao apartir de un objeto Dto
     *
     * @param objDto
     * @return
     */
    public ConteoProdDao poblarDao(ConteoProdDto objDto) {
        ConteoProdDao objDao = new ConteoProdDao();
        try {
            objDao.setCopr_copr(objDto.getCopr_copr());
            objDao.setCopr_estado(objDto.getCopr_estado());
            objDao.setCopr_tius(objDto.getCopr_tius());
            objDao.setCopr_fecha(objDto.getCopr_fecha());
            objDao.setCopr_sede(objDto.getCopr_sede());
            objDao.setCopr_fec_ini(objDto.getCopr_fec_ini());
            objDao.setCopr_fec_fin(objDto.getCopr_fec_fin());
            objDao.setCopr_desc(objDto.getCopr_desc());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objDao;
    }

    /**
     * Funcion encargada de insertar un producto en un conteo
     *
     * @param copr_copr
     * @param dska_dska
     * @param cantidad
     * @return
     */
    public String insetarProdConteo(String copr_copr, String dska_dska, String cantidad) {
        String rta = "";
        ConteoProdDao objDao = new ConteoProdDao();
        try (EnvioFunction function = new EnvioFunction()) {
            objDao.setCopr_copr(copr_copr);
            objDao.setEcop_dska(dska_dska);
            objDao.setEcop_valor(cantidad);
            boolean valida = function.enviarUpdate(objDao.insertaProdConteo());
            if (valida) {
                return "Ok";
            } else {
                return "Error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error " + e;
        }
        return rta;
    }

    /**
     * Funcion encargada para realizar la validar si existe el registro del
     * producto por cada conteo
     *
     * @param copr_copr
     * @param dska_dska
     * @return
     */
    public String validaExisConteo(String copr_copr, String dska_dska) {
        String rta = "";
        ConteoProdDao objDao = null;
        try (EnvioFunction function = new EnvioFunction()) {
            objDao = new ConteoProdDao();
            objDao.setEcop_dska(dska_dska);
            objDao.setCopr_copr(copr_copr);
            ResultSet rs = function.enviarSelect(objDao.verificaExisProdConteo());
            while (rs.next()) {
                int con = rs.getInt("conteo");
                if (con == 0) {
                    return "Insert";
                } else {
                    return "Update";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error " + e;
        }
        return rta;
    }

    /**
     * Funcion encargada de hacer toda lo logica para actulizar un producto en
     * el conteo y retornar el objeto con la informacion actualizada
     *
     * @param copr_copr
     * @param dska_dska
     * @param cantidad
     * @return
     */
    public String actualizaValorProdConteoJson(String copr_copr, String dska_dska, String cantidad) {
        Map<String, Object> mapa = null;
        Gson gson = null;
        String objJson = "";
        String valida = "";
        try {
            mapa = new HashMap<String, Object>();
            gson = new Gson();
            String accion = validaExisConteo(copr_copr, dska_dska);
            if ("Insert".equalsIgnoreCase(accion)) {
                valida = insetarProdConteo(copr_copr, dska_dska, cantidad);
            } else if ("Update".equalsIgnoreCase(accion)) {
                valida = actualizaConteoProdXConteo(copr_copr, dska_dska, cantidad);
            } else {
                mapa.put("respuesta", accion);
            }
            if ("Ok".equalsIgnoreCase(valida)) {
                mapa.put("respuesta", "Ok");
                DetalleConteoLogica detConteoLog = new DetalleConteoLogica();
                DetalleConteoDto objConteo = detConteoLog.consultaDetalleConteo(copr_copr, dska_dska);
                mapa.put("Objeto", objConteo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        objJson = gson.toJson(mapa);
        return objJson;
    }

    public String actualizaConteoProdXConteo(String copr_copr, String dska_dska, String cantidad) {
        String rta = "";
        try (EnvioFunction function = new EnvioFunction()) {
            DetalleConteoDao objDao = new DetalleConteoDao();
            objDao.setEcop_copr(copr_copr);
            objDao.setEcop_dska(dska_dska);
            objDao.setEcop_valor(cantidad);
            boolean valida = function.enviarUpdate(objDao.actulizaProdXConteo());
            if (valida) {
                return "Ok";
            } else {
                return "Error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error " + e;
        }
        return rta;
    }

    /**
     * Funcion encargada de realizar la logica para el llamado de la funcion que
     * cierra los conteos de inventario
     *
     * @param copr_copr
     * @return
     */
    public String cierraConteo(String copr_copr) {
        Map<String, String> mapa = new HashMap<String, String>();
        Gson gson = new Gson();
        String rta = "";
        try (EnvioFunction function = new EnvioFunction()) {
            function.adicionarNombre("in_cierra_conteo");
            function.adicionarNumeric(copr_copr);
            rta = function.llamarFunction(function.getSql());
            function.recuperarString();
            String[] rtaVector = rta.split("-");
            int tam = rtaVector.length;
            if (tam == 2) {
                // Este mensaje lo envia la funcion que envia la funcion de java que
                // confirma que el llamado de a la funcion fue exitiso.
                if (rtaVector[1].equalsIgnoreCase("Ok")) {
                    // Aqui verifico si la consulta fue exitosa
                    rta = function.getRespuesta();
                } else {
                    rta = "Error al llamar la funcion de cierre de conteo";
                }
            } else {
                rta = "Error al llamar la funcion de cierre de conteo";
            }
        } catch (Exception e) {
            rta = "Error " + e;
            e.printStackTrace();
        }
        mapa.put("respuesta", rta);
        return gson.toJson(mapa);
    }

    /**
     * Funcion encargada de realizar la actualizacion para cambiar el estado al
     * conteo y actualizar la fecha de inicio del conteo
     *
     * @param copr_copr
     * @return
     */
    public String actualizaInicioConteo(String copr_copr) {
        Map<String, String> mapa = new HashMap<String, String>();
        Gson gson = new Gson();
        String rta = "";
        try (EnvioFunction function = new EnvioFunction()) {
            ConteoProdDao objDao = new ConteoProdDao();
            objDao.setCopr_copr(copr_copr);
            boolean valida = function.enviarUpdate(objDao.actulazaInicioConteo());
            if (valida) {
                mapa.put("respuesta", "Ok");
            } else {
                mapa.put("respuesta", "Error al actualizar el conteo");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        rta = gson.toJson(mapa);
        return rta;
    }

}
