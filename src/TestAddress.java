import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;


public class TestAddress extends Applet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	InetAddress addressbyaddress[],addressbyip;
	URLConnection addCon;
	URL addressURL;
	public void paint(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(10, 10, 100, 100);
		setSize(500, 800);

		String defip=getParameter("defip");
		String address=getParameter("address");
		String host=null;
		int len,c;
		try {
			addressURL = new URL(address);
			addCon = addressURL.openConnection();
			host = addressURL.getHost();
			len = addCon.getContentLength();
			if(len != 0) {
				System.out.println("=== Content ===");
				InputStream input = addCon.getInputStream();
				int i = len;
				while (((c = input.read()) != -1)) { // && (--i > 0)) {
				System.out.print((char) c);
				}
				input.close();
				} else {
				System.out.println("No content available.");
				}

			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			 addressbyaddress = InetAddress.getAllByName(host);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			addressbyip = InetAddress.getByName(defip);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Addressbyaddress ip :"+addressbyaddress);
		System.out.println("given ip :"+addressbyip);
		boolean flag=false;
		for (int i = 0; i < addressbyaddress.length; i++) {
			System.out.println(addressbyaddress[i]);
			if(addressbyaddress[i].equals(addressbyip)) {
				flag=true;
				//g.drawString("Site legitimate", 200, 200);
			} else {
				//g.drawString("doubt", 200, 200);
			}			
		}
		if (flag) {
			g.drawString("Site legitimate", 200, 200);
		} else {
			
		}
	}
}
