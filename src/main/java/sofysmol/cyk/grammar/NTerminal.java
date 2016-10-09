package sofysmol.cyk.grammar;

import java.util.Objects;

/**
 * Created by sofysmo on 09.10.16.
 */
public class NTerminal implements Symbol {
    private String name;
    public String toString(){
        return name;
    }
    public NTerminal(String name) {
        this.name = name;
    }
    public NTerminal(char ch){
        this.name = String.valueOf(ch);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NTerminal)) return false;

        NTerminal nTerminal = (NTerminal) o;

        return name != null ? name.equals(nTerminal.name) : nTerminal.name == null;

    }
}
