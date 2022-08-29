package almacena_datos_servicio

public class Graba_archivo {

	public void create_Add(String a, String archivo) {
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			String data = a;
			File file = new File(archivo);
			if (!file.exists()) {
				file.createNewFile();
			}
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			bw.write(data + '\n');
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
