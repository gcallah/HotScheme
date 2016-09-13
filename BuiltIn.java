

abstract class BuiltIn extends FuncRep
{
    BuiltIn()
    {
        print_name = "Built-in";
    }


// Apply() here is taken to be the identity function.
//  I'm not sure I remember why!
    public SchemeObject Apply(SchemeObject actuals, Environment env)
        throws SchemeException
    {
        return actuals;
    }


    public String Write()
    {
        return print_name;
    }


    public String Display()
    {
        return Write() + ":\n";
    }
}
