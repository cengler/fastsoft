package cae.model;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.log4j.Logger;



public class FSProgramInfoFileAccess {

	private static Logger LOGGER = Logger.getLogger(FSProgramInfoFileAccess.class);
	
	private static final String NAME 		= "name";
	private static final String WEB 		= "web";
	private static final String SERIAL 		= "serial";
	private static final String DESCRIPTION = "description";
	private static final String KEYS 		= "keys";
	private static final String VERSION 	= "version";
	private static final String COMPANY 	= "company";
	private static final String LANGUAGE 	= "language";
	private static final String OS 			= "os";
	private static final String BITS 		= "bits";
	private static final String CATEGORY 	= "category";
	private static final String EXE 		= "exe";
	private static final String KEYGEN 		= "keygen";
	private static final String PATCH 		= "patch";
	private static final String LEAME 		= "leame";
	private static final String IMPORTANCE	= "importance";
	
	public static FSProgramInfo read(File caeFile) throws FileNotFoundException 
	{
		InputStream is = new FileInputStream(caeFile);
		
		Properties p = new Properties();
		try {
			p.load(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
		FSProgramInfo info = new FSProgramInfo(caeFile);
		
		if(p.getProperty(NAME) != null)
			info.setName(p.getProperty(NAME));
		
		if(p.getProperty(WEB) != null)
			info.setWeb(p.getProperty(WEB));
		
		if(p.getProperty(SERIAL) != null)
			info.setSerial(p.getProperty(SERIAL));
		
		if(p.getProperty(DESCRIPTION) != null)
			info.setDescription(p.getProperty(DESCRIPTION));
		
		if(p.getProperty(KEYS) != null)
			info.setKeys(p.getProperty(KEYS));

		if(p.getProperty(VERSION) != null)
			info.setVersion(p.getProperty(VERSION));
		
		if(p.getProperty(COMPANY) != null)
			info.setCompany(p.getProperty(COMPANY));
		
		if(p.getProperty(LANGUAGE) != null)
			info.setLanguage(p.getProperty(LANGUAGE));
		
		if(p.getProperty(OS) != null)
			info.setOs(p.getProperty(OS));
		
		if(p.getProperty(BITS) != null)
			info.setBits(p.getProperty(BITS));
		
		if(p.getProperty(CATEGORY) != null)
			info.setCategory(p.getProperty(CATEGORY));
		
		if(p.getProperty(IMPORTANCE) != null)
			info.setImportance(p.getProperty(IMPORTANCE));
		
		if(p.getProperty(EXE) != null && !p.getProperty(EXE).isEmpty())
			info.setExe(p.getProperty(EXE));
		
		if(p.getProperty(KEYGEN) != null && !p.getProperty(KEYGEN).isEmpty())
			info.setKeygen(p.getProperty(KEYGEN));
		
		if(p.getProperty(PATCH) != null && !p.getProperty(PATCH).isEmpty())
			info.setPatch(p.getProperty(PATCH));
		
		if(p.getProperty(LEAME) != null && !p.getProperty(LEAME).isEmpty())
			info.setLeame(p.getProperty(LEAME));
		
		try {
			is.close();
		} catch (IOException e) {
			LOGGER.error("No se pudo cerrar el archivo " + caeFile);
		}
		
		return info;
	}
	
	public static void write(FSProgramInfo info) throws FileNotFoundException, IOException 
	{
		Properties p = new Properties();

		if(info.getKeys() != null)
			p.setProperty(KEYS, info.getKeys()); 
		
		if(info.getName() != null)
			p.setProperty(NAME,info.getName());
		
		if(info.getWeb() != null)
			p.setProperty(WEB, info.getWeb());
		
		if(info.getSerial() != null)
			p.setProperty(SERIAL, info.getSerial());
		
		if(info.getDescription() != null)
			p.setProperty(DESCRIPTION, info.getDescription());
		
		if(info.getKeys() != null)
			p.setProperty(KEYS, info.getKeys());
		
		if(info.getVersion() != null)
			p.setProperty(VERSION, info.getVersion());
		
		if(info.getCompany() != null)
			p.setProperty(COMPANY, info.getCompany());
		
		if(info.getLanguage() != null)
			p.setProperty(LANGUAGE, info.getLanguage());
		
		if(info.getOs() != null)
			p.setProperty(OS, info.getOs());
		
		if(info.getBits() != null)
			p.setProperty(BITS, info.getBits());
		
		if(info.getCategory() != null)
			p.setProperty(CATEGORY, info.getCategory());
		
		if(info.getImportance() != null)
			p.setProperty(IMPORTANCE, info.getImportance());
		
		if(info.getExe() != null)
			p.setProperty(EXE, info.getExe());
		
		if(info.getKeygen() != null)
			p.setProperty(KEYGEN, info.getKeygen());
		
		if(info.getPatch() != null)
			p.setProperty(PATCH, info.getPatch());
		
		if(info.getLeame() != null)
			p.setProperty(LEAME, info.getLeame());
		
		OutputStream os = new FileOutputStream(info.getFile());
		p.store(os, "Fastsoft. caeycae\ncaeycae@gmail.com");
		os.close();
	}
	
}
