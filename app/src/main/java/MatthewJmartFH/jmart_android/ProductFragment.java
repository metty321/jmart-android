package MatthewJmartFH.jmart_android;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

//import android.util.JsonReader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.ArrayList;

import MatthewJmartFH.jmart_android.model.Product;
import MatthewJmartFH.jmart_android.request.RequestFactory;


/**
 * @author Matthew Eucharist
 *
 *this class shows the listview of product
 *
 */
public class ProductFragment extends Fragment  {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final Gson gson = new Gson();
    public static  ArrayList<Product> products = new ArrayList<>();
    final int pageSize = 10;
    static Integer page =0;
    static Product productClicked = null;

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
        View v = inflater.inflate(R.layout.fragment_product, container, false);
        EditText pageNum = v.findViewById(R.id.page);
        Button next = v.findViewById(R.id.nextbtn);
        Button prev = v.findViewById(R.id.prevbtn);
        Button go   = v.findViewById(R.id.gotbtn);

        pageNum.setText(String.valueOf(page+1), TextView.BufferType.EDITABLE);

        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONArray jsonArray = new JSONArray(response);
                    if(jsonArray != null)
                    {
                        products = gson.fromJson(jsonArray.toString(),new TypeToken<ArrayList<Product>>(){}.getType());
                        System.out.println(products);
                        ArrayAdapter<Product> adapter = new ArrayAdapter<Product>(getActivity(),
                 android.R.layout.simple_list_item_1,products);

                        ListView listView = v.findViewById(R.id.list_view);
                        listView.setAdapter(adapter);

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                                productClicked = (Product) listView.getItemAtPosition(position);
                                Toast.makeText(getActivity(),"item clicked!",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(), ProductDetailsActivity.class);
                                startActivity(intent);
                            }
                        });
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(RequestFactory.getPage("product",page,pageSize,listener,null));

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"next page",Toast.LENGTH_SHORT).show();
                page +=1;
                getActivity().finish();
                getActivity().overridePendingTransition(0,0);
                getActivity().startActivity(getActivity().getIntent());
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"prev page",Toast.LENGTH_SHORT).show();
                page -= 1;
                getActivity().finish();
                getActivity().overridePendingTransition(0,0);
                getActivity().startActivity(getActivity().getIntent());
            }
        });

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Go!!!!",Toast.LENGTH_SHORT).show();
                page = Integer.parseInt(pageNum.getText().toString())-1;
                getActivity().finish();
                getActivity().overridePendingTransition(0,0);
                getActivity().startActivity(getActivity().getIntent());
            }
        });

        // Inflate the layout for this fragment
//         final Type ITEM_MODEL_TYPE = new TypeToken<ArrayList<Product>>(){}.getType();
//         Gson gson = new Gson();
//        try {
//            JsonReader reader = new JsonReader(new FileReader("randomProductList.json"));
//            products = gson.fromJson(reader,ITEM_MODEL_TYPE);
//            for(Product prod : products)
//            {
//                product_name.add(prod.name.toString());
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//
//
//        }
        return v;

    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        String[] product = {"Chitato Chocolatos","Rexus Ubuntu","Philips GTX","Windows Premium",
//                "Sharp Sharp","Redmi Rexus","LG lG","Macintosh LG","Cimory Paseo","Sades Monde","Nokia Paseo","Pilot Tupperware","LG Philips"};
//
//
//
//         ListView listView = (ListView) view.findViewById(R.id.list_view);
//       ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
//                android.R.layout.simple_list_item_1,product_name);
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(this);
//    }




}







