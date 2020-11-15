package com.example.myapplication.UI.login_signup;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.database.DataBase;
import com.example.myapplication.R;
import com.example.myapplication.Models.User;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class Modalbottomsheet extends BottomSheetDialogFragment implements View.OnClickListener {
    TextInputLayout InputText1;
    TextInputLayout InputText2;
    EditText editText1;
    EditText editText2;
    TextView textview;
    String stage = "confirmMail";
    DataBase db;
    String verificationCode = "0007";
    AtomicReference<String> userID = new AtomicReference<String>("-1");
    User user;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.forget_password_sheet, container, false);
        Button B = v.findViewById(R.id.button);
        InputText1 = v.findViewById(R.id.editt1);
        InputText2 = v.findViewById(R.id.editt2);
        editText1 = v.findViewById(R.id.e1);
        editText2 = v.findViewById(R.id.e2);
        textview = v.findViewById(R.id.forget_pass_tv);
        InputText2.setVisibility(View.GONE);
        B.setOnClickListener(this);
        db = new DataBase(getContext());
        return v;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button) {
            switch (stage) {
                case "confirmMail": {
                    textview.setText("Enter email linked to this account");
                    String mail = Objects.requireNonNull(InputText1.getEditText()).getText().toString().trim();
                    if (!db.find("usermail", mail, null, userID))
                        InputText1.setError("No User Found");
                    else {
                        //sent intent with code
                        InputText1.setError(null);
                        editText1.setText("");
                        InputText1.setEndIconMode(TextInputLayout.END_ICON_CLEAR_TEXT);
                        stage = "confirmCode";//go to next  stage
                        textview.setText("Enter verification code ");
                        InputText1.setHint("Enter code");
                        //InputText1
                        // .

                    }
                }
                break;
                case "confirmCode": {
                    String code = Objects.requireNonNull(InputText1.getEditText()).getText().toString().trim();
                    if (!code.equals(verificationCode))
                        InputText1.setError("Wrong code");
                    else {
                        InputText1.setError(null);
                        editText1.setText("");
                        stage = "confirmPass";
                        textview.setText("Enter New Password ");
                        InputText2.setVisibility(View.VISIBLE);
                        InputText1.setHint("Enter the new password");
                        InputText2.setHint("Confirm the password");
                        InputText2.setEndIconMode(TextInputLayout.END_ICON_CLEAR_TEXT);
                    }

                }
                break;
                case "confirmPass": {
                    String pass1 = Objects.requireNonNull(InputText1.getEditText()).getText().toString().trim();
                    String pass2 = Objects.requireNonNull(InputText2.getEditText()).getText().toString().trim();

                    if (!pass1.equals(pass2))
                        InputText2.setError("Not matched password");
                    else {
                        InputText1.setError(null);
                        InputText2.setError(null);
                        stage = "end";
                        Cursor cr = db.show("UserByid", userID.toString());
                        user = new User(cr.getString(0), cr.getString(1), cr.getString(2), cr.getString(3), pass1);
                        db.updatUpassword(user);
                        Toast.makeText(getContext(), R.string.pass_Alreadychange_toast, Toast.LENGTH_LONG).show();
                        this.dismiss();
                    }
                }
                break;
                case "end": {
                    Toast.makeText(getContext(), R.string.pass_Alreadychange_toast, Toast.LENGTH_LONG).show();
                    this.dismiss();
                }
                break;

            }


        }
    }
}
