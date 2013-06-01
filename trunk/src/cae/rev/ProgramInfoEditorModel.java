package cae.rev;
import java.io.File;

import javax.swing.table.AbstractTableModel;

import cae.ProgramInfo;


@SuppressWarnings("serial")
public class ProgramInfoEditorModel extends AbstractTableModel {

	public static final int PROP_INDEX = 0;
	public static final int VALUE_INDEX = 1;

	private static final String NAME = "name";
	private static final String KEYS = "keys";
	private static final String WEB = "web";
	private static final String EXE = "exe";
	private static final String SERIAL = "serial";
	private static final String NOTES = "notes";
	private static final String KEYGEN = "keygen";

	public static final int NAME_IDX 	= 0;
	public static final int KEYS_IDX 	= 1;
	public static final int WEB_IDX 	= 2;
	public static final int EXE_IDX 	= 3;
	public static final int SERIAL_IDX = 4;
	public static final int NOTES_IDX 	= 5;
	public static final int KEYGEN_IDX = 6;

	private static final String[] COLUMNS = {"Propertie", "Value"};
	private static final String[] ROWS = {NAME,KEYS,WEB,EXE,SERIAL,NOTES,KEYGEN}; 

	protected ProgramInfo data;

	public ProgramInfoEditorModel(ProgramInfo data) {
		this.data = data;
	}

	public String getColumnName(int column) {
		return COLUMNS[column];
	}

	public boolean isCellEditable(int row, int column) {
		switch (column) {
		case PROP_INDEX:
			return false;
		case VALUE_INDEX:
			return true;
		default:
			return false;
		}
	}

	public Class<?> getColumnClass(int column) {
		return Object.class;
	}

	public Object getValueAt(int row, int column) {
		switch (column) {
		case PROP_INDEX:
			return ROWS[row];
		case VALUE_INDEX:
			switch (row) {
			case NAME_IDX:
				return data.getName();
			case KEYS_IDX:
				return data.getKeys();
			case WEB_IDX:
				return data.getWeb();
			case EXE_IDX:
				return data.getExe();
			case SERIAL_IDX:
				return data.getSerial();
			case NOTES_IDX:
				return data.getNotes();
			case KEYGEN_IDX:
				return data.getKeygen();
			default:
				return null;
			}
		default:
			return new Object();
		}
	}

	public void setValueAt(Object value, int row, int column) {
		switch (row) {
		case NAME_IDX:
			data.setName((String) value);
			break;
		case KEYS_IDX:
			data.setKeys((String)value);
			break;
		case WEB_IDX:
			data.setWeb((String) value);
			break;
		case EXE_IDX:
			data.setExe((File)value); 
			break;
		case SERIAL_IDX:
			data.setSerial((String) value);
			break;
		case NOTES_IDX:
			data.setNotes((String) value);
			break;
		case KEYGEN_IDX:
			data.setKeygen((File)value); 
			break;
		}
		ProgramInfoFileAccess.write(data);
		fireTableCellUpdated(row, column);
	}

	public int getRowCount() {
		return ROWS.length;
	}

	public int getColumnCount() {
		return 2;
	}
}