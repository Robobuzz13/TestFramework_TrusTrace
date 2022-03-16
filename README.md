# TestFramework_TrusTrace
UI and API automation test framework using Selenium Java and Rest assured. 

### About Framework

This framework is build on maven. Used testNg as testing framework and followed Page Object Mode as designing pattern. Currently it is supporting four different types browser Chrome, Firefox, Edge and IE. 
Use testng xml to run UI and API cases. if plan to trigger test from commandPrompt use following command.

mvn test -DwebSite=${url} -Dbrowser=${browserName} -DfileName=${excelFileName} -DsuiteXmlFile=${testngXml}

