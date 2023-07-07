package sg.edu.rp.c346.id22035802.ps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText editTitle, editSingers, editYear;
    RadioGroup radioGroup;
    RadioButton radio1, radio2, radio3, radio4, radio5;
    Button btnInsert, btnGetSongs;
    ListView lvSongs;
    ArrayAdapter<String> adapter;
    ArrayList<String> songsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTitle = findViewById(R.id.editTitle);
        editSingers = findViewById(R.id.editSingers);
        editYear = findViewById(R.id.editYear);
        radioGroup = findViewById(R.id.radioGroup);
        radio1 = findViewById(R.id.radio1);
        radio2 = findViewById(R.id.radio2);
        radio3 = findViewById(R.id.radio3);
        radio4 = findViewById(R.id.radio4);
        radio5 = findViewById(R.id.radio5);
        btnInsert = findViewById(R.id.btnInsert);
        btnGetSongs = findViewById(R.id.btnGetSong);
        lvSongs = findViewById(R.id.lvGetSong);
        songsList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, songsList);
        lvSongs.setAdapter(adapter);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTitle.getText().toString();
                String singers = editSingers.getText().toString();
                int year = Integer.parseInt(editYear.getText().toString());
                int stars = getSelectedStars();
                Song song = new Song(title, singers, year, stars);
                DBHelper dbHelper = new DBHelper(MainActivity.this);
                long insertedId = dbHelper.insertSong(song);
                dbHelper.close();
                if (insertedId != -1) {
                    Toast.makeText(MainActivity.this, "Song inserted successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Failed to insert song", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnGetSongs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(MainActivity.this);
                ArrayList<Song> songs = dbHelper.getSongs();
                dbHelper.close();
                songsList.clear();
                for (Song song : songs) {
                    String songDetails = "Title: " + song.getTitle() + "\nSingers: " + song.getSingers()
                            + "\nYear: " + song.getYear()
                            + "\nStars: " + song.getStar();
                    songsList.add(songDetails);
                }
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Song list retrieved", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private int getSelectedStars() {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        if (selectedId == R.id.radio1) {
            return 1;
        } else if (selectedId == R.id.radio2) {
            return 2;
        } else if (selectedId == R.id.radio3) {
            return 3;
        } else if (selectedId == R.id.radio4) {
            return 4;
        } else if (selectedId == R.id.radio5) {
            return 5;
        } else {
            return 0;
        }
    }
}