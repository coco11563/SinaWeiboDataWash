package Model;

/**
 * Created by Sha0w on 2017/2/28.
 */
public class CategoryStatistic {
    private String catagory;
    private String time;
    private int num;
    public CategoryStatistic(String catagory, String time, int num) {
        this.catagory = catagory;
        this.time = time;
        this.num = num;
    }
    public String getCatagory(){
        return catagory;
    }
    @Override
    public String toString(){
        return catagory + "," + time + "," + num ;
    }
}
