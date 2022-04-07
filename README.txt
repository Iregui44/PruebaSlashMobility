Instrucciones para el consumo de la API:

Se utilizo una autorización por edio del uso de TOKENS BEARER, por lo que la unica peticion que funciona en primera instancia es CreateUser. 
Para el correcto funcionamiento de las otras peticiones, se requiere que se use la petición Auth primero y obtener un TOKEN de verificación, luego de esto en las otras peticiones se debe consumir la petición agregandole autorización por medio de token bearer y pegar el TOKEN anteriormente obtenido en la petición Auth. 

Con esto se simula el back-end para la autenticación de un usuario. De resto el uso de las peticiones estan especificacadas en el archivo de POSTMAN o en el archivo SWAGGER. 

Adicionalmente, con elproposito de probar el ejercicio, es necesario utulizar una API_KEY valida de la herramienta externa SendGrid, ya que es posible su suplantación o expiración.
