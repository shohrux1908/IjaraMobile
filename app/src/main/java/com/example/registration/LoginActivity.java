package com.example.registration;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.registration.singleton.MyGson;
import com.example.registration.ui.model.Users;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
   private EditText username,password;
   private Button loginbtn;
   private TextView goSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findUi();
        sharedPreferences=getSharedPreferences("Users",MODE_PRIVATE);
        editor=sharedPreferences.edit();

        loginbtn.setOnClickListener(view -> {

            String userJsonString=sharedPreferences.getString("users","");
            if (userJsonString.equals("")){
                Toast.makeText(this, "Ro'yxat bo'sh", Toast.LENGTH_SHORT).show();
            }else {
                Type type=new TypeToken<List<Users>>(){}.getType();
                List<Users> usersList=MyGson.getInstance().getGson().fromJson(userJsonString,type);

                String username1 = username.getText().toString();
                String password1 = password.getText().toString();
                boolean isHave=false;
                for (Users users : usersList) {
                    if (users.getUsername1().equals(username1) && users.getPassword1().equals(password1)){
                        isHave=true;
                        break;
                    }

                }
                if (isHave){
                Intent intent = new Intent(this,CategoryActivity.class);
                startActivity(intent);}
                else {
                    Toast.makeText(this, "username yoki parol noto'g'ri kiritildi", Toast.LENGTH_SHORT).show();
                }

            }


        });
        goSignUp.setOnClickListener(view -> {
            Intent intent = new Intent(this,RegistrationActivity.class);
           someActivityResultLauncher.launch(intent);

        });



    }
    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        String username1 = data.getStringExtra("username");
                        String password1 = data.getStringExtra("password");
                        username.setText(username1);
                        password.setText(password1);

                    }
                }
            });

    private void findUi() {
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        loginbtn=findViewById(R.id.loginButton);
        goSignUp=findViewById(R.id.signupText);

    }

}