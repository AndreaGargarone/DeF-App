package it.aea_digital.df;

import android.graphics.drawable.ColorDrawable;
import android.os.StrictMode;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import Connection.Connection;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // nascondere action bar
        //getSupportActionBar().hide();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_rincipale, menu);
        return true;
    }
}
