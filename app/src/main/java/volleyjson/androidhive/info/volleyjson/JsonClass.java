package volleyjson.androidhive.info.volleyjson;

import volleyjson.androidhive.info.volleyjson.R;
import volleyjson.androidhive.info.volleyjson.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

public class JsonClass extends Activity {

    // json object response url
    private String urlJsonObj = "http://10.42.0.64/data/company/?format=json";

    // json array response url
    private String urlJsonArry = "http://api.androidhive.info/volley/person_array.json";

    private static String TAG = JsonClass.class.getSimpleName();
    private Button btnMakeObjectRequest, btnMakeArrayRequest;

    // Progress dialog
    private ProgressDialog pDialog;

    private TextView txtResponse;

    // temporary string to show the parsed response
    private String jsonResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        btnMakeObjectRequest = (Button) findViewById(R.id.btnObjRequest);
        txtResponse = (TextView) findViewById(R.id.txtResponse);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        btnMakeObjectRequest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // making json object request
                makeJsonObjectRequest();
            }
        });


    }

    /**
     * Method to make json object request where json response starts wtih {
     */
    /**
     * Method to make json object request where json response starts wtih {
     * */
    private void makeJsonObjectRequest() {

        showpDialog();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET,
                urlJsonObj, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {
                    // Parsing json object response
                    // response will be a json object
                    String name = response.getString("count");
                    String email = response.getString("next");
                    JSONObject[] objectJson = new JSONObject[10];
                    String[] nameOfUser = new String[10];
                    String[] quote  = new String[10];
                    JSONArray arrayJson = response.getJSONArray("results");
                    jsonResponse = "";
                    jsonResponse += "Count: " + name + "\n\n";
                    jsonResponse += "Next: " + email + "\n\n";

                    for(int i = 0;i<10;i++){
                        objectJson[i]   = arrayJson.getJSONObject(i);
                        nameOfUser[i] = objectJson[i].getString("name");
                        quote[i] = objectJson[i].getString("quotes");
                        jsonResponse += "Name Of User: " + nameOfUser[i] + "\n\n";
                        jsonResponse += "Quote " + quote[i] + "\n\n";
                    }


                    // String home = phone.getString("home");
                    // String mobile = phone.getString("mobile");




                    txtResponse.setText(jsonResponse);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
                hidepDialog();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                hidepDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }
    /**
     * Method to make json array request where response starts with [
     */
    /**
     * Method to make json array request where response starts with [
     * */
    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    public void showVisualizations(View view){
        Intent intent = new Intent(JsonClass.this,ChartsClass.class);
        startActivity(intent);
    }
}