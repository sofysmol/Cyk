package sofysmol.cyk.grammar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by sofysmo on 08.10.16.
 */
public class Grammar {
    private List<NTerminal> nterms;
    private List<Terminal> terms;
    private List<Product> products;
    private List<NTerminal> starts;

    public Grammar( List<NTerminal> nterms, List<Terminal> terms, List<Product> products, List<NTerminal> starts){
        this.nterms = nterms;
        this.terms = terms;
        this.products = products;
        this.starts = starts;
    }
    public List<Product> getProductsWithNTerm() {
        List<Product> result = new ArrayList<>();
        for(Product p : products)
            if(p.containsNTerm())
                result.add(p);
        return result;
    }
    public List<NTerminal> getStarts()
    {
        return starts;
    }

    public List<NTerminal> getNterms() {
        return nterms;
    }

    public List<Terminal> getTerms() {
        return terms;
    }

    public List<Product> getProducts() {
        return products;
    }
}
