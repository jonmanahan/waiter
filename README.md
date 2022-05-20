# waiter

## Description
A server that establishes a connectable with the client, receives their message and echos it back to them

## Prerequisites
- Install [Java version 18.0.1.1](https://java.com/en/download/)
- Install [Gradle](https://gradle.org/install/)
- (For Tests) Update your build.gradle with [Jqwik](https://jqwik.net/docs/current/user-guide.html#how-to-use) additions as shown in the link

## Commands to build the project
- gradle build

## Commands to start echo server
- gradle run

## Commands to establish the connectable to the running echo server (from a separate command line) and then echo a message
1. curl -v telnet://localhost:4424
2. *enter message to echo*

## Command to run the tests
- gradle test