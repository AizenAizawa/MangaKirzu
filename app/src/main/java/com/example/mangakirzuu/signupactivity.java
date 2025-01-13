package com.example.mangakirzuu;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class signupactivity extends AppCompatActivity {
    EditText signupUsername, signupEmail, signupPassword;
    TextView signin;
    Button signupButton;
    ImageView togglePasswordVisibility;
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupactivity);
        signupEmail = findViewById(R.id.emailup);
        signupUsername = findViewById(R.id.usernameup);
        signupPassword = findViewById(R.id.password);
        signin = findViewById(R.id.signin);
        signupButton = findViewById(R.id.signup);
        togglePasswordVisibility = findViewById(R.id.togglePasswordVisibility);

        final boolean[] isPasswordVisible = {false};

        togglePasswordVisibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPasswordVisible[0] = !isPasswordVisible[0];
                if (isPasswordVisible[0]) {
                    signupPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    togglePasswordVisibility.setImageResource(R.drawable.visibility);
                } else {
                    signupPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    togglePasswordVisibility.setImageResource(R.drawable.ic_visibility_off);
                }
                signupPassword.setSelection(signupPassword.getText().length());
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");
                String email = signupEmail.getText().toString();
                String username = signupUsername.getText().toString();
                String password = signupPassword.getText().toString();
                HelperClass helperClass = new HelperClass( email, username, password);
                reference.child(username).setValue(helperClass);
                Toast.makeText(signupactivity.this, "You have signup successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(signupactivity.this, signinActivity.class);
                startActivity(intent);
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signupactivity.this, signinActivity.class);
                startActivity(intent);
            }
        });
    }
}