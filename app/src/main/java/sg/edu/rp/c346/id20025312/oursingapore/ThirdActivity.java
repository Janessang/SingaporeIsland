package sg.edu.rp.c346.id20025312.oursingapore;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    EditText etID, etName, etDescription, etYear;
    Button btnUpdate, btnDelete, btnCancel;
    RatingBar rb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        etID = findViewById(R.id.etID);
        etName = findViewById(R.id.etName);
        etDescription = findViewById(R.id.etDescription);
        etYear = findViewById(R.id.etYear);
        rb2 = findViewById(R.id.ratingBar2);

        Intent i = getIntent();
        final Show currentShow = (Show) i.getSerializableExtra("show");

        etID.setText(currentShow.getId() + "");
        etID.setTextColor(getResources().getColor(R.color.grey));

        etName.setText(currentShow.getName());
        etName.setTextColor(getResources().getColor(R.color.grey));

        etDescription.setText(currentShow.getDescription());
        etDescription.setTextColor(getResources().getColor(R.color.grey));

        etYear.setText(currentShow.getYear() + "");
        etYear.setTextColor(getResources().getColor(R.color.grey));

        rb2.setRating((float) currentShow.getStars());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                currentShow.setName(etName.getText().toString().trim());
                currentShow.setDescription(etDescription.getText().toString().trim());
                int year = 0;
                try {
                    year = Integer.valueOf(etYear.getText().toString().trim());
                } catch (Exception e){
                    Toast.makeText(ThirdActivity.this, "Invalid year", Toast.LENGTH_SHORT).show();
                    return;
                }
                currentShow.setYear(year);

                currentShow.setStars((int) rb2.getRating());
                int result = dbh.updateShow(currentShow);
                if (result > 0){
                    Toast.makeText(ThirdActivity.this, "Show updated", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ThirdActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ThirdActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to delete the show\n\n" + currentShow.getName());
                myBuilder.setCancelable(false);

                myBuilder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper dbh = new DBHelper(ThirdActivity.this);
                        dbh.deleteShow(currentShow.getId());
                        finish();
                    }
                });

                 myBuilder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         DBHelper dbh = new DBHelper(ThirdActivity.this);
                         finish();
                     }
                 });
                 AlertDialog myDialog = myBuilder.create();
                 myDialog.show();

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ThirdActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to discard the changes");
                myBuilder.setCancelable(false);

                myBuilder.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                myBuilder.setPositiveButton("Do Not Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });
    }
}