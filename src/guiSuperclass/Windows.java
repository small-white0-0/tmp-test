package guiSuperclass;

import java.awt.Font;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public abstract class Windows {

	protected JFrame theFrame;
	public void displayFram() {
		theFrame.setVisible(true);
		theFrame.repaint();
	}
	
	public static void InitGlobalFont(Font font) {
	    FontUIResource fontRes = new FontUIResource(font);
	    for (Enumeration<Object> keys = UIManager.getDefaults().keys();
	         keys.hasMoreElements(); ) {
	      Object key = keys.nextElement();
	      Object value = UIManager.get(key);
	      if (value instanceof FontUIResource) {
	        UIManager.put(key, fontRes);
	      }
	    }
	}
	
	public void hideFram() {
		theFrame.setVisible(false);
		theFrame = null;
	}
	
	public void diapose() {
		theFrame.dispose();
	}
	
	public JFrame getTheFrame() {
		return theFrame;
	}
}
