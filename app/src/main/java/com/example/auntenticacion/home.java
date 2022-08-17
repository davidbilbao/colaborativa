package com.example.auntenticacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class home extends AppCompatActivity {
    Button salir,documents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        documents=findViewById(R.id.documents);
        salir=findViewById(R.id.salir);
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(home.this, "sesion cerrada", Toast.LENGTH_SHORT).show();
                gologin();
            }
        });
        documents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(home.this,archivos.class);
                startActivity(i);
            }
        });
    }
    private void gologin(){
        Intent i= new Intent(this,MainActivity.class);

        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}