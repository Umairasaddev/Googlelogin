package com.example.googlelogin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    GoogleSignInOptions gsio;
    GoogleSignInClient gsic;
    ImageView googleBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        googleBtn = findViewById(R.id.google_button);
        gsio = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsic = GoogleSignIn.getClient(this,gsio);


        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signin();
            }
        });
    }

    private void signin() {
    Intent signInIntent = gsic.getSignInIntent();
    startActivityForResult(signInIntent,200);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 200){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                task.getResult(ApiException.class);
                navigatetoSecondActivity();
            } catch (ApiException e) {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }



    }

    private void navigatetoSecondActivity() {
        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
        startActivity(intent);
        finish();
    }
}





//        TextView username = findViewById(R.id.username);
//        TextView password = findViewById(R.id.passward);
//        MaterialButton button = (MaterialButton) findViewById(R.id.button);
//        TextView forgetpassword = findViewById(R.id.forgetpassword);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (username.getText().toString().equals("admin")&& password.getText().toString().equals("admin"))
//                {
//                    Toast.makeText(MainActivity.this, "Login Sucessful", Toast.LENGTH_SHORT).show();
//                }
//                else
//                {
//                    Toast.makeText(MainActivity.this, "Login Failed !", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

