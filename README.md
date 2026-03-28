# MULTITHREADED-CHAT-APPLICATION

"COMPANY" : CODTECH IT SOLUTIONS

"NAME" : VAISHNAVI PUNSE 

"INTERN ID" : CTIS6853

"DOMAIN" :JAVA PROGRAMMING

"DURATION" : 4 WEEKS

"MENTOR" : NEELA SANTOSH 

"DESCRIPTION"

1. Establishing the Server Core
   
The Server class acts as the central hub. It starts by creating a ServerSocket tied to a specific port (1234). The server enters an infinite while(true) loop, which means it is constantly "listening" for new connection requests.

When a client attempts to connect, the serverSocket.accept() method triggers. This is a blocking call, meaning the server pauses there until someone knocks on the door. Once a connection is made, the server doesn't stop to handle that user itself; instead, it hands the connection off to a ClientHandler thread and immediately goes back to listening for the next person.

2. Managing Multiple Users with Threads
   
Since a chat room needs to handle many users simultaneously, the application uses Multithreading. Every time a new client connects, a new instance of the ClientHandler class (which extends Thread) is created.

This is crucial because if the server handled everything in one thread, it could only talk to one person at a time. By giving each user their own thread, the server can "read" a message from User A while simultaneously "waiting" for a message from User B. This concurrency is what makes the "Multi" in Multithreaded possible.

3. Data Synchronization and Broadcasting
   
To make sure everyone sees everyone else's messages, the server maintains a Set of PrintWriter objects called clientWriters. Because multiple threads might try to add or remove users from this list at the same time, the code uses Collections.synchronizedSet.

When a user sends a message, the server's broadcast() method loops through this set and pushes the text out to every connected PrintWriter. This ensures that when "Alice" types "Hello," the server catches it and immediately shoots it back out to "Bob," "Charlie," and even Alice herself.

4. The Client-Side Dual Operation
   
The Client class is designed to do two things at once: send and receive. If the client only had one thread, it would get stuck waiting for you to type a message and wouldn't be able to show you messages coming in from other people.

To solve this, the client spawns a separate background thread specifically to listen for input from the server. While that background thread sits and waits for in.readLine(), the main thread stays focused on the Scanner, waiting for you to type something into the console. This "dual-lane" approach allows for real-time, fluid conversation.

5. Connection Cleanup and Termination
   
The final step in the lifecycle is handling disconnections. In the ClientHandler, there is a finally block. This is a safety net. If a user closes their app or loses internet, an IOException is triggered. The code then automatically removes that user's PrintWriter from the clientWriters list and closes the socket. This prevents the server from trying to send messages to "ghost" users who are no longer there, keeping the system stable and efficient.


<img width="1596" height="1075" alt="Image" src="https://github.com/user-attachments/assets/18ca768d-1735-4211-9932-ff65381c27b8" />
