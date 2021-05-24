/**
 * 
 */


import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import javax.swing.JEditorPane;

/**
 * @author dinesh
 *
 */
public class ActualHelper {
	public static void main(String[] args) {
		JEditorPane htmlPane = null;
		System.setProperty("sun.net.spi.nameservice.nameservers", "8.8.8.8");
		System.setProperty("sun.net.spi.nameservice.provider.1", "dns,sun");
		System.out.println("i m helper");
		InetAddress ia = null;
		try {
			ia = InetAddress.getByName(PropertyManager.getDefadd());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			htmlPane = new JEditorPane(new URL("http://"+PropertyManager.getDefadd()));
			PropertyManager.setActSrc(htmlPane.getText());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        htmlPane.setEditable(false);
		
		System.out.println("defadd:"+PropertyManager.getDefadd()+"hadd"+ia.getHostAddress());
		PropertyManager.setRefIp(ia.getHostAddress());
		System.out.println(PropertyManager.getRefIp());
		//System.exit(0);
	}
	/*static {
		System.setProperty("sun.net.spi.nameservice.nameservers", "8.8.8.8");
		System.setProperty("sun.net.spi.nameservice.provider.1", "dns,sun");
		System.out.println("i m helper");

		InetAddress ia = null;
		try {
			ia = InetAddress.getByName(PropertyManager.getDefadd());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PropertyManager.setRefIp(ia.getHostAddress());
	}*/
}
