package cae.model;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;



public class ProgramInfoFileAccess {

	private static final String NAME = "name";
	private static final String KEYS = "keys";
	private static final String WEB = "web";
	private static final String EXE = "exe";
	private static final String SERIAL = "serial";
	private static final String NOTES = "notes";
	private static final String KEYGEN = "keygen";
	
	public static ProgramInfo read(File caeFile) 
	{
		Properties p = new Properties();
		try {
			p.load(new FileInputStream(caeFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
		ProgramInfo info = new ProgramInfo();
		
		if(p.getProperty(KEYS) != null)
			info.setKeys(p.getProperty(KEYS));
		
		if(p.getProperty(WEB) != null)
			info.setWeb(p.getProperty(WEB));
		
		if(p.getProperty(NAME) != null)
			info.setName(p.getProperty(NAME));
		
		if(p.getProperty(EXE) != null)
			info.setExe(new File(caeFile.getParentFile().getAbsolutePath()+File.separatorChar+p.getProperty(EXE)));
		
		if(p.getProperty(KEYGEN) != null)
			info.setKeygen(new File(caeFile.getParentFile().getAbsolutePath()+File.separatorChar+p.getProperty(KEYGEN)));
		
		if(p.getProperty(NOTES) != null)
			info.setNotes(p.getProperty(NOTES));
		
		if(p.getProperty(SERIAL) != null)
			info.setSerial(p.getProperty(SERIAL));
		
		info.setFile(caeFile);
		
		return info;
	}
	
	public static void write(ProgramInfo info) 
	{
		Properties p = new Properties();

		if(info.getKeys() != null)
			p.setProperty(KEYS, info.getKeys()); 
		
		if(info.getWeb() != null)
			p.setProperty(WEB, info.getWeb());
		
		if(info.getName() != null)
			p.setProperty(NAME, info.getName());
		
		if(info.getExe() != null) {
			String exe = info.getExe().getName();
			p.setProperty(EXE, exe);
		}
		
		if(info.getKeygen() != null) { 
			String keygen = info.getKeygen().getName();
			p.setProperty(KEYGEN, keygen);
		}
		
		if(info.getNotes() != null)
			p.setProperty(NOTES, info.getNotes());
		
		if(info.getSerial() != null)
			p.setProperty(SERIAL, info.getSerial());
		
		try {
			p.store(new FileOutputStream(info.getFile()), "");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
