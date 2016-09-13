import java.awt.Component;
import java.awt.Label;
import java.util.Hashtable;


class LabelRep extends JavaComponent
{
    private Label MyLabel = null;
    private static Hashtable MyMessages = new Hashtable();


/**
this constructs the "factory" LabelRep, which will have its Apply
 method called to create instances the user will employ
*/
    public LabelRep()
    {
        messages_accepted = MyMessages;
        register_message("getText", "(getText)");
    }


/**
this constructor is only called by Apply
*/
    private LabelRep(String text)
    {
        messages_accepted = MyMessages;
        MyLabel = new Label(text);
    }


    public SchemeObject Apply(SchemeObject actuals, Environment env)
        throws SchemeException
    {
        return new LabelRep((actuals.first()).StringVal());
    }


    protected SchemeObject accept_message(String msg, SchemeObject args)
         throws SchemeException
    {
        if(msg.equals("getText"))
        {
            return SchemeObject.make(MyLabel.getText());
        }

        return True;
    }


    protected Component ComponentVal()
    {
        return MyLabel;
    }
}
