package MatthewJmartFH.jmart_android.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CreateStoreRequest extends StringRequest {
    private static final String URL = "http://10.0.2.2:6969/account/%d/registerStore";
    private  static Map<String,String> params = new HashMap<>();
    public CreateStoreRequest(int id,String name, String phoneNumber, String address,
                              Response.Listener<String>listener,
                              Response.ErrorListener errorListener)
    {
        super(Request.Method.POST,String.format(URL,id),listener,errorListener);

        params.put("name",name);
        params.put("phoneNumber",phoneNumber);
        params.put("address",address);
    }

    public Map<String,String> getParams()
    {
        return params;
    }
}
