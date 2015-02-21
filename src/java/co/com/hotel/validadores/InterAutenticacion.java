package co.com.hotel.validadores;

/**
 *
 * @author Javandengi
 */
import co.com.hotel.datos.session.Usuario;
import co.com.hotel.utilidades.UsuarioHabilitado;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import java.util.Map;

public class InterAutenticacion implements Interceptor {

    public void destroy() {
    }

    public void init() {
    }

    public String intercept(ActionInvocation actionInvocation) throws Exception {
        Map session = actionInvocation.getInvocationContext().getSession();        
        Usuario usuario = (Usuario) session.get("usuario");
        if (usuario == null) {
            return Action.LOGIN;
        } else {
            Action action = (Action) actionInvocation.getAction();
            if (action instanceof UsuarioHabilitado) {
                ((UsuarioHabilitado) action).setUsuario(usuario);
            }
            return actionInvocation.invoke();
        }
    }
}
