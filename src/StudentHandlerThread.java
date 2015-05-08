import java.io.*;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: Diego
 */
public class StudentHandlerThread extends Thread
{
    private Socket socket;
    private BufferedReader inFromClient;
    private PrintWriter outToClient;

    public StudentHandlerThread(Socket socket)
    {
        try
        {
            this.socket = socket;

            inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outToClient = new PrintWriter(socket.getOutputStream(), true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void throwChallenge (int cost, int prize)
    {

        outToClient.println("CHALLENGE:"+cost+":"+prize+"\n");
    }

    public void throwQuestion (String request)
    {
        outToClient.println(request+"\n");
    }

    @Override
    public void run()
    {
        super.run();

        try
        {
            while (true)
            {
                String clientRequest = inFromClient.readLine();


            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
