package MatthewJmartFH.jmart_android.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Matthew Eucharist
 * This is the class manages all activites related to Top Up from REST Controller
 * correspond with a particular account id
 */
public class TopUpRequest extends StringRequest {
    private static  final String URL = "http://10.0.2.2:6969/account/%d/topUp";
    private static Map<String,String> params = new HashMap<>();

    public TopUpRequest(int id, String balance, Response.Listener<String>listener,
                        Response.ErrorListener errorListener)
    {
        super(Request.Method.POST,String.format(URL,id),listener,errorListener);
        params.put("balance",String.valueOf(balance));
    }

    public Map<String,String> getParams()
    {
        return params;
    }
}
