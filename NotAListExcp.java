import java.lang.Exception;

public class NotAListExcp extends SchemeException
{
// public
    public NotAListExcp()
    {
        super();
        setExcpType(NOT_A_LIST);
    }

    public NotAListExcp(String s)
    {
        super(s);
        setExcpType(NOT_A_LIST);
    }

    public NotAListExcp(String s, SchemeObject obj)
    {
        super(s, obj);
        setExcpType(NOT_A_LIST);
    }
}