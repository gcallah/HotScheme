

class List extends TRep
{
    List(SchemeObject x, SchemeObject ls)
    {
        car = x;
        cdr = ls;
    }


    List(LispTerminal lisp_term, SchemeObject ls, Environment penv)
        throws SchemeException
    {
        car = SchemeObject.make(lisp_term, START, penv);
        cdr = ls;
    }


    List(LispTerminal lisp_term, Environment penv)
        throws SchemeException
    {
        car = SchemeObject.make(lisp_term, START,     penv);
        cdr = SchemeObject.make(lisp_term, LIST_REST, penv);
    }


    public String Write()
    {
        if(car.Nullp()) return car.Write();
        else            return "( " + aux_write() + " )";
    }

    protected String aux_write()
    {
        StringBuffer s = new StringBuffer(car.Write());

    	if(!cdr.Nullp())
	    {
		    s.append(" ");
    		if(cdr.Listp()) s.append(cdr.aux_write());
	    	else            s.append(". " + cdr.Write());
    	}

        return s.toString();
    }


    public boolean Atomp()
    {
        return false;
    }


    public boolean Listp()
    {
        return true;
    }


	public boolean Equal(SchemeObject obj)
	{
        try
        {
            return(car.Equal(obj.first()) && cdr.Equal(obj.restl()));
        }
        catch(SchemeException e)
        {
            LispInterpreter.trace_print("Equal returning false after catching list exception.");
            return false;
        }
	}


    public void SetEnv(Environment old_env, Environment new_env)
        throws SchemeException
    {
        car.SetEnv(old_env, new_env);
        cdr.SetEnv(old_env, new_env);
    }


    public SchemeObject Eval(Environment env)
        throws SchemeException
    {
        if(car == null || cdr == null)
            throw new SchemeException(SchemeException.BAD_LIST_PTR);

        SchemeObject f = car.Eval(env);

// evaluate syntactical forms forms first:
        if(f instanceof SyntacticalForm)
        {
// pass the args to syntactical forms without evaluating them!
            if(LispInterpreter.getTraceState())
                LispInterpreter.trace_print("Applying " + car.Write()
                    + " to " + cdr.Write());

            return f.Apply(cdr, env);
        }
// else ordinary function application:
        else
        {
            if(LispInterpreter.getTraceState())
                LispInterpreter.trace_print("Applying " + car.Write()
                    + " to " + cdr.Write());

            return f.Apply(cdr.Evargs(env), env);
        }
    }


    public final SchemeObject first() throws SchemeException
    {
        return car;
    }


    public final SchemeObject second() throws SchemeException
    {
        return cdr.first();
    }


    public final SchemeObject third() throws SchemeException
    {
        return cdr.second();
    }


    public final SchemeObject fourth() throws SchemeException
    {
        return cdr.third();
    }


    public final SchemeObject ListRef(int n) throws SchemeException
    {
        if(n == 0) return car;
        else       return cdr.ListRef(n - 1);
    }


    public final SchemeObject restl() throws SchemeException
    {
        return cdr;
    }


    public SchemeObject set_car(SchemeObject obj) throws SchemeException
    {
        return car = obj;
    }


    public SchemeObject set_cdr(SchemeObject obj) throws SchemeException
    {
        return cdr = obj;
    }


    public final long Length() throws SchemeException
    {
        if(car == null || cdr == null)
            throw new SchemeException(SchemeException.BAD_LIST_PTR);

// the last object will be #f -- see Length there!
        return 1L + cdr.Length();
    }


    public String ListString()
        throws SchemeException
    {
        StringBuffer s = new StringBuffer("");
        return ListStringAux(s, this);
    }

    private String ListStringAux(StringBuffer s,
        SchemeObject ls)
        throws SchemeException
    {
        if(ls.Nullp()) return s.toString();
        else
        {
            s.append((ls.first()).CharVal());
            return ListStringAux(s, ls.restl());
        }
    }


    private SchemeObject car;
    private SchemeObject cdr;
}

