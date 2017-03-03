package Util;

import Model.DirectionAPIInput;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLDecoder;


/**
 * Created by Sha0w on 2017/2/28.
 */
public class UrlUtil {
    private static int isCug = 0;
    private static int num = 0;
    private static String BAIDU_API = "http://api.map.baidu.com/direction/v2/transit?";
    public static JSONObject httpGet(DirectionAPIInput directionAPIInput){
        return httpGet(BAIDU_API + directionAPIInput.toString());
    }
    private static int timeCaculater(JSONObject result) {
        if (result != null) {
            JSONObject resu = result.optJSONObject("result");
            if (resu != null){
                JSONArray routes = resu.optJSONArray("routes");
                if (!routes.isEmpty()) {
                    JSONObject route = routes.getJSONObject(0);
                    return route.getInt("duration");
                }
            }
        }
        System.out.println("done one");
        return -1;
    }
    public static int getTime(DirectionAPIInput directionAPIInput){
        System.out.println("第" + num ++ +"个用户");
        if (directionAPIInput.isCUG()) {
            System.out.println("是地大的: " + isCug++);
            return 0;
        } else {
            return timeCaculater(httpGet(BAIDU_API + directionAPIInput.toString()));
        }
    }
    /**
     * post请求
     * @param url         url地址
     * @param noNeedResponse    不需要返回结果
     * @return
     */
    public static JSONObject httpPost(String url, boolean noNeedResponse){
        //post请求返回结果
        DefaultHttpClient httpClient = new DefaultHttpClient();
        JSONObject jsonResult = null;
        HttpPost method = new HttpPost(url);
        try {
            HttpResponse result = httpClient.execute(method);
            url = URLDecoder.decode(url, "UTF-8");
            /**请求发送成功，并得到响应**/
            if (result.getStatusLine().getStatusCode() == 200) {
                String str = "";
                try {
                    /**读取服务器返回过来的json字符串数据**/
                    str = EntityUtils.toString(result.getEntity());
                    if (noNeedResponse) {
                        return null;
                    }
                    /**把json字符串转换成json对象**/
                    jsonResult = JSONObject.fromObject(str);
                } catch (Exception e) {
//                    logger.error("post请求提交失败:" + url, e);
                }
            }
        } catch (IOException e) {
//            logger.error("post请求提交失败:" + url, e);
        }
        return jsonResult;
    }


    /**
     * 发送get请求
     * @param url    路径
     * @return
     */
    public static JSONObject httpGet(String url){
        //get请求返回结果
        JSONObject jsonResult = null;
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            //发送get请求
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);

            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                String strResult = EntityUtils.toString(response.getEntity());
                /**把json字符串转换成json对象**/
                jsonResult = JSONObject.fromObject(strResult);
                url = URLDecoder.decode(url, "UTF-8");
            } else {
//                logger.error("get请求提交失败:" + url);
            }
        } catch (IOException e) {
//            logger.error("get请求提交失败:" + url, e);
        }
        return jsonResult;
    }

    public static void main(String args[]) {
        System.out.print(timeCaculater(httpGet("http://api.map.baidu.com/direction/v2/transit?origin=40.056878,116.30815&destination=31.222965,121.505821&ak=8mjXttwdGncaoZxGLjGdxYzh1Q9XrQ4H")));
    }
}
