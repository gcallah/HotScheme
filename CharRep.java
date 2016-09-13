

/**
CharRep is the representation of the Scheme character type.
@see TRep
*/
class CharRep extends TRep
{
    CharRep(char c)         { val = c; }


    public String Write()
    {
        return("#\\" + (new Character(val)).toString());
    }


    public String Display()
    {
        return((new Character(val)).toString());
    }


    public boolean Charp()
    {
        return true;
    }


    public char CharVal() throws SchemeException
    {
        return val;
    }


	public boolean Eqv(SchemeObject obj)
	{
        try
        {
            return(obj.CharVal() == val);
        }
        catch(SchemeException e)
        {
            return false;
        }
	}


    private char val;
}
