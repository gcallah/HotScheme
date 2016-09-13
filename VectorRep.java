import java.util.Vector;


/**
* the class which is the base of all numbers
*/
class VectorRep extends TRep
{

    VectorRep(LispTerminal lisp_term, Environment penv)
        throws SchemeException,
            ArrayIndexOutOfBoundsException
    {
        v = new Vector();

        try
        {
            for(int i = 0; true; i++)
            {
                v.insertElementAt(
                    SchemeObject.make(lisp_term, START, penv),
                    i);
            }
        }
        catch(UnmatchedParenExcep excep)
        {
        }
    }


    public final boolean Vectorp()
    {
        return true;
    }


	public final boolean Eqv(SchemeObject obj)
	{
        boolean b = false;

	    if(!(obj.Vectorp())) return false;
	    else
	    {
            return true;
	    }
	}


    public final int VectorLength()
        throws SchemeException
    {
        return v.size();
    }


    public final void VectorSet(int n, SchemeObject obj)
        throws SchemeException
    {
        v.setElementAt(obj, n);
    }


    public final SchemeObject VectorRef(int n)
        throws SchemeException
    {
        return (SchemeObject)(v.elementAt(n));
    }


    private Vector v;
}

