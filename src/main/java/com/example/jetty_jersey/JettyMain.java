package com.example.jetty_jersey;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class JettyMain {

	public static void main(String[] args) throws Exception {
		// Initialize the server
		Server server = new Server();

		// Add a connector
		ServerConnector connector = new ServerConnector(server);
		connector.setHost("0.0.0.0");
		connector.setPort(8080);
		connector.setIdleTimeout(30000);
		server.addConnector(connector);

		// Configure Jersey
		ResourceConfig rc = new ResourceConfig();
		rc.packages(true, "com.example.jetty_jersey.ws");
		rc.register(JacksonFeature.class);
		rc.register(LoggingFilter.class);

		// Add a servlet handler for web services
		ServletHolder servletHolder = new ServletHolder(new ServletContainer(rc));
		ServletContextHandler handlerWebServices = new ServletContextHandler(ServletContextHandler.SESSIONS);
		handlerWebServices.addServlet(servletHolder, "/*");

		// Activate handlers
		ContextHandlerCollection contexts = new ContextHandlerCollection();
		contexts.setHandlers(new Handler[] { handlerWebServices });
		server.setHandler(contexts);

		// Start server
		server.start();

	}

}
