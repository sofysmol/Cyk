package sofysmol.cyk;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import sofysmol.cyk.grammar.Grammar;
import sofysmol.cyk.mappers.GrammarMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by sofysmo on 09.10.16.
 */
public class App {

    public static void main(String[] argv){
        try {
            ObjectMapper mapper = new ObjectMapper();
            String pp = "src/main/resources/example.json";
            //byte[] p = Files.readAllBytes(Paths.get(pp));
            Input input = mapper.readValue(Files.readAllBytes(Paths.get(pp)), Input.class);
            Grammar grammar = GrammarMapper.parse(input.getNterms(),
                    input.getTerms(), input.starts, input.getProducts());
            if(CykAlgorithm.check(grammar,input.word))
                System.out.println("String is member of language");
            else System.out.println("String is not member of language");

        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
    private static class Input{
        private String[] nterms;
        private String[] terms;
        private String[] products;
        private String[] starts;
        private String word;

        @JsonCreator
        public Input(@JsonProperty("nterms") String[] nterms,
                     @JsonProperty("terms")String[] terms,
                     @JsonProperty("products")String[] products,
                     @JsonProperty("starts")String[] starts,
                     @JsonProperty("word") String word) {
            this.nterms = nterms;
            this.terms = terms;
            this.products = products;
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

        public String[] getProducts() {
            return products;
        }

        public void setProducts(String[] products) {
            this.products = products;
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
