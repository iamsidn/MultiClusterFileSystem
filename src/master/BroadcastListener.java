import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;

public class BroadcastListener {

    private static DatagramSocket socket;

    public static void main(String[] args) throws IOException {

        try {
            socket = new DatagramSocket(4446);
            System.out.println("\nListening to Broadcast messages on port 4446...");
        } catch (Exception e) {
            System.err.println("\nPort already in use.");
            System.exit(1);
        }


        while (true) {
            try {
                byte[] buf = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                System.out.println("\nReceived broadcast packet...");
                // System.out.println("\nBroadcast Packet byte array : ");
                // for(int ind=0; ind<buf.length; ind++){
                //     System.out.println(buf[ind]);
                // }
                System.out.println("\nPacket length : " + packet.getLength());
                Thread t = new Thread(new BroadcastConnection(packet));
                t.start();
                //socket.close();

            } catch (Exception e) {
                System.err.println("\nIn Broadcast Listener...Error in connection attempt.");
            }
        }
    }
}
