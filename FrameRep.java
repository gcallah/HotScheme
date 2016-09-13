import java.util.Hashtable;
import java.awt.Component;


class FrameRep extends JavaComponent
{
    private HotSchemeWindow  MyFrame    = null;
    private static Hashtable MyMessages = new Hashtable();


/**
this constructs the "factory" FrameRep, which will have its Apply
  method called to create instances the user will employ
*/
    public FrameRep()
    {
        messages_accepted = MyMessages;
        register_message("show",      "(show)");
        register_message("add",       "(add component)");
        register_message("setLayout", "(setLayout LayoutManager)");
        register_message("toBack",    "(toBack)");
        register_message("toFront",   "(toFront)");
    }


/**
this constructor is only called by Apply
*/
    private FrameRep(String title, int Width, int Height)
    {
        messages_accepted = MyMessages;
        MyFrame = new HotSchemeWindow(title);
        MyFrame.setEnabled(true);
    }


    public SchemeObject Apply(SchemeObject actuals, Environment env)
        throws SchemeException
    {
        return new FrameRep((actuals.first()).StringVal(),
            (int)(actuals.second()).IntVal(), (int)(actuals.third()).IntVal());
    }


    protected SchemeObject accept_message(String msg, SchemeObject args)
         throws SchemeException
    {
        if(msg.equals("show"))
        {
            MyFrame.show();
        }
        else if(msg.equals("add"))
        {
            SchemeObject FirstArg = args.first();
            if(FirstArg instanceof JavaComponent)
                MyFrame.add(((JavaComponent)FirstArg).ComponentVal());
            else
                throw new SchemeException(SchemeException.NOT_A_COMPONENT + FirstArg.Display());
        }
        else if(msg.equals("setLayout"))
        {
            SchemeObject FirstArg = args.first();
            if(FirstArg instanceof LayoutRep)
                MyFrame.setLayout(((LayoutRep)FirstArg).LayoutVal());
            else
                throw new SchemeException(SchemeException.NOT_A_LAYOUT + FirstArg.Display());
        }
        else if(msg.equals("toFront"))
        {
            MyFrame.toFront();
        }
        else if(msg.equals("toBack"))
        {
            MyFrame.toBack();
        }
        return True;
    }


    protected Component ComponentVal()
    {
        return MyFrame;
    }
}


