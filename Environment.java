
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Stack;


public class Environment extends HotSchemeInternalRep implements Cloneable
{
    private Hashtable   sym_table      = new Hashtable();
    private Environment parent_env     = null;
    private static int  env_no         = 0;
    private int         my_env_no;
    private Stack       paramStack     = new Stack();
    private String      pkg_name       = null;

    public Environment(Environment penv)
    {
        super();

        init(penv);
    }


    public Environment(Environment penv, String nm)
    {
        super();
        init(penv);
        pkg_name = nm;
    }


    private void init(Environment penv)
    {
        my_env_no = env_no++;  // just a distinct thing we can print

        if(penv != null) parent_env = penv;
    }
    
    
    public final Object clone()
    {
        Environment new_env = new Environment(parent_env, pkg_name);
        new_env.sym_table   = (Hashtable)sym_table.clone();
        return new_env;
    }

    
    public final void PushVars(SchemeObject names, SchemeObject vals)
        throws SchemeException
    {
        Hashtable params = new Hashtable();
        if(!names.Nullp())
        {
            params.put(
                (names.first()).Write(),
                vals.first()
            );

            pushVarsAux(params, names.restl(), vals.restl());
        }
        paramStack.push(params);
        AssignList(names, vals);
    }


    private final void pushVarsAux(Hashtable params, SchemeObject names, SchemeObject vals)
        throws SchemeException
    {
        if(!names.Nullp())
        {
            params.put(
                (names.first()).Write(),
                vals.first()
            );

            pushVarsAux(params, names.restl(), vals.restl());
        }
    }

    
    public final void PopVars()
    {
        Hashtable old_params = (Hashtable)paramStack.pop();
        Enumeration e        = old_params.keys();
        while(e.hasMoreElements())
        {
            Object key = e.nextElement();
            sym_table.put(key, old_params.get(key));
        }
    }
    
    
    public final Environment GetParentEnv()
    {
        return parent_env;
    }


    public final void SetParentEnv(Environment penv)
    {
        parent_env = penv;
    }


    public final SchemeObject Eval(String name) throws SchemeException
    {
        if(LispInterpreter.getTraceState())
        {
            LispInterpreter.trace_print("looking up " + name + " in "
                + pkg_name + ":" + my_env_no);
        }

        Object val = sym_table.get(name);

        if(val != null)
        {
            if(LispInterpreter.getTraceState())
            {
                LispInterpreter.trace_print(name + " evaluated to "
                    + ((SchemeObject)val).Display()
                    + " in " + pkg_name + ":" +  + my_env_no);
            }
            return (SchemeObject)val;
        }
        else
        {
            if(parent_env == null)
                throw new SchemeException(SchemeException.SYM_NOT_FOUND + name);
            else
            {
                return parent_env.Eval(name);
            }
        }
    }


    public final SchemeObject Set(String name, SchemeObject val)
        throws SchemeException, QuitException
    {
        if(sym_table.containsKey(name))
        {
            sym_table.put(name, val);
            return val;
        }
        else
        {
            if(parent_env == null)
                throw new SchemeException(SchemeException.SYM_NOT_FOUND + name);
            else
                return parent_env.Set(name, val);
        }
    }


    public final SchemeObject Intern(String name, SchemeObject val)
        throws SchemeException, QuitException
    {
        if(sym_table.containsKey(name))
            throw new SchemeException(SchemeException.SYM_IN_ENV + name);

        sym_table.put(name, val);

        return val;
    }


    public final SchemeObject intern(SchemeObject name,
        SchemeObject val) throws SchemeException
    {
        if(!name.Symbolp()) throw new SchemeException(SchemeException.NOT_A_SYM);

        return Intern(name.Write(), val);
    }


    public final void AddList(SchemeObject names)
        throws SchemeException
    {
        if(!names.Nullp())
        {
            Intern((names.first()).Write() , False);

            AddList(names.restl());
        }
    }


    public final void AssignList(SchemeObject names, SchemeObject vals)
        throws SchemeException
    {
        if(!names.Nullp())
        {
            Set(
                (names.first()).Write(),
                vals.first()
            );

            AssignList(names.restl(), vals.restl());
        }
    }


    public final String Display()
    {
        String s1 = "Symbols in environment " + pkg_name + ":" +  + my_env_no + ":\n";
        String s2 = "";


        Enumeration names = sym_table.keys();
        while(names.hasMoreElements())
        {
            String name = (String)names.nextElement();
            SchemeObject val = (SchemeObject)sym_table.get(name);

            s2 = s2 + "Symbol: " + name + "= "
                + val.Display() + "\n\n";
        }
        return s1 + s2;
    }


    public final LispTerminal getTerm() throws SchemeException
    {
        return (LispTerminal)Eval(LispInterpreter.TERM_NAME);
    }
}
