
import java.lang.Character;

/**
* a syntactic unit in HotScheme
*/
class SchemeToken
{

/**
* constructs a token off of terminal input
*/
    public SchemeToken(LispTerminal lisp_term)
        throws SchemeException
    {
        int state = START;

        while(state != DONE)
        {
            int i = lisp_term.read();
            char c;

            if(i <= 0)
            {
                switch(state)
                {
                    case COLLECT_NUMBER:
                        type  = INT;
                        state = DONE;
                        return;
                    case COLLECT_FLOAT:
                        type  = RATIONAL;
                        state = DONE;
                        return;
                    case COLLECT_SYMBOL:
                        type  = SYMBOL;
                        state = DONE;
                        return;
                    default: throw new SchemeException(SchemeException.UNEXPECTED_EOI);
                }
            }
            else c = (char)i;

            switch(state)
            {
                case START:
                    switch(c)
                    {
                        case ';':
                            state = EAT_COMMENT;
                            break;
                        case '"':
                            state = COLLECT_STRING;
                            break;
                        case '#':
                            state = CHAR_OR_VECTOR;
                            break;
                        case '.':
                            text.append(c);
                            state = COLLECT_FLOAT;
                            break;
                        case '-':
                            text.append(c);
                            state = COLLECT_NEG_NUMBER;
                            break;
                        case '(':
                            text.append(c);
                            type  = OPEN_PAREN;
                            state = DONE;
                            break;
                        case ')':
                            text.append(c);
                            type  = CLOSE_PAREN;
                            state = DONE;
                            break;
                        case QUOTE_CHAR:
                            text.append(c);
                            type  = QUOTE_MACRO;
                            state = DONE;
                            break;
                        default:
                            if(Character.isDigit(c))
                            {
                                text.append(c);
                                state = COLLECT_NUMBER;
                            }
                            else if(is1stSymbolChar(c))
                            {
                                text.append(c);
                                state = COLLECT_SYMBOL;
                            }
                            else if(!Character.isWhitespace(c))
                            {
                                StringBuffer excep = new StringBuffer(
                                    SchemeException.INVALID_CHAR);
                                excep.append(c);
                                throw new SchemeException(excep.toString());
                            }
                    }
                    break;
// we found a '-' -- now, if next char is a digit or '.', we have a negative number
//  otherwise, a symbol
                case COLLECT_NEG_NUMBER:
                    if(Character.isDigit(c))
                    {
                        text.append(c);
                        state = COLLECT_NUMBER;
                    }
                    else if(c == '.')
                    {
                        text.append(c);
                        state = COLLECT_FLOAT;
                    }
                    else
                    {
                        lisp_term.unread(c);
                        state = COLLECT_SYMBOL;
                    }
                    break;
                case COLLECT_NUMBER:
                    if(Character.isDigit(c)) text.append(c);
                    else if(c == '.')
                    {
                        text.append(c);
                        state = COLLECT_FLOAT;
                    }
                    else
                    {
                        lisp_term.unread(c);
                        type  = INT;
                        state = DONE;
                    }
                    break;
                case COLLECT_FLOAT:
                    if(Character.isDigit(c))
                    {
                        text.append(c);
                    }
                    else
                    {
                        lisp_term.unread(c);
                        type  = RATIONAL;
                        state = DONE;
                    }
                    break;
                case COLLECT_STRING:
                    switch(c)
                    {
                        case '"':
                            type  = STRING;
                            state = DONE;
                            break;

                        default:
                            text.append(c);
                    }
                    break;
                case CHAR_OR_VECTOR:
                    switch(c)
                    {
                        case '\\':
                            state = COLLECT_CHAR;
                            break;
                        case '(':
                            type  = VECTOR_OPEN;
                            state = DONE;
                            break;
                        case 't':
                        case 'f':
                            text.append('#');
                            text.append(c);
                            state = COLLECT_SYMBOL;
                            break;
                        default:
                            StringBuffer excep = new StringBuffer(
                                SchemeException.INVALID_CHAR);
                            excep.append(c);
                            throw new SchemeException(excep.toString());
                    }
                    break;
                case COLLECT_CHAR:
                    text.append(c);
                    type  = CHAR;
                    state = DONE;
                case COLLECT_SYMBOL:
                    if(isSymbolChar(c)) text.append(c);
                    else
                    {
                        lisp_term.unread(c);
                        type  = SYMBOL;
                        state = DONE;
                    }
                    break;
                case EAT_COMMENT:
                    if(c == '\n') state = START;
                    break;
            }
        }
    }

    public final int getType()    { return type; }
    public final String getText() { return new String(text); }

// special chars:
    public static final int QUOTE_CHAR      = '\'';

// token types (these are given semi-mnemonic values for debugging):
    public static final int CHAR            = 'A';
    public static final int CLOSE_PAREN     = 'C';
    public static final int INT             = 'I';
    public static final int OPEN_PAREN      = 'O';
    public static final int QUIT            = 'Q';
    public static final int RATIONAL        = 'R';
    public static final int STRING          = 'S';
    public static final int SYMBOL          = 'Y';
    public static final int VECTOR_OPEN     = 'V';
    public static final int QUOTE_MACRO     = '\'';

// state variables:
    public static final int START               = 0;
    public static final int COLLECT_NUMBER      = 1;
    public static final int COLLECT_STRING      = 2;
    public static final int COLLECT_SYMBOL      = 3;
    public static final int COLLECT_FLOAT       = 4;
    public static final int EAT_COMMENT         = 6;
    public static final int CHAR_OR_VECTOR      = 7;
    public static final int COLLECT_CHAR        = 8;
    public static final int COLLECT_NEG_NUMBER  = 9;
    public static final int DONE                = -1;


/**
*   is the character in question a valid char to be 1st in a symbol?
*/
    private boolean is1stSymbolChar(char c)
    {
        return Character.isUpperCase(c) || Character.isLowerCase(c)
                || c == '+' || c == '-' || c == '*' || c == '/'
                || c == '>' || c == '<';
    }

/**
*   is the character in question a valid char in a symbol?
*/
    private boolean isSymbolChar(char c)
    {
        return is1stSymbolChar(c) || Character.isDigit(c)
                || c == '_' || c == '-' || c == '/' || c == '?' || c == '!'
                || c == '>' || c == '<';
    }


    private boolean      debug = false;
    private int          type;
    private StringBuffer text = new StringBuffer("");
}