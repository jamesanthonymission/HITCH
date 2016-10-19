package com.itproject.hitch_v1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupDriver extends AppCompatActivity implements View.OnClickListener {



    EditText EditTextusername, EditTextpassword,etConfirm,etNickname,etAge;
    Button signin,login;

    String email,password;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_driver);

        java.text.DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        EditTextusername = (EditText)findViewById(R.id.username);
        EditTextpassword = (EditText)findViewById(R.id.password);
        etConfirm = (EditText)findViewById(R.id.con_password);
        etNickname = (EditText)findViewById(R.id.nickname);
        etAge = (EditText)findViewById(R.id.age);



        signin = (Button) findViewById(R.id.signup);

        signin.setOnClickListener(this);
    }


    private void registerUser(){
        String email = EditTextusername.getText().toString().trim();
        String password = EditTextpassword.getText().toString().trim();
        String confirmPass = etConfirm.getText().toString().trim();
        String name = etNickname.getText().toString().trim();
        String age = etAge.getText().toString().trim();


        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please Enter email", Toast.LENGTH_LONG).show();

            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_LONG).show();
            return;
        }

//        if(TextUtils.isEmpty(confirmPass)){
//            Toast.makeText(this, "Please Enter email", Toast.LENGTH_LONG).show();
//
//            return;
//        }
//
//        if(TextUtils.isEmpty(name)){
//            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_LONG).show();
//            return;
//        }
//
//        if(TextUtils.isEmpty(age)){
//            Toast.makeText(this, "Please Enter email", Toast.LENGTH_LONG).show();
//
//            return;
//        }




        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignupDriver.this, "Sucessfully Registered", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignupDriver.this,LoginDriver.class ));
                        }
                        else{
                            Toast.makeText(SignupDriver.this, "Could not register 😞", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }



    @Override
    public void onClick(View view) {
        if(view == signin){
            registerUser();
        }

    }

}