import java.util.Hashtable;
import java.net.URL;


/**
Java URL objects get wrapped in LayoutRep
*/
class URLRep extends JavaObject
{
    private URL    MyURL;
    private static Hashtable MyMessages = new Hashtable();


    public URLRep()
    {
        messages_accepted = MyMessages;
        register_message("getContent",  "(getContent)");
        register_message("getFile",     "(getFile)");
        register_message("getHost",     "(getHost)");
        register_message("getPort",     "(getPort)");
        register_message("getProtocol", "(getProtocol)");
        register_message("getRef",      "(getRef)");
    }


/**
this constructor is only called by Apply
*/
    private URLRep(String url)
        throws SchemeException
    {
        messages_accepted = MyMessages;
        try
        {
            MyURL = new URL(url);
        }
        catch(java.net.MalformedURLException e)
        {
        }
    }


    public SchemeObject Apply(SchemeObject actuals, Environment env)
        throws SchemeException
    {
        return new URLRep((actuals.first()).StringVal());
    }


    protected SchemeObject accept_message(String msg, SchemeObject args)
         throws SchemeException
    {
        if(msg.equals("getContent"))
        {
            if(MyURL.getProtocol().equals(HTTP))
            {
                try
                {
                    return SchemeObject.make((String)(MyURL.getContent()));
                }
                catch(java.io.IOException e)
                {
                }
            }
            else
                return SchemeObject.make("This protocol is not yet handled.");
        }
        else if(msg.equals("getFile"))
        {
            return SchemeObject.make(MyURL.getFile());
        }
        else if(msg.equals("getHost"))
        {
            return SchemeObject.make(MyURL.getHost());
        }
        else if(msg.equals("getPort"))
        {
            return SchemeObject.make(MyURL.getPort());
        }
        else if(msg.equals("getProtocol"))
        {
            return SchemeObject.make(MyURL.getProtocol());
        }
        else if(msg.equals("getRef"))
        {
            String s = MyURL.getRef();
            if(s == null) return SchemeObject.make("");
            else          return SchemeObject.make(MyURL.getRef());
        }

// this return just shuts the compiler up!
        return True;
    }

    private static final String HTTP = "http";
}

