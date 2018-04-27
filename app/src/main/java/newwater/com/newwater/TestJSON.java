package newwater.com.newwater;

import com.alibaba.fastjson.JSON;

import org.json.JSONObject;

import java.util.ArrayList;

import newwater.com.newwater.beans.Strategy;

/**
 * Created by Administrator on 2018/4/27 0027.
 */

public class TestJSON {
     public static Strategy strategy;
     public static  String strategy(){


         strategy = new Strategy();
         ArrayList<String> VideList = new ArrayList<String>();
         VideList.add("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
         VideList.add("http://cxy.kssws.ks-cdn.com/h265_56c26b7a7dc5f6043.mp4");

         //封装播放时段
         String videoplaytime = "18:00--19:00";
         strategy.setVideoList(VideList);
         strategy.setVideoplayTime(videoplaytime);
         String jsonString2 = JSON.toJSONString(strategy);
         return "";
    }
}
