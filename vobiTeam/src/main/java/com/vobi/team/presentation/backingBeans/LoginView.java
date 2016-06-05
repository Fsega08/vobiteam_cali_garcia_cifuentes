package com.vobi.team.presentation.backingBeans;

import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.presentation.businessDelegate.IBusinessDelegatorView;
import com.vobi.team.utilities.*;

import org.primefaces.component.inputtext.InputText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;


@ViewScoped
@ManagedBean(name = "loginView")
public class LoginView {
	
	public final static Logger log=LoggerFactory.getLogger(VtArtefactoView.class);
	
    private String userId;
    private String password;
    
    private String email;
    
    @ManagedProperty(value = "#{authenticationManager}")
    private AuthenticationManager authenticationManager = null;
    
    @ManagedProperty(value="#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

    
    public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    public void setAuthenticationManager(
        AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String login() throws Exception {
        try {
            Authentication request = new UsernamePasswordAuthenticationToken(this.getUserId(),
                    this.getPassword());
            Authentication result = authenticationManager.authenticate(request);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(result);

            FacesUtils.getHttpSession(true)
                      .setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
            
            
            VtUsuario vtUsuario = businessDelegatorView.findUsuarioByLogin(userId);
            Long permisos = businessDelegatorView.rolMasBajoPorUsuario(vtUsuario);
            
            FacesUtils.putinSession("permisos", permisos);
            
            if (permisos == 1L) {
				return "/XHTML/dashboard.xhtml";
			}else if (permisos == 2L) {
				return "/desarrollador/dashboard.xhtml";
			}else if (permisos == 3L) {
				return "/cliente/dashboard.xhtml";
			}else{
				throw new Exception();
			}
            
        } catch (AuthenticationException e) {          
        	FacesUtils.putinSession("permisos", 0L);
            return "/login.xhtml";
        }
        
    }
    
    
    public void recuperarAction() throws Exception{

    	try {
    		
    		if (email.isEmpty() || email.trim().equals("")) {
    			throw new Exception("Por favor ingrese un email");
			}
    		
    		if (Utilities.validateEmail(email) == false) {
    			throw new Exception("Por favor ingrese un email valido");
			}
    		
    		VtUsuario vtUsuario = businessDelegatorView.findUsuarioByLogin(email);
    		
    		if (vtUsuario==null) {
				throw new Exception("El usuario no se encuentra registrado");
			} else {
				businessDelegatorView.recuperarContrasena(vtUsuario);
				
				FacesUtils.addInfoMessage("Se ha enviado el correo");
			}

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
    
    }
    
    
}
