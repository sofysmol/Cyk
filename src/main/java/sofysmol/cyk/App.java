package sofysmol.cyk;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.vandermeer.asciitable.v2.RenderedTable;
import de.vandermeer.asciitable.v2.V2_AsciiTable;
import de.vandermeer.asciitable.v2.render.V2_AsciiTableRenderer;
import de.vandermeer.asciitable.v2.render.WidthAbsoluteEven;
import de.vandermeer.asciitable.v2.themes.V2_E_TableThemes;
import sofysmol.cyk.grammar.Grammar;
import sofysmol.cyk.grammar.NTerminal;
import sofysmol.cyk.mappers.GrammarMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by sofysmo on 09.10.16.
 */
public class App {

    public static void main(String[] argv){
        try {
            ObjectMapper mapper = new ObjectMapper();
            Input input = mapper.readValue(Files.readAllBytes(Paths.get(argv[0])), Input.class);
            Grammar grammar = GrammarMapper.parse(input.getNterms(),
                    input.getTerms(), input.getStarts(), input.getProductions());
            CykAlgorithm alg = new CykAlgorithm();
            if(alg.check(grammar,input.getWord()))
                System.out.println("\""+input.getWord()+"\" is a member of the language");
            else System.out.println("\"" + input.getWord() + "\" is not a member of the language");
            printTable(alg.getTable(), grammar.getNterms(), input.getWord());

        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
    private static void printTable(boolean[][][] d, List<NTerminal> nterm, String word) {
        int n = d.length;
        int m = d[0].length;
        int s = nterm.size();
        String[][] table = new String[n][m];
        for(int i = 0; i<n;i++)
            for(int j = 0; j<m; j++) {
                table[i][j] = "";
                for (int k = 0; k < s; k++)
                    if (d[i][j][k])
                        table[i][j] += nterm.get(k).getName() + " ";
            }
        V2_AsciiTable view = new V2_AsciiTable();
        for(int i = n-1; i>=0; i--) {
            view.addRule();
            view.addRow(table[i]);
        }
        view.addRule();
        view.addRow(word.split(""));
        view.addRule();
        V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
        rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
        rend.setWidth(new WidthAbsoluteEven(76));
        RenderedTable rt = rend.render(view);
        System.out.println(rt);
    }

    public static class Input{
        private String[] nterms;
        private String[] terms;
        private String[] productions;
        private String[] starts;
        private String word;

        @JsonCreator
        public Input(@JsonProperty("nterms") String[] nterms,
                     @JsonProperty("terms")String[] terms,
                     @JsonProperty("productions")String[] productions,
                     @JsonProperty("starts")String[] starts,
                     @JsonProperty("word") String word) {
            this.nterms = nterms;
            this.terms = terms;
            this.productions = productions;
            this.starts = starts;
            this.word = word;
        }

        public String[] getNterms() {
            return nterms;
        }

        public void setNterms(String[] nterms) {
            this.nterms = nterms;
        }

        public String[] getTerms() {
            return terms;
        }

        public void setTerms(String[] terms) {
            this.terms = terms;
        }

        public String[] getProductions() {
            return productions;
        }

        public void setProductions(String[] productions) {
            this.productions = productions;
        }

        public String[] getStarts() {
            return starts;
        }

        public void setStarts(String[] starts) {
            this.starts = starts;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }
    }
}
