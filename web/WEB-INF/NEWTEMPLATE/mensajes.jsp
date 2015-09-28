<br/>
<div class="Mensajes" style="display: none;">
    <s:if test="hasActionErrors()">
        <div class="alert alert-danger" id="info" role="alert" ><h4><s:actionerror /></h4></div>
        <script>
            mostrarMsn();
        </script>
    </s:if>
</div>
<div class="MensajesOk" style="display: none;">
    <s:if test="hasActionMessages()">
        <div class="alert alert-success" id="info" role="alert" ><h4><s:actionmessage/></h4></div>
        <script>
            mostrarMsnOk();
        </script>
    </s:if>
</div>
<br/>