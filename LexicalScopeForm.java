

// descendants of this class are syntactical forms that create a lexical scope
abstract class LexicalScopeForm extends SyntacticalForm
{
    public LexicalScopeForm(LispTerminal lisp_term, Environment penv,
        String name)
        throws SchemeException
    {
        super(name);

        local_env = new Environment(penv, name);
    }


    public void SetEnv(Environment old_env, Environment new_env)
        throws SchemeException
    {
        if(local_env.GetParentEnv() == old_env) local_env.SetParentEnv(new_env);
    }


// this protected func is essentially a macro!
    protected static final SchemeObject my_cons(SchemeObject car, SchemeObject cdr)
        throws SchemeException
    {
        try
        {
            return SchemeObject.cons(car, cdr);
        }
        catch(NotAListExcp excp)
        {
            excp.setExcpType("Not a list error in LexicalScope.cons(): car: "
                + car.Write() + "; cdr: " + cdr.Write());
            throw excp;
        }
    }


    protected static final SchemeObject my_append(SchemeObject list, SchemeObject item)
        throws SchemeException
    {
        list.set_cdr(SchemeObject.cons(item, False));
        return list;
    }


    protected static final SchemeObject list_of_nth_items(SchemeObject new_list,
            SchemeObject list_of_lists, int n)
        throws SchemeException
    {
        if(list_of_lists.Nullp())
            return new_list;
        else
        {
            try
            {
                if(new_list.Nullp())
                    return list_of_nth_items(
                        SchemeObject.cons((list_of_lists.first()).ListRef(n),
                                False),
                            list_of_lists.restl(),
                            n);
                else
                    return list_of_nth_items(
                        my_append(new_list, (list_of_lists.first()).ListRef(n)),
                            list_of_lists.restl(),
                            n);
            }
            catch(NotAListExcp excp)
            {
                excp.setExcpType("Not a list recursing in list_of_nth_items with formals: "
                    + new_list.Write() + "; vardefs: "
                    + list_of_lists.Write() + "; (car list_of_lists): "
                    + (list_of_lists.first()).Write() + "; ");
                throw excp;
            }
        }
    }


    protected Environment  local_env  = null;
}
