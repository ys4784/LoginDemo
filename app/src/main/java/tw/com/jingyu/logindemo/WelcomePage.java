package tw.com.jingyu.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class WelcomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        /*
        *   執行緒
        *   postDelayed延遲幾秒執行(1000ms = 1s)
        *
        * */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //跳頁(從WelcomePage跳到MainActivity)
                Intent intent =  new Intent(WelcomePage.this , MainActivity.class );
                startActivity(intent);

                //關閉本頁(WelcomePage)
                finish();
            }
        },3000);

    }
}