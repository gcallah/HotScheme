import java.lang.Exception;

public class UnmatchedParenExcep extends SchemeException
{
// public
    public UnmatchedParenExcep()
    {
        super();
        setExcpType(UNMATCHED_PAREN);
    }

    public UnmatchedParenExcep(String s)
    {
        super(s);
        setExcpType(UNMATCHED_PAREN);
    }

    public UnmatchedParenExcep(String s, SchemeObject obj)
    {
        super(s, obj);
        setExcpType(UNMATCHED_PAREN);
    }
}