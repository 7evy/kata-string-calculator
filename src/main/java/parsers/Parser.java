package parsers;

import java.util.List;

public interface Parser<T> {
    List<T> parse(String stringToParse);
}
