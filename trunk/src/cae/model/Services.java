package cae.model;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

public class Services {

	private static final String CAELIST_LIST = "caelist.ini";
	private static final String SO_LIST = "so.ini";
	private static final String BITS_LIST = "bits.ini";
	private static final String CATEGORY_LIST = "category.ini";
	private static final String IMPORTANCE_LIST = "importance.ini";

	private static Logger LOGGER = Logger.getLogger(ProgramInfoFileAccess.class);

	private static final String CAE_EXT = ".cae";


	/**
	 * Obitene la lista de programas desde un directorio.
	 * @param file directorio
	 * @return lista de programas encontrados
	 */
	public List<ProgramInfo> getPrograms(File file)
	{
		List<ProgramInfo> list = new ArrayList<ProgramInfo>();
		getProgramsRec(file, list);
		return list;
	}

	/**
	 * Obtiene la lista de programas de forma recursiva. 
	 * @param dir directorio desde el cual buscar programas
	 * @param infoList lista de programas encontrados
	 */
	private void getProgramsRec(File dir, List<ProgramInfo> infoList)
	{
		// OBTIENE LA LISTA DE PROGRAMAS ENCONTRADOS EN EL DIRECTORIO (*.cae)
		List<ProgramInfo> infos = getFolderInfo(dir);
		infoList.addAll(infos);

		// POR CADA DIRECTORIO HIJO BUSCA MAS PROGRAMAS
		File[] childFiles = dir.listFiles(new FileFilter(){
			public boolean accept(File pathname) {
				return pathname.isDirectory();
			}
		});
		for (File childFile : childFiles) {
			getProgramsRec(childFile, infoList);
		}		
	}

	/**
	 * Obtiene todos los programas en un directorio.
	 * @param dir directorio en el que se buscan (*.cae)
	 * @return la lista de programas encontrados
	 */
	private List<ProgramInfo> getFolderInfo(File dir) {

		List<ProgramInfo> res = new ArrayList<ProgramInfo>();
		File[] caeFiles = dir.listFiles(new FileFilter(){
			public boolean accept(File pathname) {
				return pathname.isFile() && pathname.getName().endsWith(CAE_EXT);
			}
		});
		for (File caeFile : caeFiles) {
			ProgramInfo p;
			try {
				p = ProgramInfoFileAccess.read(caeFile);
				res.add(p);
			} catch (FileNotFoundException e) {
				LOGGER.error("Error al buscar el archivo: " + caeFile, e);
			}
		}
		return res;
	}

	public void save(List<ProgramInfo> infoList) throws IOException
	{
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(CAELIST_LIST)));
		for (ProgramInfo programInfo : infoList) {
			writer.write(programInfo.getFile().getAbsolutePath());
			writer.write("\n");
		}
		writer.close();
	}

	public List<ProgramInfo> load() throws IOException
	{
		List<ProgramInfo> programs = new ArrayList<ProgramInfo>();
		File f = new File(CAELIST_LIST);
		if(f.exists())
		{
			BufferedReader reader = new BufferedReader(new FileReader(new File(CAELIST_LIST)));
			String line = null; 
			while((line = reader.readLine()) != null)
			{
				ProgramInfo p = ProgramInfoFileAccess.read(new File(line));
				programs.add(p);
			}
			reader.close();
		}
		return programs;
	}



	private static Vector<String> readList(String fileName) throws IOException {
		Vector<String> list = new Vector<String>(1);
		list.add(null);
		File f = new File(fileName);
		if(f.exists())
		{
			BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
			String line = null; 
			while((line = reader.readLine()) != null)
			{
				list.add(line);
			}
			reader.close();
		}
		return list;
	}

	public static Vector<String> getSO() {
		try {
			return readList(SO_LIST);
		} catch (IOException e) {
			File file = new File(SO_LIST);
			try {
				file.createNewFile();
			} catch (IOException e1) {
				LOGGER.error("No se pudo crear el archivo" + SO_LIST);
			}
			return new Vector<String>();
		}
	}

	public static Vector<String> getBits() {
		try {
			return readList(BITS_LIST);
		} catch (IOException e) {
			File file = new File(BITS_LIST);
			try {
				file.createNewFile();
			} catch (IOException e1) {
				LOGGER.error("No se pudo crear el archivo" + BITS_LIST);
			}
			return new Vector<String>();
		}
	}

	public static Vector<String> getCategory() {
		try {
			return readList(CATEGORY_LIST);
		} catch (IOException e) {
			File file = new File(CATEGORY_LIST);
			try {
				file.createNewFile();
			} catch (IOException e1) {
				LOGGER.error("No se pudo crear el archivo" + CATEGORY_LIST);
			}
			return new Vector<String>();
		}
	}

	public static Vector<String> getImportance() {
		try {
			return readList(IMPORTANCE_LIST);
		} catch (IOException e) {
			File file = new File(IMPORTANCE_LIST);
			try {
				file.createNewFile();
			} catch (IOException e1) {
				LOGGER.error("No se pudo crear el archivo" + IMPORTANCE_LIST);
			}
			return new Vector<String>();
		}
	}
}
