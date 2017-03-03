package HolidayPart;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 更新至2017年
 *
 * 如果非節假日就會判斷是否是週末
 *
 * 法定節假日或者補班也會識別
 *
 * 上班的日子返回 0
 *
 * 不用和上班的法定節日返回 1
 *
 * 週末返回 2
 *
 * 函數藉口調用getHoliday()
 */
public class ChineseHoliday{
    private final static Map<Date, Integer> holidayBuffer = new HashMap<>();
    private final static List<File> dataPath = new ArrayList<>();
    private final static String storDataPath = "./src/main/resources/data";

    static {
        getFilePath(new File(storDataPath));
        String temp = "";
        for (File f : dataPath) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(f));
                while ((temp = reader.readLine()) != null) {
                    if (!temp.equals("\n")) {
                        if (f.getName().matches("[2][0][1][0-7]_w.txt|[2][0][0][9]_w.txt"))
                        holidayBuffer.put(holidayToDate(f, temp), 0);
                        else holidayBuffer.put(holidayToDate(f, temp), 1);
                    }
                }
                reader.close();
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param d 想要獲取是否爲節假日的日期
     * @return 0 爲工作日 1 爲節日 2 爲週末
     */
    public static int getHoliday(Date d) {
        if (holidayBuffer.containsKey(d)) {
            return holidayBuffer.get(d);
        } else {
            return isWeekend(d) ? 2 : 0;
        }
    }
    private static boolean isWeekend(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        return (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY);
    }
    private static void getFilePath (File file) {
        if(file.isDirectory())
        {
            File f[]= file.listFiles();
            if(f!=null)
            {
                for (File aF : f) {
                    getFilePath(aF);
                }
            }
        } else {
            dataPath.add(file);
        }
    }

    private static Date holidayToDate(File f, String s) throws ParseException {
        String fileName = f.getName();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        return sdf.parse(fileName.split("\\_|\\.")[0] + "-" + s.substring(0,2) + "-" + s.substring(2));

    }
}