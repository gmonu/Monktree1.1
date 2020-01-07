package com.example.monktree11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration_Activity extends AppCompatActivity {

    private EditText username, useremail, userpassword;
    private TextView already_Signed;
    private Button register_button;

    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_);

        setupUIview();

        firebaseAuth = FirebaseAuth.getInstance();

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    //Upload the data to the database
                    String user_email = useremail.getText().toString().trim();
                    String user_password = userpassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(Registration_Activity.this, "Registration Successfull", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Registration_Activity.this, Signin_Activity.class));
                            }
                            else {
                                Toast.makeText(Registration_Activity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        already_Signed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registration_Activity.this, Signin_Activity.class));
            }
        });


    }


    private void setupUIview(){
        username = (EditText)findViewById(R.id.etname);
        useremail = (EditText)findViewById(R.id.etemail);
        userpassword = (EditText)findViewById(R.id.etpassword);
        already_Signed = (TextView)findViewById(R.id.tvAlready);
        register_button = (Button)findViewById(R.id.bregister);


    }

    private Boolean validate()
    {
        Boolean result = false;
        String name = username.getText().toString();
        String email = useremail.getText().toString();
        String password = userpassword.getText().toString();

        if(name.isEmpty() || email.isEmpty() || password.isEmpty())
        {
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }
        else
        {
            return true;
        }
        return result;
    }
}
