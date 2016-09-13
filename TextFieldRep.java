import java.awt.Component;
import java.awt.TextField;
import java.util.Hashtable;


class TextFieldRep extends JavaComponent
{
    private TextField MyTextField = null;
    private static Hashtable MyMessages = new Hashtable();


/**
this constructs the "factory" TextFieldRep, which will have its Apply
   method called to create instances the user will employ
*/
    public TextFieldRep()
    {
        messages_accepted = MyMessages;
        register_message("getText", "(getText)");
    }


/**
this constructor is only called by Apply
*/
    private TextFieldRep(String text, int cols)
    {
        messages_accepted = MyMessages;
        MyTextField = new TextField(text, cols);
    }


    public SchemeObject Apply(SchemeObject actuals, Environment env)
        throws SchemeException
    {
        return new TextFieldRep((actuals.first()).StringVal(),
            (int)(actuals.second()).IntVal());
    }


    protected SchemeObject accept_message(String msg, SchemeObject args)
         throws SchemeException
    {
        if(msg.equals("getText"))
        {
            return SchemeObject.make(MyTextField.getText());
        }

        return True;
    }


    protected Component ComponentVal()
    {
        return MyTextField;
    }
}
