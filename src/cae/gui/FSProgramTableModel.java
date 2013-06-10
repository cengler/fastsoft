package cae.gui;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import cae.model.FSProgramInfo;



public class FSProgramTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private List<FSProgramInfo> data = null;
	
	public static final int COMPANY 		= 0;
	public static final int NAME 			= 1;
	public static final int VERSION 		= 2;
	public static final int DESCRIPTION 	= 3;
	public static final int WEB 			= 4;
	public static final int SERIAL 			= 5;
	public static final int KEYS 			= 6;
	public static final int LANGUAGE 		= 7;
	public static final int OS 				= 8;
	public static final int BITS 			= 9;
	public static final int CATEGORY 		= 10;
	public static final int IMPORTANCE		= 11;
	
	public static final int FIRST_TO_HIDE	= WEB;
	public static final int LAST_TO_HIDE	= IMPORTANCE;
	
	private String[] columnNames = {"Compania", "Nombre", "Ver.", "Descripción", "Web", "Serial", "Palabras Clave", "Lenguaje", "Sistema Operativo", "Bits", "Categoria", "Importancia"};
	
	public FSProgramTableModel(List<FSProgramInfo> data) 
	{
		this.data = data;
	}
	
	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return data.size();
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
		switch(col)
		{
		case NAME:
			return data.get(row).getName();
		case WEB:
			return data.get(row).getWeb();
		case SERIAL:
			return data.get(row).getSerial();
		case DESCRIPTION:
			return data.get(row).getDescription();
		case KEYS:
			return data.get(row).getKeys();
		case VERSION:
			return data.get(row).getVersion();
		case COMPANY:
			return data.get(row).getCompany();
		case LANGUAGE:
			return data.get(row).getLanguage();
		case OS:
			return data.get(row).getOs();
		case BITS:
			return data.get(row).getBits();
		case CATEGORY:
			return data.get(row).getCategory();
		case IMPORTANCE:
			return data.get(row).getImportance();
		default:
			return null;
		}
	}

	public Class<String> getColumnClass(int c) {
		return String.class;
	}

	public boolean isCellEditable(int row, int col) {
		return true;
	}
	
	public FSProgramInfo getRowObject(int row)
	{
		return data.get(row);
	}

	public List<FSProgramInfo> getData() {
		return data;
	}

	public void setData(List<FSProgramInfo> data) {
		this.data = data;
	}
}