
/**
* functor to call eqv on args
*/
class Eqv extends BuiltIn
{
    public Eqv()
    {
        super();
        print_name = "eqv";
    }
    
    
    public final SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        return SchemeObject.make((args.first()).Eqv(args.second()));
    }


    public final String Write()
    {
        return print_name;
    }
    
    
    public final String Display()
    {
        return "(" + print_name + "? obj1 obj2)\n"
            + "returns: true if obj1 and obj2 are eq?, "
            + "or if they are characters or numbers with the same value\n"
            + "; false otherwise";
    }
}
