import androidx.annotation.NonNull;

public class Song {
    private int id;
    private String title;
    private String singers;
    private int year;
    private int star;

    public Song(String title, String singers, int year, int star) {
        this.id = id;
        this.title = title;
        this.singers = singers;
        this.year = year;
        this.star = star;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSingers() {
        return singers;
    }

    public int getYear() {
        return year;
    }

    public int getStar() {
        return star;
    }

    @NonNull
    @Override
    public String toString() {
        return id + "\n" + title + "\n" + singers + "\n" + year + "\n" + star;
    }
}