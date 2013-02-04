import org.apache.ws.java2wsdl.Java2WSDL;

public class GeneratorWSDL {
	public static void main(String[] args) {
		
//		try{
//		ConfigurationContext	context = ConfigurationContextFactory.createDefaultConfigurationContext();
//		File serviceArchiveFile = new File("Location of the file");
//		AxisConfiguration axiConfiguration = context.getAxisConfiguration();
//		AxisService service = AxisService.createService(serviceArchiveFile, axiConfiguration);
//
//		axiConfiguration.addService(service);
//		}catch(AxisFault ae){
//			ae.printStackTrace();
//		}catch(Exception e){
//			e.printStackTrace();
//		}
		
//		ServiceDeployer sd = new ServiceDeployer();
		
//		Java2WSDL -o wp.wsdl -l "http://localhost:8080/axis/services/WidgetPrice"
//	    -n  "urn:Example6" -p"samples.userguide.example6" "urn:Example6"
//	    samples.userguide.example6.WidgetPrice
		
	    try{
			Java2WSDL.main(new String[]{"-cp", "C:/workspace2/ServiceGenerator/bin", "-cn", "test.Test", "-of", "Test.wsdl"});
		}catch(Exception e){
			e.printStackTrace();
		}
//		//////
//		try{
//			Java2WSDL.main(new String[]{"-cp", "C:/workspace2/ServiceGenerator/bin", "-cn", "test.Test", "-of", "Test.wsdl"});
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		//////
	}
}
