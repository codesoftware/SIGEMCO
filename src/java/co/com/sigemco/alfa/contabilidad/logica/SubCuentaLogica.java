/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.contabilidad.logica;

import co.com.hotel.persistencia.general.EnvioFunction;
import co.com.sigemco.alfa.contabilidad.dao.SubCuentaDao;
import co.com.sigemco.alfa.contabilidad.dto.ClaseDto;
import co.com.sigemco.alfa.contabilidad.dto.CuentaDto;
import co.com.sigemco.alfa.contabilidad.dto.GrupoDto;
import co.com.sigemco.alfa.contabilidad.dto.SubCuentaDto;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Funcion encargada de realizar la logica para Sub Cuentas
 *
 * @author Daniel
 */
public class SubCuentaLogica {

    /**
     * Funcion encargada de realizar la logica para obtener la lista de Sub
     * Cuentas por medio del id de la cuenta
     *
     * @author Daniel
     */
    public List<SubCuentaDto> obtieneSubCuentaXCuenta(String cuen_cuen) {
        List<SubCuentaDto> rta = null;
        SubCuentaDao objDao = null;
        try (EnvioFunction function = new EnvioFunction();) {
            objDao = new SubCuentaDao();
            objDao.setSbcu_cuen(cuen_cuen);
            ResultSet rs = function.enviarSelect(objDao.subCuentasXIdCuenta());
            while (rs.next()) {
                if (rta == null) {
                    rta = new ArrayList<SubCuentaDto>();
                }
                SubCuentaDto objDto = new SubCuentaDto();
                objDto.setSbcu_clas(rs.getString("sbcu_clas"));
                objDto.setSbcu_codigo(rs.getString("sbcu_codigo"));
                objDto.setSbcu_cuen(rs.getString("sbcu_cuen"));
                objDto.setSbcu_descripcion(rs.getString("sbcu_descripcion"));
                objDto.setSbcu_estado(rs.getString("sbcu_estado"));
                objDto.setSbcu_grup(rs.getString("sbcu_grup"));
                objDto.setSbcu_nombre(rs.getString("sbcu_nombre"));
                objDto.setSbcu_sbcu(rs.getString("sbcu_sbcu"));
                rta.add(objDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion encargada de realizar la logica para Sub Cuentas
     *
     * @author Daniel
     */
    public String insertSubCuenta(SubCuentaDto objDto, ClaseDto clasDto, CuentaDto cuenDto, GrupoDto grupoDto) {
        String rta = "OK";
        SubCuentaDao objDao = null;
        try (EnvioFunction function = new EnvioFunction();) {
            objDao = new SubCuentaDao();

            objDto.setSbcu_clas(clasDto.getClas_clas());
            objDto.setSbcu_grup(grupoDto.getGrup_grup());
            objDto.setSbcu_cuen(cuenDto.getCuen_cuen());

            objDao = poblarDao(objDto);
            boolean rs = function.enviarUpdate(objDao.insertSubCuenta());
            if (!rs) {
                rta = "Error ";
            }
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error " + e;
        }
        return rta;
    }

    /**
     * Funcion encargada de validar la existencia de una subcuenta
     *
     * @param sbcu_codigo
     * @return
     */
    public String validaExistenciaSubCuenta(String sbcu_codigo) {
        String rta = "";
        try (EnvioFunction function = new EnvioFunction()) {
            SubCuentaDao objDao = new SubCuentaDao();
            objDao.setSbcu_codigo(sbcu_codigo);
            ResultSet rs = function.enviarSelect(objDao.validaSubcuenta());
            if (rs.next()) {
                rta = "" + rs.getString("valida");
            } else {
                rta = "Error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error SubCuentaLogica.validaExistenciaSubCuenta " + e;
        }
        return rta;
    }

    /**
     * Funcion encargada de realizar la busqueda de una subcuenta por medio de
     * su codigo
     *
     * @param sbcu_codigo
     * @return
     */
    public SubCuentaDto buscaSubcuentaXCodigo(String sbcu_codigo) {
        SubCuentaDto objDto = null;
        try (EnvioFunction function = new EnvioFunction()) {
            SubCuentaDao objDao = new SubCuentaDao();
            objDao.setSbcu_codigo(sbcu_codigo);
            ResultSet rs = function.enviarSelect(objDao.buscaSubCuentaXCodigo());
            if (rs.next()) {
                objDto = new SubCuentaDto();
                objDto.setSbcu_sbcu(rs.getString("SBCU_SBCU"));
                objDto.setSbcu_cuen(rs.getString("SBCU_CUEN"));
                objDto.setSbcu_clas(rs.getString("SBCU_CLAS"));
                objDto.setSbcu_grup(rs.getString("SBCU_GRUP"));
                objDto.setSbcu_estado(rs.getString("SBCU_ESTADO"));
                objDto.setSbcu_nombre(rs.getString("SBCU_NOMBRE"));
                objDto.setSbcu_codigo(rs.getString("SBCU_CODIGO"));
                objDto.setSbcu_descripcion(rs.getString("SBCU_DESCRIPCION"));
                objDto.setSbcu_naturaleza(rs.getString("SBCU_NATURALEZA"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            objDto = null;
        }
        return objDto;
    }

    /**
     * Funcion encargada de buscar las subcuentas fijas por cada tipo de
     * documento
     *
     * @param tido_nombre
     * @return
     */
    public List<SubCuentaDto> buscaSubCuentasFijasPorTipoDocumento(String tido_nombre) {
        List<SubCuentaDto> rta = null;
        try (EnvioFunction function = new EnvioFunction()) {
            SubCuentaDao objDao = new SubCuentaDao();
            ResultSet rs = function.enviarSelect(objDao.buscaSubCuentasFijas(tido_nombre));
            while (rs.next()) {
                if (rta == null) {
                    rta = new ArrayList<SubCuentaDto>();
                }
                SubCuentaDto aux = new SubCuentaDto();
                aux.setSbcu_codigo(rs.getString("sbcu_codigo"));
                aux.setSbft_porcentaje(rs.getString("sbft_porcentaje"));
                aux.setSbft_comentario(rs.getString("sbft_comentario"));
                aux.setSbcu_naturaleza(rs.getString("sbft_naturaleza"));
                rta.add(aux);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion encargada de poblar del dao de SubCuentas con el Dto de
     * SubCuentas
     *
     * @param objDto
     * @return
     */
    public SubCuentaDao poblarDao(SubCuentaDto objDto) {
        SubCuentaDao objDao = new SubCuentaDao();
        try {
            objDao.setSbcu_clas(objDto.getSbcu_clas());
            objDao.setSbcu_codigo(objDto.getSbcu_codigo());
            objDao.setSbcu_cuen(objDto.getSbcu_cuen());
            objDao.setSbcu_descripcion(objDto.getSbcu_descripcion());
            objDao.setSbcu_estado(objDto.getSbcu_estado());
            objDao.setSbcu_grup(objDto.getSbcu_grup());
            objDao.setSbcu_nombre(objDto.getSbcu_nombre());
            objDao.setSbcu_sbcu(objDto.getSbcu_sbcu());
            objDao.setSbcu_naturaleza(objDto.getSbcu_naturaleza());
        } catch (Exception e) {
            e.printStackTrace();
            objDao = null;
        }
        return objDao;
    }

    public ArrayList<String> buscaSubCuentasXFiltro(String sbcu_codigo) {
        SubCuentaDao objDao = new SubCuentaDao();
        ArrayList lista = null;
        try (EnvioFunction function = new EnvioFunction()) {
            objDao.setSbcu_codigo(sbcu_codigo);
            ResultSet rs = function.enviarSelect(objDao.buscaSubcuentaXFiltroCodigo());
            while (rs.next()) {
                if (lista == null) {
                    lista = new ArrayList();
                }
                lista.add(rs.getString("sbcu_codigo"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * Funcion encargada de evaluar si se puede editar una subcuenta por medio
     * de su id
     *
     * @param sbcu_sbcu
     * @return
     */
    public String evaluaEdicionSubcuenta(String sbcu_sbcu) {
        String rta = null;
        SubCuentaDao objDao = new SubCuentaDao();
        try (EnvioFunction function = new EnvioFunction()) {
            ResultSet rs = function.enviarSelect(objDao.cuentaSubcuentasMvco(sbcu_sbcu));
            if (rs.next()) {
                rta = rs.getString("conteo");
                int aux = Integer.parseInt(rta);
                if (aux > 0) {
                    rta = "No";
                } else {
                    rta = "Si";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion encargada de realizar la busqueda de una subcuenta por medio de
     * su id
     *
     * @param sbcu_codigo
     * @return
     */
    public SubCuentaDto buscaSubcuentaXId(String sbcu_sbcu) {
        SubCuentaDto objDto = null;
        try (EnvioFunction function = new EnvioFunction()) {
            SubCuentaDao objDao = new SubCuentaDao();
            objDao.setSbcu_sbcu(sbcu_sbcu);
            ResultSet rs = function.enviarSelect(objDao.buscaSubCuentaXId());
            if (rs.next()) {
                objDto = new SubCuentaDto();
                objDto.setSbcu_sbcu(rs.getString("SBCU_SBCU"));
                objDto.setSbcu_cuen(rs.getString("SBCU_CUEN"));
                objDto.setSbcu_clas(rs.getString("SBCU_CLAS"));
                objDto.setSbcu_grup(rs.getString("SBCU_GRUP"));
                objDto.setSbcu_estado(rs.getString("SBCU_ESTADO"));
                objDto.setSbcu_nombre(rs.getString("SBCU_NOMBRE"));
                objDto.setSbcu_codigo(rs.getString("SBCU_CODIGO"));
                objDto.setSbcu_descripcion(rs.getString("SBCU_DESCRIPCION"));
                objDto.setSbcu_naturaleza(rs.getString("SBCU_NATURALEZA"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            objDto = null;
        }
        return objDto;
    }

    /**
     * Funcion encargada de realizar la logica para actualizar los datos de una
     * subcuenta por medio de su id
     *
     * @param objDto
     * @return
     */
    public String actualizaSubCuenta(SubCuentaDto objDto) {
        String rta = null;
        try (EnvioFunction function = new EnvioFunction()) {
            SubCuentaDao objDao = new SubCuentaDao();
            boolean valida = function.enviarUpdate(objDao.actualizasubCuenta(objDto));
            if (valida) {
                rta = "Ok";
            } else {
                rta = "Error";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion la cual contiene la logica para obtener el numero de subcuentas
     * que tiene una cuenta
     *
     * @param cuenta_cuenta
     * @return
     */
    public int cuentaSubcuentasCuenta(String cuenta_cuenta) {
        int conteo = 0;
        try (EnvioFunction function = new EnvioFunction()) {
            SubCuentaDao objDao = new SubCuentaDao();
            ResultSet rs = function.enviarSelect(objDao.cuentaSubcuentas(cuenta_cuenta));
            if (rs.next()) {
                conteo = rs.getInt("conteo");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conteo;
    }
}
