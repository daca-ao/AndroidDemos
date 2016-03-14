package com.hku.demos.aohuijun.androiddemos.memos;

/**
 * Created by aohuijun on 16/2/3.
 */
public class Memos {
    public static final String _ID = "_id";
    public static final String _MEMO_TITLE = "_title";
    public static final String _MEMO_CONTENT = "_content";
    public static final String _UPDATE_TIME = "_update_time";
    public static final String AUTHORITY = "com.hku.demos.aohuijun.androiddemos.memos.provider";
    public static final String TABLE_NAME = "memos";
    public static final String CONTENT_URI = "content://" + AUTHORITY + "/" + TABLE_NAME;
    public static final String CREATE_TABLE_MEMO = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                                                    " (" +
                                                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                    _MEMO_TITLE + " TEXT, " +
                                                    _MEMO_CONTENT + " TEXT, " +
                                                    _UPDATE_TIME + " LONG" +
                                                    ")";
    public static final int MEMO_DIR = 0;
    public static final int MEMO_ITEM = 1;
    public static final String CONTENT_TYPE_PREFIX_PATH = "vnd.android.cursor.dir/vnd.";
    public static final String CONTENT_TYPE_PREFIX_ITEM = "vnd.android.cursor.item/vnd.";
    public static final String ACTION_CREATE_MEMO = "com.hku.demos.aohuijun.androiddemos.memos.action.CREATE_MEMO";
    public static final String ACTION_VIEW_MEMO = "com.hku.demos.aohuijun.androiddemos.memos.action.VIEW_MEMO";
    public static final int MEMO_CONTENT_LENGTH = 10;
}
