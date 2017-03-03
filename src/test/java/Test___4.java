import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.*;

import static Util.FileUtil.fileRead;

/**
 * Created by Sha0w on 2017/3/1.
 */
public class Test___4 {
    @Test
    public void testReader() throws FileNotFoundException {
        List<Integer> li = new ArrayList<>();
        for (String s : fileRead("/Part IIII/TravelTime.txt",",")){
            if (s.equals("")|| s.equals("\r\n")) continue;
            int t = Integer.valueOf(s);
            li.add(t);
        }
        Integer[] result = new Integer[li.size()];
        li = filterNull(li);
        li.toArray(result);
        Map<Integer, Integer> mapper = filter(li, getMax(result), getMin(result), 0);
        int[] temp = new int[mapper.keySet().size()];
        int i = 0;
        for (int ins : mapper.keySet()) {
            temp[i] = ins;
            i ++;
        }
        Arrays.sort(temp);
        System.out.println(getMin(result));
        System.out.println(Arrays.toString(temp));
        System.out.print("[");
        for (int ins : temp){
            System.out.print(mapper.get(ins) + ",");
        }
        System.out.print("]");
        System.out.println();
        for (int ins : temp) {
            System.out.print("" +ins + "," + mapper.get(ins) +"\n");
        }
        int sum = 0;
        for (int times : mapper.keySet()) {
            sum += mapper.get(times);
        }
        System.out.println("\r\n" + sum);
    }

    private static int getMin(Integer[] ints) {
        Arrays.sort(ints);
        return ints[0];
    }


    private static int getMax(Integer[] ints) {
        Arrays.sort(ints);
        return ints[ints.length - 1];
    }

    private static List<Integer> filterNull(List<Integer> list){
        List<Integer> filterList = new ArrayList<>();
        for(Integer str : list){
            if(str != null){
                filterList.add(str);
            }
        }
        return filterList;
    }

    private static Map<Integer, Integer> filter(List<Integer> list, int max, int min, int scale) {
        Map<Integer, Integer> ret = new HashMap<>();
        if (scale == 0) {
            for (int i : list) {
                if (!ret.containsKey(i)) {
                    ret.put(i, 1);
                } else {
                    ret.put(i, ret.get(i) + 1);
                }
            }
            return ret;
        }
        List<Integer> scaleList = spliterScale(max, min, scale);

        for (int i : list) {
            for (int part = 0; part < scaleList.size(); part++) {
                if (part == 0) {
                    if (i < scaleList.get(part)) {
                        if (!ret.containsKey(scaleList.get(part))) {
                            ret.put(scaleList.get(part), 1);
                        } else {
                            ret.put(scaleList.get(part), ret.get(scaleList.get(part)) + 1);
                        }
                    }
                } else {
                    if (i < scaleList.get(part) && i >= scaleList.get(part - 1)) {
                        if (!ret.containsKey(scaleList.get(part))) {
                            ret.put(scaleList.get(part), 1);
                        } else {
                            ret.put(scaleList.get(part), ret.get(scaleList.get(part)) + 1);
                        }
                    }
                }
            }
        }
        return ret;
    }

    private static List<Integer> spliterScale(int max, int min, int scale) {
        int part = (max - min) / scale ;
        List<Integer> ret = new LinkedList<>();
        for (int i = 0 ; i < scale - 1 ; i ++) {
            ret.add(min + (i + 1) * part);
        }
        ret.add(max);
        return ret;
    }

    public static void  main(String args[]) {
    }
}
