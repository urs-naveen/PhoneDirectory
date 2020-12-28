# PhoneDirectory
This application is used to preserve the telephone directory entries without using any Database(DB).

# Testing using swagger-ui
Deploy the application by running the main method in PhoneDirectoryApplication.java and hit the below url to view the swagger-ui.
http://localhost:9090/phone/swagger-ui.html

# Testing TelephoneDirectoryController
inserting records: Use swagger-ui for inserting which would be better than curl

fetching a record:

curl -i http://localhost:9090/phone/aPhoneNo/1234567890

fetching all records:

curl -i http://localhost:9090/phone/allPhoneNos

# Status
The below api will provide the status of the application.

curl -i http://localhost:9090/phone/status

# Actuator
Below are the actuators calls for the application and should be executed while the application is running.

info: curl -i http://localhost:19091/management/info
health-check: curl -i http://localhost:19091/management/health
env properties: curl -i http://localhost:19091/management/env