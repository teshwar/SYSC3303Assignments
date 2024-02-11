# In this README.md, I will present the files present and how to run it. 

## Open Assignment 2 Folder in IntelliJea:
	In this Assignment we were asked to implement the requirements from Assignment 2: Introduction to UDP.

	Notes: The only program that ends is Server cause it gets an invalid request, the Client and Intermediate host will wait for them.
	
	Files: 
	Client.java: Implement Client, that is used to create 5 write, 5 read, and invalid requests (as supposed to in Assignment 2)
	IntermediateHost.java: Implement IntermediateHost that is continously runningm it takes client packet, forward it server which validates and response, and get server response to client again
	Server.java: Implement Server, that is used to validate packet from Client and send response packet back to IntermediateHost

	To run the project, run the main program in each class in the following order: 
	1.IntermediateHost
	2.Server
	3.Client
	
## In UML Diagram Folder:
	Find a Seuqence diagram and a Class diagram.