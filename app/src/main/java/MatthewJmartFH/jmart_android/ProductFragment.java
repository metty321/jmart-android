package MatthewJmartFH.jmart_android;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 * implements AdapterView.OnItemClickListener
 */
public class ProductFragment extends Fragment implements AdapterView.OnItemClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProductFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductFragment newInstance(String param1, String param2) {
        ProductFragment fragment = new ProductFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String[] product = {"Chitato Chocolatos","Rexus Ubuntu","Philips GTX","Windows Premium",
                "Sharp Sharp","Redmi Rexus","LG lG","Macintosh LG","Cimory Paseo","Sades Monde","Nokia Paseo","Pilot Tupperware","LG Philips"};



        ListView listView = (ListView) view.findViewById(R.id.list_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1,product);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        
//        ListView listView = (ListView) view.findViewById(R.id.list_view);
//        ArrayList arrayList = new ArrayList<>();
//        String jsonString =loadJSONFromAsset();
//
//        try{
//            JSONObject obj = new JSONObject(loadJSONFromAsset);
//            JSONArray array= obj.getJSONArray("name");
//
//            for(int i = 0;i < array.length(); i++)
//            {
//                JSONObject jsonObject = array.getJSONObject(i);
//                String accountId = jsonObject.getString("accountId");
//                String category  = jsonObject.getString("category");
//                String conditionUsed = jsonObject.getString("conditionUsed");
//                String discount  = jsonObject.getString("discount");
//                String price = jsonObject.getString("price");
//                String name  = jsonObject.getString("name");
//                String shipmentPlans  = jsonObject.getString("shipmentPlans");
//                String weight  = jsonObject.getString("weight");
//
//                itemModel model = new itemModel();
//                model.setName(name);
//                model.setId(accountId);
//                model.setCategory(category);
//                model.setCondition(conditionUsed);
//                model.setPrice(price);
//                model.setDiscount(discount);
//                model.setWeight(weight);
//                model.setShipmentPlans(shipmentPlans);
//                arrayList.add(model);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        CustomAdapter adapter =new CustomAdapter(getContext(),arrayList);
//        listView.setAdapter(adapter);
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(position == 0)
            {
                Toast.makeText(getActivity(),"Chitato Chocolatos",Toast.LENGTH_SHORT).show();
            }
    }




}