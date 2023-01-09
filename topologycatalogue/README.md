# **A Domain driven application for a network based classification application based on Hexagonal architecture**

## Hexagonal architecture offers a more change-tolerant monolithic system, testable even without the Frontend and the Database

Based on a Service Oritented Architecture, the application is a microservices oriented demo of a real world usecase of a router 
configuration process which involves setting up a router using different specifications and usecases. Based on the layer 
of arhcitecture, it is divided into three maven modules .
### Domain
### Application
### Framework
## Domain
It contains all the abstraction of the real world scenario into a structure of Entities and corressponding value objects assigned
to it. It represents all the resources used by the router as a system of classes and interfaces and wraps a topology into networks
routers and switches and uses services to fetch data between different entities, and also filter through different specs of the
router or network.
### Testing
> mvn clean test
Tests are done using JUnit and tests using different scenarios of creating edge and core routers and filtering by specifications
## Application
Application consists of handling the logic of the business rules implementation using the concept of Ports and Usecases

### Usecases
Usecases are the requirements which we are dealing with for interacting with the respective topology. It consists of templates
of different methods which are implmented in usecase
