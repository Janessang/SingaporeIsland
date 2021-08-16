package sg.edu.rp.c346.id20025312.oursingapore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<Show> showsList;
    CustomAdapter adapter;
    Button btnStars;

    ArrayList<String> year;
    Spinner spinner;
    ArrayAdapter<String> spinnerAdapter;

    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(this);
        showsList.clear();
        showsList.addAll(dbh.getAllShows());
        adapter.notifyDataSetChanged();

        year.clear();
        year.addAll(dbh.getYear());
        spinnerAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        lv = findViewById(R.id.lv);
        btnStars = findViewById(R.id.btnStars);
        spinner = findViewById(R.id.spinnerYear);

        DBHelper dbh = new DBHelper(this);
        showsList = dbh.getAllShows();
        year = dbh.getYear();
        dbh.close();

        adapter = new CustomAdapter(this, R.layout.row, showsList);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(SecondActivity.this, ThirdActivity.class);
                i.putExtra("show", showsList.get(position));
                startActivity(i);
            }
        });

        btnStars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(SecondActivity.this);
                showsList.clear();
                showsList.addAll(dbh.getAllShowsByStars(5));
                adapter.notifyDataSetChanged();
            }
        });

        spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, year);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DBHelper dbh = new DBHelper(SecondActivity.this);
                showsList.clear();
                showsList.addAll(dbh.getAllShowsByYear(Integer.valueOf(year.get(position))));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}