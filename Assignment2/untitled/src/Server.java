/**
 * Represents Server class interacts and sends response to intermediate class
 *
 * @author Teshwar Tarachand 
 * @version 1.0
 */
import java.io.*;
import java.net.*;
import java.util.Arrays;

public class Server {
    DatagramPacket receivedIntermediateHostPacket, sendIntermediateHostPacket;
    DatagramSocket receiveIntermediateHostSocket, sendIntermediateHostSocket;

    //Stores client port to send to client again
    InetAddress intermediateHostAddress ;
    int intermediateHostclientPort;
    /**
     * Constructor for the server
     */
    public Server(){
        try {
            // Constructs the required Datagram socket
            receiveIntermediateHostSocket = new DatagramSocket(69);
            sendIntermediateHostSocket = new DatagramSocket();

            // to test socket timeout (2 seconds)
            //receiveSocket.setSoTimeout(2000);
        } catch (SocketException se) {
            se.printStackTrace();
            System.exit(1);
        }
    }

    /**
     *
     * @param packet : Print the packet about to send
     */
    private static void printPacketInfo(DatagramPacket packet) {
        System.out.println("Packet Information:");
        System.out.println("Data as String: " + new String(packet.getData()));
        System.out.println("Data as Bytes: " + Arrays.toString(packet.getData()));
        System.out.println("Length: " + packet.getLength());
        System.out.println("Destination Address: " + packet.getAddress());
        System.out.println("Destination Port: " + packet.getPort());
        System.out.println();
    }

    /**
     *
     * @param packet : Received Packet
     */
    private static void printReceivedPacketInfo(DatagramPacket packet) {
        System.out.println("Received Packet Information:");
        System.out.println("Data as String: " + new String(packet.getData()));
        System.out.println("Data as Bytes: " + Arrays.toString(packet.getData()));
        System.out.println("Length: " + packet.getLength());
        System.out.println("Sender Address: " + packet.getAddress());
        System.out.println("Sender Port: " + packet.getPort());
        System.out.println();
    }

    /**
     * @param packet : receivedPacket check if is valid or not
     * @return boolean : True if valid, False if not
     */
    private static boolean isValidRequest(DatagramPacket packet) {
        byte[] requestData = packet.getData();
        int length = packet.getLength();

        // Check if the packet contains at least 2 bytes
        if (length < 2) {
            return false;
        }

        // Check the format of the first two bytes
        if (!(requestData[0] == 0 && (requestData[1] == 1 || requestData[1] == 2))) {
            return false;
        }

        // Check if the last byte is 0
        if (!(requestData[length - 1] == 0)) {
            return false;
        }

        return true;
    }

    /**
     *
     * @param packet : receivedPacket check if is read or write
     * @return serverResponse : {0 3 0 1} if read else {0 4 0 0} if write
     */
    private static byte[] serverResponse(DatagramPacket packet) {
        byte[] requestData = packet.getData();

        // Check the format of the first two bytes
        if (requestData[0] == 0 && requestData[1] == 1){
            return new byte[]{0, 3, 0, 1} ;
        }
        return new byte[]{0, 4, 0, 0} ;
    }


    public void receiveSend(){

        System.out.println("--------------------------------");

        //Constructs datagram to receive use 20 as min byte to read all modes(invalid, read, write)
        byte data[] = new byte[20];
        receivedIntermediateHostPacket = new DatagramPacket(data, data.length);
        System.out.println("Server: Waiting for IntermediateHost Packet.\n");

        //Wait until Datagram Receive Packet
        try {
            System.out.println("Waiting..."); // so we know we're waiting
            receiveIntermediateHostSocket.receive(receivedIntermediateHostPacket);
        } catch (IOException e) {
            System.out.print("IO Exception: likely:");
            System.out.println("Receive Socket Timed Out.\n" + e);
            e.printStackTrace();
            System.exit(1);
        }

        //Prints Received Packet
        printReceivedPacketInfo(receivedIntermediateHostPacket);

        // Check the validity of data
        try{
            if (!isValidRequest(receivedIntermediateHostPacket)) {
                throw new IllegalArgumentException("Invalid request format");
            }
        }catch (IllegalArgumentException e){
            System.err.println("Format not valid");
            System.exit(1);
        }


        //Stores client port to send to client again
        intermediateHostAddress = receivedIntermediateHostPacket.getAddress();
        intermediateHostclientPort = receivedIntermediateHostPacket.getPort();

        // Prepare the response packet for writing request and reading
        byte[] responseData = serverResponse(receivedIntermediateHostPacket);

        //Duplicates Datagram Packet to send to Client
        sendIntermediateHostPacket = new DatagramPacket(responseData, responseData.length,
                intermediateHostAddress, intermediateHostclientPort);

        //Prints Datagram Packet to send to Client
        printPacketInfo(sendIntermediateHostPacket);


        //Send the Datagram to the client
        // Send the datagram packet to the client via the send socket.
        try {
            sendIntermediateHostSocket.send(sendIntermediateHostPacket);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println("Server: response packet sent");

        System.out.println("--------------------------------");

        // We're finished, so close the sockets.
        receiveIntermediateHostSocket.close();
        sendIntermediateHostSocket.close();

    }


    public static void main( String args[] )
    {
        while(true) {
            Server c = new Server();
            c.receiveSend();
        }
    }
}
