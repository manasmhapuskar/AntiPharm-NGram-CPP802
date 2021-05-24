import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;


public class Browser extends JFrame implements HyperlinkListener, 
                                               ActionListener {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VisualIndicator vi;
	
	public static void main(String[] args) {
		//System.setProperty("sun.net.spi.nameservice.nameservers", "8.8.8.8");
		//System.setProperty("sun.net.spi.nameservice.provider.1", "dns,sun");
		//System.setProperty("networkaddress.cache.ttl", "0");
		//System.setProperty("sun.net.inetaddr.ttl", "0");
		//System.clearProperty("sun.net.inetaddr.ttl");
		//System.clearProperty("networkaddress.cache.ttl");
		//java.security.Security.setProperty("sun.net.spi.nameservice.nameservers", "8.8.8.8");
		//java.security.Security.setProperty("sun.net.spi.nameservice.provider.1", "dns,sun");
		
		//java.security.Security.setProperty("networkaddress.cache.ttl" , "1");
		//sun.net.InetAddressCachePolicy.setIfNotSet(0);
	
		if (args.length == 0)
	      new Browser("http://www.google.com");
	    else
	      new Browser(args[0]);
	}
	
	private JIconButton homeButton;
	private JTextField urlField;
	private JEditorPane htmlPane;
	private String initialURL;

	public Browser(String initialURL) {
	  
	    super("Simple Swing Browser");
	    this.initialURL = initialURL;
	    addWindowListener(new ExitListener());
	    WindowUtilities.setJavaLookAndFeel();
	
	    JPanel topPanel = new JPanel();
	    topPanel.setBackground(Color.lightGray);
	    homeButton = new JIconButton("home.gif");
	    homeButton.addActionListener(this);
	    JLabel urlLabel = new JLabel("URL:");
	    
	    vi = new VisualIndicator("V.I.");
	    
	    urlField = new JTextField(30);
	    urlField.setText(initialURL);
	    urlField.addActionListener(this);
	    topPanel.add(homeButton);
	    topPanel.add(urlLabel);
	    topPanel.add(urlField);
	    topPanel.add(vi);
	    getContentPane().add(topPanel, BorderLayout.NORTH);

	    try {
	        htmlPane = new JEditorPane(initialURL);
	        htmlPane.setEditable(false);
	        htmlPane.addHyperlinkListener(this);
	        JScrollPane scrollPane = new JScrollPane(htmlPane);
	        getContentPane().add(scrollPane, BorderLayout.CENTER);
	        
	    } catch(IOException ioe) {
	    	warnUser("Can't build HTML pane for " + initialURL 
	                + ": " + ioe);
	    }

	    Dimension screenSize = getToolkit().getScreenSize();
	    int width = screenSize.width * 8 / 10;
	    int height = screenSize.height * 8 / 10;
	    setBounds(width/8, height/8, width, height);
	    setVisible(true);
	    
	    //String proxyhost = "10.10.10.1";
	  	//String proxyport = "80";
	  	//System.setProperty("http.proxyHost", proxyhost);
	  	//System.setProperty("http.proxyPort", proxyport);
	    
	}

	public void actionPerformed(ActionEvent event) {
	    String url;
	    if (event.getSource() == urlField) 
	    	url = urlField.getText();
	    else  // Clicked "home" button instead of entering URL
	    	url = initialURL;
	    try {
	    	URL surl = new URL(url);
	    	htmlPane.setPage(surl);
	    	urlField.setText(url);
	    	checkPharming();
	      
	    } catch(IOException ioe) {
	    	warnUser("Can't follow link to " + url + ": " + ioe);
	    }
	}

	private void checkPharming() {
		PropertyManager.clearAll();
		System.out.println("i m checkpharming");
		String defip = URLReader.getIP(urlField.getText().replaceFirst("http://", ""));
		PropertyManager.setDefIp(defip);
		PropertyManager.setDefadd(urlField.getText().replaceFirst("http://", ""));
		//com.antipharmer.ui.ActualHelper.main(null);
		System.out.println(System.getProperty("java.class.path"));

		Process process;
		try {
			
			process = new ProcessBuilder("java ActualHelper").redirectErrorStream(true).start();
			//process = Runtime.getRuntime().exec("java com.antipharmer.ui.ActualHelper");
			String line=null;
			final InputStream is =process.getInputStream();
			Thread outputDrainer = new Thread() {
				public void run() {
					try {
						int c;
						do {
							c=is.read();
							if (c>=0) {
								System.out.print((char)c);
							}	
						} while (c>=0);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				};
			};
			outputDrainer.start();
			process.waitFor();
			System.out.println("Exit value="+process.exitValue());
			
			//System.out.println(java.security.Security.getProperty("sun.net.spi.nameservice.nameservers"));
			//System.out.println(java.security.Security.getProperty("sun.net.spi.nameservice.provider.1"));
			java.security.Security.setProperty("sun.net.spi.nameservice.nameservers", "8.8.8.8");
			java.security.Security.setProperty("sun.net.spi.nameservice.provider.1", "dns,sun");
			//System.out.println(java.security.Security.getProperty("sun.net.spi.nameservice.nameservers"));
			//System.out.println(java.security.Security.getProperty("sun.net.spi.nameservice.provider.1"));
			InetAddress ia[];
			try {
				ia = InetAddress.getAllByName(urlField.getText().replaceFirst("http://", ""));
				for (int i = 0; i < ia.length; i++) {
					System.out.println("real:"+ia[i].getHostAddress()+" prop:"+java.security.Security.getProperty("networkaddress.cache.ttl"));
					PropertyManager.setRefIp(ia[i].getHostAddress());
				}
				
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		System.out.println("runned");
		if (!PropertyManager.getDefIp().equals(PropertyManager.getRefIp())) {
			System.out.println(PropertyManager.getDefIp()+"/"+PropertyManager.getRefIp());
			vi.setWarning();
		}
	}
	
	public void hyperlinkUpdate(HyperlinkEvent event) {
	    if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
	    	try {
	    		htmlPane.setPage(event.getURL());
	    		urlField.setText(event.getURL().toExternalForm());
	    	} catch(IOException ioe) {
	    		warnUser("Can't follow link to " 
	                 + event.getURL().toExternalForm() + ": " + ioe);
	    	}
	    }
	}

	private void warnUser(String message) {
		JOptionPane.showMessageDialog(this, message, "Error", 
                                  JOptionPane.ERROR_MESSAGE);
	}
}
    
    
