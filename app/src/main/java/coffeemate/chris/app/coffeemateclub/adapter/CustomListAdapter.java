package coffeemate.chris.app.coffeemateclub.adapter;

/**
 * Created by Chris on 25/02/2016.
 */


import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import coffeemate.chris.app.coffeemateclub.R;
import coffeemate.chris.app.coffeemateclub.app.AppController;
import coffeemate.chris.app.coffeemateclub.model.Coffee;

public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Coffee> coffeeItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListAdapter(Activity activity, List<Coffee> coffeeItems) {
        this.activity = activity;
        this.coffeeItems = coffeeItems;
    }

    @Override
    public int getCount() {
        return coffeeItems.size();
    }

    @Override
    public Object getItem(int location) {
        return coffeeItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView.findViewById(R.id.thumbnail);

        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView brand = (TextView) convertView.findViewById(R.id.brand);
        TextView price = (TextView) convertView.findViewById(R.id.price);
        TextView votes = (TextView) convertView.findViewById(R.id.votes);

        // getting coffee data for the row
        Coffee m = coffeeItems.get(position);

        // thumbnail image
        thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);

        // title
        title.setText(m.getTitle());

        // brand
        brand.setText(m.getBrand());

        // rating
        price.setText("€" + String.valueOf(m.getPrice())+".00");

        // release year
        votes.setText(String.valueOf(m.getVotes()));

        return convertView;
    }

}
