import Model.CategoryStatistic;
import Model.DataFormat;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static Process.CategoryCacu.*;
import static Util.FileUtil.FileWrite;

/**
 * Created by Sha0w on 2017/3/1.
 *
 */
public class Test___3 {
    private Map<String, Map<String, Map<String,List<DataFormat>>>> stringMapMap;
    private Map<String, Map<String, List<CategoryStatistic>>> finalMap;
    @Before
    public void inial() throws ParseException {
        List<DataFormat> list = readDb(81059);
        Map<String, List<DataFormat>> mapper = MapMake(list);
        Map<String, Map<String, List<DataFormat>>> mapMap = categorysStatistic(mapper);
        stringMapMap = catagorysTimeMapper(mapMap);
        finalMap = finalCategoryStatistic(stringMapMap);
    }

    @Test
    public void testMapperMaker() {

        for (String date : finalMap.keySet()) {
            StringBuilder sb = new StringBuilder();
            for (String time : finalMap.get(date).keySet()) {
               for (CategoryStatistic cs : finalMap.get(date).get(time)) {
                   sb.append("[");
                   sb.append(cs.toString()).append("],");
               }
            }
            FileWrite(sb.toString(),date + "__output.txt");
        }


    }




}
