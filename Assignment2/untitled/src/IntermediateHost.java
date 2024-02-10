/**
 * Represents IntermediateClass class between client and server
 *
 * @author Teshwar Tarachand 
 * @version 1.0
 */
import java.io.*;
import java.net.*;
import java.util.Arrays;

public class IntermediateHost {
    DatagramPacket receiveClientPacket, receiveServerPacket, sendServerPacket, sendClientPacket;
    DatagramSocket receiveClientSocket, sendReceiveServerSocket, sendClientSocket;
    int clientPort;
    InetAddress clientAddress;

    /**
     *  Constructor for  IntermediateHost class
     */
    public IntermediateHost(){
        try {
            // Constructs the required Datagram socket
            receiveClientSocket = new DatagramSocket(23);
            sendReceiveServerSocket = new DatagramSocket();
            sendClientSocket = new DatagramSocket();


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
     *  Does the desirable function of assignment(acts as intermediary for server and client)
     */
    public void receiveSendReceiveSend(){

        System.out.println("-------------------------------");

        //----------------------Receive Client Packet
        //Clears data to be used again
        byte data[] = new byte[20];
        receiveClientPacket = new DatagramPacket(data, data.length);
        System.out.println("IntermediateHost: Waiting for Client Packet.\n");

        //Wait until Datagram Receive Packet
        try {
            System.out.println("Waiting..."); // so we know we're waiting
            receiveClientSocket.receive(receiveClientPacket);
        } catch (IOException e) {
            System.out.print("IO Exception: likely:");
            System.out.println("Receive Socket Timed Out.\n" + e);
            e.printStackTrace();
            System.exit(1);
        }

        //Prints Received Packet
        printReceivedPacketInfo(receiveClientPacket);

        //Stores client port to send to client again
        clientAddress = receiveClientPacket.getAddress();
        clientPort = receiveClientPacket.getPort();

        //----------------------Send Server Packet
        //Duplicates Datagram Packet to send to Server
        sendServerPacket = new DatagramPacket(data, receiveClientPacket.getLength());

        //Prints Datagram Packet to send to Server
        printPacketInfo(sendServerPacket);

        //Send the Datagram to the Server on port 69
        try {
            //Set IP outside Packet creation to allows more flexibility later
            InetAddress host = InetAddress.getLocalHost();
            sendServerPacket.setAddress(host);
            sendServerPacket.setPort(69);
            sendReceiveServerSocket.send(sendServerPacket);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        //----------------------Received Server Response Packet
        //Clear data to be used again
        Arrays.fill(data, (byte) 0);
        receiveServerPacket = new DatagramPacket(data, data.length);
        System.out.println("IntermediateHost: Waiting for Server Response Packet.\n");

        //Wait until Datagram Receive Packet
        try {
            System.out.println("Waiting..."); // so we know we're waiting
            sendReceiveServerSocket.receive(receiveServerPacket);
        } catch (IOException e) {
            System.out.print("IO Exception: likely:");
            System.out.println("Receive Socket Timed Out.\n" + e);
            e.printStackTrace();
            System.exit(1);
        }

        //Prints Received Packet
        printReceivedPacketInfo(receiveServerPacket);
        //----------------------Send Client Server Response
        //Duplicates Datagram Packet to send to Client
        sendClientPacket = new DatagramPacket(data, receiveClientPacket.getLength(),
                clientAddress, clientPort);

        //Prints Datagram Packet to send to Client
        printPacketInfo(sendClientPacket);


        //Send the Datagram to the client
        // Send the datagram packet to the client via the send socket.
        try {
            sendClientSocket.send(sendClientPacket);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println("Intermediate Host: Sending Client Server response packet sent");

        System.out.println("--------------------------------");

        // We're finished, so close the sockets.
        sendReceiveServerSocket.close();
        sendClientSocket.close();
        receiveClientSocket.close();

    }

    public static void main( String args[] )
    {
        while(true) {
            IntermediateHost c = new IntermediateHost();
            c.receiveSendReceiveSend();
        }
    }
}
