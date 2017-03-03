import Model.DataFormat;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static Process.Statistic.readDb;
import static Process.Statistic.MapMake;
import static Process.Statistic.timeCacu;

/**
 * Created by Sha0w on 2017/2/28.
 */
public class Test__2 {
    private List<DataFormat> list;
    private Map<String ,List<DataFormat>> mapper;
    private List<Integer> timeList;
    @Before
    public void inial() {
        list = readDb(81059);
        mapper = MapMake(list);
    }

    @Test
    public void testStatisticMapper() {
        mapper = MapMake(list);
        for (String i : mapper.keySet()) {
            System.out.println(i + ":" + mapper.get(i).size());
        }
        System.out.println(mapper.keySet().size());
    }

    @Test
    public void timeGetter() {
        timeList = timeCacu(mapper);
        for (int i : timeList) {
            System.out.print(i + ",");
        }
    }
}
