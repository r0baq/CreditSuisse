package org.mroczkarobert.creditsuisse.util;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.catalina.connector.Connector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

public class GracefulShutdownCustomizer implements TomcatConnectorCustomizer, ApplicationListener<ContextClosedEvent> {

	private Logger log = LogManager.getLogger();

	private volatile Connector connector;

	@Override
	public void customize(Connector connector) {
		this.connector = connector;
	}

	@Override
	public void onApplicationEvent(ContextClosedEvent event) {
		connector.pause();
		Executor executor = connector.getProtocolHandler().getExecutor();
		
		if (executor instanceof ThreadPoolExecutor) {
			try {
				ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
				threadPoolExecutor.shutdown();
				if (!threadPoolExecutor.awaitTermination(30, TimeUnit.SECONDS)) {
					log.warn("Tomcat thread pool did not shut down gracefully within 30 seconds. Proceeding with forceful shutdown");
				}
				
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
	}
}