package ic7cc.ovchinnikov.compiler.parser.location;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Location {

    private final int row;
    private final int column;

}
