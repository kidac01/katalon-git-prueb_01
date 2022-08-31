package almacena_datos_servicio

import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil

import internal.GlobalVariable as Global

public class Val_mensaje_serv {

	
	
	@Keyword

	def val_login(Map parsedJson) {

		String val = parsedJson.ValidationMessage

		if(val == null) {
			val = 'null'
		}

		if (parsedJson.Content != null){
			Global.token = 'misfacturas ' + parsedJson.Content.JWT
			Global.resp_Content = Global.token
		}
	}


	@Keyword

	def valida_mensaje_serv_login(Map parsedJson, String type, String ValidationMessage) {

		Global.resp_type = parsedJson.Type
		Global.resp_ValidationMessage = parsedJson.ValidationMessage

		Global.resp_Content = parsedJson.Content

		String val_mess = parsedJson.ValidationMessage
		String val_content = parsedJson.Content

		if(val_mess == null) {
			Global.resp_ValidationMessage = 'null'
		}

		if(val_content == null) {
			Global.resp_Content = 'null'
		}

		if (parsedJson.Content != null){
			Global.token = 'misfacturas ' + parsedJson.Content.JWT
			Global.resp_Content = Global.token
		}

		println ('token -->' + Global.token)

		if (parsedJson.Type == type && val_mess == parsedJson.ValidationMessage ) {
			Global.estado_val_mensaje_login = 'EXITO'
			KeywordUtil.markPassed('Exito')
		}else {
			Global.estado_val_mensaje_login = 'FALLIDO'
			KeywordUtil.markFailed('Fallido -->  Valor (Type ó ValidationMessage) NO corresponde')
		}
	}


	@Keyword

	def valida_consulta_id_serv_report(Map parsedJson, String val_consulta_id) {

		Global.ConsultaId = parsedJson.Consulta_Id
		Global.fecha = parsedJson.Fecha
		def val_per = parsedJson.Periodos
		def dim_arreglo = parsedJson.Data.size()

		println (val_per)

		if (val_per <= 13 || dim_arreglo != val_per ) {

			KeywordUtil.markPassed ('EXITO')
			Global.estado_val_periodo = 'EXITO'
		}else {

			KeywordUtil.markFailed ('FALLIDO')
			Global.estado_val_periodo = 'FALLIDO -->  Valor Períodos mayor a 13. ó NO corresponde a valor en DATA[]'
		}


		/*if (parsedJson.Consulta_Id == Integer.parseInt(val_consulta_id)) {
		 KeywordUtil.markPassed ('EXITO')
		 Global.estado_val_consulta_id_report = 'EXITO'
		 }else {
		 KeywordUtil.markFailed ('FALLIDO')
		 Global.estado_val_consulta_id_report = 'FALLIDO'
		 }*/
	}


	@Keyword

	def guarda_salida_mensaje_serv_login() {

		FileInputStream fis = new FileInputStream (Global.ruta_salida)
		XSSFWorkbook workbook = new XSSFWorkbook (fis)

		XSSFSheet sheet = workbook.getSheet(Global.nom_func)

		sheet.getRow(Global.regis).createCell(6).setCellType(CellType.NUMERIC)
		sheet.getRow(Global.regis).createCell(7).setCellType(CellType.STRING)
		sheet.getRow(Global.regis).createCell(8).setCellType(CellType.STRING)
		sheet.getRow(Global.regis).createCell(9).setCellType(CellType.STRING)
		sheet.getRow(Global.regis).createCell(10).setCellType(CellType.NUMERIC)
		sheet.getRow(Global.regis).createCell(11).setCellType(CellType.STRING)

		sheet.getRow(Global.regis).getCell(6).setCellValue(Global.status_login)
		sheet.getRow(Global.regis).getCell(7).setCellValue(Global.resp_type)
		sheet.getRow(Global.regis).getCell(8).setCellValue(Global.resp_ValidationMessage)
		sheet.getRow(Global.regis).getCell(9).setCellValue(Global.resp_Content)
		sheet.getRow(Global.regis).getCell(10).setCellValue(Global.val_time)
		sheet.getRow(Global.regis).getCell(11).setCellValue(Global.estado_val_mensaje_login)

		FileOutputStream outFile = new FileOutputStream (Global.ruta_salida)
		workbook.write(outFile)

		outFile.close()
		fis.close()
	}


	@Keyword

	def guarda_salida_consultar_factura() {

		FileInputStream fis = new FileInputStream (Global.ruta_salida)
		XSSFWorkbook workbook = new XSSFWorkbook (fis)

		XSSFSheet sheet = workbook.getSheet(Global.nom_func)

		sheet.getRow(Global.regis).createCell(4).setCellType(CellType.NUMERIC)
		sheet.getRow(Global.regis).createCell(5).setCellType(CellType.STRING)
		sheet.getRow(Global.regis).createCell(6).setCellType(CellType.NUMERIC)
		sheet.getRow(Global.regis).createCell(7).setCellType(CellType.STRING)
		sheet.getRow(Global.regis).createCell(8).setCellType(CellType.STRING)
		sheet.getRow(Global.regis).createCell(9).setCellType(CellType.STRING)
		sheet.getRow(Global.regis).createCell(10).setCellType(CellType.STRING)

		sheet.getRow(Global.regis).getCell(4).setCellValue(Global.status)
		sheet.getRow(Global.regis).getCell(5).setCellValue(Global.salida_json)
		sheet.getRow(Global.regis).getCell(6).setCellValue(Global.val_time)
		sheet.getRow(Global.regis).getCell(7).setCellValue(Global.ConsultaId)
		sheet.getRow(Global.regis).getCell(8).setCellValue(Global.fecha)
		sheet.getRow(Global.regis).getCell(9).setCellValue(Global.estado_val_consulta_id_report)
		sheet.getRow(Global.regis).getCell(10).setCellValue(Global.estado_val_periodo)


		FileOutputStream outFile = new FileOutputStream (Global.ruta_salida)
		workbook.write(outFile)

		outFile.close()
		fis.close()
	}
}
