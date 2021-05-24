/**
 * 
 */


import java.awt.Color;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * @author dinesh
 *
 */
public class VisualIndicator extends JPanel implements MyStandardComp{

	/**
	 * 
	 */
	private JLabel messageL;
	String default_message;
	private static final long serialVersionUID = 1L;
	public VisualIndicator(String defmessage) {
		default_message=defmessage;
		setProperty();
		initControls();
		addControls();
	}
	@Override
	public void setProperty() {
		setBackground(Color.GREEN);		
		
	}
	@Override
	public void initControls() {
		messageL=(new JLabel(default_message));
	}
	@Override
	public void addControls() {
		add(messageL);
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return messageL.getText();
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String messageText) {
		messageL.setText(messageText);
	}
	@Override
	public Insets getInsets() {
		return new Insets(10, 10, 10, 10);
	}
	public void setWarning() {
		setBackground(Color.RED);		
	}
}
