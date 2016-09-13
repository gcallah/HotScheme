import java.awt.Component;
import java.awt.Button;
import java.util.Hashtable;


class ButtonRep extends JavaComponent
{
    private Button MyButton = null;
    private static Hashtable MyMessages = new Hashtable();


/**
this constructs the "factory" ButtonRep, which will have its Apply
   method called to create instances the user will employ
*/
    public ButtonRep()
    {
        messages_accepted = MyMessages;
        register_message("getLabel", "(getLabel)");
    }


/**
this constructor is only called by Apply
*/
    private ButtonRep(String label, int Width, int Height)
    {
        messages_accepted = MyMessages;
        MyButton = new Button(label);
    }


    public SchemeObject Apply(SchemeObject actuals, Environment env)
        throws SchemeException
    {
        return new ButtonRep((actuals.first()).StringVal(),
            (int)(actuals.second()).IntVal(), (int)(actuals.third()).IntVal());
    }


    protected SchemeObject accept_message(String msg, SchemeObject args)
         throws SchemeException
    {
        if(msg.equals("getLabel"))
        {
            return SchemeObject.make(MyButton.getLabel());
        }

        return True;
    }

    
    protected Component ComponentVal()
    {
        return MyButton;
    }
}
