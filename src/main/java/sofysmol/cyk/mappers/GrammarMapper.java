package sofysmol.cyk.mappers;

import sofysmol.cyk.exceptions.BadInputException;
import sofysmol.cyk.grammar.Grammar;
import sofysmol.cyk.grammar.NTerminal;
import sofysmol.cyk.grammar.Product;
import sofysmol.cyk.grammar.Terminal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sofysmo on 09.10.16.
 */
public class GrammarMapper {
    public static Grammar parse(String[] nterms, String[] terms,
                                String[] starts, String[] products){
        List<NTerminal> ntermGr = new ArrayList<>();
        List<Terminal> termGr = new ArrayList<>();
        List<Product> productsGr = new ArrayList<>();
        List<NTerminal> startsGr = new ArrayList<>();
        for(String nt: nterms) {
            ntermGr.add(new NTerminal(nt));
        }
        for(String t: terms){
            termGr.add(new Terminal(t));
        }
        for(String s: starts){
            int i = ntermGr.indexOf(new NTerminal(s));
            if(i>=0)
                startsGr.add(ntermGr.get(i));
            else throw new BadInputException("Bad start nterm "+s);
        }
        for(String p: products){
            productsGr.add(ProductMapper.parse(p, ntermGr, termGr));
        }
        return new Grammar(ntermGr,termGr,productsGr,startsGr);
    }
}
