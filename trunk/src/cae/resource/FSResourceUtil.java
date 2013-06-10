package cae.resource;

import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.apache.log4j.Logger;

/**
 * Obtiene los recursos.
 * 
 * @author caeycae
 *
 */
public final class FSResourceUtil {
	
	public static final Logger LOGGER = Logger.getLogger(FSResourceUtil.class);
	
	public static ImageIcon getIcon(FSImageEnum id)
	{
		ImageIcon icon = new ImageIcon(Thread.currentThread().getContextClassLoader().getResource(id.resource()));
		return icon;
	}

	public static Icon getExeIcon(File exeFile) {
		sun.awt.shell.ShellFolder sf;
		Icon icon = null;
		try {
			sf = sun.awt.shell.ShellFolder.getShellFolder(exeFile);
			icon = new ImageIcon(sf.getIcon(true));
	
		} catch (FileNotFoundException e) {
			LOGGER.error("Error al intentar obtener el icono del programa: " + exeFile, e);
		}
		return icon;
	}
}
