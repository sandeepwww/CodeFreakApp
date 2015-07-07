package com.example.sandeepwww.codefreakfinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;


public class Splash extends AppCompatActivity{

    private static String url ="http://codefreak-json.herokuapp.com/";


    String[] upStartTime,upEndTime,upName,upPlatform,upDuration,upURL,upChallengeType;

    String[] onEndTime,onName,onPlatform,onURL,onChallengeType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);


        ImageView im=(ImageView) findViewById(R.id.splashimage);
        im.setBackgroundResource(R.drawable.splash2);
        AsyncHttpClient client = new AsyncHttpClient();

        client.setTimeout(10000);

        // Have the client get a JSONArray of data
        // and define how to respond
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                // called before request is started

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"

                try {
                    String jsonStr = new String(response, "UTF-8");
                    Log.d("Response: ", "> " + jsonStr);
                    GetJsonData(jsonStr);

                    Intent mainIntent = new Intent(Splash.this, TabAnimationActivity.class);

                    mainIntent.putExtra("OET",onEndTime);
                    mainIntent.putExtra("ONAME",onName);
                    mainIntent.putExtra("OURL",onURL);
                    mainIntent.putExtra("OCT",onChallengeType);
                    mainIntent.putExtra("OPF",onPlatform);

                    mainIntent.putExtra("UET",upEndTime);
                    mainIntent.putExtra("UST",upStartTime);
                    mainIntent.putExtra("UNAME",upName);
                    mainIntent.putExtra("UURL",upURL);
                    mainIntent.putExtra("UCT",upChallengeType);
                    mainIntent.putExtra("UPF",upPlatform);
                    mainIntent.putExtra("UDUR",upDuration);

                    startActivity(mainIntent);
                    finish();


                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Intent myIntent = new Intent(Splash.this, RetryActivity.class);
                startActivity(myIntent);
                finish();
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });

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


    protected void GetJsonData(String jsonStr) {

        if (jsonStr != null) {
            try {
                // Checking for SUCCESS TAG
                JSONObject jsonObj = new JSONObject(jsonStr);

                // Getting JSON Array node

                String val;

                JSONObject jsonObj2 = jsonObj.getJSONObject("result");

                //System.out.println(jsonObj);

                JSONArray tag1 = jsonObj2.getJSONArray("ongoing");
                JSONArray tag = jsonObj2.getJSONArray("upcoming");

                //System.out.println("length============"+tag.length());

                upStartTime = new String[tag.length()];
                upEndTime = new String[tag.length()];
                upName = new String[tag.length()];
                upDuration = new String[tag.length()];
                upChallengeType = new String[tag.length()];
                upURL = new String[tag.length()];
                upPlatform = new String [tag.length()];



                onEndTime = new String[tag1.length()];
                onName = new String[tag1.length()];
                onPlatform = new String[tag1.length()];
                onChallengeType = new String[tag1.length()];
                onURL = new String[tag1.length()];

               // System.out.println("\n\n\n\n\n\n\n\n\n\n\nONGOING\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

                // looping through All Contacts
                for (int i = 0; i < tag1.length(); i++) {
                    JSONObject c = tag1.getJSONObject(i);



                    if(c.has("EndTime")) {
                        val = c.getString("EndTime");
                    }
                    else
                        val = "";
                    onEndTime[i]=val;
                    System.out.println(val);
                    if(c.has("Name")) {
                        val = c.getString("Name");
                    }
                    else
                        val = "";
                    onName[i]=val;
                    System.out.println(val);
                    if(c.has("url")) {
                        val = c.getString("url");
                    }
                    else
                        val = "";
                    onURL[i]=val;
                    System.out.println(val);

                    if(c.has("challenge_type")) {
                        val = c.getString("challenge_type");
                    }
                    else
                        val = "";
                    onChallengeType[i]=val;

                    System.out.println(val);
                    if(c.has("Platform")) {
                        val = c.getString("Platform");
                    }
                    else
                        val = "";
                    onPlatform[i]=val;

                    System.out.println(val);


                }

                //System.out.println("\n\n\n\n\n\n\n\n\n\n\nUPCOMING\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                // looping through All Contacts
                for (int i = 0; i < tag.length(); i++) {
                    JSONObject c = tag.getJSONObject(i);

                    if(c.has("StartTime")) {
                         val = c.getString("StartTime");
                    }
                    else
                        val = "";
                    upStartTime[i] = val;
                    System.out.println(val);
                    if(c.has("EndTime")) {
                        val = c.getString("EndTime");
                    }
                    else
                        val = "";
                    upEndTime[i]=val;
                    System.out.println(val);
                    if(c.has("Name")) {
                        val = c.getString("Name");
                    }
                    else
                        val = "";
                    upName[i]=val;
                    System.out.println(val);
                    if(c.has("url")) {
                        val = c.getString("url");
                    }
                    else
                        val = "";
                    upURL[i]=val;
                    System.out.println(val);
                    if(c.has("Duration")) {
                        val = c.getString("Duration");
                    }
                    else
                        val = "";
                    upDuration[i]=val;
                    System.out.println(val);
                    if(c.has("challenge_type")) {
                        val = c.getString("challenge_type");
                    }
                    else
                        val = "";
                    upChallengeType[i]=val;

                    System.out.println(val);
                    if(c.has("Platform")) {
                        val = c.getString("Platform");
                    }
                    else
                        val = "";
                    upPlatform[i]=val;

                    System.out.println(val);


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.e("ServiceHandler", "Couldn't get any data from the url");
        }
    }


}