package MatthewJmartFH.jmart_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * @Author Matthew Eucharist
 * This class is not used
 *
 */
public class CustomAdapter extends BaseAdapter {

    Context context;
    ArrayList<itemModel> arrayList;

    public CustomAdapter(Context context, ArrayList<itemModel>arrayList)
    {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount()
    {
        return arrayList.size();
    }

    @Override
    public Object getItem (int position)
    {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        if(convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list,parent,
                    false);
        }
        TextView name;
        name = (TextView) convertView.findViewById(R.id.Product);
        name.setText(arrayList.get(position).getName());

        return convertView;
    }
}
