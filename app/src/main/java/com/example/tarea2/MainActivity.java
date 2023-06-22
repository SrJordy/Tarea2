package com.example.tarea2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import WebService.Asynchtask;
import WebService.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new
                WebService("https://dummyjson.com/users",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        TextView txt=(TextView) findViewById(R.id.id);
        String dat="";
        String list="";
        JSONObject res=new JSONObject(result);
        dat=res.getString("users");
        JSONArray JSONlista = new JSONArray(dat);
        for(int i=0; i< JSONlista.length();i++){
            JSONObject Lista= JSONlista.getJSONObject(i);
            list = list + "\n" +
                    Lista.getString("id").toString()+"--"+Lista.getString("firstName")+
                    "--"+Lista.getString("age")+"--"+Lista.getString("email");
        }
        txt.setText(list);
    }
}