package cae.model;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;



public class ProgramInfoFileAccess {

	private static final String NAME 		= "name";
	private static final String WEB 		= "web";
	private static final String SERIAL 		= "serial";
	private static final String DESCRIPTION = "description";
	private static final String KEYS 		= "keys";
	private static final String PLATAFORM 	= "plataform";
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
	
	public static ProgramInfo read(File caeFile) 
	{
		Properties p = new Properties();
		try {
			p.load(new FileInputStream(caeFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
		ProgramInfo info = new ProgramInfo();
		
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
		
		if(p.getProperty(PLATAFORM) != null)
			info.setPlataform(p.getProperty(PLATAFORM));
		
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
		
		if(p.getProperty(EXE) != null && !p.getProperty(EXE).isEmpty())
			info.setExe(new File(caeFile.getParentFile().getAbsolutePath()+File.separatorChar+p.getProperty(EXE)));
		
		if(p.getProperty(KEYGEN) != null && !p.getProperty(KEYGEN).isEmpty())
			info.setKeygen(new File(caeFile.getParentFile().getAbsolutePath()+File.separatorChar+p.getProperty(KEYGEN)));
		
		if(p.getProperty(PATCH) != null && !p.getProperty(PATCH).isEmpty())
			info.setPatch(new File(caeFile.getParentFile().getAbsolutePath()+File.separatorChar+p.getProperty(PATCH)));
		
		if(p.getProperty(LEAME) != null && !p.getProperty(LEAME).isEmpty())
			info.setLeame(new File(caeFile.getParentFile().getAbsolutePath()+File.separatorChar+p.getProperty(LEAME)));
		
		info.setFile(caeFile);
		
		return info;
	}
	
	public static void write(ProgramInfo info) 
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
		
		if(info.getPlataform() != null)
			p.setProperty(PLATAFORM, info.getPlataform());
		
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
		
		if(info.getExe() != null)
			p.setProperty(EXE, info.getExe().getName());
		
		if(info.getKeygen() != null)
			p.setProperty(KEYGEN, info.getKeygen().getName());
		
		if(info.getPatch() != null)
			p.setProperty(PATCH, info.getPatch().getName());
		
		if(info.getLeame() != null)
			p.setProperty(LEAME, info.getLeame().getName());
		
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
