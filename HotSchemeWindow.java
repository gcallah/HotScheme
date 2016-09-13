
import java.awt.Event;
import java.awt.Frame;


public class HotSchemeWindow extends Frame
{
    public HotSchemeWindow(String s)
    {
        super(s);
    }

    public boolean handleEvent(Event event)
    {
        if(event.id == Event.WINDOW_DESTROY)
        {
            dispose();
            return true;
        }

        return false;
    }
}