import java.applet.Applet;
import java.util.Hashtable;


class AppletRep extends JavaObject
{
    private Applet MyApplet = null;
    private static Hashtable MyMessages = new Hashtable();


    public AppletRep(Applet a)
    {
        MyApplet = a;

        messages_accepted = MyMessages;
        register_message("getCodeBase",     "(getCodeBase)");
        register_message("getDocumentBase", "(getDocumentBase)");
        register_message("showStatus",      "(showStatus string)");
        register_message("resize",          "(resize width height)");
    }


    protected SchemeObject accept_message(String msg, SchemeObject args)
         throws SchemeException
    {
        if(msg.equals("getCodeBase"))
        {
            return SchemeObject.make((
                MyApplet.getCodeBase()).toString());
        }
        else if(msg.equals("getDocumentBase"))
        {
            return SchemeObject.make((
                MyApplet.getDocumentBase()).toString());
        }
        else if(msg.equals("showStatus"))
        {
            MyApplet.showStatus((args.first()).StringVal());
            return True;
        }
        else if(msg.equals("resize"))
        {
            MyApplet.resize((int)(args.first()).IntVal(), (int)(args.second()).IntVal());
            return True;
        }
        return False;
    }
}
