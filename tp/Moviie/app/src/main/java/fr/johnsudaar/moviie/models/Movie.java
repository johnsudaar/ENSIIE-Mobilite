package fr.johnsudaar.moviie.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;
import android.util.LongSparseArray;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import fr.johnsudaar.moviie.Configuration;

public class Movie implements Serializable {
    private static final String TAG = "MOVIE";
    private static final String URL_PREFIX = "https://image.tmdb.org/t/p/w300";

    private static LongSparseArray<Movie> cache = new LongSparseArray<Movie>();

    private Long id;
    private String title;
    private String backdrop;
    private String poster;
    private String overview;
    private String releaseDate;
    private double voteAverage;
    private boolean seen = false;

    private boolean inMyMovies = false;

    interface Tables {
        String MOVIE = "movie";
    }

    interface MovieColumns {
        String TITLE = "movie_title";
        String BACKDROP = "movie_backdrop";
        String POSTER = "movie_poster";
        String OVERVIEW = "movie_overview";
        String RELEASE = "movie_release";
        String VOTES = "movie_votes";
        String SEEN = "movie_seen";
    }

    public Movie(){}

    public Movie(JSONObject m) throws JSONException {
        /*
        ID          int64   `json:"id" bson:"movie_id"`
        Title       string  `json:"title" bson:"title"`
        Backdrop    string  `json:"backdrop" bson:"backdrop"`
        Poster      string  `json:"poster" bson:"poster"`
        Overview    string  `json:"overview" bson:"overview"`
        ReleaseDate string  `json:"release_date" bson:"release_date"`
        VoteAverage float64 `json:"vote_average" bson:"vote_average"`
        Seen        bool    `json:"seen" bson:"seen"`
        */

        this.setId(m.getLong("id"));
        this.setTitle(m.getString("title"));
        this.setBackdrop(m.getString("backdrop"));
        this.setPoster(m.getString("poster"));
        this.setOverview(m.getString("overview"));
        this.setReleaseDate(m.getString("release_date"));
        this.setVoteAverage(m.getDouble("vote_average"));
        this.setSeen(m.getBoolean("seen"));
    }

    public Movie(Cursor cursor) {
        this.setId(cursor.getLong(cursor.getColumnIndex(BaseColumns._ID)));
        this.setTitle(cursor.getString(cursor.getColumnIndex(MovieColumns.TITLE)));
        this.setBackdrop(cursor.getString(cursor.getColumnIndex(MovieColumns.BACKDROP)));
        this.setPoster(cursor.getString(cursor.getColumnIndex(MovieColumns.POSTER)));
        this.setOverview(cursor.getString(cursor.getColumnIndex(MovieColumns.OVERVIEW)));
        this.setReleaseDate(cursor.getString(cursor.getColumnIndex(MovieColumns.RELEASE)));
        this.setVoteAverage(cursor.getDouble(cursor.getColumnIndex(MovieColumns.VOTES)));
        this.setSeen(cursor.getInt(cursor.getColumnIndex(MovieColumns.SEEN)) == 1);
        this.inMyMovies = true;
        cache.append(this.getId(), this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getBackdropUrl(){
        return URL_PREFIX+this.getBackdrop();
    }

    public String getPosterUrl(){
        return URL_PREFIX+this.getPoster();
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public boolean isInMyMovies() {
        return inMyMovies;
    }

    public JSONObject toJsonObject() throws JSONException {
        JSONObject m = new JSONObject();
        m.put("id", this.getId());
        m.put("title", this.getTitle());
        m.put("backdrop", this.getBackdrop());
        m.put("poster", this.getPoster());
        m.put("overview", this.getOverview());
        m.put("release_date", this.getReleaseDate());
        m.put("vote_average", this.getVoteAverage());
        m.put("seen", this.isSeen());

        /*
        ID          int64   `json:"id" bson:"movie_id"`
        Title       string  `json:"title" bson:"title"`
        Backdrop    string  `json:"backdrop" bson:"backdrop"`
        Poster      string  `json:"poster" bson:"poster"`
        Overview    string  `json:"overview" bson:"overview"`
        ReleaseDate string  `json:"release_date" bson:"release_date"`
        VoteAverage float64 `json:"vote_average" bson:"vote_average"`
        Seen        bool    `json:"seen" bson:"seen"`
        */
        return m;
    }

    public void save(){
        SQLiteDatabase db = Configuration.get().getDbHelper().getDb();

        ContentValues values = new ContentValues();
        values.put(BaseColumns._ID, this.getId());
        values.put(MovieColumns.BACKDROP, this.getBackdrop());
        values.put(MovieColumns.OVERVIEW, this.getOverview());
        values.put(MovieColumns.POSTER, this.getPoster());
        values.put(MovieColumns.RELEASE, this.getReleaseDate());
        values.put(MovieColumns.SEEN, this.isSeen()? 1 : 0);
        values.put(MovieColumns.TITLE, this.getTitle());
        values.put(MovieColumns.VOTES, this.getVoteAverage());

        if(this.isInMyMovies()) {
            db.update(Tables.MOVIE,values, BaseColumns._ID+" = ?", new String[]{String.valueOf(this.getId())});
        } else {
            db.insert(Tables.MOVIE, null, values);
            this.inMyMovies = true;
        }
        db.close();

        cache.append(this.getId(), this);
    }

    public void delete() {
        SQLiteDatabase db = Configuration.get().getDbHelper().getDb();
        db.delete(Tables.MOVIE, BaseColumns._ID +" = ?", new String[]{String.valueOf(this.getId())});
        this.inMyMovies = false;
        this.setSeen(false);
        db.close();
        cache.remove(this.getId());
    }

    public static ArrayList<Movie> getAll(){
        SQLiteDatabase db = Configuration.get().getDbHelper().getDb();
        ArrayList<Movie> movies = new ArrayList<Movie>();

        Cursor cursor = db.query(
                Tables.MOVIE,
                new String[]{
                        BaseColumns._ID,
                        MovieColumns.TITLE,
                        MovieColumns.BACKDROP,
                        MovieColumns.POSTER,
                        MovieColumns.OVERVIEW,
                        MovieColumns.RELEASE,
                        MovieColumns.VOTES,
                        MovieColumns.SEEN
                },
                null, null, null, null, null);

        if(cursor.moveToFirst()) {
            do{
                long id = cursor.getLong(cursor.getColumnIndex(BaseColumns._ID));
                Movie m = cache.get(id);
                if (m == null) {
                    m = new Movie(cursor);
                }
                movies.add(m);
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return movies;
    }

    public static ArrayList<Movie> seen(){
        SQLiteDatabase db = Configuration.get().getDbHelper().getDb();
        ArrayList<Movie> movies = new ArrayList<Movie>();

        Cursor cursor = db.query(
                Tables.MOVIE,
                new String[]{
                        BaseColumns._ID,
                        MovieColumns.TITLE,
                        MovieColumns.BACKDROP,
                        MovieColumns.POSTER,
                        MovieColumns.OVERVIEW,
                        MovieColumns.RELEASE,
                        MovieColumns.VOTES,
                        MovieColumns.SEEN
                },
                MovieColumns.SEEN + " = ?",
                new String[]{"1"},
                null, null, null, null);

        if(cursor.moveToFirst()) {
            do{
                long id = cursor.getLong(cursor.getColumnIndex(BaseColumns._ID));
                Movie m = cache.get(id);
                if (m == null) {
                    m = new Movie(cursor);
                }
                movies.add(m);
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return movies;
    }

    public static ArrayList<Movie> notSeen(){
        SQLiteDatabase db = Configuration.get().getDbHelper().getDb();
        ArrayList<Movie> movies = new ArrayList<Movie>();

        Cursor cursor = db.query(
                Tables.MOVIE,
                new String[]{
                        BaseColumns._ID,
                        MovieColumns.TITLE,
                        MovieColumns.BACKDROP,
                        MovieColumns.POSTER,
                        MovieColumns.OVERVIEW,
                        MovieColumns.RELEASE,
                        MovieColumns.VOTES,
                        MovieColumns.SEEN
                },
                MovieColumns.SEEN+ "= ?",
                new String[]{"0"},
                null, null, null, null);

        if(cursor.moveToFirst()) {
            do{
                long id = cursor.getLong(cursor.getColumnIndex(BaseColumns._ID));
                Movie m = cache.get(id);
                if (m == null) {
                    m = new Movie(cursor);
                }
                movies.add(m);
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return movies;
    }

    public static Movie findById(long id){
        Movie movie = cache.get(id);

        if (movie != null) {
            return movie;
        }

        SQLiteDatabase db = Configuration.get().getDbHelper().getDb();

        Cursor cursor = db.query(
                Tables.MOVIE,
                new String[]{
                        BaseColumns._ID,
                        MovieColumns.TITLE,
                        MovieColumns.BACKDROP,
                        MovieColumns.POSTER,
                        MovieColumns.OVERVIEW,
                        MovieColumns.RELEASE,
                        MovieColumns.VOTES,
                        MovieColumns.SEEN
                },
                BaseColumns._ID +" = ?",
                new String[]{String.valueOf(id)}
                , null, null, null, null);
        if(cursor.moveToFirst()){
            movie = new Movie(cursor);
        }
        cursor.close();
        db.close();
        return movie;
    }


    public static void create(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Tables.MOVIE + " ("
                + BaseColumns._ID + " INTEGER PRIMARY KEY,"
                + MovieColumns.BACKDROP + " TEXT,"
                + MovieColumns.TITLE + " TEXT,"
                + MovieColumns.POSTER + " TEXT,"
                + MovieColumns.OVERVIEW+ " TEXT,"
                + MovieColumns.RELEASE + " TEXT,"
                + MovieColumns.VOTES + " REAL,"
                + MovieColumns.SEEN + " INTEGER)");
    }

    public static void upgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+Tables.MOVIE);
        create(db);
    }

    public static void clearCache(){
        cache = new LongSparseArray<Movie>();
    }
}
