import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import almacena_datos_servicio.Elimina_archivo
import almacena_datos_servicio.Graba_archivo
import groovy.json.JsonSlurper
import internal.GlobalVariable as Global


Graba_archivo grabar = new Graba_archivo()

Global.regis = Integer.parseInt(registro)

ResponseObject response = WS.sendRequestAndVerify(findTestObject('miNomina_Mareigua-ConsultarNomina/ConsultarConsolidado_Factura', [('TipoIdentificacion') :TipoIdentificacion, ('NumeroIdentificacion') :NumeroIdentificacion, ('token') :token]))

Global.val_time = response.getElapsedTime()

Global.status = response.getStatusCode()

String jsonString = response.getResponseText()

Global.salida_json = jsonString.replaceAll("\n", "")

JsonSlurper slurper = new JsonSlurper()

//grabar.create_Add(Global.regis + ' - '+jsonString, Global.ruta_arch_salida)

Map parsedJson = slurper.parseText(jsonString)

Global.nom_func = 'val_mens_serv_report'

println(jsonString)

//CustomKeywords.'almacena_datos_servicio.Val_mensaje_serv.valida_mensaje_serv_report'(parsedJson,val_mensaje_report)

//CustomKeywords.'almacena_datos_servicio.Val_mensaje_serv.guarda_salida_consultar_factura'()





