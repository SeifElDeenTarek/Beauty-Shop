package com.example.retrofit.ui.settings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.retrofit.R;
import com.example.retrofit.ui.main.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;


public class SettingsActivity extends AppCompatActivity
{
    AutoCompleteTextView brandName, productName;
    private static final String TAG = "SettingsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        FloatingActionButton searchButton = (FloatingActionButton) findViewById(R.id.search_button);

        brandName = ((AutoCompleteTextView) findViewById(R.id.brand_name));
                brandName.setAdapter(
                new ArrayAdapter<String> (this, android.R.layout.simple_dropdown_item_1line,
                        getResources().getStringArray(R.array.brand_list)));

        productName = ((AutoCompleteTextView) findViewById(R.id.product_name));
                productName.setAdapter(
                new ArrayAdapter<String> (this, android.R.layout.simple_dropdown_item_1line,
                        getResources().getStringArray(R.array.product_type_list)));

        searchButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(TextUtils.isEmpty(brandName.getText().toString())  && TextUtils.isEmpty(productName.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(),"Please select at least one to search", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onClick: " + brandName.getText() + productName.getText());
                }
                else if (brandName.getText() != null && productName.getText() == null)
                {
                    brandQuery(brandName.getText().toString());
                    Log.d(TAG, "onClick: " + brandName.getText() + productName.getText());
                }
                else if (brandName.getText() == null && productName.getText() != null)
                {
                    productQuery(productName.getText().toString());
                    Log.d(TAG, "onClick: " + brandName.getText() + productName.getText());
                }
                else if (brandName.getText() != null && productName.getText() != null)
                {
                    fullQuery(brandName.getText().toString(), productName.getText().toString());
                    Log.d(TAG, "onClick: " + brandName.getText() + productName.getText());
                }
            }
        });


    }

    public Intent fullQuery(String brand, String product)
    {
        // int condition = 1;
        Intent intent = new Intent(this, MainActivity.class);
        // intent.putExtra("condition", condition);
        intent.putExtra("extra_brand", brand);
        intent.putExtra("extra_product_name", product);
        startActivity(intent);
        return intent;
    }

    public Intent brandQuery(String brand)
    {
        // int condition = 2;
        Intent intent = new Intent(this, MainActivity.class);
        // intent.putExtra("condition", condition);
        intent.putExtra("extra_brand", brand);
        startActivity(intent);
        return intent;
    }

    public Intent productQuery(String product)
    {
        // int condition = 3;
        Intent intent = new Intent(this, MainActivity.class);
        // intent.putExtra("condition", condition);
        intent.putExtra("extra_product_name", product);
        startActivity(intent);
        return intent;
    }
};