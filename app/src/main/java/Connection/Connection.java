package Connection;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Andrea Gargarone on 15/09/2017.
 */

public class Connection extends AsyncTask {

    InputStream is = null;
    String stringaFinale;
    String result = "";

    @Override
    protected Object doInBackground(Object... arg0) {
        connect();
        return null;
    }

    public String connect() {

        stringaFinale = "";

        try {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet("http://www.aea-digital.it/dbConnection.php");
            HttpResponse response = client.execute(request);
            is = response.getEntity().getContent();
            if(is != null){
                try{
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
                    StringBuilder sb = new StringBuilder();
                    String line = null;
                    while((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    is.close();
                    result=sb.toString();
                }catch(Exception e){
                    Log.e("TEST", "Errore nel convertire il risultato "+e.toString());
                }
                //parsing dei dati arrivati in formato json
                try{
                    JSONArray jArray = new JSONArray(result);

                    for(int i=0;i<jArray.length();i++){
                        JSONObject json_data = jArray.getJSONObject(i);
                        Log.i("TEST","id: "+json_data.getInt("id")+
                                ", cognome: "+json_data.getString("cognome")+
                                ", anno: "+json_data.getInt("anno")
                        );
                        stringaFinale += json_data.getInt("id") + " "+ json_data.getString("cognome") + " "+ json_data.getInt("anno") + "\n\n";
                    }
                }
                catch(JSONException e){
                    Log.e("log_tag", "Error parsing data "+e.toString());
                }
                return stringaFinale;
            } else {
                return "Sbagliato";
            }
        } catch (ClientProtocolException e) {
            Log.d("HTTPCLIENT", e.getLocalizedMessage());
        } catch (IOException e) {
            Log.d("HTTPCLIENT", e.getLocalizedMessage());
        }
        return "Ciao";
    }
}