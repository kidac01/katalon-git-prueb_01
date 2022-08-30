import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper as JsonSlurper

import internal.GlobalVariable as Global

Global.regis = Integer.parseInt(registro)

ResponseObject response = WS.sendRequestAndVerify(findTestObject('miFactura_Mareigua-ConsultarFactura/Login', [('Login') :Login,('password') :password]))

Global.val_time = response.getElapsedTime()

Global.status_login = response.getStatusCode()

String jsonString = response.getResponseText()

JsonSlurper slurper = new JsonSlurper()

KeywordUtil.markWarning('JSON '+ jsonString)

Map parsedJson = slurper.parseText(jsonString)

Global.nom_func = 'val_login'

CustomKeywords.'almacena_datos_servicio.Val_mensaje_serv.valida_mensaje_serv_login'(parsedJson,type,ValidationMessage)

CustomKeywords.'almacena_datos_servicio.Val_mensaje_serv.guarda_salida_mensaje_serv_login'()