package com.shalam.zeth.chateazy;

import android.app.ProgressDialog;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText email;
    private EditText password;
    private Button login;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email=(EditText)findViewById(R.id.email);
        password=(EditText) findViewById(R.id.password);
        login=(Button)findViewById(R.id.login);

        firebaseAuth=FirebaseAuth.getInstance();
        login.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if(view==login)
        {
            UserLogin();
        }
    }
    private void UserLogin(){
        String textEmail=email.getText().toString().trim();
        String textPassword=password.getText().toString().trim();

        if(TextUtils.isEmpty(textEmail))
        {
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(textPassword))
        {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        }

        firebaseAuth.signInWithEmailAndPassword(textEmail, textPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override

            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    finish();
                    startActivity(new Intent(MainActivity.this, ChatActivity.class));
                }

            }
        });
    }
}
