import java.lang.Exception;

public class SpecialFormException extends SchemeException
{
    public SpecialFormException()          { super(); }
    public SpecialFormException(String s)  { super(s); }

    public static final String SPECIAL_FORM
        = "Special form processed";
}