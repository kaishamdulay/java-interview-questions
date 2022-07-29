To run the project please extract the zip in a folder
Run the following command to run the application


mvn clean install -Pdocker-image

The server will start on port 8080

To login to h2 database please follow the below link

http://localhost:8080/h2-console

Please refer application.properties and use
spring.datasource.username 
spring.datasource.password
spring.datasource.url
to login to the database to verify database values

Use the below  curl request from postman or command line to start using the application rest service

curl --location --request POST 'http://localhost:8080/v1/url/short?url=https://stash.backbase.com/projects/PO/repos/payment-order-integration-spec/browse/src/main/resources/schemas/definitions.json' \
--header 'x-api-key: xapi'

curl --location --request GET 'http://localhost:8080/v1/url/long?tiny=fPhamgtapOtaWxpagssaeMsaHptau6aaw2saGT' \
--header 'x-api-key: xapi'
you need to pass the value from application.properties collective.authtoken in xapi in the curl request fpr both apis
