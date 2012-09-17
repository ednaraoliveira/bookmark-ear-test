package br.gov.frameworkdemoiselle.sample.business;


import javax.inject.Inject;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.annotation.Name;
import br.gov.frameworkdemoiselle.annotation.Priority;
import br.gov.frameworkdemoiselle.lifecycle.Shutdown;
import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.sample.security.Login;
import br.gov.frameworkdemoiselle.sample.config.PropertiesConfig;
import br.gov.frameworkdemoiselle.sample.domain.Bookmark2;
import br.gov.frameworkdemoiselle.sample.persistence.BookmarkDAO2;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.security.SecurityContext;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

@BusinessController
public class BookmarkBC2 extends DelegateCrud<Bookmark2, Long, BookmarkDAO2> {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	@Name("messages-core")
	private ResourceBundle bundle;
	
	@Inject
	PropertiesConfig propertiesConfig;
	
	@Inject
	Login login;
	
	@Inject
	Logger logger;
	
	@Inject
	private SecurityContext securityContext;	
	

	@Priority(3)
	@Startup
	@Transactional
	public void load() {
		if (findAll().isEmpty()) {
			insert(new Bookmark2("Demoiselle Portal", "http://www.frameworkdemoiselle.gov.br"));
			insert(new Bookmark2("Demoiselle SourceForge", "http://sf.net/projects/demoiselle"));
			insert(new Bookmark2("Twitter", "http://twitter.frameworkdemoiselle.gov.br"));
			insert(new Bookmark2("Blog", "http://blog.frameworkdemoiselle.gov.br"));
			insert(new Bookmark2("Wiki", "http://wiki.frameworkdemoiselle.gov.br"));
			insert(new Bookmark2("Bug Tracking", "http://tracker.frameworkdemoiselle.gov.br"));
			insert(new Bookmark2("Forum", "http://forum.frameworkdemoiselle.gov.br"));
			insert(new Bookmark2("SVN", "http://svn.frameworkdemoiselle.gov.br"));
			insert(new Bookmark2("Maven", "http://repository.frameworkdemoiselle.gov.br"));
			insert(new Bookmark2("Downloads", "http://download.frameworkdemoiselle.gov.br"));
		}
	}
	
	@Priority(1)
	@Startup
	public void initServer() {
		logger.info("********************** INICIANDO O SERVIDOR 2 ********************** ");
	}
	
	@Priority(2)
	@Startup
	public void executeGrant() {
		logger.info("********************** HABILITANDO AS PERMISSÕES 2 ********************** ");
	}

	@Priority(4)
	@Startup
	public void readConfig() {
		logger.info("********************** CONFIGURACOES ********************** ");
		logger.info("backgroundColor....: " + propertiesConfig.getBackgroundColor());
		logger.info("fontFamily.........: " + propertiesConfig.getFontFamily());
		logger.info("fontColor..........: " + propertiesConfig.getFontColor());
		logger.info("fontSize...........: " + propertiesConfig.getFontSize());
		logger.info("textAlign..........: " + propertiesConfig.getAlign());
		logger.info("resourseBundle.....: " + bundle.getString("button.test"));
	}

	@Priority(1)
	@Shutdown
	public void removeGrant() {
		logger.info("********************** DESABILITANDO AS PERMISSÕES 2 ********************** ");
	}
	
	@Priority(2)
	@Shutdown
	public void stopServer() {
		logger.info("********************** FINALIZANDO O SERVIDOR 2 ********************** ");
	}	
	
	@Override
	@RequiredRole("admin")
	public void insert(Bookmark2 bean) {
		logger.info("Inserindo...");
		super.insert(bean);
	}	
	
	public void logar() {
		logger.info("LOGANDO...");
		login.setLogin("admin");
		login.setPassword("admin");
		login.setRole("admin");
		securityContext.login();
	}	
	
}
