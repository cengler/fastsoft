package cae.model;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;




public class Services {

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
			ProgramInfo p = ProgramInfoFileAccess.read(caeFile);
			res.add(p);
		}
		return res;
	}

	public void save(List<ProgramInfo> infoList)
	{
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File("caelist.list")));
			for (ProgramInfo programInfo : infoList) {
				writer.write(programInfo.getFile().getAbsolutePath());
				writer.write("\n");
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<ProgramInfo> load()
	{
		List<ProgramInfo> programs = new ArrayList<ProgramInfo>();
		try {
			File f = new File("caelist.list");
			if(f.exists())
			{
				BufferedReader reader = new BufferedReader(new FileReader(new File("caelist.list")));
				String line = null; 
				while((line = reader.readLine()) != null)
				{
					ProgramInfo p = ProgramInfoFileAccess.read(new File(line));
					programs.add(p);
				}
				reader.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return programs;
	}
}
