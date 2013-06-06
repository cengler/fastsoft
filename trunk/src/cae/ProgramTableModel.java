package cae;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import cae.model.ProgramInfo;


public class ProgramTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private List<ProgramInfo> data = null;
	
	private static final int COMPANY 		= 0;
	private static final int NAME 			= 1;
	private static final int VERSION 		= 2;
	private static final int DESCRIPTION 	= 3;
	
	private static final int WEB 			= 4;
	private static final int SERIAL 		= 5;
	private static final int KEYS 			= 6;
	private static final int PLATAFORM 		= 7;
	private static final int LANGUAGE 		= 8;
	private static final int OS 			= 9;
	private static final int BITS 			= 10;
	private static final int CATEGORY 		= 11;
	
	private String[] columnNames = {"Compania", "Nombre", "Ver.", "Descripción", "Web", "Serial", "Palabras Clave", "Plataforma", "Lenguaje", "Sistema Operativo", "Bits", "Categoria"};
	
	public ProgramTableModel(List<ProgramInfo> data) 
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
		case PLATAFORM:
			return data.get(row).getPlataform();
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
		default:
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public Class getColumnClass(int c) {
		return String.class;
	}

	public boolean isCellEditable(int row, int col) {
		return true;
	}
	
	public ProgramInfo getRowObject(int row)
	{
		return data.get(row);
	}

	public List<ProgramInfo> getData() {
		return data;
	}

	public void setData(List<ProgramInfo> data) {
		this.data = data;
	}
	
	
}