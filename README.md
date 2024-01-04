# reactive-spring

* Most scaling handled these days is done through horizontal scaling and using some sort of container orchestration
  * This entails some sort of increase in cost
* Reactive programming places emphasis on the idea to move away from the thread per request model
  * Traditional REST design follows an imperative style top-down approach
    * This is blocking and synchronous with no indications of back pressure
  * We want to be able to handle a higher load of requests with the limited threads available by the server
    * This leads to a design that needs to be asynchronous, non-blocking, fewer threads, back pressure compatible, etc.

## Reactive Programming
  * Fundamentally asynchronous and non-blocking 
  * Event/message driven stream
  * Function style code
  * Back pressure support
  
## Reactive Streams Specification
  * Rules for reactive stream to follow
  * Publisher
    * Represents the data source, it is the data producer
  * Subscriber
    * Reads from publisher, similar to consumer
    * Can specify n number of items to retrieve to handle back pressure
  * Subscription
    * Publisher sends subscription to subscriber
    * Subscriber can use cancel to stop using the subscription
  * Processor
    * Interface combination of subscriber and publisher
  * All of these interfaces are a contract to implement the rules for reactive streams
  * Reactive libraries implement this specification
    * Project Reactor, RxJava, Flow class
  
## Project Reactor
 * reactor-core implements reactive stream specification
 * Flux and Mono are the implementation classes
 * Flux represents 0 to N elements
 * Mono represents 0 to 1 element