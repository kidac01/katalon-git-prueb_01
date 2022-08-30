package almacena_datos_servicio

public class Elimina_archivo {

	public void deleteFile(String pathFile) {
		try {
			File fichero = new File(pathFile);
			if (fichero.delete()) {
			} else {
			}
		} catch (Exception e) {
		}
	}
}
