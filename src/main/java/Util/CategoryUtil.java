package Util;

import Model.CategoryStatistic;
import Model.DataFormat;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Sha0w on 2017/2/28.
 *
 */
public class CategoryUtil {



    /**
     * input <Cata, List>
     */
    public static List<CategoryStatistic> toCataStastic(Map<String , List> mapper, String time) {
        List<CategoryStatistic> li = new LinkedList<>();
        for (String s : mapper.keySet()) {
            li.add(new CategoryStatistic(s, time, mapper.get(s).size()));
        }
        return li;
    }
    //出行住宿 0  校园7 餐饮 1 购物 2 生活服务 3 娱乐 4 户外 5 工作相关 6
    public static String categoryStatistic(DataFormat dataFormat) {
        String[] category_array = dataFormat.getCategorys().split(" ");
        if (category_array.length > 1) {
            int cat_id = Integer.valueOf(category_array[1]);
            if (19 < cat_id && cat_id < 39 || 211 < cat_id && cat_id < 220 || cat_id == 251 || cat_id == 267 || cat_id == 619) {
                return "0"; //出行
            }
//            else if (51 < cat_id && cat_id < 58 || 670 < cat_id && cat_id < 679 || cat_id == 191 || cat_id == 181 || cat_id == 253) {
//                return "0"; //校园
//            }
            else if (64 < cat_id && cat_id < 70 || 110 < cat_id && cat_id < 114 || 267 < cat_id && cat_id < 270 || 646 < cat_id && cat_id < 649 || cat_id == 93 || cat_id == 247) {
                return "1"; //餐饮
            }else if (115 < cat_id && cat_id < 125 || 137 < cat_id && cat_id < 142 || cat_id == 249 || cat_id == 624 || cat_id == 676 || cat_id == 683) {
                return "2";  //购物
            }else if (147 < cat_id && cat_id < 164 || 181 < cat_id && cat_id < 191 || 191 < cat_id && cat_id < 194 || 609 < cat_id && cat_id < 614 ||
                    +667 < cat_id && cat_id < 671 || cat_id == 266 || cat_id == 616 || cat_id == 625 || cat_id == 629 || cat_id == 47 || cat_id == 602 || cat_id == 606) {
                return "3";  //生活服务
            }else if (169 < cat_id && cat_id < 181 || cat_id == 248 || cat_id == 603 || cat_id == 605) {
                return "4";  //娱乐
            }else if (194 < cat_id && cat_id < 209 || 219 < cat_id && cat_id < 247 || cat_id == 250 || cat_id == 604 || cat_id == 607 || cat_id == 61 || cat_id == 677) {
                return "5";  //户外
            }else if (44 < cat_id && cat_id < 47 || 47 < cat_id && cat_id < 50 || 58 < cat_id && cat_id < 64 || 607 < cat_id && cat_id < 610 || 616 < cat_id && cat_id < 619 ||
                    +620 < cat_id && cat_id < 624 || 629 < cat_id && cat_id < 647 || cat_id == 252 || cat_id == 259 || cat_id == 263 || cat_id == 265 || cat_id == 614) {
                return "6";  //工作相关
            }

        }
        return null;
    }
}
