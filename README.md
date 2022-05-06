# waiter

## Description
A server that establishes a connection with the client, receives their message and echos it back to them

## Prerequisites
- gradle build

## Commands to start echo server
- gradle run

## Commands to establish the connection to the running echo server (from a separate command line) and then echo a message
1. curl -v telnet://localhost:4424
2. *enter message to echo*

## Command to run the tests
- gradle test