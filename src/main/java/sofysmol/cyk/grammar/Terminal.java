package sofysmol.cyk.grammar;

/**
 * Created by sofysmo on 09.10.16.
 */
public class Terminal implements Symbol{
    private String name;
    public Terminal(String name){
        this.name = name;
    }

    public Terminal(char ch){
        this.name = String.valueOf(ch);
    }

    public String toString(){
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Terminal)) return false;

        Terminal terminal = (Terminal) o;

        return name != null ? name.equals(terminal.name) : terminal.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
