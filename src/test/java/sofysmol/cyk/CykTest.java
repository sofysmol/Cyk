package sofysmol.cyk;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import sofysmol.cyk.exceptions.BadInputException;
import sofysmol.cyk.grammar.Grammar;
import sofysmol.cyk.mappers.GrammarMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

/**
 * Created by sofysmo on 09.10.16.
 */
public class CykTest {
    private ObjectMapper mapper = new ObjectMapper();
    private String root = "src/test/resources/";
    @Test
    public void testWordInLang() throws IOException, BadInputException {
        String filename = root + "word_is_in_lang.json";
        boolean res = getResult(filename);
        assertEquals(res, true);
    }

    @Test
    public void testWordNotInLang() throws IOException, BadInputException {
        String filename = root + "word_is_ not_in_lang.json";
        boolean res = getResult(filename);
        assertEquals(res, false);
    }

    @Test
    public void testBigGrammar() throws IOException, BadInputException {
        String filename = root + "big_grammar.json";
        boolean res = getResult(filename);
        assertEquals(res, true);
    }

    @Test
    public void testBigGrammar1() throws IOException, BadInputException {
        String filename = root + "big_grammar1.json";
        boolean res = getResult(filename);
        assertEquals(res, false);
    }

    @Test
    public void testSomeStarts() throws IOException, BadInputException {
        String filename = root + "some_starts.json";
        boolean res = getResult(filename);
        assertEquals(res, true);
    }

    @Test(expected = BadInputException.class)
    public void testBadStarts() throws IOException, BadInputException {
        String filename = root + "bad_starts.json";
        boolean res = getResult(filename);
        assertEquals(res, true);
    }
    @Test(expected = BadInputException.class)
    public void testBadProduct1() throws IOException, BadInputException {
        String filename = root + "bad_product1.json";
        boolean res = getResult(filename);
        assertEquals(res, true);
    }
    @Test(expected = BadInputException.class)
    public void testBadProduct2() throws IOException, BadInputException {
        String filename = root + "bad_product2.json";
        boolean res = getResult(filename);
        assertEquals(res, true);
    }
    @Test(expected = BadInputException.class)
    public void testBadProduct3() throws IOException, BadInputException {
        String filename = root + "bad_product3.json";
        boolean res = getResult(filename);
        assertEquals(res, true);
    }

    private boolean getResult(String filename) throws IOException, BadInputException{
        App.Input input = mapper.readValue(Files.readAllBytes(Paths.get(filename)), App.Input.class);
        Grammar grammar = GrammarMapper.parse(input.getNterms(),
                input.getTerms(), input.getStarts(), input.getProducts());
        return new CykAlgorithm().check(grammar, input.getWord());
    }
}
