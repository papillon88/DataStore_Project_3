# DataStore_Project

This project involves constructing a client-server system over TCP/IP sockets. The service stores,
retrieves, deletes, & lists named data sets on the server. Each data set is associated with a string name
provided in the request. The service is responsible for storing this data in a persistent fashion for later
retrieval. Data is persistently stored in files in a directory under the project directory.

The project includes :
1. A datastore server application implementing the protocol described in the section “DatastoreClient Protocol”. This server listens for incoming socket connections at port 10023.
2. A Client-side API that is used to build applications that connect to the server over a TCP socket and stores, retrieves, deletes, and lists named datasets.
3. Objectstore feature that allows for Java objects to be persisted and retrieved from the datastore service.

