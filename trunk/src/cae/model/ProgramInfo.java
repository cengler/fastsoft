package cae.model;

import java.io.File;

public class ProgramInfo {

	private String name;
	private String web;
	private String serial;
	private String description;
	private String keys;
	private String plataform;
	private String version;
	private String company;
	private String language;
	private String os;
	private String bits;
	private String category;
	
	private File exe;
	private File file;
	private File keygen;
	private File patch;
	private File leame;
	
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
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public File getPatch() {
		return patch;
	}
	public void setPatch(File patch) {
		this.patch = patch;
	}
	public File getLeame() {
		return leame;
	}
	public void setLeame(File leame) {
		this.leame = leame;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPlataform() {
		return plataform;
	}
	public void setPlataform(String plataform) {
		this.plataform = plataform;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getBits() {
		return bits;
	}
	public void setBits(String bits) {
		this.bits = bits;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
}
