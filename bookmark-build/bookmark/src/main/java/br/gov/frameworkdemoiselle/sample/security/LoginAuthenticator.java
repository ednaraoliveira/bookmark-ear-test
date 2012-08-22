package br.gov.frameworkdemoiselle.sample.security;


import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.security.Authenticator;
import br.gov.frameworkdemoiselle.security.User;

@SessionScoped
public class LoginAuthenticator implements Authenticator {
	
	@Inject
	private Login credential;
	
	private User user;
	
	@Override
	public boolean authenticate() {
		if ((credential.getLogin().equals("admin"))&&(credential.getPassword().equals("admin"))) {
			user = new User() {
				
				@Override
				public void setAttribute(Object key, Object value) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public String getId() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public Object getAttribute(Object key) {
					// TODO Auto-generated method stub
					return null;
				}
			};
			
			return true;
		}
		
		//c�digo de autentica��o
		
		// credencial -> banco (verificar usuario e senha)
		
		// carregar um objeto do tipo User
		
		return false;
	}

	@Override
	public User getUser() {
		
		//retornar o objeto User carregado no authenticate
		
		//c�digo para retornar usu�rio logado
		return user;
	}

	@Override
	public void unAuthenticate() {
		user = null;
		
		// TODO Auto-generated method stub
	}

}
