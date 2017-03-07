package Model;

import ch.hsr.geohash.GeoHash;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static Util.CastUtil.castDouble;
import static Util.CastUtil.castInt;

/**
 * 特定数据格式，选取了idstr;id;text;created;lon;lat;name;gender;verified等20个字段
 * @author licong、dx
 * 
 *
 */
public class DataFormat {
	private String id;
	private String user_id;
	private String text;
	private String time;
	private String date;
	private String day;
	private String month;
	private String year;
	private String lon;
	private String lat;
	private String name;
	private String gender;
	private String verified;
	private String poi_id;
	private String title;
	private String pic_url;
	private String pic_ids;
	private String user_name;
	private String followers_count;
	private String friends_count;
	private String category;
	private String categorys;
	private String category_name;
	

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getVerified() {
		return verified;
	}
	public void setVerified(String verified) {
		this.verified = verified;
	}

	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getPic_url() {
		return pic_url;
	}
	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}
	public String getPic_ids() {
		return pic_ids;
	}
	public void setPic_ids(String pic_ids) {
		this.pic_ids = pic_ids;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getFriends_count() {
		return friends_count;
	}
	public void setFriends_count(String friends_count) {
		this.friends_count = friends_count;
	}
	public String getFollowers_count() {
		return followers_count;
	}
	public void setFollowers_count(String followers_count) {
		this.followers_count = followers_count;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCategorys() {
		return categorys;
	}
	public void setCategorys(String categorys) {
		this.categorys = categorys;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
    public String getId(){
	    return id;
    }
	@Override
	public String toString(){
		return id + "`" + user_id + "`" + user_name + "`" + verified + "`" + friends_count + "`" + followers_count + "`" + poi_id + "`" + categorys
				+"`" + category + "`" + category_name + "`" + gender + "`" + pic_url + "`" + pic_ids + "`" + title + "`" + year + "`" + month + "`" + date +
				"`" + day + "`" + time + "`" + removeComma(text) + "`" + lat + "`" + lon + "`" + 0 + "`" + 0 ;
	}

    public void setId(String id) {
        this.id = id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setPoi_id(String poi_id) {
        this.poi_id = poi_id;
    }

	public String getPoi_id() {
		return poi_id;
	}

    private String removeComma(String s){
	    return s.replace('`',' ');
    }
	public static String isWinterHoliday(Date date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if ((date.after(sdf.parse("2014-07-11")) && date.before(sdf.parse("2014-09-01")) ||
				(date.after(sdf.parse("2015-07-10")) && date.before(sdf.parse("2015-09-09"))))){
			return "Summer"; //shujia
		} else if ((date.after(sdf.parse("2014-01-18")) && date.before(sdf.parse("2014-02-17")) ||
				(date.after(sdf.parse("2015-01-26")) && date.before(sdf.parse("2015-03-01"))))) {
			return "Winter"; //hanjia
		}
		return "Normal";
	}
    public String getCoordinateHash() {
		return GeoHash.geoHashStringWithCharacterPrecision(Double.parseDouble(lat), Double.parseDouble(lon), 8);
	}
	public String getDateTime() {
    	return year + "-" + monthToNum(month) + "-" + date;
	}
 //zerenggan
	//zilv
	private int monthToNum(String month) {
		switch (month) {
			case "Jan":
				return 1;
			case "Feb":
				return 2;
			case "Mar":
				return 3;
			case "Apr":
				return 4;
			case "May":
				return 5;
			case "Jun":
				return 6;
			case "Jul":
				return 7;
			case "Aug":
				return 8;
			case "Sep":
				return 9;
			case "Oct":
				return 10;
			case "Nov":
				return 11;
			case "Dec":
				return 12;
		}
		return 0;
	}
}