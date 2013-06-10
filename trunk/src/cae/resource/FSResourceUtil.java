package cae.resource;

import javax.swing.ImageIcon;

public final class FSResourceUtil {
	
	public static ImageIcon getIcon(FSImageEnum id)
	{
		ImageIcon icon = new ImageIcon(Thread.currentThread().getContextClassLoader().getResource(id.resource()));
		return icon;
	}
}
