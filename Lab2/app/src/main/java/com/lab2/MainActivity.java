package com.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.lab2.models.Product;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddFragment.OnFragmentSendDataListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSendData(ArrayList<Product> selectedItem) {
        OutputFragment fragment = (OutputFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_output);
        if (fragment != null)
            fragment.setSelectedItem(selectedItem);
    }
}