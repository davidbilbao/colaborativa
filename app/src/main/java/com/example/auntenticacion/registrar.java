package com.example.auntenticacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.annotation.NonNull;

import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class registrar extends AppCompatActivity {
    EditText et_mail,et_pass;
    Button btnregis;
    FirebaseAuth firebaseAuth;
    AwesomeValidation awesomeValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth= FirebaseAuth.getInstance();
        awesomeValidation=new AwesomeValidation(ValidationStyle.BASIC);
       awesomeValidation.addValidation(this,R.id.et_mail, Patterns.EMAIL_ADDRESS,R.string.invalid_mail);
        awesomeValidation.addValidation(this,R.id.et_pass,".{6,}",R.string.invalid_password);
        setContentView(R.layout.activity_registrar);
        et_mail=findViewById(R.id.et_mail);
        et_pass=findViewById(R.id.et_pass);
        btnregis=findViewById(R.id.button2);
        btnregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String mail = et_mail.getText().toString();
            String pass = et_pass.getText().toString();
            if(awesomeValidation.validate()){
                firebaseAuth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(registrar.this, "El usuario se ha creado", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(registrar.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }else{
                Toast.makeText(registrar.this, "Complete los datos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}