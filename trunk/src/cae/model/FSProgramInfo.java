package cae.model;

import java.io.File;

public class FSProgramInfo {

	private String name;
	private String web;
	private String serial;
	private String description;
	private String keys;
	private String version;
	private String company;
	private String language;
	private String os;
	private String bits;
	private String category;
	private String importance;	
	private File file;
	private String exe;
	private String keygen;
	private String patch;
	private String leame;
	
	public FSProgramInfo(File file)
	{
		this.file = file;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKeys() {
		return keys;
	}

	public void setKeys(String keys) {
		this.keys = keys;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
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

	public String getImportance() {
		return importance;
	}

	public void setImportance(String importance) {
		this.importance = importance;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getExe() {
		return exe;
	}

	public void setExe(String exe) {
		this.exe = exe;
	}

	public String getKeygen() {
		return keygen;
	}

	public void setKeygen(String keygen) {
		this.keygen = keygen;
	}

	public String getPatch() {
		return patch;
	}

	public void setPatch(String patch) {
		this.patch = patch;
	}

	public String getLeame() {
		return leame;
	}

	public void setLeame(String leame) {
		this.leame = leame;
	}
	
	public String getAbsolutePath()
	{
		return file.getParent()+File.separatorChar;
	}
	
	public File getKeygenFile() {
		return new File(getAbsolutePath()+keygen);
	}
	
	public File getPatchFile() {
		return new File(getAbsolutePath()+patch);
	}
	
	public File getLeameFile() {
		return new File(getAbsolutePath()+leame);
	}
	
	public File getExeFile() {
		return new File(getAbsolutePath()+exe);
	}
}
