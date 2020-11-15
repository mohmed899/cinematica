package com.example.myapplication.UI.login_signup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Models.User;
import com.example.myapplication.R;
import com.example.myapplication.UI.MainActivity;
import com.example.myapplication.database.DataBase;
import com.google.android.material.textfield.TextInputLayout;

import java.util.concurrent.atomic.AtomicReference;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    TextInputLayout Email;
    TextInputLayout Password;
   // EditText Email, Password;
    Button Login;
    TextView signUp, forgetPassword;
    DataBase db;
    CheckBox RememberMe;
    String mail, pswd , fname,lname;
    private SharedPreferences SharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Email = findViewById(R.id.loginMail);
        Password = findViewById(R.id.loginPassword);
        Login = findViewById(R.id.Login);
        RememberMe = findViewById(R.id.RememberMe);
        signUp = findViewById(R.id.sign_up);
        forgetPassword = findViewById(R.id.forget_password);
        Login.setOnClickListener(this);
        signUp.setOnClickListener(this);
        forgetPassword.setOnClickListener(this);
        db = new DataBase(this);
        SharedPreferences = getSharedPreferences(User.PREFERENCE_NAME, Context.MODE_PRIVATE);
        if (SharedPreferences.getBoolean(User.REMEMBER_ME, false))
            goToMainActivity();

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.Login: {
                Log.i("check ", "im in log in");
                AtomicReference<String> userID = new AtomicReference<String>("-1");
                mail = Email.getEditText().getText().toString().trim();
                pswd = Password.getEditText().getText().toString().trim();
                if (db.find("usermail", mail, null, userID)) { //check if exist user
                    if (!mail.isEmpty() && !pswd.isEmpty()) {
                         // get user fname and lname
                        Cursor cr=db.show("UserByid",userID.toString());
                        if(cr.getCount()==1)
                        {
                            fname=cr.getString(1);
                            lname=cr.getString(2);

                        }
                        SharedPreferences.Editor editor = SharedPreferences.edit();
                        //editor.putString(User.EMAIL, mail);
                        editor.putString(User.ID, userID.toString());
                        editor.putBoolean(User.REMEMBER_ME, RememberMe.isChecked());
                        editor.putString(User.Fname,fname);
                        editor.putString(User.Lname,lname);
                        editor.putString(User.EMAIL, mail);
                        editor.apply();



                        if (db.IsUpasswordMatch(userID.toString(), pswd))
                            goToMainActivity();
                        else
                           // Toast.makeText(LoginActivity.this, "wrong pass  " +pswd, Toast.LENGTH_LONG).show();
                        Password.setError("wrong password");
                        Email.setError(null);
                    } else
                        //Toast.makeText(LoginActivity.this, "Epty field", Toast.LENGTH_LONG).show();
                        Password.setError("Empty field");
                } else {
                    Toast.makeText(LoginActivity.this, "Wrong E-mail NO such user",
                            Toast.LENGTH_LONG).show();
                    Email.setError("No such user!");

                }

            }
            break;
            case R.id.sign_up: {
                Log.i("check ", "im in signup");
                Intent intent = new Intent(this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
            break;
            case R.id.forget_password: {
                Log.i("check ", "im in forget");
                Modalbottomsheet bottomSheet = new Modalbottomsheet();
                bottomSheet.show(getSupportFragmentManager(), "im here mbs");
            }
            break;
        }
    }

    private void goToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}