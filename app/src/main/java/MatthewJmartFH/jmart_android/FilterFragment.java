package MatthewJmartFH.jmart_android;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import static MatthewJmartFH.jmart_android.LoginActivity.getLoggedAccount;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;

import MatthewJmartFH.jmart_android.model.Product;
import static MatthewJmartFH.jmart_android.ProductFragment.products;
import MatthewJmartFH.jmart_android.request.RequestFactory;

/**
 * @author Matthew Eucharist
 *
 * this fragment is used to filter the list view in product fragment
 *
 *
 *
 */
public class FilterFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final Gson gson = new Gson();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FilterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FilterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FilterFragment newInstance(String param1, String param2) {
        FilterFragment fragment = new FilterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_filter, container, false);
        TextInputLayout nameFilter = v.findViewById(R.id.name_filter);
        TextInputLayout lowestPrice = v.findViewById(R.id.low_price);
        TextInputLayout highPrice = v.findViewById(R.id.highest_price);

        RadioButton used_radio_btn  = v.findViewById(R.id.used_tag);
        RadioButton new_radio_btn   = v.findViewById(R.id.new_tag);
        Spinner category   = v.findViewById(R.id.spinner_category);
        Button filter_btn  = v.findViewById(R.id.apply_tbtn);

        String[] prod = {"ART","CRAFT","AUTOMOTIVE","BOOK","CARPENTRY","COSMETICS","ELECTRONIC",
                "FASHION","FNB","FURNITURE","GADGET","GAMING","HEALTHCARE","JEWELRY"};
        Spinner spinner = (Spinner)v.findViewById(R.id.spinner_category);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item,prod);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(arrayAdapter);
        // Inflate the layout for this fragment

        new_radio_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    used_radio_btn.setChecked(false);
                }
            }
        });

        used_radio_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    new_radio_btn.setChecked(false);
                }
            }
        });

        filter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String>listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        Toast.makeText(getActivity(),"filtered",Toast.LENGTH_SHORT).show();
                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            if(jsonArray != null)

                            {products = gson.fromJson(jsonArray.toString(),new TypeToken<ArrayList<Product>>(){}.getType());
                                ArrayAdapter<Product> adapter = new ArrayAdapter<>(getActivity(),
                                        android.R.layout.simple_list_item_1,products);
                                ListView listView = v.findViewById(R.id.list_view);


                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };




                    Response.ErrorListener errorListener = new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_LONG).show();
                        }
                    };



                StringRequest request = RequestFactory.getProduct(1, 5, getLoggedAccount().id,
                        nameFilter.getEditText().getText().toString().trim(), lowestPrice.getEditText().getText().toString().trim(),
                        highPrice.getEditText().getText().toString().trim(), category.getSelectedItem().toString(),
                        String.valueOf(used_radio_btn.isChecked()),
                        listener,
                        errorListener);
                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                requestQueue.add(request);

            }
        });
        return v;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}


