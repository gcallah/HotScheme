import java.util.Vector;


class GUILispTerminal extends BufferedLispTerminal
{
	private Vector  m_vOutput;
	private boolean debug = false;


    public GUILispTerminal()
    {
		m_vOutput = new Vector(5);
	}


	private void print(String s)
	{
		m_vOutput.addElement(s);
		if(debug)
		{
		    System.out.println("GUILispTerminal::print Vector is " + m_vOutput);
		    System.out.println("GUILispTerminal::print " + s);
		}
	}


	public void setInput(String s)
	{
        setBuffer(s);
		if(debug) System.out.println("GUILispTerminal::setInput " + s); //debug
	}


	public Vector getOutput()
	{
		if(debug) System.out.println("GUILispTerminal::getOutput " + m_vOutput); //debug

		Vector vTemp = (Vector)m_vOutput.clone();
		m_vOutput.setSize(0);
		return(vTemp);
	}


    public void my_print(Object obj)
    {
		print(obj.toString());
    }


    public void my_println(Object obj)
    {
		print(obj.toString() + "\n");
	}
}
