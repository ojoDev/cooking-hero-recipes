package com.ojodev.cookinghero.recipes.config;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Controller
public class ServerInfo {
	
	private static final Logger LOGGER = Logger.getLogger( ServerInfo.class);
	
	private static final String PORT_SEPARATOR = ":";
	private static final String RESOURCE_SEPARATOR = "/";

	@Autowired
	private Environment environment;

	public String getServerURL() {
		String host = "";
		try {
			host = InetAddress.getLocalHost().getHostAddress();
			String port = environment.getProperty("server.port");
			return isValidPort(port) ? new StringBuilder().append(host).append(PORT_SEPARATOR).append(port).toString(): host;
		} catch (UnknownHostException e) {
			LOGGER.warn("Error obtaining server.port environment property");
			return "";
		}
	}
	
	public String getServerResourceURI(String resource) {
		return new StringBuilder(getServerURL()).append(RESOURCE_SEPARATOR).append(resource).toString();
	}
	
	private boolean isValidPort(String port) {
		return NumberUtils.isNumber(port) && Integer.valueOf(port) > -1;
	}


	

}
