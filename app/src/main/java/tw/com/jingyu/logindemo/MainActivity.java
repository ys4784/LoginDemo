package tw.com.jingyu.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText userId,userPwd;
    private TextView result;
    private Button sendBtn, cancelBtn;
    private CheckBox remMe;

    private Button btn1,btn2,btn3;

    private TextView touchMe;
    private LinearLayout layout1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        /*
        *   ctrl + alt + m 快速建立function
        *   選取要收入function的程式碼，按ctrl + alt + m，會自動增加所需的函式
        * */

    }

    //自定的私有化方法
    private void findViews(){
        userId = findViewById(R.id.userId);
        userPwd = findViewById(R.id.userPwd);
        result = findViewById(R.id.result);
        sendBtn = findViewById(R.id.sendBtn);
        cancelBtn = findViewById(R.id.cancelBtn);
        remMe = findViewById(R.id.remMe);   //核取方塊

        //預設核取方塊不勾選
        remMe.setChecked(false);

        //註冊監聽(叫用函式)
        sendBtn.setOnClickListener(sendListener);

        //註冊監聽(匿名類別)
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userId.setText("");
                userPwd.setText("");
            }
        });

        //註冊監聽(Lambda表示式)
        remMe.setOnClickListener(v -> {
            if(remMe.isChecked()){
                //isChecked() : 核取方塊已勾選
                result.setText("已勾選");
            }else {
                result.setText("");
            }
        });

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);

        btn1.setOnClickListener(btnLis);
        btn2.setOnClickListener(btnLis);
        btn3.setOnClickListener(btnLis);

        touchMe = findViewById(R.id.touchMe);
        layout1 = findViewById(R.id.layout1);

        //使用物件方式(new 物件)來處理控制
        touchMe.setOnTouchListener(new MyTouch());
        layout1.setOnTouchListener(new MyTouch());

        userPwd.setOnFocusChangeListener(new MyFocus());

    }
    private View.OnClickListener sendListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String uid = userId.getText().toString();
            String upwd = userPwd.getText().toString();
            result.setText(uid + "-" +upwd);
        }
    };  //匿名類別

    //用Lambda表示式控制監聽
    private View.OnClickListener btnLis = v -> {
        switch (v.getId()){
            case R.id.btn1:
                result.setText("按鈕1被點擊");
                break;
            case R.id.btn2:
                result.setText("按鈕2被點擊");
                break;
            case R.id.btn3:
                result.setText("按鈕3被點擊");
                break;
        }
    };

    //用物件控制監聽(touchMe)
    private class MyTouch implements View.OnTouchListener{

        //touch的三種可能性 : down , move , up
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            int action = event.getAction();

            if (action == MotionEvent.ACTION_DOWN){
                result.setText("Down");

            }else if (action == MotionEvent.ACTION_MOVE){
                //result.setText("Move");
                int pointCount = event.getPointerCount();
                StringBuffer sb = new StringBuffer();

                for (int i = 0 ; i < pointCount ; i++){
                    sb.append("點:" + event.getPointerId(i) + ":" +
                            (int) event.getX(i) + "-" +
                            (int) event.getY(i));

                    if (i < pointCount-1)   sb.append("\n");
                }
                result.setText(sb.toString());

            }else if (action == MotionEvent.ACTION_UP){
                result.setText("Up");
            }
            return true;    //  return true持續反應，return false只做一次
        }
    }

    //用物件控制監聽(touchMe)
    private class MyFocus implements View.OnFocusChangeListener{

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            //onFocusChange : 常用於驗證，當離開當前元件(比如Text)時，驗證內容是否合語法
            result.setText(hasFocus ? "關注":"離開");
        }
    }

}