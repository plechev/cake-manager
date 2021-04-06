Migrating from legacy Cake Manager app:

* Use Gradle instead of Maven as build tool
* Use SpringBoot 2.x as execution framework
* Remove Hibernate setup boilerplate and replace with Spring JPA repositories and Boot's auto-configuration of H2 database.    
* Clean-up entity class `Cake` - fix naming, tidy annotations, ID generation strategy, introduce equality by title
* Use Jackson parser to initialise Cake data from external JSON file instead of manually processing JSON streams. 
  This is done in a separate Spring component attached to application lifecycle events.   
* Remove references to `System.out.print`, add Log4j2 as logging mechanism
* Use Spring's built-in facilities to marshal and unmarshal JSON payloads during REST calls, removing the need to do so manually.
* Use Spring MVC/REST controllers to expose REST API and static HTML content
* Add REST endpoints to create and delete cakes   
* Use separation of UI and API via basic JQuery/Ajax for the front-end and REST/JSON for back-end.
* Authenticate users via either GitHub or built-in FusionAuth resource server. 
* Containerise application ready for cloud deployment

