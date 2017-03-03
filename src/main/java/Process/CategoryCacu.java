package Process;

import Model.CategoryStatistic;
import Model.DataFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static HolidayPart.ChineseHoliday.getHoliday;
import static Util.CategoryUtil.categoryStatistic;
import static Util.DatabaseHelper.queryEntityList;

/**
 * Created by Sha0w on 2017/2/28.
 *
 */
public class CategoryCacu {
    //数据到List中
    public static List<DataFormat> readDb(int limit) {
        String SQL = "select * from cug_userdata_3757";
        if (limit != 0) {
            SQL = SQL + " limit 0," + limit;
        }
        return queryEntityList(DataFormat.class,SQL);
    }
    //遍历到Map中
    public static Map<String ,List<DataFormat>> MapMake(List<DataFormat> list) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, List<DataFormat>> mapper = new HashMap<>();
        for (DataFormat d : list) {
            if (mapper.containsKey(d.getDay())) {
                mapper.get(d.getDay()).add(d);
            } else {
                mapper.put(d.getDay(), new LinkedList<DataFormat>());
                mapper.get(d.getDay()).add(d);
            }
        }
        for (DataFormat d : list) {
            String flag = Integer.toString(getHoliday(sdf.parse(d.getDateTime())));

            if (flag.equals("2") || flag.equals("1")) {
                flag = "1";
            } else {
                flag = "0";
            }
            if (mapper.containsKey(flag)) {
                mapper.get(flag).add(d);
            } else {
                mapper.put(flag, new LinkedList<DataFormat>());
                mapper.get(flag).add(d);
            }
        }
        return mapper;
    }

    /**
     *
     * @param mapper riqi : List DF
     * @return riqi : <time: ListDF>
     */
    public static Map<String, Map<String, List<DataFormat>>> categorysStatistic(Map<String, List<DataFormat>> mapper){
        Map<String, Map<String, List<DataFormat>>> retMapper = new HashMap<>();
        for(String date : mapper.keySet()) {
            Map<String, List<DataFormat>> temp = new HashMap<>();
            for (DataFormat df : mapper.get(date)) {
                if (!temp.containsKey(df.getTime().split(":")[0])) {
                    temp.put(df.getTime().split(":")[0], new LinkedList<DataFormat>());
                    temp.get(df.getTime().split(":")[0]).add(df);
                } else {
                    temp.get(df.getTime().split(":")[0]).add(df);
                }
            }
            retMapper.put(date, temp);
        }
        return retMapper;
    }


    public static Map<String, Map<String, Map<String,List<DataFormat>>>> catagorysTimeMapper(Map<String, Map<String, List<DataFormat>>> mapper) {
        //date         time           cate           ldf
        Map<String, Map<String, Map<String,List<DataFormat>>>> stringMapMap = new HashMap<>();
        for(String date : mapper.keySet()) {
            //time           ldf
            Map<String, List<DataFormat>> mdfl = mapper.get(date);
            //time           cate           ldf
            Map<String, Map<String, List<DataFormat>>> mapMap = new HashMap<>();
            for (String time : mdfl.keySet()) {
                //cate           ldf
                Map<String, List<DataFormat>> stringListMap = new HashMap<>();
                for (DataFormat df : mdfl.get(time)){
                    if (df.getCategorys() == null || categoryStatistic(df) == null) continue;
                    if (!stringListMap.containsKey(categoryStatistic(df))) {
                        stringListMap.put(categoryStatistic(df), new LinkedList<DataFormat>());
                        stringListMap.get(categoryStatistic(df)).add(df);
                    } else {
                        stringListMap.get(categoryStatistic(df)).add(df);
                    }
                }
                mapMap.put(time,stringListMap);
            }
            stringMapMap.put(date, mapMap);
        }
        return stringMapMap;
    }

    public static Map<String, Map<String, List<CategoryStatistic>>> finalCategoryStatistic(Map<String, Map<String, Map<String,List<DataFormat>>>> stringMapMap) {
        Map<String, Map<String, List<CategoryStatistic>>> retmap = new HashMap<>();
        for (String date : stringMapMap.keySet()) {
            Map<String, Map<String,List<DataFormat>>> mapMap = stringMapMap.get(date);
            Map<String, List<CategoryStatistic>> map1 = new HashMap<>();
            for (String time : mapMap.keySet()) {
                Map<String,List<DataFormat>> map = mapMap.get(time);
                for (String cate : map.keySet()) {
                    if (!map1.containsKey(removeZero(time))) {
                        map1.put(removeZero(time), new LinkedList<CategoryStatistic>());
                        map1.get(removeZero(time)).add(new CategoryStatistic(cate, removeZero(time), map.get(cate).size()));
                    } else {
                        map1.get(removeZero(time)).add(new CategoryStatistic(cate, removeZero(time), map.get(cate).size()));
                    }
                }
            }
            retmap.put(date, map1);
        }
        return retmap;
    }

    private static String removeZero(String s) {
        if (s.equals("01") || s.equals("02") || s.equals("03")
                || s.equals("04") || s.equals("05") || s.equals("06") || s.equals("07") || s.equals("08") || s.equals("09")) {
            return s.replaceAll("0", "");
        } else if (s.equals("00")){
            return "0";
        }
        return s;
    }

    public static  void main(String args[]) {
        System.out.print(removeZero("10"));
    }
}


