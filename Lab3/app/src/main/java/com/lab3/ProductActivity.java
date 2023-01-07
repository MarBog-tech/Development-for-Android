package com.lab3;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.lab3.adapters.DatabaseAdapter;
import com.lab3.adapters.ProductAdapter;
import com.lab3.models.Product;

import java.util.List;

public class ProductActivity extends AppCompatActivity{

    String firm;
    String type;
    String product;
    Button delButton;
    EditText editText;
    DatabaseAdapter adapter;
    int userId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        delButton = findViewById(R.id.deleteButton);
        editText = findViewById(R.id.products);
        adapter = new DatabaseAdapter(this);
        delButton.setVisibility(View.GONE);

        Spinner spinnerFirm = findViewById(R.id.firms);
        Spinner spinnerTypes = findViewById(R.id.types);

        ArrayAdapter<CharSequence> adapterFirm = ArrayAdapter.createFromResource(this, R.array.firms, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterTypes = ArrayAdapter.createFromResource(this, R.array.types, android.R.layout.simple_spinner_item);

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

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                product = editable.toString();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        DatabaseAdapter adapter = new DatabaseAdapter(this);
        adapter.open();

        List<Product> product = adapter.getProducts();
        if(product.size() == 0)
            Toast.makeText(this, "database is empty", Toast.LENGTH_SHORT).show();

        RecyclerView recyclerView = findViewById(R.id.list);
        ProductAdapter productAdapter = new ProductAdapter(this, product, new ProductAdapter.OnProductClickListener() {

            @Override
            public void onProductClick(Product product, int position) {
                delButton.setVisibility(View.VISIBLE);
                userId = product.getId();
                editText.setText(product.getProduct());
            }
        });
        recyclerView.setAdapter(productAdapter);
        adapter.close();
    }

    public void save(View view){

        Product products = new Product(userId, firm, type, product);

        adapter.open();
        if (userId > 0) {
            adapter.update(products);
        } else {
            adapter.insert(products);
        }
        adapter.close();
        goHome();
    }
    public void delete(View view){

        adapter.open();
        adapter.delete(userId);
        if(adapter.getCount() == 0)
            Toast.makeText(this, "database is empty", Toast.LENGTH_SHORT).show();
        adapter.close();
        goHome();
    }
    private void goHome(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}