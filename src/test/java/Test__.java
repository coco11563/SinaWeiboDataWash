import Model.DataFormat;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static Process.Main.*;

/**
 * Created by Sha0w on 2017/2/27.
 */
public class Test__ {
    private List<DataFormat> list;
    private Map<String ,List<DataFormat>> mapper;
    private List<Map<String, List<DataFormat>>> maplist;
    @Before
    public void inial(){
        list = readDb(81059);
        mapper = MapMake(list);
        maplist = departMap(mapper);
    }
    @Test
    public void testDistinctOutput() {
        distinctOutput(maplist);
    }
    @Test
    public void testOutput() {
        randomOutput(maplist);
    }
    @Test
    public void testDepart() {
        maplist = departMap(mapper);
        for (Map<String, List<DataFormat>> mapper : maplist) {
            System.out.println("-.-.-.-.-.-.-.-.-.--.-.-.-.-.-.-");
            for (String i : mapper.keySet()) {
                System.out.println(i + ":" + mapper.get(i).size());
            }
        }
    }
    @Test
    public void testMapper(){
        mapper = MapMake(list);
        for (String i : mapper.keySet()) {
            System.out.println(i + ":" + mapper.get(i).size());
        }
        System.out.println(mapper.keySet().size());
    }

    @Test
    public void testDb(){
        list = readDb(100);
        for (DataFormat d : list) {
            System.out.println(d.toString());
        }
    }


}
