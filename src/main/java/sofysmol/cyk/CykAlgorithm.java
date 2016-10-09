package sofysmol.cyk;

import sofysmol.cyk.grammar.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sofysmo on 09.10.16.
 */
public class CykAlgorithm {

    private boolean[][][] d;

    public boolean[][][] getTable()
    {
        return d;
    }

    public boolean check(Grammar grammar, String word){
        int n = word.length();
        int r = grammar.getNterms().size();
        d = new boolean[n][n][r];
        for(int i = 0; i<n; i++){
            for(int j = 0 ; j<r; j++){
                List<Symbol> res = new ArrayList<>();
                res.add((Symbol)(new Terminal(word.charAt(i))));
                Product pr = new Product(grammar.getNterms().get(j), res);
                if(grammar.getProducts().contains(pr))
                    d[0][i][j] = true;
            }
        }
        List<Product> products = grammar.getProductsWithNTerm();
        for(int i = 2; i<=n; i++) {
            for(int j = 1; j<=n-i+1; j++)
                for(int k = 1; k<=i-1; k++)
                    for(Product p: products){
                        int a = grammar.getNterms().indexOf(p.getSource());
                        int b = grammar.getNterms().indexOf(p.getResults().get(0));
                        int c = grammar.getNterms().indexOf(p.getResults().get(1));
                        if(d[k-1][j-1][b] && d[i-k-1][j+k-1][c])
                            d[i-1][j-1][a] = true;
                    }
        }
        for(NTerminal x: grammar.getStarts()){
            int i = grammar.getNterms().indexOf(x);
            if(d[n-1][0][i]) return true;
        }
        return false;
    }
}
