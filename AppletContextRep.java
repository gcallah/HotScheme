import java.applet.AppletContext;
import java.util.Hashtable;
import java.net.URL;


/**
AppletContextRep and AppletRep do not implement Apply,
  as it only makes sense to have one of each, and a global
  instance is constructed in LispInterpreter.
*/
class AppletContextRep extends JavaObject
{
    private AppletContext MyContext = null;
    private static Hashtable MyMessages = new Hashtable();


    public AppletContextRep(AppletContext a)
    {
        MyContext = a;

        messages_accepted = MyMessages;
        register_message("showDocument", "(showDocument string)");
    }


    protected SchemeObject accept_message(String msg, SchemeObject args)
        throws SchemeException
    {
        if(msg.equals("showDocument"))
        {
            try
            {
                if(args.Length() == 1)
                    MyContext.showDocument(new URL((args.first()).StringVal()));
                else
                    MyContext.showDocument(new URL((args.first()).StringVal()), args.second().StringVal());
            }
            catch(java.net.MalformedURLException e)
            {
                throw new SchemeException(e.getMessage());
            }
        }
        return True;
    }
}
