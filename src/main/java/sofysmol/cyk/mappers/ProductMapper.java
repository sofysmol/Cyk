package sofysmol.cyk.mappers;

import sofysmol.cyk.exceptions.BadInputException;
import sofysmol.cyk.grammar.NTerminal;
import sofysmol.cyk.grammar.Product;
import sofysmol.cyk.grammar.Symbol;
import sofysmol.cyk.grammar.Terminal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sofysmo on 09.10.16.
 */
public class ProductMapper {
    public static Product parse(String p, List<NTerminal> nterm, List<Terminal> term){
        String[] s = p.split("->");
        NTerminal source;
        List<Symbol> result = new ArrayList<>();
        int i = nterm.indexOf(new NTerminal(s[0]));
        if(i>=0)
            source = nterm.get(i);
        else throw new BadInputException("Bad product"+p);
        String[] res = s[1].split("\\+");
        for(String r: res){
            i = nterm.indexOf(new NTerminal(r));
            if(i>=0)
                result.add(nterm.get(i));
            else{
                i = term.indexOf(new Terminal(r));
                if(i>=0) {
                    result.add(term.get(i));
                }
                else throw new BadInputException("Bad product "+p);
            }
        }
        return new Product(source, result);
    }
}
