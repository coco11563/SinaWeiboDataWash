package Process;

import Model.DataFormat;
import Model.DirectionAPIInput;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static Util.DatabaseHelper.queryEntityList;
import static Util.UrlUtil.getTime;

/**
 * Created by Sha0w on 2017/2/28.
 */
public class Statistic {
    public static void main(String args[]) {
        //读数据到一个List对象中
        //遍历List到Map对象中
        //Map对象分成三个大类型人群List
        //List的随机输出函数

    }

    //数据到List中
    public static List<DataFormat> readDb(int limit) {
        String SQL = "select * from cug_userdata_3757";
        if (limit != 0) {
            SQL = SQL + " limit 0," + limit;
        }
        return queryEntityList(DataFormat.class,SQL);
    }
    //遍历到Map中
    public static Map<String ,List<DataFormat>> MapMake(List<DataFormat> list) {
        Map<String, List<DataFormat>> mapper = new HashMap<>();
        for (DataFormat d : list) {
            if (mapper.containsKey(d.getCoordinateHash())) {
                mapper.get(d.getCoordinateHash()).add(d);
            } else {
                mapper.put(d.getCoordinateHash(), new LinkedList<DataFormat>());
                mapper.get(d.getCoordinateHash()).add(d);
            }
        }
        return mapper;
    }

    //遍历Map获取每个Map的一个坐标
    public static List<Integer> timeCacu(Map<String, List<DataFormat>> mapper) {
        List<Integer> timelist = new LinkedList<>();
        for (String s : mapper.keySet()) {
            System.out.println("done one time");
            double lat = Double.parseDouble(mapper.get(s).get(0).getLat());
            double lon = Double.parseDouble(mapper.get(s).get(0).getLon());
            DirectionAPIInput dai = new DirectionAPIInput(lat, lon, mapper.get(s).size());
            int duration = getTime(dai);
            for (int i = 0 ; i < dai.getCheckintime() ; i ++) {
                if (duration == -1)
                    break;
                else timelist.add(duration);
            }
        }
        return timelist;
    }
}
