package com.example.myapplication.UI.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.AdapterS.GeneralAdabter;
import com.example.myapplication.Models.Movie;
import com.example.myapplication.R;
import com.example.myapplication.UI.Cinema;
import com.example.myapplication.UI.CinemaDetails;
import com.example.myapplication.UI.MyIterFace;
import com.example.myapplication.database.DataBase;
import com.example.myapplication.database.DataBeasInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;

public class CinemaFragment extends Fragment implements MyIterFace, View.OnClickListener, TextWatcher, PopupMenu.OnMenuItemClickListener {
    private final int VOICE_REQUEST = 1999;
    DataBase db;
    ImageView voiceSearch;
    EditText searchField;
    List<Cinema> cinemaList;
    GeneralAdabter adapter;
    String speachResult;
    ImageView searchBtn;


//    LocationManager locationManager;
//    boolean isNetworkEnabled;
//    boolean isGPSenabled;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        cinemaList = new ArrayList<>();
        db = new DataBase(getContext());
        adapter = new GeneralAdabter(cinemaList, this, getContext(), 1, 0);
        RecyclerView view1 = view.findViewById(R.id.cinema_receycler_view);
        voiceSearch = view.findViewById(R.id.voice_search_img);
        searchField = view.findViewById(R.id.search_field);
        searchBtn = view.findViewById(R.id.search_cinema_btn);
        ImageView filterMenu = view.findViewById(R.id.filtterx);
        filterMenu.setOnClickListener(this);
        voiceSearch.setOnClickListener(this);
        searchField.addTextChangedListener(this);
        searchBtn.setOnClickListener(this);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
//        view1.setLayoutManager(gridLayoutManager);
        view1.setLayoutManager(new LinearLayoutManager(getContext()));
        view1.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), DividerItemDecoration.VERTICAL));
        view1.setAdapter(adapter);

//
//        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
//        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
//        } else {
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
//        }

//        //check for network access
//        isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//        isGPSenabled=locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        showCinema("Allcinema", "Null");
        // Log.i("Cinema frag", " im in onresume");
    }

    @Override
    public void onMovieClik(Movie movie) {
        Log.d("onclick", "Cinema clicked ");
    }

    @Override
    public void onCinemaClik(Cinema cinema) {
        Intent intent = new Intent(getContext(), CinemaDetails.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(DataBeasInfo.CinemaTable.TABLE_NAME, cinema);
        intent.putExtras(bundle);
        startActivity(intent);
        Log.i("cinema frag", "cinema price " + cinema.getTimeMoneyInfo());

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.voice_search_img) {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            startActivityForResult(intent, VOICE_REQUEST);

        } else if (view.getId() == R.id.search_cinema_btn) {

            if (speachResult != null)
                showCinema("cinemaBysearch", speachResult);
        } else if (view.getId() == R.id.filtterx) {
            PopupMenu popup = new PopupMenu(getContext(), view);
            popup.setOnMenuItemClickListener(this);
            popup.inflate(R.menu.cinema_fillter_popup);
            popup.show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VOICE_REQUEST && resultCode == RESULT_OK) {
            if (data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                if (result != null) {
                    speachResult = result.get(0);
                    searchField.setText(speachResult);
                    // showCinema("cinemaBysearch",speachResult);
                }
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        Log.i("cinemaf  text change", editable.toString());
        showCinema("cinemaBysearch", editable.toString());
    }

    private void showCinema(String mode, String filter) {
        Cursor cr = db.show(mode, filter);
        cinemaList.clear();
        cr.moveToFirst();
        while (!cr.isAfterLast()) {  //id,name,add,mocount,url,phone,3d
            cinemaList.add(new Cinema(cr.getString(0), cr.getString(1), cr.getString(2), cr.getString(3), cr.getString(4), cr.getString(5), cr.getString(6), cr.getString(7), "0"));
            Log.d("", cr.getString(6));
            cr.moveToNext();


        }
        cr.close();
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.filter_3d) {
            showCinema("3Dcinema", "yes");
        } else {
            return false;
        }

        return true;
    }


//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
//            }
//        }
//    }

//    @Override
//    public void onLocationChanged(@NonNull Location location) {
//
//    }

//    public   double calcul( )
//    {
//        Location location =null ;
//        double dis=0;
//        try {
//            if(!isGPSenabled) {
//                Log.i("is gps ","");
//                return 0;
//            }
//            if(!isNetworkEnabled){
//                Log.i("is net ","");
//                return 0;
//            }
//
//            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//            if(location!=null) {
//                Log.i(" log " + location.getLongitude() + " & ", " lan " + location.getLatitude() + " ");
//                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
//                LatLng latLng2 = new LatLng(30.020341, 31.495012);
//                dis = SphericalUtil.computeDistanceBetween(latLng, latLng2);
//                //  Location.distanceBetween(location.getLatitude(),location.getLongitude(),-29.3773660,71.3823879,arr);
//                Log.i(" res " + dis, "<----");
//            }
//        } catch (SecurityException e) {
//            Log.d("TAG", "onClick: You did not allow to access the current location");
//        }
//        return dis/1000;
//    }
}
