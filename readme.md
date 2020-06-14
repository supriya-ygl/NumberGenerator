# Number Generator Rest API running on port 3001

Run com.in28minutes.rest.webservices.restfulwebservices.RestfulWebServicesApplication as a Java Application.

- http://localhost:3001/generate 

POST METHOD :
REQUEST : 
{
      "goal":"10",
      "step":"5"
}

RESPONSE

{"bce2b223-f8e3-4629-a9e4-df042daca727"}

- http://localhost:3001/api/tasks/{task-id}/status
GET METHOD :
RESPONSE :
{
    "result": "SUCCESS "
} 


- http://localhost:3001/api/tasks/{task-id}
GET METHOD :
RESPONSE : {
    "result": "10,5,0"
}

