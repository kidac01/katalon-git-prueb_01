import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper as JsonSlurper

import internal.GlobalVariable as Global

ResponseObject response = WS.sendRequestAndVerify(findTestObject('miNomina_Mareigua-ConsultarNomina/Login', [('Login') :Login,('password') :password]))

String jsonString = response.getResponseText()

JsonSlurper slurper = new JsonSlurper()

KeywordUtil.markWarning('JSON '+ jsonString)

Map parsedJson = slurper.parseText(jsonString)

CustomKeywords.'almacena_datos_servicio.Val_mensaje_serv.val_login'(parsedJson)
