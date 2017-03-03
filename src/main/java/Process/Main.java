package Process;

import Model.DataFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static Util.DatabaseHelper.queryEntityList;
import static Util.FileUtil.FileWrite;

/**
 * Created by Sha0w on 2017/2/27.
 */
public class Main {
    private static int timesLimit = 50;
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
            if (mapper.containsKey(d.getUser_name())) {
                mapper.get(d.getUser_name()).add(d);
            } else {
                mapper.put(d.getUser_name(), new LinkedList<DataFormat>());
                mapper.get(d.getUser_name()).add(d);
            }
        }
        return mapper;
    }
    //Map对象分成三个大类型人群List
    public static List<Map<String, List<DataFormat>>> departMap(Map<String, List<DataFormat>> mapper) {
        List<Map<String, List<DataFormat>>> maplist = new LinkedList<>();
        maplist.add(new HashMap<String, List<DataFormat>>());
        maplist.add(new HashMap<String, List<DataFormat>>());
        maplist.add(new HashMap<String, List<DataFormat>>());
        for (String i : mapper.keySet()) {
            List<DataFormat> insider = mapper.get(i);
            int size = insider.size();
           if (size == 1) {
               maplist.get(0).put(i, insider);
           }else if (size >= 2 && size < 10) {
               maplist.get(1).put(i, insider);
           }else if (size > 10){
               maplist.get(2).put(i, insider);
           }
        }
        System.out.println(maplist.get(0).size());

        System.out.println(maplist.get(1).size());

        System.out.println(maplist.get(2).size());
        return maplist;
    }
    //List的随机输出函数
            public static void randomOutput(List<Map<String, List<DataFormat>>> mapList) {
                for (int i1 = 0; i1 < mapList.size(); i1++) {
                    Map<String, List<DataFormat>> mapper = mapList.get(i1);
                    int times = 0;
            int fileNum = 0;
            StringBuilder temp = new StringBuilder();
            for (String i : mapper.keySet()) {
                if (times == timesLimit) {
                    times = 0;
                    // 写入
                    FileWrite(temp.toString(), i1 + "_" + fileNum);
                    temp = new StringBuilder();
                    fileNum++;
                } else {
                    times++;
                    for (DataFormat dataFormat : mapper.get(i)) {
                        temp.append(dataFormat.toString());
                        temp.append("\r\n");
                    }
                    //插入
                }

            }
        }
    }


    public static void distinctOutput(List<Map<String, List<DataFormat>>> mapList) {
        for (int i1 = 0; i1 < mapList.size(); i1++) {
            Map<String, List<DataFormat>> mapper = mapList.get(i1);
            int times = 0;
            int fileNum = 0;
            StringBuilder temp = new StringBuilder();
            for (String i : mapper.keySet()) {
                if (times == timesLimit) {
                    times = 0;
                    // 写入
                    FileWrite(temp.toString(), i1 + "_" + fileNum);
                    temp = new StringBuilder();
                    fileNum++;
                } else {
                    times++;
                    for (DataFormat dataFormat : mapper.get(i)) {
                        temp.append(dataFormat.toString());
                        temp.append("\r\n");
                        break;
                    }
                    //插入
                }

            }
        }
    }
}
