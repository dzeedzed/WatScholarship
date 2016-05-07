package me.dzed.uwscholarshipfinder;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class ScholarshipActivity extends AppCompatActivity {

    private String id;
    private String title;
    private String status;
    private String value;
    private String type;
    private String enrollmentYear;
    private String eligibility;
    private String instructions;
    private String additional;
    private String description;
    private String citizenship;
    private String programs;
    private String deadlines;
    private String contact;
    private String link;


    private TextView t1;
    private TextView t2;
    private TextView t3;
    private TextView t4;
    private TextView t5;
    private TextView t6;
    private TextView t7;
    private TextView t8;
    private TextView t9;
    private TextView t10;
    private TextView t11;
    private TextView t12;
    private TextView t13;
    private TextView t14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scholarship);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        t1 = (TextView) findViewById(R.id.title);
        t2 = (TextView) findViewById(R.id.status);
        t3 = (TextView) findViewById(R.id.value);
        t4 = (TextView) findViewById(R.id.description);
        t5 = (TextView) findViewById(R.id.citizenship);
        t6 = (TextView) findViewById(R.id.contact);
        t7 = (TextView) findViewById(R.id.link);
        t8 = (TextView) findViewById(R.id.programs);
        t9 = (TextView) findViewById(R.id.deadlines);
        t10 = (TextView) findViewById(R.id.type);
        t11 = (TextView) findViewById(R.id.enrollmentYear);
        t12 = (TextView) findViewById(R.id.eligibility);
        t13 = (TextView) findViewById(R.id.instructions);
        t14 = (TextView) findViewById(R.id.additional);

        Bundle b = this.getIntent().getExtras();
        title = b.getString("title");
        status = b.getString("status");
        value = b.getString("value") + "\n";
        description = b.getString("description") + "\n";
        citizenship = b.getString("citizenship");
        contact = b.getString("contact") + "\n";
        link = b.getString("link");
        programs = b.getString("programs");
        deadlines = b.getString("deadlines") + "\n";
        type = b.getString("type");
        enrollmentYear = b.getString("enrollmentYear");
        eligibility = b.getString("eligibility");
        instructions = b.getString("instructions");
        additional = b.getString("additional");

        t1.setText(title);
        t2.setText(status);
        t3.setText(value);
        t4.setText(description);
        t5.setText(citizenship);
        t6.setText(contact);
        t7.setText(link);
        t8.setText(programs);
        t9.setText(deadlines);
        t10.setText(type);
        t11.setText(enrollmentYear);
        t12.setText(eligibility);
        t13.setText(instructions);
        t14.setText(additional);
    }



}
