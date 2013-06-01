package cae.rev;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import cae.ProgramInfo;

public class ProgramTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private static final int NAME = 0;
	private static final int WEB = 1;
	private static final int KEYS = 2;
	private static final int SERIAL = 3;
	private static final int NOTES = 4;
	private List<ProgramInfo> data = null;

	public ProgramTableModel(List<ProgramInfo> data) 
	{
		this.data = data;
	}

	private String[] columnNames = {"Name", "Web", "Keys", "Serial", "Notes"};
	
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
		case KEYS:
			return data.get(row).getKeys();
		case SERIAL:
			return data.get(row).getSerial();
		case NOTES:
			return data.get(row).getNotes();
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
}