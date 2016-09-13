import java.util.Enumeration;
import java.util.Hashtable;


/**
AbstractObject is the base class for all abstract objects
 it extends FuncRep because Scheme abstract objects are functions
 that are constructed by calling them, and accept messages
 through a syntactic extension to the function syntax
*/
abstract class AbstractObject extends FuncRep
{
/**
subclasses should initialize this to their own static copy,
  that way, each subclass has its own list of messages, once
*/
    protected Hashtable messages_accepted;

    protected void register_message(String msg, String descr)
    {
        messages_accepted.put(msg, descr);
    }


    protected boolean valid_message(String msg)
    {
        return messages_accepted.containsKey(msg);
    }


    protected String get_syntax(String msg)
    {
        return (String)messages_accepted.get(msg);
    }


    public String Display()
    {
        StringBuffer s = new StringBuffer("messages accepted:\n");

        Enumeration descriptions = messages_accepted.elements();

        while(descriptions.hasMoreElements())
        {
            String descr = (String)descriptions.nextElement();
            s.append(descr + "\n");
        }

        return s.toString();
    }


    protected SchemeObject accept_message(String msg, SchemeObject args)
        throws SchemeException
    {
        return False;
    }


    public final SchemeObject AcceptMessage(String msg, SchemeObject args)
        throws SchemeException
    {
        if(!valid_message(msg))
            throw new SchemeException(SchemeException.MSG_NOT_ACCEPTED + msg);
        else
            return accept_message(msg, args);
    }
}
