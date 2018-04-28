package newwater.com.newwater;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.danikula.videocache.HttpProxyCacheServer;

import org.json.JSONObject;

import java.util.Timer;

import newwater.com.newwater.adapter.VideoAdapter;
import newwater.com.newwater.utils.TimeBack;
import newwater.com.newwater.view.CustomerVideoView;
import newwater.com.newwater.view.PopWindow;
import newwater.com.newwater.view.PopWindowChooseWaterGetWay;
import newwater.com.newwater.view.Pop_LeftOperate;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView exit;
    private TextView dixieccup;//纸杯和我要饮水按钮
    private Boolean operateornot  = false;
    public static  LinearLayout leftoperate;//左边操作区域
    public static LinearLayout rightoperate;//右边操作区域

    public static ImageView  wantwater;//我要饮水

    //popwindow操作
    private View contentView;
    private LinearLayout leftpop;
    private LinearLayout rightpop;


    //出杯子的提示
    private View outCupView;
    private RelativeLayout outcupleft;
    private RelativeLayout outcupright;

    private  PopWindow popChooseWater;
    private PopWindowChooseWaterGetWay popChooseWatera;


    private ImageView qrcode;

    //VideoView
    private CustomerVideoView videoplay;

    private FrameLayout root;

    //viewpager 视频
    private ViewPager viewpager;

    private Timer timer;//定时器，用于实现轮播

    static int pos = 0;
    HttpProxyCacheServer proxy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void initView(){
        wantwater = (ImageView) findViewById(R.id.wantwater);
        wantwater.setOnClickListener(this);
        //初次进入界面隐藏所有操作界面，显示我要饮水和广告
        leftoperate = (LinearLayout) findViewById(R.id.leftoperate);
        leftoperate.setVisibility(View.GONE);

        rightoperate = (LinearLayout) findViewById(R.id.rightoperate);
        rightoperate.setVisibility(View.GONE);

        exit = (TextView) findViewById(R.id.exit);
        exit.setOnClickListener(this);
        dixieccup = findViewById(R.id.dixieccup);
        dixieccup.setOnClickListener(this);
        root = (FrameLayout) findViewById(R.id.root);
//        popwindow的初始化
        //出热水的时候的警告框框

//        outCupView = LayoutInflater.from(MainActivity.this).inflate(R.layout.prompt_pop,null);
//        outcupleft = (RelativeLayout) outCupView.findViewById(R.id.outcupleft);
//        outcupright = (RelativeLayout) outCupView.findViewById(R.id.outcupright);
//        outcupleft.setOnClickListener(this);



        videoplay = (CustomerVideoView) findViewById(R.id.playvideo);
        videoplay.setZOrderOnTop(true);

        //视频播放


//        viewpager = (ViewPager) findViewById(R.id.viewpage);
        proxy = App.getProxy(MainActivity.this);
        playVideo();



    }




        public void playVideo(){
          //是否需要播放的资源


        final int maxloop;
        String test = TestJSON.strategy();
        Log.e("test","test"+test);
        com.alibaba.fastjson.JSONObject testobj = JSON.parseObject(test);
        String videoListString = testobj.getString("videoList");
        final JSONArray videolist = JSON.parseArray(videoListString);
        //循环

        maxloop = videolist.size();

//        for(int i=0;i<videolist.size();i++){
//            Log.e("test1","test1"+videolist.get(i));
//        }
//        VideoAdapter videoAdapter = new VideoAdapter(MainActivity.this,videolist);
//        viewpager.setAdapter(videoAdapter);

//        Uri data = Uri.parse("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");

        String proxyUrl = proxy.getProxyUrl(videolist.getString(0));
        videoplay.setVideoPath(proxyUrl);
        videoplay.start();

//        videoplay.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                mp.start();
//                mp.setLooping(true);
//
//            }
//        });

             final int videoflag = 0;//标志播放次数
            videoplay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mPlayer) {
                // TODO Auto-generated method stub
                pos = pos+1;
                Log.e("pos","pos"+pos);
                Log.e("maxloop","maxloop"+maxloop);

                if(pos==maxloop){
                    pos =0;
                    String proxyUrl = proxy.getProxyUrl(videolist.getString(pos));
                    videoplay.setVideoPath(proxyUrl);
                    videoplay.start();
                }else{
                    videoplay.setVideoPath(videolist.getString(pos));
                    videoplay.start();
                }
//                mPlayer.start();
//                mPlayer.setLooping(true);

            }
        });


        popChooseWater = new PopWindow(MainActivity.this);
        contentView = LayoutInflater.from(MainActivity.this).inflate(R.layout.free_pay_pop, null);


    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.exit:
                //
                TimeBack timeback = new TimeBack(exit,30000,1000);
                break;

            case R.id.dixieccup:
                PopWindow popWindow = new PopWindow(MainActivity.this);
                popWindow.showPopupWindow(new View(MainActivity.this));
                break;

            case R.id.leftpop:
                //免费喝水跳转到广告，倒计时然后进入操作界面，隐藏操作界面
                leftoperate.setVisibility(View.GONE);
                rightoperate.setVisibility(View.GONE);
                break;

            case R.id.rightpop:
                //弹出二维码


                break;

            case R.id.wantwater:
//                if(false ==operateornot){
//                    operateornot = true;
//                    leftoperate.setVisibility(View.GONE);
//                    rightoperate.setVisibility(View.GONE);
//                }else{
//                    operateornot = false;
//                    leftoperate.setVisibility(View.VISIBLE);
//                    rightoperate.setVisibility(View.VISIBLE);
//                }

                popChooseWater.showPopupWindow(new View(MainActivity.this));
        }

    }
}
