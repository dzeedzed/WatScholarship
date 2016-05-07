package me.dzed.uwscholarshipfinder;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // API key, API URL, and json format
    private final String API_KEY = "3ea2436df77a555842dc8fff85a2a841";
    private final String FORMAT = "json";
    private final String URL = "https://api.uwaterloo.ca/v2/awards/undergraduate.";

    // Error Tags
    private final String TAG_NULL = "NULL EVENTLIST";
    private final String TAG_CLICKED = "CLICKED LIST";
    private final String TAG_JSON_ERROR = "JSON error";

    // Scholarship List
    public List<Scholarship> scholarshipList;

    // Context and Intent
    private Context context;
    private Intent intent;
    private FetchScholarshipsTask task;

    // Scholarship listView and recylclerView
    private ListView listView;
    private RecyclerView recyclerView;

    // Faculty filter booleans
    private boolean math;
    private boolean engineering;
    private boolean arts;
    private boolean ahs;
    private boolean science;
    private boolean environment;

    static MainActivity mainActivity;

    public static MainActivity getInstance(){
        return mainActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO
        // 1. Set up menu in top bar
        // 2. filter for faculties
        // 3. filter for years

        math = true;
        mainActivity = this;

        if (!isNetworkAvailable()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String time = sdf.format(new Date());
            intent = new Intent(MainActivity.this, NoInternetActivity.class);
            intent.putExtra("time", time);
            startActivity(intent);
        } else {

            // Kill NoInternetActivity
            if (NoInternetActivity.getInstance() != null) {
                NoInternetActivity.getInstance().finish();
            }

            // Set up listView for scholarships
            listView = (ListView) findViewById(R.id.listView1);


            context = getApplicationContext();

            task = new FetchScholarshipsTask();
            task.execute();



            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent,
                                        View view, int position, long id) {
                    intent = new Intent(MainActivity.this, ScholarshipActivity.class);
                    Bundle b = putBundle(scholarshipList.get(position));
                    intent.putExtras(b);
                    startActivity(intent);
                }
            });
        }

    }

    private Bundle putBundle(Scholarship scholarship) {
        Bundle b = new Bundle();
        b.putString("title", scholarship.getTitle());
        b.putString("status", scholarship.getStatus());
        b.putString("value", scholarship.getValue());
        b.putString("type", scholarship.getType());
        b.putString("enrollmentYear", scholarship.getEnrollmentYear());
        b.putString("eligibility", scholarship.getEligibility());
        b.putString("instructions", scholarship.getInstructions());
        b.putString("additional", scholarship.getAdditional());
        b.putString("description", scholarship.getDescription());
        b.putString("citizenship", scholarship.getCitizenship());
        b.putString("programs", scholarship.getPrograms());
        b.putString("contact", scholarship.getContact());
        b.putString("link", scholarship.getLink());
        b.putString("deadlines", scholarship.getDeadlines());
        b.putBoolean("math", scholarship.isMath());
        b.putBoolean("engineering", scholarship.isEngineering());
        b.putBoolean("arts", scholarship.isArts());
        b.putBoolean("ahs", scholarship.isAhs());
        b.putBoolean("science", scholarship.isScience());
        b.putBoolean("environment", scholarship.isEnvironment());
        return b;
    }

    // ASyncTask to get Scholarship info
    private class FetchScholarshipsTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            // Retrieve JSON String with the help of the HTTPJson class
            HTTPJson httpJson = HTTPJson.getInstance();
            String url = URL + FORMAT + "?key=" + API_KEY;
            String jsonString = httpJson.connectHTTP(url);

            // Parse JSON String, store Scholarship data
            try {
                scholarshipList = httpJson.readJSONString(jsonString);


            } catch(Exception e) {
                Log.d(TAG_JSON_ERROR, e + "");
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
//            scholarshipList = filterFaculty(scholarshipList,
//                    math, engineering, arts, ahs, science,
//                    environment);

//            ArrayAdapter<Scholarship> adapter =
//                    new ArrayAdapter<Scholarship>(context,
//                    R.layout.list_item_scholarship, scholarshipList);

            ArrayAdapter<Scholarship> adapter = new ArrayAdapter<Scholarship>(context,
                    R.layout.scholarship_list_item, scholarshipList);

            listView.setAdapter(adapter);

//            ScholarshipAdapter sa = new ScholarshipAdapter(scholarshipList);
//            recyclerView.setAdapter(sa);
        }

    }

    // Check if network connection is available
    private boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    private List<Scholarship> filterFaculty
            (List<Scholarship> scholarshipList, boolean math, boolean engineering,
             boolean arts, boolean ahs, boolean science, boolean environment) {
        List<Scholarship> newList = new ArrayList<Scholarship>();
        if (math) {
            for (int k = 0; k < scholarshipList.size(); k++) {
                if (scholarshipList.get(k).isMath()) {
                    newList.add(scholarshipList.get(k));
                }
            }
        }
        if (engineering) {
            for (int k = 0; k < scholarshipList.size(); k++) {
                if (scholarshipList.get(k).isEngineering()) {
                    newList.add(scholarshipList.get(k));
                }
            }
        }
        if (arts) {
            for (int k = 0; k < scholarshipList.size(); k++) {
                if (scholarshipList.get(k).isArts()) {
                    newList.add(scholarshipList.get(k));
                }
            }
        }
        if (ahs) {
            for (int k = 0; k < scholarshipList.size(); k++) {
                if (scholarshipList.get(k).isAhs()) {
                    newList.add(scholarshipList.get(k));
                }
            }
        }
        if (science) {
            for (int k = 0; k < scholarshipList.size(); k++) {
                if (scholarshipList.get(k).isScience()) {
                    newList.add(scholarshipList.get(k));
                }
            }
        }
        if (environment) {
            for (int k = 0; k < scholarshipList.size(); k++) {
                if (scholarshipList.get(k).isEnvironment()) {
                    newList.add(scholarshipList.get(k));
                }
            }
        }
        return newList;
    }
}
