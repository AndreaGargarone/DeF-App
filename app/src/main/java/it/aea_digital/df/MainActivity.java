package it.aea_digital.df;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import Connection.Connection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //commento di prova
        //HttpPostActivity httpInstance = new HttpPostActivity();
        TextView myText = (TextView) findViewById(R.id.myText);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Connection conn = new Connection();
        conn.execute();

        myText.setText(conn.connect());

    }
}
