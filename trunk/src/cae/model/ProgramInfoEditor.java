package cae.model;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

import cae.resource.FSImageEnum;
import cae.resource.FSResourceUtil;


/**
 * @author caeycae
 *
 */
public class ProgramInfoEditor extends JDialog implements ActionListener
{
	private static final Logger LOGGER = Logger.getLogger(ProgramInfoEditor.class);
	
	private static final long serialVersionUID = 1L;
	public static final int OK = 0;
	public static final int CANCEL = 1;
	private JTextField nameTextField;
	private JTextField webTextField;
	private JTextField serialTextField;
	private JTextArea descriptionTextArea;
	private JTextField keysTextField;
	private JTextField versionTextField;
	private JTextField companyTextField;
	private JTextField languageTextField;
	private JComboBox<String> osCombo;
	private JComboBox<String> bitsCombo;
	private JComboBox<String> categoryCombo;
	private JComboBox<String> importanceCombo;
	private JTextField exeTextField;
	private JTextField keygenTextField;
	private JTextField patchTextField;
	private JTextField leameTextField;
	
	private JButton exeButton;
	private JButton keygenButton;
	private JButton patchButton;
	private JButton leameButton;
	
	private ProgramInfo info;
	protected int returnValue = CANCEL;
	
	public ProgramInfoEditor(String title, ProgramInfo info)
	{
		this.setTitle(title);
		this.setIconImage(FSResourceUtil.getIcon(FSImageEnum.PROGRAM_ICON).getImage());
		this.info = info;
		
	    Container cp = getContentPane();
	    cp.setLayout(new GridBagLayout());
	    cp.setBackground(UIManager.getColor("control"));
	    GridBagConstraints c = new GridBagConstraints();

	    c.gridx = 0;
	    c.gridy = GridBagConstraints.RELATIVE;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.insets = new Insets(2, 2, 2, 2);
	    c.anchor = GridBagConstraints.EAST;
	    
	    cp.add(new JLabel("Nombre:", SwingConstants.RIGHT), c);
	    cp.add(new JLabel("Web:", SwingConstants.RIGHT), c);
	    cp.add(new JLabel("Serial:", SwingConstants.RIGHT), c);
	    cp.add(new JLabel("Descripcion:", SwingConstants.RIGHT), c);
	    cp.add(new JLabel("Keys:", SwingConstants.RIGHT), c);
	    cp.add(new JLabel("Version:", SwingConstants.RIGHT), c);
	    cp.add(new JLabel("Editor:", SwingConstants.RIGHT), c);
	    cp.add(new JLabel("Idioma:", SwingConstants.RIGHT), c);
	    cp.add(new JLabel("Sistema:", SwingConstants.RIGHT), c);
	    cp.add(new JLabel("Bits:", SwingConstants.RIGHT), c);
	    cp.add(new JLabel("Categoria:", SwingConstants.RIGHT), c); 
	    cp.add(new JLabel("Importancia:", SwingConstants.RIGHT), c); 
	    cp.add(new JLabel("Programa:", SwingConstants.RIGHT), c);
	    cp.add(new JLabel("KeyGen:", SwingConstants.RIGHT), c);
	    cp.add(new JLabel("Patch:", SwingConstants.RIGHT), c);
	    cp.add(new JLabel("Leame:", SwingConstants.RIGHT), c);

	    c.gridx = 1;
	    c.gridy = 0;
	    c.weightx = 1.0;
	    c.gridwidth = 2;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.anchor = GridBagConstraints.CENTER;

	    nameTextField = new JTextField(35);
	    cp.add(nameTextField, c);
	    c.gridx = 1;
	    c.gridy = GridBagConstraints.RELATIVE;
	    webTextField = new JTextField();
	    cp.add(webTextField, c);
	    serialTextField = new JTextField();
	    cp.add(serialTextField, c);
	    descriptionTextArea = new JTextArea(3, 35);
	    JScrollPane scroll = new JScrollPane(descriptionTextArea);
	    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    cp.add(scroll, c);
	    keysTextField = new JTextField();
	    cp.add(keysTextField, c);
	    versionTextField = new JTextField();
	    cp.add(versionTextField, c);
	    companyTextField = new JTextField();
	    cp.add(companyTextField, c);
	    languageTextField = new JTextField();
	    cp.add(languageTextField, c);
	    osCombo = new JComboBox<String>(Services.getSO());
	    cp.add(osCombo, c);
	    bitsCombo = new JComboBox<String>(Services.getBits());
	    cp.add(bitsCombo, c);
		categoryCombo= new JComboBox<String>(Services.getCategory());
	    cp.add(categoryCombo, c);
	    importanceCombo= new JComboBox<String>(Services.getImportance());
	    cp.add(importanceCombo, c);
	    c.gridwidth = 1;
	    exeTextField = new JTextField();
	    exeTextField.setEditable(false);
	    cp.add(exeTextField, c);
	    keygenTextField = new JTextField();
	    keygenTextField.setEditable(false);
	    cp.add(keygenTextField, c);
	    patchTextField = new JTextField();
	    patchTextField.setEditable(false);
	    cp.add(patchTextField, c);
	    leameTextField = new JTextField();
	    leameTextField.setEditable(false);
	    cp.add(leameTextField, c);
	    
	    c.gridx = 2;
	    c.gridy = 12;
	    c.weightx = 0;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.anchor = GridBagConstraints.CENTER;
	    exeButton = new JButton("...");
	    exeButton.addActionListener(this);
	    cp.add(exeButton, c);
	    c.gridx = 2;
	    c.gridy = GridBagConstraints.RELATIVE;
	    keygenButton = new JButton("...");
	    keygenButton.addActionListener(this);
	    cp.add(keygenButton, c);
	    patchButton = new JButton("...");
	    patchButton.addActionListener(this);
	    cp.add(patchButton, c);
	    leameButton = new JButton("...");
	    leameButton.addActionListener(this);
	    cp.add(leameButton, c);
		
	    
	    c.gridx = 0;
	    c.gridy = 30;
	    c.gridwidth = 3;
	    JPanel panel = new JPanel();
	    panel.setLayout(new BorderLayout());
	    
	    JPanel subPanel = new JPanel();
	    JButton okButton = new JButton("Aceptar");
	    okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					saveData();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(getParent(),
						    "Ocurrió un error al gardar el archivo",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
					LOGGER.error("Error al guardar el archivo de informacion del archivo", ex);
				}
				returnValue = OK;
				setVisible(false);
				
			}
		});
	    subPanel.add(okButton);
	    JButton cancelButton = new JButton("Cancelar");
	    cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int value = JOptionPane.showConfirmDialog(getParent(),
					    "Esta seguro de salir sin guardar?",
					    "Confirmacion",
					    JOptionPane.WARNING_MESSAGE);
				if(value == JOptionPane.OK_OPTION)
					setVisible(false);
			}
		});
	    subPanel.add(cancelButton);
	    
	    panel.add(subPanel, BorderLayout.EAST);
	    cp.add(panel, c);

	    // para hacer shortcuts
	    //setMnemonic
	    //setDisplayedMnemonic
	    //setFocusAccelerator
	    
	    updateData(info);
	    
	    pack();
	}

	protected void saveData() throws FileNotFoundException, IOException {
		info.setName(nameTextField.getText());
		info.setWeb(webTextField.getText());
		info.setSerial(serialTextField.getText());
		info.setDescription(descriptionTextArea.getText());
		info.setKeys(keysTextField.getText());
		info.setVersion(versionTextField.getText());
		info.setCompany(companyTextField.getText());
		info.setLanguage(languageTextField.getText());
		
		if(osCombo.getSelectedItem() != null)
			info.setOs((String) osCombo.getSelectedItem());
		else
			info.setOs(null);
		
		if(bitsCombo.getSelectedItem() != null)
			info.setBits((String) bitsCombo.getSelectedItem());
		else
			info.setBits(null);
		
		if(categoryCombo.getSelectedItem() != null)
			info.setCategory((String) categoryCombo.getSelectedItem());
		else
			info.setCategory(null);
		
		if(importanceCombo.getSelectedItem() != null)
			info.setImportance((String) importanceCombo.getSelectedItem());
		else
			info.setImportance(null);
		
		if(exeTextField.getText() != null && !exeTextField.getText().isEmpty())
			info.setExe(new File(info.getFile().getParentFile().getAbsolutePath()+File.separatorChar+exeTextField.getText()));
		else
			info.setExe(null);
		
		if(keygenTextField.getText() != null && !keygenTextField.getText().isEmpty())
			info.setKeygen(new File(info.getFile().getParentFile().getAbsolutePath()+File.separatorChar+keygenTextField.getText()));
		else
			info.setKeygen(null);
		
		if(patchTextField.getText() != null && !patchTextField.getText().isEmpty())
			info.setPatch(new File(info.getFile().getParentFile().getAbsolutePath()+File.separatorChar+patchTextField.getText()));
		else
			info.setPatch(null);
		
		if(leameTextField.getText() != null && !leameTextField.getText().isEmpty())
			info.setLeame(new File(info.getFile().getParentFile().getAbsolutePath()+File.separatorChar+leameTextField.getText()));
		else
			info.setLeame(null);
		
		ProgramInfoFileAccess.write(info);
	}

	private void updateData(ProgramInfo info) {
		
		nameTextField.setText(info.getName());
		webTextField.setText(info.getWeb());
		serialTextField.setText(info.getSerial());
		descriptionTextArea.setText(info.getDescription());
		keysTextField.setText(info.getKeys());
		versionTextField.setText(info.getVersion());
		companyTextField.setText(info.getCompany());
		languageTextField.setText(info.getLanguage());
		osCombo.setSelectedItem(info.getOs());
		bitsCombo.setSelectedItem(info.getBits());
		categoryCombo.setSelectedItem(info.getCategory());
		importanceCombo.setSelectedItem(info.getImportance());
		if(info.getExe() != null)
			exeTextField.setText(info.getExe().getName());
		if(info.getKeygen() != null)
			keygenTextField.setText(info.getKeygen().getName());
		if(info.getPatch() != null)
			patchTextField.setText(info.getPatch().getName());
		if(info.getLeame() != null)
			leameTextField.setText(info.getLeame().getName());
	}

	public int getReturnValue() {
		return returnValue;
	}

	public ProgramInfo getInfo() {
		return info;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		final JFileChooser fc = new JFileChooser(info.getFile().getParent());
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

		if (JFileChooser.APPROVE_OPTION == fc.showOpenDialog(null)) {
			File file = fc.getSelectedFile();
			
			String relative = info.getFile().getParentFile().toURI().relativize(file.toURI()).getPath();
			System.out.println(relative);
			if(e.getSource() == exeButton)
			{
				exeTextField.setText(relative); 
			} else if(e.getSource() == patchButton)
			{
				patchTextField.setText(relative);
			} else if(e.getSource() == keygenButton)
			{
				keygenTextField.setText(relative); 
			} else if(e.getSource() == leameButton)
			{
				leameTextField.setText(relative); 
			}
				
		}
	}
}
