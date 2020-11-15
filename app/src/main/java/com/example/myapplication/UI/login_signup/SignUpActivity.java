package com.example.myapplication.UI.login_signup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Models.User;
import com.example.myapplication.R;
import com.example.myapplication.database.DataBase;
import com.google.android.material.textfield.TextInputLayout;

import java.util.concurrent.atomic.AtomicReference;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    DataBase db;
    private TextInputLayout inputTextEmail;
    private TextInputLayout inputTextPassword;
    private TextInputLayout inputTextLname;
    private TextInputLayout inputTextFname;
    AtomicReference<String> userID;
    String mail,fName,lName,uPassword;
    private android.content.SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        db = new DataBase(this);
        inputTextEmail = findViewById(R.id.text_input_email);
        inputTextFname = findViewById(R.id.text_input_firstName);
        inputTextLname = findViewById(R.id.text_input_lastName);
        inputTextPassword = findViewById(R.id.text_input_password);
        Button submit=findViewById(R.id.submit);
        submit.setOnClickListener(this);
         userID=new AtomicReference<String>("-1");
        sharedPreferences = getSharedPreferences(User.PREFERENCE_NAME, Context.MODE_PRIVATE);



    }

    private boolean validateMail() {
         mail = inputTextEmail.getEditText().getText().toString().trim();
        if (mail.isEmpty()) {
            inputTextEmail.setError("Field can't be empty!");
            return false;
        }
        else if(db.find("usermail",mail,null,userID)){
            inputTextEmail.setError("User Already Exesit");
            return false;
        }

        else {
            inputTextEmail.setError(null);

            return true;
        }
    }

    private boolean validateFName() {
         fName = inputTextFname.getEditText().getText().toString().trim();
        if (fName.isEmpty()) {
            inputTextFname.setError("Field can't be empty!");
            return false;
        } else if (fName.length() > 15) {
            inputTextFname.setError("First Name is too long!");
            return false;
        } else {
            inputTextFname.setError(null);
            return true;
        }
    }

    private boolean validateLName() {
         lName = inputTextLname.getEditText().getText().toString().trim();
        if (lName.isEmpty()) {
            inputTextLname.setError("Field can't be empty!");
            return false;
        } else if (lName.length() > 15) {
            inputTextLname.setError("Last Name is too long!");
            return false;
        } else {
            inputTextLname.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
         uPassword = inputTextPassword.getEditText().getText().toString().trim();
        if (uPassword.isEmpty()) {
            inputTextPassword.setError("Field can't be empty!");
            return false;
        } else {
            inputTextPassword.setError(null);
            return true;
        }
    }



    @Override
    public void onClick(View view) {
        if (!validateFName() | !validateLName() | !validateMail() | !validatePassword()) {
            return;
        }
        String input = "First name:" + inputTextFname.getEditText().getText().toString();
        input += "\n";
        input += "Last name:" + inputTextLname.getEditText().getText().toString();
        input += "\n";
        input += "Email:" + inputTextEmail.getEditText().getText().toString();
        input += "\n";
        input += "Password:" + inputTextPassword.getEditText().getText().toString();
        Toast.makeText(SignUpActivity.this, input, Toast.LENGTH_SHORT).show();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(User.EMAIL, mail);
        editor.putString(User.Fname,fName);
        editor.putString(User.Lname,lName);
        editor.apply();




        db.createuser(mail,uPassword,fName,lName);
        Intent intent=new Intent(SignUpActivity.this,LoginActivity.class);
        startActivity(intent);
    }
}



