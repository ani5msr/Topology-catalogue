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
of different methods which are implmented in ports. Usecases interact directly with the domain entities or they use other usecases
```java 
public interface RouterUseCase {
    Router createRouter(
            ID id,
            Vendor vendor,
            Model model,
            IP ip,
            Location location,
            Routertype routerType)
	Router removeRouterFromCoreRouter(
            Router router, CoreRouter coreRouter)
```
Example of a usecase is a router method of creating a router and removing router from core router
### Ports
Ports are two types of data interfaces Output and input, Input Port allows the software intents for manipulating domain data
Like if we want to get routers or filter through routers.Output port then is used to interact with particular kind of data to interact with
using database service.

#### Testing
Cucumber is used for unit testing of all the usecases and handle business exceptions.
```java
 @Given("I provide all required data to create a switch")
	    public void i_provide_all_required_data_to_create_a_switch(){
	        networkSwitch = this.switchUseCase.createSwitch(
	                Vendor.CISCO,
	                Model.ABC0001,
	                IP.fromAddress("20.0.0.100"),
	                locationA,
	                Switchtype.Layer3
	        );
	    }

	    @Then("A new switch is created")
	    public void a_new_switch_is_created() {
	        assertNotNull(networkSwitch);
	        assertEquals(Vendor.CISCO, networkSwitch.getVendor());
	        assertEquals(Model.ABC0001,networkSwitch.getModel());
	        assertEquals(IP.fromAddress("20.0.0.100"), networkSwitch.getIp());
	        assertEquals(locationA, networkSwitch.getLocation());
	        assertEquals(Switchtype.Layer3, networkSwitch.getSwitchType());
	    }
```
Case of using cucumber a test for providing data to create switch and the checking it
## Framework
Fremwework comprises of technologies used for communication with the client. It uses Adaptors as a change tolerant approach
for dealing with different kinds of data input and output channels and have a technology agnostic approach
