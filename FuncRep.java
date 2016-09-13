

abstract class FuncRep extends TRep
{
    protected String print_name = "#<procedure>";

    public String Display()
    {
        return print_name;
    }


    public boolean Procedurep()
    {
        return true;
    }
}

