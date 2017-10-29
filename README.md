# CreditSuisse
This is project made for recruitment process in Credit Suisse.

1. Its a Maven Spring Boot project. If you want to run server you need to run ServerApplication.java class as normal Java application.
2. There is a small client in ClientApplication.java. You can run it without parameters so it will load input from SampleTestData.txt, or you can provide JSON input data as program parameter.
3. After running ServerApplication.java the REST interface will be available at http://localhost:8080/validate.
4. Service support graceful shutdown, but it should be closed correctly. I tested it in Eclipse with Spring Tool Suite and it worked well.
5. I don't think that all ISO 4217 codes should be available on production (like XXX code), but I left them according to task content.
6. I didn't understand the task: "validate the value date against the product type", so I decided to validate that type "Spot" is valid if has value date greater than 2011-01-01 and type "Forward" is valid if has value date greater than 2010-02-03.