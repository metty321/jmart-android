package MatthewJmartFH.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import MatthewJmartFH.jmart_android.model.ProductCategory;
import MatthewJmartFH.jmart_android.request.CreateProductRequest;

/**
 * @Author Matthew Eucharist
 * This class manages the creation of product
 * correspond with a particular account id
 */
public class CreateProductActivity extends AppCompatActivity {
    private Spinner spinner;
    private Spinner spinner_2;
    private boolean conditionUsed;
    byte chosen_shipmentPlan;
    ProductCategory productCategory;
    private final String[] ProductCategory ={"ART","CRAFT","AUTOMOTIVE","BOOK","CARPENTRY","COSMETICS","ELECTRONIC",
    "FASHION","FNB","FURNITURE","GADGET","GAMING","HEALTHCARE","JEWELRY"};
    private final String[] shipmentPlans = {"INSTANT","KARGO","NEXT DAY","REGULER","SAME DAY"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);

        EditText CreatedProduct  = findViewById(R.id.name_3);
        EditText productWeight   = findViewById(R.id.weight);
        EditText productPrice    = findViewById(R.id.Price);
        EditText productDiscount = findViewById(R.id.discount);
        CheckBox used_check = findViewById(R.id.used_check);
        CheckBox new_check  = findViewById(R.id.new_check);

        spinner = (Spinner) findViewById(R.id.spinner_category_2);
        spinner_2 = (Spinner) findViewById(R.id.shipment_plans);

        final ArrayAdapter<String>arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,ProductCategory);
        spinner.setAdapter(arrayAdapter);
        final ArrayAdapter<String>arrayAdapter2 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,shipmentPlans);
        spinner_2.setAdapter(arrayAdapter2);
        Button create_btn        = findViewById(R.id.create_btn);
        Button cancel_btn        = findViewById(R.id.cancel_btn);

        new_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    used_check.setChecked(false);
                }
            }
        });

        used_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    new_check.setChecked(false);
                }
            }
        });


        //Action When the create button is clicked
        create_btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            conditionUsed = used_check.isChecked();
            chosen_shipmentPlan = shipmentplanChecker(spinner_2);

            Response.Listener<String>listener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.println(response);
                    try {

                        JSONObject obj = new JSONObject(response);
                        if(obj != null)
                        {
                            Toast.makeText(CreateProductActivity.this,"Product registration success!",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(CreateProductActivity.this,MainActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(CreateProductActivity.this,"Product registration failed!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }catch (JSONException e){
                        e.printStackTrace();
                        Toast.makeText(CreateProductActivity.this,"Product registration failed!",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            };

            Response.ErrorListener errorListener = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(CreateProductActivity.this,"Listener failed!",
                            Toast.LENGTH_SHORT).show();
                }
            };

            CreateProductRequest productRequest = new CreateProductRequest(
                    CreatedProduct.getText().toString(),productPrice.getText().toString(),
                    productWeight.getText().toString(),
                    String.valueOf(new_check.isChecked()),
                    productDiscount.getText().toString(),
                    spinner.getSelectedItem().toString(),
                    String.valueOf(chosen_shipmentPlan),
                    listener,errorListener);

            RequestQueue requestQueue = Volley.newRequestQueue(CreateProductActivity.this);
            requestQueue.add(productRequest);
        }
    });
    }


    protected byte shipmentplanChecker (Spinner shipmentplan)
    {
        if(shipmentplan.getSelectedItem()=="INSTANT")
        {
            return (byte)00000001;
        }
        if(shipmentplan.getSelectedItem()=="KARGO")
        {
            return (byte)00010000;
        }
        if(shipmentplan.getSelectedItem()=="NEXT DAY")
        {
            return (byte)00000100;
        }
        if(shipmentplan.getSelectedItem()=="REGULER")
        {
            return (byte)00001000;
        }
        if(shipmentplan.getSelectedItem()=="SAME DAY")
        {
            return (byte)00000110;
        }

        return 0;
    }




}