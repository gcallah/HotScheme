

/**
StringRep is the representation of the Scheme string type.
At present it implements the Scheme commands <code>string?</code>,
<code>equal?</code>,
and <code>substring</code>.
@see TRep
*/
class StringRep extends TRep
{
    StringRep(StringBuffer s)   { val = s; }
    StringRep(String s)         { val = new StringBuffer(s); }

    public String Write()
    {
        return("\"" + val.toString() + "\"");
    }


    public String Display()
    {
        return(val.toString());
    }


    public boolean Stringp()
    {
        return true;
    }


	public boolean Equal(SchemeObject obj)
	{
	    try
	    {
	        return(val.toString()).equals(obj.StringVal());
	    }
	    catch(SchemeException e)
	    {
	        return false;
	    }
	}


    public String StringVal() throws SchemeException
    {
        return val.toString();
    }


    public SchemeObject string_list()
        throws SchemeException
    {
        return string_list_aux(val.length() - 1, SchemeObject.False);
    }

    private SchemeObject string_list_aux(int buf_pos, SchemeObject list)
        throws SchemeException
    {
        if(buf_pos < 0) return list;
        else
        {
            SchemeObject new_list =
                SchemeObject.cons(
                    SchemeObject.make(val.charAt(buf_pos)),
                    list);
            return string_list_aux(buf_pos - 1, new_list);
        }
    }


    protected SchemeObject StringSet(int n, char c)
    {
        val.setCharAt(n, c);
        return this;
    }

    private StringBuffer val;
}
