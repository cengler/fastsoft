package cae;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;

import cae.model.ProgramInfo;
import cae.model.Services;


public class MainPanel extends Panel implements ActionListener {

	private JTable table;
	private ProgramTableModel model;
	private TableRowSorter<ProgramTableModel> sorter;
	private EditionPanel editPanel;
	
	private JButton buscarButton;
	
	public void createComponents()
	{
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(3,3,3,3);
		c.weightx = 10;
		c.weighty = 0;

		// CATEGORIA
		JLabel categoriaLabel = new JLabel("Categoria");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		add(categoriaLabel, c);
		
		JComboBox categoriaCombo = new JComboBox();
		categoriaCombo.addItem(null);
		categoriaCombo.addItem("General");
		categoriaCombo.addItem("Diseño");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		add(categoriaCombo, c);
		
		// SISTEMA OPERATIVO
		JLabel soLabel = new JLabel("Sistema Operativo");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		add(soLabel, c);
		
		JComboBox soCombo = new JComboBox();
		soCombo.addItem(null);
		soCombo.addItem("Windows");
		soCombo.addItem("Unix");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		add(soCombo, c);

		// BITS
		JLabel bitsLabel = new JLabel("Bits");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		add(bitsLabel, c);
		
		JComboBox bitsCombo = new JComboBox();
		bitsCombo.addItem(null);
		bitsCombo.addItem("32");
		bitsCombo.addItem("64");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		add(bitsCombo, c);
		
		// BUSCAR CAEs
		ImageIcon buscarIcon = new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("Search.png"));
		buscarButton = new JButton("Buscar", buscarIcon);
		buscarButton.addActionListener(this);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 3;
		c.gridy = 0;
		c.gridheight = 3;
		add(buscarButton, c);
		c.gridheight = 1;
		
		// NUEVO CAE
		JButton nuevoButton = new JButton("Nuevo");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 3;
		add(nuevoButton, c);
		
		// FILTRO
		JLabel filtroLabel = new JLabel("Filtro");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth=3;
		add(filtroLabel, c);
		
		final JTextField filtroTextField = new JTextField();
		filtroTextField.getDocument().addDocumentListener(new DocumentListener(){

			public void changedUpdate(DocumentEvent e) {
				updateFilter(filtroTextField.getText());
			}

			public void insertUpdate(DocumentEvent e) {
				updateFilter(filtroTextField.getText());
			}

			public void removeUpdate(DocumentEvent e) {
				updateFilter(filtroTextField.getText());
			}
			
		});
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		add(filtroTextField, c);
		
        List<ProgramInfo> data = new Services().load();
        model = new ProgramTableModel(data);
        sorter = new TableRowSorter<ProgramTableModel>(model);
        table = new JTable(model);
        table.setRowSorter(sorter);
        table.setPreferredScrollableViewportSize(new Dimension(800, 200));
        table.setFillsViewportHeight(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {
                    public void valueChanged(ListSelectionEvent event) {
                        int viewRow = table.getSelectedRow();
                        if (viewRow < 0) {
                        	editPanel.setInfo(null);
                        } else {
                            int modelRow = 
                                table.convertRowIndexToModel(viewRow);
                            ProgramInfo info = model.getRowObject(modelRow);
                            editPanel.setInfo(info);
                        }
                    }
                }
        );
        JScrollPane scrollPane = new JScrollPane(table);
        c.gridx = 0;
		c.gridy = 4;
		c.gridwidth=4;
		c.weighty = 100;
		c.fill = GridBagConstraints.BOTH;
		add(scrollPane, c);
		
		editPanel = new EditionPanel();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 5;
		add(editPanel, c);
	}

	protected void updateFilter(String text) {
		List<RowFilter<Object, Object>> filterList = 
			new ArrayList<RowFilter<Object,Object>>();
		String[] filterTexts = text.split(" ");
		for (String f : filterTexts) {
			filterList.add(RowFilter.regexFilter(f, 0,1,2,3));
		}
		sorter.setRowFilter(RowFilter.andFilter(filterList));
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == buscarButton) {
			buscarArchivos();
		}
	}

	private void buscarArchivos() {
		Preferences prefs = Preferences.userNodeForPackage(MainPanel.class);

		// Preference key name
		final String PREF_NAME = "soft_dir";

		String defaultValue = ".";
		String value = prefs.get(PREF_NAME, defaultValue); 

		
		final JFileChooser fc = new JFileChooser(value);
		JList list = new JList();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if (JFileChooser.APPROVE_OPTION == fc.showOpenDialog(list)) {
			File file = fc.getSelectedFile();
			prefs.put(PREF_NAME, file.getAbsolutePath());
			List<ProgramInfo> listInfo = new Services().getPrograms(file);
			model.setData(listInfo);
			model.fireTableDataChanged();
			
			
			new Services().save(listInfo);
			
		} 
	}
}
