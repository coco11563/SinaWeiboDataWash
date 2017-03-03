package Process;

import Model.DataFormat;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static Util.DatabaseHelper.queryEntityList;
import static Util.FileUtil.FileWrite;

/**
 * Created by Sha0w on 2017/2/28.
 */
public class CacuVanishPoint {
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
            if (mapper.containsKey(d.getPoi_id())) {
                mapper.get(d.getPoi_id()).add(d);
            } else {
                mapper.put(d.getPoi_id(), new LinkedList<DataFormat>());
                mapper.get(d.getPoi_id()).add(d);
            }
        }
        return mapper;
    }

    public static void FileOutputter(Map<String, List<DataFormat>> mapper) {
        List<DataFormat> vague = new LinkedList<>();
        List<DataFormat> notvague = new LinkedList<>();

        for (String i : mapper.keySet()) {
            if (isVague(mapper.get(i))){
                vague.addAll(mapper.get(i));
            } else {
                notvague.addAll(mapper.get(i));
            }
        }
        StringBuilder vagueBuilder = new StringBuilder();

        StringBuilder notVagueBuilder = new StringBuilder();
        for (DataFormat df : vague) {
            vagueBuilder.append(df.getLat()).append(",").append(df.getLon()).append("\r\n");
        }
        for (DataFormat df_ : notvague) {
            notVagueBuilder.append(df_.getLat()).append(",").append(df_.getLon()).append("\r\n");
        }

        FileWrite(vagueBuilder.toString(),"vague__.txt");
        FileWrite(notVagueBuilder.toString(),"notVague__.txt");

    }
    //true if vague
    public static boolean isVague(List<DataFormat> list) {
        return list.get(0).getCategory() == null;
    }

    public static void main(String args[]) {
        FileOutputter(MapMake(readDb(81059)));
    }
}
