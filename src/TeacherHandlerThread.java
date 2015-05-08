import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Diego
 */
public class TeacherHandlerThread extends Thread
{
    private ArrayList<StudentHandlerThread> studentHandlerThreads = new ArrayList<StudentHandlerThread>();

    private Socket socket;
    private BufferedReader inFromClient;
    private PrintWriter outToClient;

    public TeacherHandlerThread(Socket socket)
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

    public void addStudentThread (StudentHandlerThread studentHandlerThread)
    {
        studentHandlerThreads.add(studentHandlerThread);
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
                System.out.println("Teacher: "+clientRequest);

                if (clientRequest.startsWith("THROW CHALLENGE:"))
                {
                    String[] components = clientRequest.split(":");

                    for (StudentHandlerThread student: studentHandlerThreads)
                    {
                        student.throwChallenge(Integer.parseInt(components[1]),Integer.parseInt(components[2]));
                    }
                }
                else if (clientRequest.startsWith("THROW QUESTION:"))
                {
                    for (StudentHandlerThread student: studentHandlerThreads)
                    {
                        student.throwQuestion(clientRequest);
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
