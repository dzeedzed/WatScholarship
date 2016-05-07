package me.dzed.uwscholarshipfinder;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.MalformedInputException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dzklavier on 2016-01-20.
 */
public class HTTPJson {

    // Field Tags
    private final String TAG_DATA = "data";
    private final String TAG_ID = "id";
    private final String TAG_TITLE = "title";
    private final String TAG_STATUS = "status";
    private final String TAG_VALUE = "value";
    private final String TAG_DESCRIPTION = "description";
    private final String TAG_CITIZENSHIP = "citizenship";
    private final String TAG_PROGRAMS = "programs";
    private final String TAG_APPLICATION = "application";
    private final String TAG_DEADLINES = "deadlines";
    private final String TAG_CONTACT = "contact";
    private final String TAG_LINK = "link";
    private final String TAG_TYPE = "type";
    private final String TAG_ENROLLMENT_YEAR = "enrollment_year";
    private final String TAG_ELIGIBILITY = "eligibility";
    private final String TAG_INSTRUCTIONS = "instructions";
    private final String TAG_ADDITIONAL = "additional";
    private final String TAG_FACULTY = "faculty";


    // Error Tags
    private final String TAG_HTTP_ERROR = "HTTP ERROR";
    private final String TAG_PARSE = "PARSE";
    private final String TAG_NULL_STRING = "NULL JSON STRING";

    private static HTTPJson instance;

    private HTTPJson() {

    }

    public static HTTPJson getInstance() {
        if (instance == null) {
            instance = new HTTPJson();
        }

        return instance;
    }

    public String connectHTTP(String url) {

        BufferedReader reader = null;
        HttpURLConnection urlConnection = null;
        String result = null;

        try {
            // Establishes connection with url
            // Create request to UW API, open the connection
            URL obj = new URL(url);
            urlConnection = (HttpURLConnection) obj.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the page using input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            // If empty inputStream, do nothing
            if(inputStream == null) {
                return null;
            }

            reader = new BufferedReader(new InputStreamReader(inputStream));
            String inputLine;

            while ((inputLine = reader.readLine()) != null) {
                buffer.append(inputLine + "\n");
            }

            if(buffer.length() == 0) {
                // Stream empty, don't parse
                return null;
            }

            // Convert StringBuffer to String, return JSON String
            result = buffer.toString();
            return result;

        } catch (MalformedURLException e) {
            Log.d(TAG_HTTP_ERROR, e + "");
            e.printStackTrace();
        } catch (IOException e) {
            Log.d(TAG_HTTP_ERROR, e + "");
            e.printStackTrace();
        } finally {
            if(urlConnection != null) {
                urlConnection.disconnect();
            }
            if(reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("HTTPJson", "Error closing stream", e);
                }
            }
        }
        return null;
    }

    public List<Scholarship> readJSONString(String jsonString) throws Exception {
        if (jsonString == null) {
            Log.d(TAG_NULL_STRING, "readJSONString method");
        }
        else {
            try {
                JSONObject reader = new JSONObject(jsonString);
                JSONArray data = reader.getJSONArray(TAG_DATA);

                List<Scholarship> tmpList = new ArrayList<Scholarship>(data.length());

                for(int i = 0; i < data.length(); i++) {
                    JSONObject read = data.getJSONObject(i);

                    String id = read.getString(TAG_ID);
                    String title = read.getString(TAG_TITLE);
                    String status = read.getString(TAG_STATUS);
                    String value = read.getString(TAG_VALUE);
                    String description = read.getString(TAG_DESCRIPTION);
                    String contact = read.getString(TAG_CONTACT);
                    String link = read.getString(TAG_LINK);

                    JSONArray citi = read.getJSONArray(TAG_CITIZENSHIP);
                    String citizenship = getArrayString(citi);

                    // Get programs
                    JSONArray prog = read.getJSONArray(TAG_PROGRAMS);
                    String programs = getArrayString(prog);

                    // Get faculty information
                    JSONArray fa = read.getJSONArray(TAG_PROGRAMS);

                    boolean math = false;
                    boolean engineering = false;
                    boolean arts = false;
                    boolean ahs = false;
                    boolean science = false;
                    boolean environment = false;

                    // Use if faculty information is available
//                    try {
//                        for (int k = 0; k < fa.length(); k++) {
//                            String temp = fa.getString(k);
//                            if (temp.equalsIgnoreCase("Mathematics")) {
//                                math = true;
//                            }
//                            if (temp.equalsIgnoreCase("Engineering")) {
//                                engineering = true;
//                            }
//                            if (temp.equalsIgnoreCase("Arts")) {
//                                arts = true;
//                            }
//                            if (temp.equalsIgnoreCase("Applied Health Science")) {
//                                ahs = true;
//                            }
//                            if (temp.equalsIgnoreCase("Science")) {
//                                science = true;
//                            }
//                            if (temp.equalsIgnoreCase("Environment")) {
//                                environment = true;
//                            }
//                            if (temp.equalsIgnoreCase("Open to any program")) {
//                                math = true;
//                                engineering = true;
//                                arts = true;
//                                ahs = true;
//                                science = true;
//                                environment = true;
//                            }
//                        }
//                    } catch (JSONException e) {
//                        Log.e("HTTPJson", "FACULTY ERROR");
//                        e.printStackTrace();
//                    }

                    // Get deadline date String
                    JSONObject dl = read.getJSONObject(TAG_DEADLINES);
                    JSONArray dlArray = dl.getJSONArray(TAG_APPLICATION);
                    String deadlines = getArrayString(dlArray);
                    // String deadlines = "N/A";
                    // deadlines = dlArray.getString(0);

                    // Get Applictaion Info
                    JSONObject app = read.getJSONObject(TAG_APPLICATION);
                    JSONArray typeArray = app.getJSONArray(TAG_TYPE);
                    String type = getArrayString(typeArray);
                    JSONArray enrollArray = app.getJSONArray(TAG_ENROLLMENT_YEAR);
                    String enrollmentYear = getArrayString(enrollArray);
                    JSONArray eligiArray = app.getJSONArray(TAG_ELIGIBILITY);
                    String eligibility = getArrayString(eligiArray);
                    JSONArray instruArray = app.getJSONArray(TAG_INSTRUCTIONS);
                    String instructions = getArrayString(instruArray);
                    JSONArray additionalArray = app.getJSONArray(TAG_ADDITIONAL);
                    String additional = getArrayString(additionalArray);

                    Scholarship sch = new Scholarship(id, title, status, value, type, enrollmentYear,
                            eligibility, instructions, additional,
                            description, citizenship, programs, deadlines, contact, link,
                            math, engineering, arts, ahs, science, environment);

                    tmpList.add(sch);
                }
                return tmpList;

            } catch(JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private String getArrayString(JSONArray jsonArray) {
        String result = "";
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                result = result + "- " + jsonArray.getString(i) + "\n";
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
