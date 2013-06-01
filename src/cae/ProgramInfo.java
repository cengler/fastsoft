package cae;

import java.io.File;

public class ProgramInfo {

	private String name;
	private String web;
	private String serial;
	private String notes;
	private String keys;

	private File exe;
	private File file;
	private File keygen;
	
	public String getWeb() {
		return web;
	}
	public void setWeb(String web) {
		this.web = web;
	}
	public String getKeys() {
		return keys;
	}
	public void setKeys(String keys) {
		this.keys = keys;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public File getExe() {
		return exe;
	}
	public void setExe(File exe) {
		this.exe = exe;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public File getKeygen() {
		return keygen;
	}
	public void setKeygen(File keygen) {
		this.keygen = keygen;
	}
	@Override
	public String toString() {
		return "name: " + name;
	}
}
