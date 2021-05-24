

import java.net.*;
import java.util.logging.LogManager;
import java.io.*;

public class URLReader {
    public static void main(String[] args) throws Exception {
    	//String proxyhost = "10.10.10.1";
    	//String proxyport = "80";
    	//System.setProperty("http.proxyHost", proxyhost);
    	
		//System.setProperty("http.proxyPort", proxyport);
    	
    	//System.setProperty("sun.net.spi.nameservice.nameservers", "8.8.8.8");
   	 	//System.setProperty("sun.net.spi.nameservice.provider.1", "dns,sun");
        
        URL oracle = new URL("http://www.google.co.in/");
        BufferedReader in = new BufferedReader(
        new InputStreamReader(oracle.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();
        System.out.println("----------------------------------END----------------------------------");
        //getIP("www.google.com", false);
    }
    public static String getIP(String hostName) {
    	InetAddress ia = null;     	
        	System.out.println("Hostname = "+hostName);
    	try {
			ia = InetAddress.getByName(hostName);
			return ia.getHostAddress();
    	} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
	}
}
