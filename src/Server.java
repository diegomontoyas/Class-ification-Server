import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Diego
 */
public class Server
{
    private TeacherHandlerThread teacherHandlerThread;

    public static void main(String[] args)
    {
        Server server = new Server();
        server.start();
    }

    public Server ()
    {
    }

    public void start ()
    {
        try
        {
            ServerSocket studentWelcomeSocket = new ServerSocket(8484);
            ServerSocket teacherWelcomeSocket = new ServerSocket(8282);

            System.out.println("Waiting for teacher");

            teacherHandlerThread = new TeacherHandlerThread(teacherWelcomeSocket.accept());
            teacherHandlerThread.start();
            System.out.println("Welcome teacher");

            while(true)
            {
                Socket connectionSocket = studentWelcomeSocket.accept();
                System.out.println("New student");
                StudentHandlerThread studentHandlerThread = new StudentHandlerThread(connectionSocket);
                teacherHandlerThread.addStudentThread(studentHandlerThread);
                studentHandlerThread.start();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
