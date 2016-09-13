import java.lang.Exception;

public class QuitException extends SchemeException
{
    public QuitException()          { super(); }
    public QuitException(String s)  { super(s); }

    public static final String GOODBYE
        = "Goodbye from Hot Scheme!";


    public String Print()
    {
        return GOODBYE;
    }
}