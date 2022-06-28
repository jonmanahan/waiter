# waiter

## Description
An HTTP server that (when running) can receive a client HTTP Request message via a curl command, browser or POSTMAN, and sends back a corresponding and appropriate HTTP Response message

## Prerequisites
- Install [Java version 18.0.1.1](https://java.com/en/download/)
- Install [Gradle](https://gradle.org/install/)
- (For Tests) Update your build.gradle with [Jqwik](https://jqwik.net/docs/current/user-guide.html#how-to-use) additions as shown in the link

## Commands to build the project
- gradle build

<<<<<<< HEAD
## Commands to start HTTP server
#### Must be done before any requests are sent
- gradle run

## Example curl command to send an HTTP message to the server
#### (This is a GET request, with an example url)
- curl -v telnet://localhost:5000*/insert_requested_resource_here*
#### For more curl methods/help, use the following curl command
- curl -h

## Command to run the unit tests
- gradle test

## Example commands to run a set of acceptance tests 
#### (Specific rake example: 1st set of acceptance tests, for the other 3 test sets replace the f1 with f2-f4 respectively)
1. cd http_server_spec
2. rake test:f1
