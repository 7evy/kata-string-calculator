package parsers;

import java.util.ArrayList;

public interface Parser<T> {
    ArrayList<T> parse(String stringToParse);
}
