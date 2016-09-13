import java.awt.LayoutManager;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Hashtable;


/**
Java awt layouts get wrapped in LayoutRep
*/
class LayoutRep extends JavaObject
{
    private LayoutManager MyLayoutManager;
    private static Hashtable MyMessages = new Hashtable();


    public LayoutRep()
    {
        messages_accepted = MyMessages;
    }


/**
this constructor is only called by Apply
*/
    private LayoutRep(String LayoutType, SchemeObject other_args)
        throws SchemeException
    {
        if(LayoutType.equals("Grid"))
            MyLayoutManager =
                new GridLayout((int)(other_args.first()).IntVal(), (int)(other_args.second()).IntVal());
        else if(LayoutType.equals("Card"))
            MyLayoutManager =
                new CardLayout((int)(other_args.first()).IntVal(), (int)(other_args.second()).IntVal());
        else if(LayoutType.equals("Flow"))
            MyLayoutManager =
                new CardLayout((int)(other_args.first()).IntVal(), (int)(other_args.second()).IntVal());
    }


    public SchemeObject Apply(SchemeObject actuals, Environment env)
        throws SchemeException
    {

        return new LayoutRep((actuals.first()).StringVal(), actuals.restl());
    }


    protected LayoutManager LayoutVal()
    {
        return MyLayoutManager;
    }
}
