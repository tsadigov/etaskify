# etaskify
ABB Coding Task


# Requirements to run project
1. Java 17
2. (Optional)MySql installed on your local computer (H2 is configured in project)
3. Postman

# Instructions
1. (Optional)Create SCHEMA named "abb_etaskify" in MySQL using WORKBENCH
2. (Optional)Edit username and password in application.properties file to create connection to database
3. Run ETaskifyApplication.java file to run project
4. Swagger is configured so open "http://localhost:8080/swagger-ui/index.html"

# User story 1
5. Click on user-controller (open to everyone) and choose "signUp"
6. Click on "Try it out" , fill JSON formatted data and execute. You'll see response. ADMIN user is created in this process.

# User story 2
7. Just this process requires Postman. Send POST request to "http://localhost:8080/api/login" using body format
{
    "username":"lisa",
    "password":"123aaa"
}
8. If request successful, there will be ACCESS and REFRESH tokens in HEADERs. Take access token and paste to Authorize part in the opened SWAGGER UI page, with prefix "Bearer "

# User story 3
9. Only Admin can create a user and role of that user will be EMPLOYEE. To create one, click on choose "createUser" and fill the form as previously done.
10. Users' usernames will be their email addresses' first part (until @ symbol)

# User story 4
11. To create task, choose "task-controller" and "createTask" then fill the form. Taskstatus should be from 12th step, usernames from 13th step 
12. To get list of taskStatuses choose "getTaskStatuses" in "task-status-controller" 
13. To get all users call "getUsers" in "user-controller"


These are for USER STORIES. There are other api's which can be used for further processes. For more help, contact me...
Email: turalsadiqov497@gmail.com

 
