package com.lab2;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.lab2.adapters.ProductAdapter;
import com.lab2.models.Product;

import java.util.ArrayList;

public class AddFragment extends Fragment {

    interface OnFragmentSendDataListener {
        void onSendData(ArrayList<Product> data);
    }

    private OnFragmentSendDataListener fragmentSendDataListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            fragmentSendDataListener = (OnFragmentSendDataListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " должен реализовывать интерфейс OnFragmentInteractionListener");
        }
    }

    String firm;
    String type;
    String product;

    ArrayList<Product> products = new ArrayList<Product>();
    public AddFragment() {
        super(R.layout.fragment_add);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setInitialData();
        Spinner spinnerFirm = view.findViewById(R.id.firms);
        Spinner spinnerTypes = view.findViewById(R.id.types);
        RecyclerView recyclerView = view.findViewById(R.id.list);

        ArrayAdapter<CharSequence> adapterFirm = ArrayAdapter.createFromResource(getContext(), R.array.firms, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterTypes = ArrayAdapter.createFromResource(getContext(), R.array.types, android.R.layout.simple_spinner_item);
        ProductAdapter adapter = new ProductAdapter(getContext(), products);

        adapterFirm.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFirm.setAdapter(adapterFirm);

        adapterTypes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTypes.setAdapter(adapterTypes);

        recyclerView.setAdapter(adapter);

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

        EditText editText = (EditText) view.findViewById(R.id.editText);
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            public void afterTextChanged(Editable s) {
                product = s.toString();
            }
        });

        Button addButton = (Button) view.findViewById(R.id.add_button);
        addButton.setOnClickListener(view1 -> {
            products.add(new Product(firm, type, product));
            fragmentSendDataListener.onSendData(products);
            recyclerView.setAdapter(adapter);
        });
    }
    public void setInitialData() {
        products.add(new Product("Samsung", "laptop", "samsung laptop"));
        products.add(new Product("Samsung", "washing_machine", "samsung washing_machine"));
        products.add(new Product("Philips", "laptop", "philips laptop"));
        products.add(new Product("Philips", "microwave", "philips microwave"));
        products.add(new Product("Bosch", "refrigerator", "bosch refrigerator"));
        fragmentSendDataListener.onSendData(products);
    }
}