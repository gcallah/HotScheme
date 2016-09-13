import java.lang.Exception;

public class SchemeException extends Exception
{
    protected SchemeObject  scheming_offender;
    private   String        excp_type = "";
    private   String        local_msg = "";

// public
// constructors:
    public SchemeException()
    {
        super(SCHEME_EXCP);
        excp_type = SCHEME_EXCP;
        local_msg = UNKNOWN_EXCP;
    }

    public SchemeException(String s)
    {
        super(SCHEME_EXCP);
        excp_type = SCHEME_EXCP;
        local_msg = s;
    }

    public SchemeException(String s, SchemeObject obj)
    {
        super(SCHEME_EXCP);
        excp_type         = SCHEME_EXCP;
        local_msg         = s;
        scheming_offender = obj;
    }


// selectors:
    public String Print()
    {
        return excp_type + local_msg
            + ((scheming_offender != null) ?
                scheming_offender.Write()
                : "");
    }


    public void setLocalMsg(String s) { local_msg = s; }

    public void setExcpType(String s) { excp_type = s; }

// scheme syntax errors:

    public static final String SCHEME_EXCP
        = "Exception: ";
    public static final String UNMATCHED_PAREN
        = "Unmatched closing parenthesis: ";
    public static final String INVALID_CHAR
        = "Invalid input character: ";
    public static final String NOT_A_FUNC
        = "Arg not a function: ";
    public static final String NOT_A_SYM
        = "Arg not a symbol: ";
    public static final String NOT_A_NUM
        = "Arg not a number: ";
    public static final String NOT_A_CHAR
        = "Arg not a character: ";
    public static final String NOT_A_STRING
        = "Arg not a string: ";
    public static final String NOT_A_VECTOR
        = "Arg not a vector: ";
    public static final String NOT_A_LIST
        = "Arg not a list: ";
    public static final String NOT_A_COMPONENT
        = "Arg not a component: ";
    public static final String NOT_A_LAYOUT
        = "Arg not a layout manager: ";
    public static final String UNEXPECTED_TOKEN
        = "Unexpected token in input";
    public static final String SYM_NOT_FOUND
        = "Symbol not found: ";
    public static final String SYM_IN_ENV
        = "Symbol already exists in env: ";
    public static final String UNEXPECTED_EOI
        = "Unexpected end of input";
    public static final String TOO_FEW_ARGS
        = "Too few args: ";
    public static final String TOO_MANY_ARGS
        = "Too many args: ";
    public static final String CAR_ON_EMPTY
        = "Can't call car on empty list: ";
    public static final String CDR_ON_EMPTY
        = "Can't call cdr on empty list: ";
    public static final String NOT_AN_OBJ
        = "Arg not a abstract object: ";
    public static final String MSG_NOT_ACCEPTED
        = "This object does not accept this method: ";

// internal errors:
    public static final String UNKNOWN_EXCP
        = "Unknown Scheme Exception thrown: ";
    public static final String BAD_LIST_PTR
        = "Bad list pointer: ";
    public static final String NULL_REP_PTR
        = "Attempt to access null rep pointer in: ";
    public static final String BAD_DEFINE
        = "Defines may only occur at the top level: ";
    public static final String LAMBDA_SYM
        = "Lambda formals must be symbols: ";
    public static final String NULL_PTR
        = "Unexpected null pointer in: ";
}