package me.dzed.uwscholarshipfinder;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NoInternetActivity extends AppCompatActivity {

    private TextView timeView;
    private Intent intent;

    static NoInternetActivity noInternetActivity;

    public static NoInternetActivity getInstance(){
        return noInternetActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet);

        // Kill MainActivity
        noInternetActivity = this;
        MainActivity.getInstance().finish();

        // Display error message
        String time = this.getIntent().getStringExtra("time");
        timeView = (TextView) findViewById(R.id.time);
        timeView.setText("Last Try: " + time);

        Button button = (Button) findViewById(R.id.retry);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (isNetworkAvailable()) {
                    intent = new Intent(NoInternetActivity.this,
                            MainActivity.class);
                    startActivity(intent);
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat
                            ("yyyy/MM/dd HH:mm:ss");
                    String time = sdf.format(new Date());
                    timeView.setText("Last Try: " + time);
                }
            }
        });
    }

    // Check if network connection is available
    private boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
