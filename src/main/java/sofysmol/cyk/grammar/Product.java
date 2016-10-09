package sofysmol.cyk.grammar;

import sofysmol.cyk.utils.ListHelper;
import sofysmol.cyk.utils.StringHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sofysmo on 09.10.16.
 */
public class Product {
    private NTerminal source;
    private List<Symbol> result;

    public Product(NTerminal source, List<Symbol> result) {
        this.source = source;
        this.result = result;//new ArrayList<List<Symbol>>();
    }

    public boolean containsNTerm(){
        for(Symbol s: result)
            if(s instanceof NTerminal)
                return true;
        return false;
    }

    public boolean containsTerm(){
        for(Symbol s: result)
            if(s instanceof Terminal)
                return true;
        return false;
    }

    public NTerminal getSource(){
        return source;
    }

    public List<Symbol> getResults(){
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        if (getSource() != null ? !getSource().equals(product.getSource()) : product.getSource() != null) return false;
        return getResults() != null ? ListHelper.equals(product.getResults(), getResults()): product.getResults() == null;

    }

    @Override
    public int hashCode() {
        int result = getSource() != null ? getSource().hashCode() : 0;
        result = 31 * result + (getResults() != null ? getResults().hashCode() : 0);
        return result;
    }
}
