package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.registration.singleton.MyGson;
import com.example.registration.ui.model.Users;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RegistrationActivity extends AppCompatActivity {

   private   EditText  fullname,username,phoneNumber,password,password2;
   private Button registerBtn;
   private SharedPreferences sharedPreferences;
   private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        findUi();
        sharedPreferences= getSharedPreferences("Users",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        registerBtn.setOnClickListener(view -> {
            if(isValid()){
                String fullName=fullname.getText().toString();
                String phoneNumber1=phoneNumber.getText().toString();
                String username1=username.getText().toString();
                String password1=password.getText().toString();
                Users u1=new Users(fullName,phoneNumber1,username1,password1);

                String usersJsonString = sharedPreferences.getString("users", "");
                List<Users> usersList;
                if (usersJsonString.equals("")){
                    usersList=new ArrayList<>();
                }else {
                    Type type = new TypeToken<List<Users>>() {
                    }.getType();
                     usersList = MyGson.getInstance().getGson().fromJson(usersJsonString,type);
                }
                usersList.add(u1);
                String jsonString = MyGson.getInstance().getGson().toJson(usersList);
                editor.putString("users",jsonString);

                if (editor.commit()){
                    Intent intent=new Intent(this,LoginActivity.class);
                    intent.putExtra("username",username1);
                    intent.putExtra("password",password1);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
        });


    }

    private boolean isValid() {
        if(fullname.getText().toString().isEmpty()){
            Toast.makeText(this, "Ism Familya kiritimagan!", Toast.LENGTH_SHORT).show();
            return false;}
        else if(phoneNumber.getText().toString().isEmpty()){
            Toast.makeText(this, "Telefon raqami kiritimagan!", Toast.LENGTH_SHORT).show();
            return false;}
        else if(username.getText().toString().isEmpty()){
            Toast.makeText(this, "Username kiritimagan!", Toast.LENGTH_SHORT).show();
            return false;}
        else if(password.getText().toString().isEmpty()){
            Toast.makeText(this, "Parol raqami kiritimagan!", Toast.LENGTH_SHORT).show();
            return false;}
        else if(password2.getText().toString().isEmpty()){
            Toast.makeText(this, "Parol raqami kiritimagan!", Toast.LENGTH_SHORT).show();
            return false;}
        else if(!password2.getText().toString().equals(password.getText().toString())){
            Toast.makeText(this, "Parolni tekshirib qayta kiriting!", Toast.LENGTH_SHORT).show();
            return false;}


        return true;
    }

    private void findUi() {
        fullname=findViewById(R.id.fullname);
        phoneNumber=findViewById(R.id.phoneNumber);
        username=findViewById(R.id.rusername);
        password=findViewById(R.id.rpassword);
        password2=findViewById(R.id.vrpassword);
        registerBtn=findViewById(R.id.loginButton);

    }


}
