/**
 * 
 */


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author dinesh
 *
 */
public class PropertyManager {
	public static String getDefIp() {
		Properties p = new Properties();
		FileInputStream fin;
		try {
			fin = new FileInputStream("ap.properties");
			p.load(fin);
			fin.close();
		} catch (FileNotFoundException e) {
			System.out.println("db.properties not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Problem in read db.properties");
			e.printStackTrace();
		}
		return p.getProperty("defip");
	}
	public static void setDefIp(String defip) {
		Properties p = new Properties();
		FileInputStream fin;
		try {
			fin = new FileInputStream("ap.properties");
			p.load(fin);
			fin.close();
		} catch (FileNotFoundException e) {
			System.out.println("db.properties not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Problem in read db.properties");
			e.printStackTrace();
		}
		p.setProperty("defip", defip);
		FileOutputStream out;
		try {
			out = new FileOutputStream("ap.properties");
			p.store(out, "---No Comment---");
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static String getDefadd() {
		Properties p = new Properties();
		FileInputStream fin;
		try {
			fin = new FileInputStream("ap.properties");
			p.load(fin);
			fin.close();
		} catch (FileNotFoundException e) {
			System.out.println("db.properties not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Problem in read db.properties");
			e.printStackTrace();
		}
		return p.getProperty("defadd");
	}
	public static void setDefadd(String defadd) {
		Properties p = new Properties();
		FileInputStream fin;
		try {
			fin = new FileInputStream("ap.properties");
			p.load(fin);
			fin.close();
		} catch (FileNotFoundException e) {
			System.out.println("db.properties not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Problem in read db.properties");
			e.printStackTrace();
		}
		p.setProperty("defadd", defadd);
		FileOutputStream out;
		try {
			out = new FileOutputStream("ap.properties");
			p.store(out, "---No Comment---");
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void clearAll() {
		Properties p = new Properties();
		FileInputStream fin;
		try {
			fin = new FileInputStream("ap.properties");
			p.load(fin);
			fin.close();
		} catch (FileNotFoundException e) {
			System.out.println("db.properties not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Problem in read db.properties");
			e.printStackTrace();
		}
		String s="";
		p.setProperty("defadd", s);
		p.setProperty("defip", s);
		p.setProperty("refip", s);
		p.setProperty("actsrc", s);
		
		FileOutputStream out;
		try {
			out = new FileOutputStream("ap.properties");
			p.store(out, "---No Comment---");
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void setRefIp(String Refip) {
		Properties p = new Properties();
		FileInputStream fin;
		try {
			fin = new FileInputStream("ap.properties");
			p.load(fin);
			fin.close();
		} catch (FileNotFoundException e) {
			System.out.println("db.properties not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Problem in read db.properties");
			e.printStackTrace();
		}
		p.setProperty("refip", Refip);
		FileOutputStream out;
		try {
			out = new FileOutputStream("ap.properties");
			p.store(out, "---No Comment---");
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String getRefIp() {
		Properties p = new Properties();
		FileInputStream fin;
		try {
			fin = new FileInputStream("ap.properties");
			p.load(fin);
			fin.close();
		} catch (FileNotFoundException e) {
			System.out.println("db.properties not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Problem in read db.properties");
			e.printStackTrace();
		}
		return p.getProperty("refip");
	}
	public static void setActSrc(String text) {
		Properties p = new Properties();
		FileInputStream fin;
		try {
			fin = new FileInputStream("ap.properties");
			p.load(fin);
			fin.close();
		} catch (FileNotFoundException e) {
			System.out.println("db.properties not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Problem in read db.properties");
			e.printStackTrace();
		}
		p.setProperty("actsrc", text);
		FileOutputStream out;
		try {
			out = new FileOutputStream("ap.properties");
			p.store(out, "---No Comment---");
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public static String getActSrc() {
		Properties p = new Properties();
		FileInputStream fin;
		try {
			fin = new FileInputStream("ap.properties");
			p.load(fin);
			fin.close();
		} catch (FileNotFoundException e) {
			System.out.println("db.properties not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Problem in read db.properties");
			e.printStackTrace();
		}
		return p.getProperty("actsrc");
	}
}
