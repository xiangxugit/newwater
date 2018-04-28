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
         VideList.add("http://flv2.bn.netease.com/videolib3/1604/28/fVobI0704/SD/fVobI0704-mobile.mp4");
         VideList.add("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
         VideList.add("http://mirror.aarnet.edu.au/pub/TED-talks/911Mothers_2010W-480p.mp4");
         //封装播放时段
         String videoplaytime = "18:00--19:00";
         strategy.setVideoList(VideList);
         strategy.setVideoplayTime(videoplaytime);
         String jsonString2 = JSON.toJSONString(strategy);
         return jsonString2;
    }
}
