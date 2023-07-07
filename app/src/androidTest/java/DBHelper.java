import static android.app.DownloadManager.COLUMN_TITLE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    // Start version with 1    // increment by 1 whenever db schema changes.    private static final int DATABASE_VER = 1;    // Filename of the database    private static final String DATABASE_NAME = "Song.db";    private static final String TABLE_Song = "Song";    private static final String COLUMN_ID = "_id";    private static final String COLUMN_TITLE = "title";    private static final String COLUMN_SINGERS = "singers";    private static final String COLUMN_YEAR = "year";    private static final String COLUMN_STAR = "star";    public DBHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VER);
}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_Song + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_TITLE + " TEXT," + COLUMN_SINGERS + " TEXT," + COLUMN_YEAR + " INTEGER," + COLUMN_STAR + " INTEGER)";
        db.execSQL(createTableSql);
        Log.i("info", "created tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Song);        // Create table(s) again        onCreate(db);    }

        public void insertSong (String title, String singers,int year, int star){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_TITLE, title);
            values.put(COLUMN_SINGERS, singers);
            values.put(COLUMN_YEAR, year);
            values.put(COLUMN_STAR, star);
            db.insert(TABLE_Song, null, values);
            db.close();
        }

        public ArrayList<Song> getSongs () {
            ArrayList<Song> songs = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();
            String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGERS, COLUMN_YEAR, COLUMN_STAR};
            Cursor cursor = db.query(TABLE_Song, columns, null, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String title = cursor.getString(1);
                    String singers = cursor.getString(2);
                    int year = cursor.getInt(3);
                    int star = cursor.getInt(4);
                    Song song = new Song(title, singers, year, star);
                    songs.add(song);
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
            return songs;
        }

        public long insertSong (Song song){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_TITLE, song.getTitle());
            values.put(COLUMN_SINGERS, song.getSingers());
            values.put(COLUMN_YEAR, song.getYear());
            values.put(COLUMN_STAR, song.getStar());
            long insertedId = db.insert(TABLE_Song, null, values);
            db.close();
            return insertedId;
        }
    }