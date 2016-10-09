package sofysmol.cyk.utils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by sofysmo on 09.10.16.
 */
public class StringHelper {
    public static <T> String listToStr(List<T> list, String del){
        return list.stream().map(Object::toString)
                .collect(Collectors.joining(del));
    }
    public static <T> String listToStr(List<T> list){
        return listToStr(list,",");
    }
}
