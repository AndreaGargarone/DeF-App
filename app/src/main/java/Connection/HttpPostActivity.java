package Connection;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class HttpPostActivity extends Activity {

    private class Connection extends AsyncTask {

        @Override
        protected Object doInBackground(Object... arg0) {
            connect();
            return null;
        }

    }

    private String connect() {
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet("http://www.aea-digital.it/dbConnection.php");
            HttpResponse response = client.execute(request);
            return response.toString();
        } catch (ClientProtocolException e) {
            Log.d("HTTPCLIENT", e.getLocalizedMessage());
        } catch (IOException e) {
            Log.d("HTTPCLIENT", e.getLocalizedMessage());
        }
        return "Ciao";
    }
}



/*
    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textviewDatiRicevuti = (TextView) findViewById(R.id.datiRicevuti);
        Button buttonInviaDati = (Button) findViewById(R.id.buttonInviaDati);
        buttonInviaDati.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //invio richiesta
                textviewDatiRicevuti.setText(inviaDati());
            }
        });
    }
    public String inviaDati(){
        String result = "";
        String stringaFinale = "";
        String query = "SELECT * FROM Iscritti WHERE id = 1";
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("nameValuePairs", query)); //???Da verificare e capire cosa è???
        InputStream is = null;
        //http post
        try{
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://www.aea-digital.it/dbConnection.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        }catch(Exception e){
            Log.e("TEST", "Errore nella connessione http "+e.toString());
        }
        if(is != null){
            //converto la risposta in stringa
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
                    stringaFinale = json_data.getInt("id") + " "+ json_data.getString("cognome") + " "+ json_data.getInt("anno") + "\n\n";
                }
            }
            catch(JSONException e){
                Log.e("log_tag", "Error parsing data "+e.toString());
            }
        }
        else{//is è null e non ho avuto risposta
            return "Nessuna risposta";
        }
        return stringaFinale;
    }*/
