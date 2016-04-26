package com.vobi.team.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Component;

import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.modelo.VtUsuarioRol;
import com.vobi.team.presentation.businessDelegate.IBusinessDelegatorView;
import com.vobi.team.utilities.FacesUtils;

import java.util.ArrayList;
import java.util.List;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
@Scope("singleton")
@Component("zathuraCodeAuthenticationProvider")
public class ZathuraCodeAuthenticationProvider implements AuthenticationProvider {
    /**
     * Security Implementation
     */
	
	@Autowired
	private IBusinessDelegatorView buisnessDelegator;
    @Override
    public Authentication authenticate(Authentication authentication)
        throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        boolean autorizado = false;
		try {
			autorizado = buisnessDelegator.autenticarUsuario(name, password);
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
        
        if(autorizado){
        	 final List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();

             try {
            	VtUsuario vtUsuario = buisnessDelegator.findUsuarioByLogin(name);
				List<VtUsuarioRol> usuarioRol = buisnessDelegator.findUsuarioRolbyUsuario(vtUsuario);
				
				for (VtUsuarioRol vtUsuarioRol : usuarioRol) {
					String rol = vtUsuarioRol.getVtRol().getRolNombre();
					
					grantedAuths.add(new SimpleGrantedAuthority("ROLE_" + rol));
				}
			} catch (Exception e) {
				FacesUtils.addErrorMessage(e.getMessage());
			}
                     
             
             final UserDetails principal = new User(name, password, grantedAuths);
             final Authentication auth = new UsernamePasswordAuthenticationToken(principal,
                     password, grantedAuths);

             return auth;
        }else{
        	return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
