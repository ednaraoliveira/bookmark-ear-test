package br.gov.frameworkdemoiselle.sample.security;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.security.Authorizer;

public class BusinessAuthorizer implements Authorizer{

	@Inject
	private Login login;
	
	@Override
	public boolean hasRole(String role) {
		if(role.equals(login.getRole())){
			return true;
		}else{
			return false;
		}		
	}

	@Override
	public boolean hasPermission(String resource, String operation) {
		if (resource.equals("negocio") && operation.equals("metodo2")){
			return true;
		} else {
			return false;
		}
	}

}
