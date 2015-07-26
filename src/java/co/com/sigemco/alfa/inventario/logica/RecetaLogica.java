/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sigemco.alfa.inventario.logica;

import co.com.hotel.logica.sede.Adm_SedeLogica;
import co.com.hotel.persistencia.general.EnvioFunction;
import co.com.sigemco.alfa.inventario.dao.RecetaDao;
import co.com.sigemco.alfa.inventario.dto.PrecioRecetaDto;
import co.com.sigemco.alfa.inventario.dto.PrecioSedeRecetaDto;
import co.com.sigemco.alfa.inventario.dto.RecetaDto;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author nicolas
 */
public class RecetaLogica {

    /**
     * Funcion encargada de realizar la inserccion de una receta a la base de
     * datos
     *
     * @param objDto
     * @return
     */
    public String insertaReceta(RecetaDto objDto) {
        String rta = "";
        try (EnvioFunction function = new EnvioFunction()) {
            RecetaDao objDao = new RecetaDao();
            boolean valida = function.enviarUpdate(objDao.insertareceta(objDto));
            if (valida) {
                rta = "Ok";
            } else {
                rta = "Error al insertar la receta";
            }
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error " + e;
        }
        return rta;
    }

    /**
     * Funcion encargada de realizar la logica de consulta de las recetas
     * teniendo en cuenta los filtros dados por el usuario
     *
     * @param objDto
     * @return
     */
    public List consultaGeneralXFiltros(RecetaDto objDto) {
        List<RecetaDto> rta = null;
        try (EnvioFunction function = new EnvioFunction()) {
            RecetaDao objDao = new RecetaDao();
            ResultSet rs = function.enviarSelect(objDao.consultaGeneralRecetasXFiltros(objDto));
            while (rs.next()) {
                if (rta == null) {
                    rta = new ArrayList<>();
                }
                RecetaDto aux = new RecetaDto();
                aux.setRece_rece(rs.getString("Rece_rece"));
                aux.setRece_codigo(rs.getString("Rece_codigo"));
                aux.setRece_nombre(rs.getString("Rece_nombre"));
                aux.setRece_desc(rs.getString("Rece_desc"));
                aux.setRece_iva(rs.getString("Rece_iva"));
                aux.setRece_estado(rs.getString("Rece_estado"));
                aux.setRece_fec_ingreso(rs.getString("Rece_fec_ingreso"));
                aux.setRece_promedio(rs.getString("Rece_promedio"));
                rta.add(aux);

            }
        } catch (Exception e) {
            e.printStackTrace();
            rta = null;
        }
        return rta;
    }

    /**
     * Funcion con la cual se realiza la logica para actualizar las recetas del
     * sistema
     *
     * @param objDto
     * @return
     */
    public String actualizaReceta(RecetaDto objDto) {
        String rta = "Ok";
        try (EnvioFunction function = new EnvioFunction()) {
            RecetaDao objDao = new RecetaDao();
            boolean valida = function.enviarUpdate(objDao.actualizaRecetaXId(objDto));
            if (!valida) {
                rta = "Error al ejecutar la receta";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion que se encarga de realizar la logica para la busqueda de una
     * receta por su id
     *
     * @param objDto
     * @return
     */
    public RecetaDto consultaRecetaXId(RecetaDto objDto) {
        RecetaDto rta = null;
        try (EnvioFunction function = new EnvioFunction()) {
            RecetaDao objDao = new RecetaDao();
            ResultSet rs = function.enviarSelect(objDao.consultaGeneralRecetasXId(objDto));
            if (rs.next()) {
                if (rta == null) {
                    rta = new RecetaDto();
                }
                rta.setRece_rece(rs.getString("Rece_rece"));
                rta.setRece_codigo(rs.getString("Rece_codigo"));
                rta.setRece_nombre(rs.getString("Rece_nombre"));
                rta.setRece_desc(rs.getString("Rece_desc"));
                rta.setRece_iva(rs.getString("Rece_iva"));
                rta.setRece_estado(rs.getString("Rece_estado"));
                rta.setRece_fec_ingreso(rs.getString("Rece_fec_ingreso"));
                rta.setRece_promedio(rs.getString("Rece_promedio"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion encargada de realizar la logica de consulta de las recetas
     * teniendo en cuenta el codigo de la receta
     *
     * @param objDto
     * @return
     */
    public RecetaDto consultaGeneralXCodigo(RecetaDto objDto) {
        RecetaDto aux = null;
        try (EnvioFunction function = new EnvioFunction()) {
            RecetaDao objDao = new RecetaDao();
            ResultSet rs = function.enviarSelect(objDao.consultaGeneralRecetasXCodigo(objDto));
            while (rs.next()) {
                if (aux == null) {
                    aux = new RecetaDto();
                }
                aux.setRece_rece(rs.getString("Rece_rece"));
                aux.setRece_codigo(rs.getString("Rece_codigo"));
                aux.setRece_nombre(rs.getString("Rece_nombre"));
                aux.setRece_desc(rs.getString("Rece_desc"));
                aux.setRece_iva(rs.getString("Rece_iva"));
                aux.setRece_estado(rs.getString("Rece_estado"));
                aux.setRece_fec_ingreso(rs.getString("Rece_fec_ingreso"));
                aux.setRece_promedio(rs.getString("Rece_promedio"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            aux = null;
        }
        return aux;
    }

    /**
     * Funcion con la cual actualiza los precios de las recetas
     *
     * @return
     */
    public String actualizaPrecioReceta(RecetaDto objDto, String tius_tius) {
        String rta = "Ok";
        try (EnvioFunction function = new EnvioFunction()) {
            RecetaDao objDao = new RecetaDao();
            String valida = this.inactivaRecetasXSede(objDto);
            if ("Ok".equalsIgnoreCase(valida)) {
                boolean respuesta = function.enviarUpdate(objDao.insertPrecioReceta(objDto, tius_tius));
                if (!respuesta) {
                    rta = "Error al insertar el precio nuevo de la receta ";
                }
            } else {
                rta = valida;
            }
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error " + e;
        }
        return rta;
    }

    /**
     * Funcion con la cual inactivo todos los precios de una receta por sede
     *
     * @param objDto
     * @return
     */
    public String inactivaRecetasXSede(RecetaDto objDto) {
        String rta = "Ok";
        try (EnvioFunction function = new EnvioFunction()) {
            RecetaDao objDao = new RecetaDao();
            boolean valida = function.enviarUpdate(objDao.actualizaPreciosInactivosXSede(objDto));
            if (!valida) {
                rta = "Error al inactivar los precios";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion encargada de buscar por sede todos los precios que se han
     * parametrizados para determinada receta
     *
     * @return
     */
    public ArrayList<PrecioSedeRecetaDto> buscaPreciosSedeRecetas(String rece_rece) {
        ArrayList<PrecioSedeRecetaDto> lista = null;
        try {
            Adm_SedeLogica sedeLogica = new Adm_SedeLogica();
            Map<String, String> sedes = sedeLogica.obtieneSedes();
            for (Map.Entry<String, String> e : sedes.entrySet()) {
                if (lista == null) {
                    lista = new ArrayList<PrecioSedeRecetaDto>();
                }
                PrecioSedeRecetaDto aux = new PrecioSedeRecetaDto();
                aux.setSede_sede(e.getKey());
                aux.setSede_nombre(e.getValue());
                aux.setPrecios(this.buscaPreciosSedeReceta(e.getKey(), rece_rece));
                lista.add(aux);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * Funcion encargada de realizar la logica para buscar el historial de
     * precios de una receta por sede
     *
     * @param sede_sede
     * @param rece_rece
     * @return
     */
    public ArrayList<PrecioRecetaDto> buscaPreciosSedeReceta(String sede_sede, String rece_rece) {
        ArrayList<PrecioRecetaDto> rta = null;
        try (EnvioFunction function = new EnvioFunction()) {
            RecetaDao objDao = new RecetaDao();
            ResultSet rs = function.enviarSelect(objDao.buscaPreciosPorSede(sede_sede, rece_rece));
            while (rs.next()) {
                if (rta == null) {
                    rta = new ArrayList<>();
                }
                PrecioRecetaDto aux = new PrecioRecetaDto();
                aux.setPrre_sede(rs.getString("prre_sede"));
                aux.setPrre_prre(rs.getString("prre_prre"));
                aux.setPrre_precio(rs.getString("prre_precio"));
                aux.setPrre_fecha(rs.getString("prre_fecha"));
                String aux2 = rs.getString("prre_estado");
                if ("A".equalsIgnoreCase(aux2)) {
                    aux.setPrre_estado("ACTIVO");
                } else {
                    aux.setPrre_estado("INACTIVO");
                }
                rta.add(aux);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion encargada de realizar la logica para insertar un producto en una
     * receta
     *
     * @param rece_rece
     * @param dska_dska
     * @param tius_tius
     * @return
     */
    public String insertaProductoReceta(String rece_rece, String dska_dska, String tius_tius) {
        String rta = "";
        RecetaDto objDto = new RecetaDto();
        try (EnvioFunction function = new EnvioFunction()) {
            ProductoLogica objLogicaProd = new ProductoLogica();
            String auxProm = objLogicaProd.obtieneValorPonderadoProducto(dska_dska);
            if (auxProm != null) {
                objDto.setRece_promedio(auxProm);
                objDto.setRece_rece(rece_rece);
                RecetaDao objDao = new RecetaDao();
                boolean valida = function.enviarUpdate(objDao.adicionaProductoReceta(objDto, dska_dska, tius_tius));
                if (valida) {
                    rta = "Ok";
                } else {
                    rta = "Error al crear la insercion del producto en la receta";
                }
            } else {
                rta = "Error al obtener el promedio ponderado por favor contacte al administrador del sistema";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion encargada de realizar la logica para adicionar una unidad a un
     * producto de una receta en especifico
     *
     * @param rece_rece
     * @param rece_dska
     * @return
     */
    public String adicionaCantidadProductoReceta(String rece_rece, String rece_dska) {
        String rta = "";
        try (EnvioFunction function = new EnvioFunction()) {
            RecetaDao objDao = new RecetaDao();
            boolean valida = function.enviarUpdate(objDao.sumaUnoCantidadProductoReceta(rece_rece, rece_dska));
            if (valida) {
                rta = "Ok";
            } else {
                rta = "Error al realizar la actualizacion ";
            }
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error " + e;
        }
        return rta;
    }

    /**
     * Funcion la cual realiza la logica para contar si un producto en
     * especifico existe en una receta
     *
     * @param rece_rece
     * @param dska_dska
     * @return
     */
    public int validaProductoReceta(String rece_rece, String dska_dska) {
        int conteo = 0;
        try (EnvioFunction function = new EnvioFunction()) {
            RecetaDao objDao = new RecetaDao();
            ResultSet rs = function.enviarSelect(objDao.cuentaProductosReceta(rece_rece, dska_dska));
            while (rs.next()) {
                conteo = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conteo;
    }

    /**
     * Funcion encargada de realizar la logica para eliminar un producto de una
     * receta especifica
     *
     * @param rece_rece
     * @param rece_dska
     * @return
     */
    public String eliminaProdReceta(String rece_rece, String rece_dska) {
        String rta = "";
        try (EnvioFunction function = new EnvioFunction()) {
            RecetaDao objDao = new RecetaDao();
            boolean valida = function.enviarUpdate(objDao.eliminaFilaProductoReceta(rece_rece, rece_dska));
            if (valida) {
                rta = "Ok";
            } else {
                rta = "Error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error " + e;
        }
        return rta;
    }

    /**
     * .Funcion con la cual se le quita una unidad a un producto de una receta
     * en especifico
     *
     * @param rece_rece
     * @param rece_dska
     * @return
     */
    public String reduceProdReceta(String rece_rece, String rece_dska) {
        String rta = "";
        try (EnvioFunction function = new EnvioFunction()) {
            RecetaDao objDao = new RecetaDao();
            boolean valida = function.enviarUpdate(objDao.reduceUnProductoReceta(rece_rece, rece_dska));
            if (valida) {
                rta = "Ok";
            } else {
                rta = "Error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error " + e;
        }
        return rta;
    }

    /**
     * Funcion la cual realiza la logica para obtener la cantidad de productos
     * que hay en una receta
     *
     * @param rece_rece
     * @param dska_dska
     * @return
     */
    public int obtieneCantidadProductoReceta(String rece_rece, String dska_dska) {
        int conteo = 0;
        try (EnvioFunction function = new EnvioFunction()) {
            RecetaDao objDao = new RecetaDao();
            ResultSet rs = function.enviarSelect(objDao.obtieneCatidadProdReceta(rece_rece, dska_dska));
            while (rs.next()) {
                conteo = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conteo;
    }
    
}
