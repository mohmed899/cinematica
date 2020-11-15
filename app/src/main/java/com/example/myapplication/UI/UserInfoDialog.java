package com.example.myapplication.UI;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.myapplication.Models.User;
import com.example.myapplication.R;
import com.example.myapplication.UI.login_signup.LoginActivity;
import com.example.myapplication.database.DataBase;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;

public class UserInfoDialog extends AppCompatDialogFragment implements View.OnClickListener {

    static int RESULT_LOAD_IMG = 0004;
    TextView userName, userEmail, logout;
    ImageView accountImage;
    DataBase db;
    String userId;
    private android.content.SharedPreferences sharedPreferences;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = Objects.requireNonNull(getActivity()).getLayoutInflater();
        View view = inflater.inflate(R.layout.user_info_dialgo, null);

        userName = view.findViewById(R.id.user_name);
        userEmail = view.findViewById(R.id.textView_user_email);
        logout = view.findViewById(R.id.Log_out);
        accountImage = view.findViewById(R.id.profile_image);
        db = new DataBase(getContext());

        //get user data from sharedPreferences
        sharedPreferences = Objects.requireNonNull(getContext()).getSharedPreferences(User.PREFERENCE_NAME, Context.MODE_PRIVATE);
        userId = sharedPreferences.getString(User.ID, "-1");
        String fname = sharedPreferences.getString(User.Fname, "First name");
        String lname = sharedPreferences.getString(User.Lname, "First name");
        String mail = sharedPreferences.getString(User.EMAIL, "First name");

        //check if user load profile img or not
        Cursor cr = db.show("UserByid", userId);
        if (cr.getString(5) != null) { //img uri

            final InputStream imageStream;
            try {
                imageStream = getActivity().getContentResolver().openInputStream(Uri.parse(cr.getString(5)));
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                accountImage.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }
        // set dialog withe value
        userName.setText(fname + " " + lname);
        userEmail.setText(mail);
        builder.setView(view).setTitle("User Info");

        logout.setOnClickListener(this);
        accountImage.setOnClickListener(this);
        return builder.create();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Log_out: {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                Objects.requireNonNull(getActivity()).finish();

            }
            break;
            case R.id.profile_image:
                getImageFromAlbum();
                break;

        }

    }

    void getImageFromAlbum() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);

    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == -1) {
            try {
                final Uri imageUri = data.getData();
                //store the uri in db
                assert imageUri != null;
                db.addUserProImg(imageUri.toString(), userId);
                final InputStream imageStream = Objects.requireNonNull(getActivity()).getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                accountImage.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "Something went wrong didnt store ", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(getContext(), "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }
}
