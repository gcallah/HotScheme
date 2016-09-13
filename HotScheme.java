import java.awt.*;
import java.applet.Applet;

public class HotScheme extends Applet
{
	public void init()
	{
		setBackground(new Color(212,212,192));
		setLayout(new GridLayout(1,0));
		add(new TopPanel(this));
	}

	public Frame getFrame(Container c)
	{
		if (c instanceof Frame || c == null)
			return((Frame)c);
		else
			return(getFrame(c.getParent()));
	}


    public static void main(String args[])
    {
        Frame hotFrame = new Frame("HotScheme");
        hotFrame.setLayout(new GridLayout(1,0));
        HotScheme hotScheme = new HotScheme();
        hotScheme.init();
        hotFrame.add(hotScheme);
        hotFrame.show();
    }
}

