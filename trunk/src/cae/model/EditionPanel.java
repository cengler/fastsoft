package cae.model;

import java.awt.Desktop;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import cae.resource.FSImageEnum;
import cae.resource.FSResourceUtil;


public class EditionPanel extends Panel implements ActionListener
{
	private static Logger LOGGER = Logger.getLogger(EditionPanel.class);
	
	private static final long serialVersionUID = 1L;
	private JLabel nameLabel;
	private ProgramInfo info;
	
	private JButton leameButton;
	private JButton setupButton;
	private JButton abrirButton;
	private JButton patchButton;
	private JButton keygenButton;
	private JTextField serialTextFiled;
	
	private Icon setupIcon;
	
	
	
	public EditionPanel()
	{
		
		ImageIcon icon = null;
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(3,3,3,3);
		c.weightx = 10;
		c.weighty =10;

		nameLabel = new JLabel("");

		c.gridx = 0;
		c.gridy = 0;
		add(nameLabel, c);
		
		JLabel serialLabel = new JLabel("Serial: ");
		c.gridx = 1;
		c.gridy = 0;
		add(serialLabel, c);
		
		serialTextFiled = new JTextField();
		c.gridwidth = 3;
		c.gridx = 2;
		c.gridy = 0;
		add(serialTextFiled, c);
		c.gridwidth = 1;
		
		icon = FSResourceUtil.getIcon(FSImageEnum.INFO);
		leameButton = new JButton("Leame", icon);
		leameButton.addActionListener(this);
		c.gridx = 0;
		c.gridy = 1;
		add(leameButton, c);
		
		setupIcon = FSResourceUtil.getIcon(FSImageEnum.SETTINGS);
		setupButton = new JButton("Setup", setupIcon);
		setupButton.addActionListener(this);
		c.gridx = 1;
		c.gridy = 1;
		add(setupButton, c);
		
		icon = FSResourceUtil.getIcon(FSImageEnum.TOOLS);
		patchButton = new JButton("Patch", icon);
		patchButton.addActionListener(this);
		c.gridx = 2;
		c.gridy = 1;
		add(patchButton, c);
		
		icon = FSResourceUtil.getIcon(FSImageEnum.KEY);
		keygenButton = new JButton("Keygen", icon);
		keygenButton.addActionListener(this);
		c.gridx = 3;
		c.gridy = 1;
		add(keygenButton, c);
		
		icon = FSResourceUtil.getIcon(FSImageEnum.FOLDER);
		abrirButton = new JButton("Abrir", icon);
		abrirButton.addActionListener(this);
		c.gridx = 4;
		c.gridy = 1;
		add(abrirButton, c);
		
		setInfo(null);
	}
	
	public void setInfo(ProgramInfo info)
	{
		this.info = info;
		
		leameButton.setEnabled(false);
		setupButton.setEnabled(false);
		abrirButton.setEnabled(false);
		patchButton.setEnabled(false);
		keygenButton.setEnabled(false);
		serialTextFiled.setText(null);
		nameLabel.setIcon(null);
		nameLabel.setText(null);
		
		if(info != null)
		{
			nameLabel.setText(info.getName());
			serialTextFiled.setText(info.getSerial());
			leameButton.setEnabled(info.getLeame() != null);
			setupButton.setEnabled(info.getExe() != null);
			abrirButton.setEnabled(info.getFile() != null);
			patchButton.setEnabled(info.getPatch() != null);
			keygenButton.setEnabled(info.getKeygen() != null);
			
			if(info.getExe() != null)
			{
				sun.awt.shell.ShellFolder sf;
				try {
					sf = sun.awt.shell.ShellFolder.getShellFolder(info.getExe());
					Icon icon = new ImageIcon(sf.getIcon(true)); // TODO sacar  resource
					nameLabel.setIcon(icon);
				} catch (FileNotFoundException e) {
					LOGGER.error("Error al intentar obtener el icono del programa", e);
				}
			} 

		}
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == abrirButton) {
			open(info.getFile().getParentFile());
		} else if(e.getSource() == setupButton) {
			openExe(info.getExe().getAbsolutePath());
		} else if(e.getSource() == patchButton) {
			openExe(info.getPatch().getAbsolutePath());
		}else if(e.getSource() == keygenButton) {
			openExe(info.getKeygen().getAbsolutePath());
		}else if(e.getSource() == leameButton) {
			openExe(info.getLeame().getAbsolutePath());
		}
	}
	
	private void open(File file)
    {
    	try {
			Desktop.getDesktop().open(file);
		} catch (IOException e) {
			LOGGER.error( "No se pudo abrir: " + file, e);
			JOptionPane.showMessageDialog(this,
				    "No se pudo abrir: " + file,
				    "Error",
				    JOptionPane.ERROR_MESSAGE);
		}
    }
    
    private void openExe(String exe)
    {
    	System.out.println("EXE:"+ exe);
    	try {
    		Runtime rt = Runtime.getRuntime() ;
    		rt.exec(exe);
		} catch (IOException e) {
			LOGGER.error( "No se pudo abrir: " + exe, e);
			JOptionPane.showMessageDialog(this,
				    "No se pudo abrir: " + exe,
				    "Error",
				    JOptionPane.ERROR_MESSAGE);
		}
    }
}
