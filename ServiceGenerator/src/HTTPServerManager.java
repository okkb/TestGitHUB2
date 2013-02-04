import org.apache.axis2.AxisFault;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.axis2.deployment.DeploymentEngine;
import org.apache.axis2.description.AxisServiceGroup;
import org.apache.axis2.engine.AxisConfiguration;
import org.apache.axis2.transport.http.SimpleHTTPServer;

import java.io.File;

public class HTTPServerManager {
	private static SimpleHTTPServer server;
	protected static int serverPort = 8080;

	public synchronized static void startHTTPServer() {
		ConfigurationContext context;
		try {
//			context=ConfigurationContextFactory.createConfigurationContextFromFileSystem(location of axis2.xml, null);
			context = ConfigurationContextFactory.createDefaultConfigurationContext();
			server = new SimpleHTTPServer(context, serverPort);
			String resourcePath = HTTPServerManager.class.getResource("location of axis2service.aar").getPath();
			AxisServiceGroup serviceGroup = DeploymentEngine.loadServiceGroup(new File(resourcePath), context);
			AxisConfiguration xConfig = context.getAxisConfiguration();
			xConfig.addServiceGroup(serviceGroup);
			server.start();

		} catch (AxisFault axisFault) {
			axisFault.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method used to stop SimpleHTTPServer
	 */
	public synchronized static void stopHTTPServer() {
		server.stop();
	}

	public static void main(String[] args) {
		startHTTPServer();
		stopHTTPServer();
	}
}
