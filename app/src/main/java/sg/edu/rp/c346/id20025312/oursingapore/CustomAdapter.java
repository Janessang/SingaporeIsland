package sg.edu.rp.c346.id20025312.oursingapore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Show> showslist;

    public CustomAdapter (Context context, int resource, ArrayList<Show> objects) {
        super (context, resource, objects);

        parent_context = context;
        layout_id = resource;
        showslist = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(layout_id, parent, false);

        TextView tvName = rowView.findViewById(R.id.textViewName3);
        TextView tvDescription = rowView.findViewById(R.id.textViewDescription3);
        TextView tvYear = rowView.findViewById(R.id.textViewYear3);
        RatingBar rb = rowView.findViewById(R.id.ratingBar3);

        Show current = showslist.get(position);

        tvName.setText(current.getName());
        tvYear.setText(Integer.toString(current.getYear()));

        rb.setRating(current.getStars());
        tvDescription.setText(current.getDescription());

        return rowView;
    }
}
