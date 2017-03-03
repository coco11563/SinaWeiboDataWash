package Model;

import java.text.DecimalFormat;

/**
 * Created by Sha0w on 2017/2/28.
 *
 * 调用baidu DirectionAPI所需要的参数Class
 */
public class DirectionAPIInput {

    private double olat;
    private double olon;
    private boolean isCUG;
    private int checkintime;

    public DirectionAPIInput(double olat, double olon, int checkintime) {
        DecimalFormat df = new DecimalFormat( "0.000000 ");
        this.olat = Double.parseDouble(df.format(olat));
        this.olon = Double.parseDouble(df.format(olon));
        this.checkintime = checkintime;
        isCUG = (olat <= 30.529799 && olat >= 30.522294 && olon <= 114.414777 && olon >= 114.402219) ||
                (olat <= 30.536759 && olat >= 30.532777 && olon <= 114.408201 && olon >= 114.402632);
    }
    public boolean isCUG(){
        return isCUG;
    }
    @Override
    public String toString(){
        int page_size = 1;
        String ak = "396489b6094a72b5a5c6c2e1c68f4c59";
        String output = "json";
        int tactics_incity = 4;
        String coord_type = "bd09ll";
        double dlat = 30.526081;
        double dlon = 114.407541;
        return "origin=" + olat + "," + olon + "&destination=" + dlat + ","
                + dlon + "&coord_type=" + coord_type + "&tactics_incity=" +
                tactics_incity + "&output=" + output + "&page_size=" + page_size
                + "&ak=" + ak;
    }

    public int getCheckintime() {
        return checkintime;
    }

    public void setCheckintime(int checkintime) {
        this.checkintime = checkintime;
    }
}
