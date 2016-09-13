/**
* tokens for syntactical forms:
*/
class SyntacticalForm extends TRep
{
    private String print_name;

    protected SyntacticalForm(String s)
    {
        print_name = s;
    }


    public String Write()
    {
        return print_name;
    }
}

