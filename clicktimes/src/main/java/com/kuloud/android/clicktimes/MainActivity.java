package com.kuloud.android.clicktimes;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
    private Context mContext;
    TextView mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getApplicationContext();
        setContentView(R.layout.activity_main);
        mBtn = (TextView) findViewById(R.id.hello);
//        mBtn.setOnClickListener(new OnValidClickListener() {
//            @Override
//            public void onValidClick(View v) {
//                Toast.makeText(mContext, "onValidClick!", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onInvalidClick(View v) {
//                Toast.makeText(mContext, "onInvalidClick!", Toast.LENGTH_SHORT).show();
//            }
//        });
        mBtn.setOnClickListener(new OnCountClickListener(4, 7) {
            @Override
            protected void onCountdown(int count) {
                if (count > 0) {
                    Toast.makeText(mContext, "onCountdown: " + count, Toast.LENGTH_SHORT).show();
                } else {
                    // Do something
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
