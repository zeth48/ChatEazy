package com.shalam.zeth.chateazy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.SignInButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener{
    private Button send;
    private TextView message;
    private EditText textMessage;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference myData;
    private Button logout;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        progressDialog=new ProgressDialog(this);
        logout=(Button)findViewById(R.id.logout);
        send=(Button) findViewById(R.id.send);
        message=(TextView) findViewById(R.id.message);
        textMessage=(EditText) findViewById(R.id.textMessage);
        myData=firebaseDatabase.getInstance().getReference();
        send.setOnClickListener(this);
        logout.setOnClickListener(this);

        myData.child("Chat").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String chatMessage=dataSnapshot.getValue(String.class);
                message.setText(chatMessage);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public void Update()
    {
        String textMess=textMessage.getText().toString();
        String ty=new String(textMess);
        myData.child("Chat").setValue(ty);
    }

    public void SignOut(){
        progressDialog.setMessage("Logging Out...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        finish();
        startActivity(new Intent(this, MainActivity.class));

    }
    @Override
    public void onClick(View view) {
        if(view==send)
        {
            Update();
        }
        if(view==logout)
        {
            SignOut();
        }
    }
}
