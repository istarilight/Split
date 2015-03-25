package split.dashdash.com.model;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import split.dashdash.com.Util.DatabaseUtil;

public class BillContentProvider extends ContentProvider {
    @Override
    public boolean onCreate() {
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        BillDao billDao = DatabaseUtil.getBillDao(getContext());
        return billDao.getDatabase().query(BillDao.TABLENAME, billDao.getAllColumns(), selection, selectionArgs, null, null, sortOrder);
    }

    @Override
    public String getType(Uri uri) {
        int match = URI_MATCHER.match(uri);
        switch (match) {

            case URI_MATCH_CONTACT:
                return BillContentProvider.CONTENT_TYPE_CONTACT;

            case URI_MATCH_CONTACT_LIST:
                return BillContentProvider.CONTENT_TYPE_CONTACT_LIST;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
    public static final String SYNC_AUTHORITY;
    public static final Uri CONTENT_URI;
    public static final String CONTENT_TYPE_CONTACT_LIST;
    public static final String CONTENT_TYPE_CONTACT;
    private static final Uri BASE_CONTENT_URI;
    private static final String PATH_CONTACT_LIST;
    private static final String PATH_CONTACT;
    private static final int URI_MATCH_CONTACT_LIST = 1;
    private static final int URI_MATCH_CONTACT = 2;
    private static final UriMatcher URI_MATCHER;

    static {

        SYNC_AUTHORITY = "com.split.bill.provider";
        BASE_CONTENT_URI = Uri.parse("content://" + SYNC_AUTHORITY);

        PATH_CONTACT = "contact";
        PATH_CONTACT_LIST = "contacts";

        CONTENT_TYPE_CONTACT_LIST = ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd.imeet.sync." + PATH_CONTACT_LIST;
        CONTENT_TYPE_CONTACT = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vnd.imeet.sync." + PATH_CONTACT;

        CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_CONTACT_LIST).build();

        URI_MATCHER = new UriMatcher(android.content.UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(SYNC_AUTHORITY, PATH_CONTACT_LIST, URI_MATCH_CONTACT_LIST);
        URI_MATCHER.addURI(SYNC_AUTHORITY, PATH_CONTACT_LIST + "/*", URI_MATCH_CONTACT);
    }
}
