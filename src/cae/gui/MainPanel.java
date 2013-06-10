package cae.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import org.apache.log4j.Logger;

import cae.model.FSProgramInfo;
import cae.model.FSProgramInfoFileAccess;
import cae.resource.FSImageEnum;
import cae.resource.FSResourceUtil;
import cae.services.FSServices;


public class MainPanel extends Panel 
{
	private static Logger LOGGER = Logger.getLogger(MainPanel.class);

	private static final long serialVersionUID = 1L;
	private JTable table;
	private static FSProgramTableModel model;
	private TableRowSorter<FSProgramTableModel> sorter;
	private FSActiveProgramPanel editPanel;
	private JComboBox<String> bitsCombo;
	private JComboBox<String> soCombo;
	private JComboBox<String> categoriaCombo;
	private JComboBox<String> importanceCombo;
	private JTextField filtroTextField;
	private JButton buscarButton;
	private JButton nuevoButton;

	/**
	 * Crea todos los componentes visuales.
	 */
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
		c.gridx = 0;
		c.gridy = 0;
		add(categoriaLabel, c);

		categoriaCombo = new JComboBox<String>(FSServices.getCategory());
		categoriaCombo.addActionListener(new UpdateFilterAction());
		c.gridx = 0;
		c.gridy = 1;
		add(categoriaCombo, c);

		// SISTEMA OPERATIVO
		JLabel soLabel = new JLabel("Sistema Operativo");
		c.gridx = 1;
		c.gridy = 0;
		add(soLabel, c);

		soCombo = new JComboBox<String>(FSServices.getSO());
		soCombo.addActionListener(new UpdateFilterAction());
		c.gridx = 1;
		c.gridy = 1;
		add(soCombo, c);

		// BITS
		JLabel bitsLabel = new JLabel("Bits");
		c.gridx = 2;
		c.gridy = 0;
		add(bitsLabel, c);

		bitsCombo = new JComboBox<String>(FSServices.getBits());
		bitsCombo.addActionListener(new UpdateFilterAction());
		c.gridx = 2;
		c.gridy = 1;
		add(bitsCombo, c);
		
		// IMPORTANCE
		JLabel importanceLabel = new JLabel("Importancia");
		c.gridx = 3;
		c.gridy = 0;
		add(importanceLabel, c);

		importanceCombo = new JComboBox<String>(FSServices.getImportance());
		importanceCombo.addActionListener(new UpdateFilterAction());
		c.gridx = 3;
		c.gridy = 1;
		add(importanceCombo, c);

		// BUSCAR CAEs
		ImageIcon buscarIcon = FSResourceUtil.getIcon(FSImageEnum.SEARCH);
		buscarButton = new JButton("Buscar", buscarIcon);
		buscarButton.addActionListener(new CaeAction());
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 4;
		c.gridy = 0;
		c.gridheight = 3;
		add(buscarButton, c);
		c.gridheight = 1;

		// NUEVO CAE
		nuevoButton = new JButton("Nuevo");
		nuevoButton.addActionListener(new CaeAction());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 3;
		add(nuevoButton, c);

		// FILTRO
		JLabel filtroLabel = new JLabel("Filtro");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth=4;
		add(filtroLabel, c);

		filtroTextField = new JTextField();
		filtroTextField.getDocument().addDocumentListener(new UpdateDocumentListener());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		add(filtroTextField, c);

		List<FSProgramInfo> data = null;
		try {
			data = new FSServices().loadProgramCacheList();
		} catch (IOException e) {
			LOGGER.error("No se pudo cargar el archivo de CAEs", e);
			data = new ArrayList<FSProgramInfo>();
		}
		model = new FSProgramTableModel(data);
		sorter = new TableRowSorter<FSProgramTableModel>(model);
		table = new JTable(model);
		table.setRowSorter(sorter);
		table.setPreferredScrollableViewportSize(new Dimension(800, 200));
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
						FSProgramInfo info = getSelectedInfo();
						editPanel.setInfo(info);
					}
				}
		);
		table.addMouseListener(new MenuAdapter());

		for (int i = FSProgramTableModel.FIRST_TO_HIDE; i <= FSProgramTableModel.LAST_TO_HIDE; i++) {
			TableColumn column = table.getColumnModel().getColumn(i);
			column.setMinWidth(0);
			column.setMaxWidth(0);	
			column.setWidth(0);
			column.setPreferredWidth(0);
			doLayout();
		}

		JScrollPane scrollPane = new JScrollPane(table);
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth=5;
		c.weighty = 100;
		c.fill = GridBagConstraints.BOTH;
		add(scrollPane, c);

		editPanel = new FSActiveProgramPanel();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 5;
		add(editPanel, c);
	}

	/**
	 * Filtra la tabla con todos los filtros disponibles.
	 */
	protected void updateFilter() {
		List<RowFilter<Object, Object>> filterList = 
				new ArrayList<RowFilter<Object,Object>>();

		// FILTROS DE GENERALES
		if(categoriaCombo.getSelectedItem() != null) {
			filterList.add(RowFilter.regexFilter((String)(categoriaCombo.getSelectedItem()), FSProgramTableModel.CATEGORY));
		}
		if(soCombo.getSelectedItem() != null) {
			filterList.add(RowFilter.regexFilter((String)(soCombo.getSelectedItem()), FSProgramTableModel.OS));
		}
		if(bitsCombo.getSelectedItem() != null) {
			filterList.add(RowFilter.regexFilter((String)(bitsCombo.getSelectedItem()), FSProgramTableModel.BITS));
		}
		if(importanceCombo.getSelectedItem() != null) {
			filterList.add(RowFilter.regexFilter((String)(importanceCombo.getSelectedItem()), FSProgramTableModel.IMPORTANCE));
		}

		// FILTROS DE CAMPO FILTRO
		String text = filtroTextField.getText();
		if(text != null)
		{
			String[] filterTexts = text.split(" ");
			for (String f : filterTexts) {
				filterList.add(RowFilter.regexFilter(f, 
						FSProgramTableModel.COMPANY, 	
						FSProgramTableModel.NAME, 		
						FSProgramTableModel.VERSION, 	
						FSProgramTableModel.DESCRIPTION, 
						FSProgramTableModel.WEB, 		
						FSProgramTableModel.SERIAL, 		
						FSProgramTableModel.KEYS, 			
						FSProgramTableModel.LANGUAGE, 	
						FSProgramTableModel.OS, 			
						FSProgramTableModel.BITS, 		
						FSProgramTableModel.CATEGORY,
						FSProgramTableModel.IMPORTANCE));
			}
		}

		sorter.setRowFilter(RowFilter.andFilter(filterList));
	}
	
	/**
	 * Busca archivos cae, el el directorio a eleccion.
	 * Conserva una referencia al ultimo directorio seleccionado.
	 * Actualiza la tabla.
	 */
	private void buscarArchivos() {
		Preferences prefs = Preferences.userNodeForPackage(MainPanel.class);

		// Preference key name
		final String PREF_NAME = "soft_dir";

		String defaultValue = ".";
		String value = prefs.get(PREF_NAME, defaultValue); 


		final JFileChooser fc = new JFileChooser(value);
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if (JFileChooser.APPROVE_OPTION == fc.showOpenDialog(null)) {
			File file = fc.getSelectedFile();
			prefs.put(PREF_NAME, file.getAbsolutePath());
			List<FSProgramInfo> listInfo = new FSServices().getPrograms(file);
			model.setData(listInfo);
			model.fireTableDataChanged();

			try {
				new FSServices().saveProgramCacheList(listInfo);
			} catch (IOException e) {
				LOGGER.error("No se pudo guardar el archivo de CAEs", e);
			}

		} 
	}
	
	/**
	 * Abre el panel de edicion. 
	 * Si se realizan modificaciones y son aceptadas, se devuelve el objeto modificado.
	 * 
	 * @param info info con el cual llenar el panel
	 * @param title titulo de la ventana
	 * @return el objeto modificado o null en caso contrario.
	 */
	private FSProgramInfo openEditDialog(FSProgramInfo info, String title) {
		FSProgramInfoEditor dialog = new FSProgramInfoEditor(title, info);
		dialog.setLocationRelativeTo(MainPanel.this);
		dialog.setModal(true);
		dialog.setVisible(true);
		
		if(dialog.getReturnValue() == FSProgramInfoEditor.OK)
			return dialog.getInfo();
		else
			return null;
	}

	/**
	 * Obtiene el programa seleccionado.
	 * @return el programa seleccionado.
	 */
	private FSProgramInfo getSelectedInfo() {
		int viewRow = table.getSelectedRow();
		FSProgramInfo info = null;
		if (viewRow >= 0) {
			int modelRow = 
					table.convertRowIndexToModel(viewRow);
			info = model.getRowObject(modelRow);
		}
		return info;
	}
	
	/**
	 * Update Action.
	 * 
	 * @author caeycae
	 *
	 */
	class UpdateFilterAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			updateFilter();
		}
	}
	
	/**
	 * Update Document Listener.
	 * 
	 * @author caeycae
	 *
	 */
	class UpdateDocumentListener implements DocumentListener {
		@Override
		public void insertUpdate(DocumentEvent e) {
			updateFilter();
		}
		@Override
		public void removeUpdate(DocumentEvent e) {
			updateFilter();
		}
		@Override
		public void changedUpdate(DocumentEvent e) {
			updateFilter();
		}
	}
	

	class CaeAction implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == buscarButton) {
				buscarArchivos();
			} else if(e.getSource() == nuevoButton) {
				Preferences prefs = Preferences.userNodeForPackage(MainPanel.class);
				final String PREF_NAME = "soft_dir";
				String defaultValue = ".";
				String value = prefs.get(PREF_NAME, defaultValue); 
				final JFileChooser fc = new JFileChooser(value);
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				if (JFileChooser.APPROVE_OPTION == fc.showOpenDialog(null)) {
					// EXE SELECCIONADO
					File file = fc.getSelectedFile();
					// CAE PARA EL EXE
					File caeFile = new File(file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf("."))+".cae"); 
					FSProgramInfo info = null;
					if(caeFile.exists()) {
						try {
							info = FSProgramInfoFileAccess.read(caeFile);
							info.setName(file.getName());
						} catch (FileNotFoundException e1) {
							LOGGER.error("El archivo cae existe, pero no se pudo cargar");
							JOptionPane.showMessageDialog(getParent(),
									"El archivo cae existe pero no se pudo cargar",
									"Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
					} else
						info = new FSProgramInfo(caeFile);
					
					FSProgramInfo retInfo = openEditDialog(info, "Nuevo");
					if(retInfo != null)
					{
				        // TODO AGREGAR A TABLA Y SALVAR ARCHIVO
						model.getData().add(retInfo);
						model.fireTableDataChanged();
					}
				} 
			}
		}
	}
	
	class MenuAdapter extends MouseAdapter {
		public void mousePressed( final MouseEvent e )
		{
			if(e.getButton() == MouseEvent.BUTTON3)
			{
				Point p = e.getPoint();
				int rowNumber = table.rowAtPoint( p );
				ListSelectionModel model = table.getSelectionModel();
				model.setSelectionInterval( rowNumber, rowNumber );

				javax.swing.SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						JPopupMenu Pmenu = new JPopupMenu();

						Icon icon = FSResourceUtil.getIcon(FSImageEnum.EDIT);
						JMenuItem menuItem = new JMenuItem("Editar", icon);
						menuItem.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								
								if( openEditDialog(getSelectedInfo(), "Editar") != null);
								{
									MainPanel.model.fireTableDataChanged();
								}
								
							}
						});
						Pmenu.add(menuItem);
						/*icon = FSResourceUtil.getIcon(FSResouceEnum.COPY);
						menuItem = new JMenuItem("Duplicar", icon);
						Pmenu.add(menuItem); TODO duplicar*/
						icon = FSResourceUtil.getIcon(FSImageEnum.DELETE);
						menuItem = new JMenuItem("Borrar", icon);
						menuItem.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
								
								int value = JOptionPane.showConfirmDialog(getParent(),
										"Esta seguro de borrar el programa?",
										"Confirmacion",
										JOptionPane.WARNING_MESSAGE);
								if(value == JOptionPane.OK_OPTION)
								{
									MainPanel.model.getData().remove(getSelectedInfo());
									MainPanel.model.fireTableDataChanged();
									// TODO BORRAR REAL y guardar lista de caes
								}
							}
						});
						Pmenu.add(menuItem);

						Pmenu.show(e.getComponent(), e.getX(), e.getY());
					}
				});
			}

		}
	}

}
