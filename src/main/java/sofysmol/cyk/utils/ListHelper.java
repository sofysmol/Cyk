package sofysmol.cyk.utils;

import java.util.List;

/**
 * Created by sofysmo on 09.10.16.
 */
public class ListHelper {
    public static <T> boolean equals(List<T> one, List<T> two){
        if((one == null && two != null)
                || one != null && two == null
                || one.size() != two.size()){
            return false;
        }
        return StringHelper.listToStr(one).equals(StringHelper.listToStr(two));
    }
    public static <T> boolean equals(List<T> one, String two){
        return StringHelper.listToStr(one).equals(two);
    }
}
