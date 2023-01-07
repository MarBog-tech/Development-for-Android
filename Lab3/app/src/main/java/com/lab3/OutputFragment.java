package com.lab3;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.lab3.adapters.DatabaseAdapter;
import com.lab3.models.Product;

import java.util.List;
import java.util.Objects;


public class OutputFragment extends Fragment {


    String firm;
    String type;
    TextView textView;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    List<Product> products;

    public OutputFragment() {
        super(R.layout.fragment_output);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        databaseHelper = new DatabaseHelper(getContext().getApplicationContext());

        textView = view.findViewById(R.id.textView);
        textView.setMovementMethod(new ScrollingMovementMethod());
        Spinner spinnerFirm = view.findViewById(R.id.firms);
        Spinner spinnerTypes = view.findViewById(R.id.types);

        ArrayAdapter<CharSequence> adapterFirm = ArrayAdapter.createFromResource(getContext(), R.array.firms, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterTypes = ArrayAdapter.createFromResource(getContext(), R.array.types, android.R.layout.simple_spinner_item);

        adapterFirm.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFirm.setAdapter(adapterFirm);

        adapterTypes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTypes.setAdapter(adapterTypes);
        AdapterView.OnItemSelectedListener itemSelectedFirmListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                firm = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinnerFirm.setOnItemSelectedListener(itemSelectedFirmListener);

        AdapterView.OnItemSelectedListener itemSelectedTypeListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinnerTypes.setOnItemSelectedListener(itemSelectedTypeListener);
        Button okButton = (Button) view.findViewById(R.id.ok_button);
        okButton.setOnClickListener(view1 -> {
            int counter = 0;
            String product = "";
            if (firm != null){
                if(type != null){
                    for (int i = 0; i < products.size(); i++) {
                        Product productArray = products.get(i);
                        if (Objects.equals(productArray.getFirm(), firm)) {
                            if (Objects.equals(productArray.getType(), type)){
                                product += productArray.getProduct() + "\n";
                                textView.setText(product);
                                counter++;
                            }
                        }
                    }
                    if (counter == 0)
                        Toast.makeText(getContext(), "Product not found", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getContext(), "Type not selected", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(getContext(), "Firm not selected", Toast.LENGTH_SHORT).show();
        });

        Button cancelButton = (Button) view.findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(view1 -> {
            firm = null;
            type = null;
            textView.setText("");
        });

        Button addButton = (Button) view.findViewById(R.id.add_button);
        addButton.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext(),ProductActivity.class);
            startActivity(intent);
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        db = databaseHelper.getReadableDatabase();
        userCursor =  db.rawQuery("select * from "+ DatabaseHelper.TABLE, null);
        DatabaseAdapter adapter = new DatabaseAdapter(getContext());
        adapter.open();
        products = adapter.getProducts();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        db.close();
        userCursor.close();
    }
}