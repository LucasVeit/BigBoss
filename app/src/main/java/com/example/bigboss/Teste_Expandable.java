package com.example.bigboss;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

public class Teste_Expandable extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste_expandable);
          getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Perguntas Frequentes");


    }


    public void showmyinformation(View view){
        ExpandableRelativeLayout expandableRelativeLayout = null;

        switch (view.getId()){
            case R.id.button1:
                expandableRelativeLayout = findViewById(R.id.expand1);
                break;

            case R.id.button2:
                expandableRelativeLayout = findViewById(R.id.expand2);
                break;

            case R.id.button3:
                expandableRelativeLayout = findViewById(R.id.expand3);
                break;
        }
        assert expandableRelativeLayout != null;
        expandableRelativeLayout.toggle();
    }
}
