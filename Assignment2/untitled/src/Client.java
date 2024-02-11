/**
 * Represents Client class to send and receive datagram from
 * intermediate host
 *
 * @author Teshwar Tarachand
 * @version 1.0
 */
import java.io.*;
import java.net.*;
import java.util.Arrays;

public class Client {
    DatagramSocket sendReceiveSocket;

    DatagramPacket sendPacket, receivePacket;

    /**
     *  Constructor for  Client class
     */
    public Client(){
        try{
            // Construct socket and bind it to any port on local host
            sendReceiveSocket = new DatagramSocket();
        } catch (SocketException se){
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
     *
     * @param index: is used to create write, read, invalid packet (and choose string mode)
     * @return DatagramPacket: returns Datagram packet according to index
     * @throws Exception: Function throws errors if error occurs during packet creation
     */
    private static DatagramPacket createPacket(int index) throws Exception {
        byte[] data;
        String requestType;
        String filename = "test.txt";
        String mode = (index % 2 == 0) ? "netascii" : "octet";

        if (index == 10) {
            // Invalid request
            requestType = "invalid";
            data = new byte[1]; // Just one byte for invalid request
        } else {
            requestType = (index % 2 == 0) ? "read" : "write";
            String requestStr = (requestType.equals("read")) ? "\u0000\u0001" : "\u0000\u0002";
            String fullRequestStr = requestStr + filename + "\u0000" + mode + "\u0000";
            data = fullRequestStr.getBytes();
        }

        return new DatagramPacket(data, data.length);
    }
    public void sendAndReceive()
    {
        for (int i = 0; i < 11; i++) {

            System.out.println(i + "-------------------------------");

            //packet created using createPacket()
            try {
                //packet created using createPacket()
                sendPacket = createPacket(i);
            } catch (Exception e) {
                // Handle the exception (e.g., print an error message)
                e.printStackTrace();
            }

            //Send Packet to destination
            try {

                //Set IP outside Packet creation to allows more flexibility later
                InetAddress host = InetAddress.getLocalHost();
                sendPacket.setAddress(host);
                sendPacket.setPort(23);

                // Print the Sending Packet
                printPacketInfo(sendPacket);

                sendReceiveSocket.send(sendPacket);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }

            System.out.println("Client: Packet sent.\n");

            //-----------------------------------------------------
            //Constructs datagram to receive use 20 as min byte to read all modes(invalid, read, write)
            byte data[] = new byte[4];
            receivePacket = new DatagramPacket(data, data.length);
            System.out.println("IntermediateHost: Waiting for IntermediateHost Server Response Packet.\n");
            try {
                // Block until a datagram is received via sendReceiveSocket.
                System.out.println("Waiting..."); // so we know we're waiting
                sendReceiveSocket.receive(receivePacket);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }

            // Prints the receive packet
            printReceivedPacketInfo(receivePacket);

            System.out.println("--------------------------------");
        }

        // We're finished, so we close the socket.
        sendReceiveSocket.close();
    }

    public static void main(String args[])
    {
        Client c = new Client();
        c.sendAndReceive();
    }


}




