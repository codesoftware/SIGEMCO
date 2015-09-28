<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/WEB-INF/TEMPLATE/cabecera.jsp"></s:include>
            <script type="text/javascript" src="/PROYECTOHOTEL/JS/FACTURACION/Fac_ReservaHabitacion.js"></script>
        <s:head/>       
    </head>
    <body>
        <s:div cssClass="header">
            <s:include value="/WEB-INF/TEMPLATE/FrameTop.jsp" > 
                <s:param name="nombre"><s:text name="usuario.apellido"/> <s:text name="usuario.nombre"/></s:param>
                <s:param name="perfil"><s:text name="usuario.NomPerfil"/></s:param>
                <s:param name="ultimoIngreso"><s:text name="usuario.ultimoIngreso"/></s:param>
            </s:include>
        </s:div>
        <br/>
        <s:div cssClass="navigator">
            <s:include value="/WEB-INF/TEMPLATE/menuHorizontal.jsp">
                <s:param name="title"><s:property value="usuario.usuario" /></s:param>
            </s:include> 
        </s:div>
        <br/>
        <br/>
    <center>
        <s:if test="hasActionErrors()">
            <div class="errorMensajes" style="display: none;">
                <div class="errors">
                    <s:actionerror/>                                               
                    <script>
                        mostrarMsnErr();
                    </script>
                </div>                
            </div>
            <br/>
        </s:if>

        <form method="post" action="Fac_BuscaHabitaciones" id="Fac_BuscaHabitaciones" autocomplete="off">
            <div class="tableInsert tabla1">
                <table>
                    <thead>
                        <tr>
                            <td colspan="3"> Datos del Cliente</td>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <b>Nombre:</b> <s:text name="cliente.apellidos" /> <s:text name="cliente.nombres" />

                            </td>                           
                            <td>
                                <b>Cedula:</b> <s:text name="cliente.cedula" />

                            </td>
                            <td>
                                <b>Correo:</b> <s:text name="cliente.mail" />                                
                            </td>                        
                            <td style="display:none">
                                <s:textfield name="cliente.idCliente" style="display:none"/>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <br/>
            <s:textfield name="cliente.mail" style="display:none" />
            <s:textfield name="cliente.cedula" style="display:none" />
            <s:textfield name="cliente.apellidos" style="display:none" /> 
            <s:textfield name="cliente.nombres" style="display:none" />
            <div class="tableConsultaMultiFiltro" >
                <table>
                    <tr width="100%">
                        <td colspan="2" align="center" class="titulo">BUSCAR HABITACIÓN</td>
                    </tr>
                    <tr>
                        <td>
                            <table>
                                <tr>
                                    <td>
                                        <s:textfield label="Numero de Pesonas"      name="numPersonas" placeholder="Filtro"/>
                                    <td>
                                </tr>                            
                            </table>
                        </td>
                        <td>
                            <table>
                                <tr>
                                    <td>
                                        <s:textfield label="Numero de Habitaciones" name="numHabitacion" placeholder="Filtro"/>
                                    <td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <table>
                                <tr>
                                    <td>
                                        <s:textfield label="Fecha Inicial" name="fechaInicial" placeholder="Filtro" cssClass="calendar"/>
                                    <td>
                                </tr>                            
                            </table>
                        </td>
                        <td>
                            <table>
                                <tr>
                                    <td>
                                        <s:textfield label="Numero de Días" name="numDias" placeholder="Filtro"/>
                                    <td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: right; height: 28px;">
                            <s:include value="/WEB-INF/TEMPLATE/botones/find.jsp" >
                                <s:param name="function">buscar</s:param>
                                <s:param name="title">Buscar Habiaciones Disponibles con los filtros ingresados</s:param>                                    
                            </s:include>
                        </td>
                    </tr>
                </table>
            </div>
        </form>
        <br/>
        <s:if test="rtaCon != null">
            <div id="tabs">
                <ul>
                    <li><a href="#tabs-1">Capacidad y Disponibilidad</a></li>
                    <li><a href="#tabs-2">Capacidad</a></li>
                    <li><a href="#tabs-3">Todas</a></li>
                </ul>
                <div id="tabs-1">
                    <div class="tableConsulta">
                        <table>                        
                            <tr>
                                <td>RESERVAR</td>
                                <td>HABITACIÓN NUM</td>
                                <td>NUM. CAMAS</td>
                                <td>MAX. PERSONAS</td>
                                <td>MIN. PERSONAS</td>
                            </tr>
                            <s:iterator value="rtaConDispo">
                                <tr>
                                    <td><input type="checkbox" name="reservo" value="<s:property value="idHabitacion" />" onchange="agregaQuitaReserva(this.value, this);
                                            verDisponibilidad(this.value, this.checked, this);"/></td>
                                    <td><a href="#" onclick="verReservas('<s:property value="idHabitacion" />')"><s:property value="numHabi"/></a></td>
                                    <td><s:property value="numCamas"/></td>
                                    <td><s:property value="NumMaxPers"/></td>
                                    <td><s:property value="NumMinPers"/></td>
                                </tr>
                            </s:iterator>
                            <tr>
                                <td colspan="5" style="height: 30px; text-align: right;"><a href="#" class="btnInserta" onclick="reservar();">RESERVAR</a></td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div id="tabs-2">
                    <div class="tableConsulta">
                        <table>
                            <tr>
                                <td>RESERVAR</td>
                                <td>HABITACIÓN NUM</td>
                                <td>NUM. CAMAS</td>
                                <td>MAX. PERSONAS</td>
                                <td>MIN. PERSONAS</td>
                            </tr>
                            <s:iterator value="rtaCon">
                                <tr>
                                    <td><input type="checkbox" name="reservo" value="<s:property value="idHabitacion" />" onchange="agregaQuitaReserva(this.value, this);
                                            verDisponibilidad(this.value, this.checked, this);"/></td>
                                    <td><a href="#" onclick="verReservas('<s:property value="idHabitacion" />')"><s:property value="numHabi"/></a></td>
                                    <td><s:property value="numCamas"/></td>
                                    <td><s:property value="NumMaxPers"/></td>
                                    <td><s:property value="NumMinPers"/></td>
                                </tr>
                            </s:iterator>
                            <tr>
                                <td colspan="5" style="height: 30px; text-align: right;"><a href="#" class="btnInserta" onclick="reservar();">RESERVAR</a></td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div id="tabs-3">
                    <div class="tableConsulta">
                        <table>                        
                            <tr>
                                <td>RESERVAR</td>
                                <td>HABITACIÓN NUM</td>
                                <td>NUM. CAMAS</td>
                                <td>MAX. PERSONAS</td>
                                <td>MIN. PERSONAS</td>
                            </tr>
                            <s:iterator value="rtaTodas">
                                <tr>
                                    <td><input type="checkbox" name="reservo" value="<s:property value="idHabitacion" />" onchange="agregaQuitaReserva(this.value, this);
                                            verDisponibilidad(this.value, this.checked, this);"/></td>
                                    <td><a href="#" onclick="verReservas('<s:property value="idHabitacion" />')"><s:property value="numHabi"/></a></td>
                                    <td><s:property value="numCamas"/></td>
                                    <td><s:property value="NumMaxPers"/></td>
                                    <td><s:property value="NumMinPers"/></td>
                                </tr>
                            </s:iterator>
                            <tr>
                                <td colspan="5" style="height: 30px; text-align: right;"><a href="#" class="btnInserta" onclick="reservar();">RESERVAR</a></td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>            
        </s:if>        
    </center>
    <form action="Fac_ReservarHabitaciones" id="Fac_ReservarHabitaciones" style="display:none"  autocomplete="off">
        <s:textfield name="habitacionesReservadas" cssClass="habitacionesReservadas" value=""/>
        <s:textfield name="cliente.cedula" />
        <s:textfield name="cliente.idCliente"/>
        <s:textfield name="numDias" />                                        
        <s:textfield name="fechaInicial"  />                                        
        <s:textfield name="accion" value="reservar"/>
    </form>   
    <div id="informacion" title="Información">
        <span id="mensaje"></span>        
    </div>
</body>
</html>
