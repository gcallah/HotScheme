
/**
TRep is the representation of the Scheme value true.
All other Scheme types except FRep descend from it.
It is instantiated as a singleton.
@see SchemeObject
@see FRep
*/

class TRep extends SchemeObject
{
// selectors:
    public String Write()
    {
        return(LispInterpreter.T_NAME);
    }


    public boolean Not()
    {
        return false;
    }


    public boolean Nullp()
    {
        return false;
    }
}
