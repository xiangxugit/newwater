package newwater.com.newwater;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import newwater.com.newwater.utils.TimeBack;
import newwater.com.newwater.view.PopWindow;
import newwater.com.newwater.view.PopWindowChooseWaterGetWay;

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



//        popwindow的初始化
        popChooseWater = new PopWindow(MainActivity.this);
        contentView = LayoutInflater.from(MainActivity.this).inflate(R.layout.free_pay_pop, null);






        //出热水的时候的警告框框

//        outCupView = LayoutInflater.from(MainActivity.this).inflate(R.layout.prompt_pop,null);
//        outcupleft = (RelativeLayout) outCupView.findViewById(R.id.outcupleft);
//        outcupright = (RelativeLayout) outCupView.findViewById(R.id.outcupright);
//        outcupleft.setOnClickListener(this);


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
