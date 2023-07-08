package com.ericnewman.sfaapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {
    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "success";

    public static String DATABASE_NAME = DB_NAME;

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "users";

    public static String TABLE_NAM = TABLE_NAME;

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our first name column
    private static final String FIRST_NAME_COL = "first_name";

    public static String Table_Column_1_Name = FIRST_NAME_COL;

    public static String Table_Column_ID = ID_COL;

    // below variable is for our last name column
    private static final String LAST_NAME_COL = "last_name";

    // below variable is for our email column
    private static final String EMAIL_COL = "email";

    public static String Table_Column_2_Email = EMAIL_COL;

    // below variable is for our password column
    private static final String PW_COL = "password";

    public static String Table_Column_3_Password = PW_COL;

    // below variable is for our age column
    private static final String AGE_COL = "age_of_user";

    // below variable is for our phone number column
    private static final String PHONE_COL = "phone_number";

    // below variable is for our parent or guardian column
    private static final String PARENT_OR_GAURDIAN_COL = "parent_or_guardian";

    // below variable is for our number of children column
    private static final String NUMBER_OF_CHILD_COL = "number_of_children";

    // below variable is for our street column
    private static final String STREET_COL = "street";

    // below variable is for our town column
    private static final String TOWN_COL = "user_town";

    // below variable is for our state column
    private static final String STATE_COL = "user_state";
    // below variable is for our gender column
    private static final String GENDER_COL = "gender";

    // below variable is for our zip code column
    private static final String ZIP_COL = "user_zip";

    // below variable is for our country code column
    private static final String COUNTRY_COL = "country";

    // below variable is for our concerns column
    private static final String CONCERNS_COL = "concerns";

    // below variable is for our person of concern column
    private static final String PERSON_OF_CONCERN_COL = "person_of_concern";

    // below variable is for our age of person of concern column
    private static final String AGE_OF_PERSON_OF_CONCERN_COL = "age_of_person_of_concern";

    public SQLiteHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
       /* on below line we are creating
          an sqlite query and we are
          setting our column names
          along with their data types.*/
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + ID_COL + "BIG INTEGER PRIMARY KEY NOT NULL,"
                + FIRST_NAME_COL + " VARCHAR,"
                + LAST_NAME_COL + " VARCHAR,"
                + EMAIL_COL + " VARCHAR,"
                + PW_COL + " VARCHAR,"
                + AGE_COL + " INT,"
                + PHONE_COL + " VARCHAR,"
                + PARENT_OR_GAURDIAN_COL + " VARCHAR,"
                + GENDER_COL + " VARCHAR,"
                + NUMBER_OF_CHILD_COL + " INT,"
                + STREET_COL + " VARCHAR,"
                + TOWN_COL + " VARCHAR,"
                + STATE_COL + " VARCHAR,"
                + ZIP_COL + " VARCHAR,"
                + COUNTRY_COL + " VARCHAR,"
                + PERSON_OF_CONCERN_COL + " VARCHAR,"
                + CONCERNS_COL + " VARCHAR,"
                + AGE_OF_PERSON_OF_CONCERN_COL + " INT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        database.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertAllData(String firstName, String lastName, String password, String email,
                                 String phonenumber, int userAge, int personOfConcernAge, String street, String zip,
                                 String parentOrGuardian, int numberOfChildren, String town, String country,
                                 String personOfConcern, String concerns, String gender) {
        SQLiteDatabase db = this.getReadableDatabase();          // create the database and table
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIRST_NAME_COL, firstName);
        contentValues.put(LAST_NAME_COL, lastName);
        contentValues.put(PW_COL, password);
        contentValues.put(AGE_COL, userAge);
        contentValues.put(PHONE_COL, phonenumber);
        contentValues.put(EMAIL_COL, email);
        contentValues.put(AGE_OF_PERSON_OF_CONCERN_COL, personOfConcernAge);
        contentValues.put(STREET_COL, street);
        contentValues.put(ZIP_COL, zip);
        contentValues.put(GENDER_COL, gender);
        contentValues.put(PARENT_OR_GAURDIAN_COL, parentOrGuardian);
        contentValues.put(NUMBER_OF_CHILD_COL, numberOfChildren);
        contentValues.put(TOWN_COL, town);
        contentValues.put(COUNTRY_COL, country);
        contentValues.put(PERSON_OF_CONCERN_COL, personOfConcern);
        contentValues.put(CONCERNS_COL, concerns);
        long result = db.insert(TABLE_NAME, null, contentValues);

        return result != -1;
    }

    // query the database and storing it in the Cursor instance res
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public boolean updateData(Long ID, String firstName, String lastName, String password, String email,
                              String phonenumber, int userAge, int personOfConcernAge, String street, String zip,
                              String parentOrGuardian, int numberOfChildren, String town, String country,
                              String personOfConcern, String concerns, String gender) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIRST_NAME_COL, firstName);
        contentValues.put(LAST_NAME_COL, lastName);
        contentValues.put(PW_COL, password);
        contentValues.put(AGE_COL, userAge);
        contentValues.put(PHONE_COL, phonenumber);
        contentValues.put(EMAIL_COL, email);
        contentValues.put(AGE_OF_PERSON_OF_CONCERN_COL, personOfConcernAge);
        contentValues.put(STREET_COL, street);
        contentValues.put(ZIP_COL, zip);
        contentValues.put(PARENT_OR_GAURDIAN_COL, parentOrGuardian);
        contentValues.put(NUMBER_OF_CHILD_COL, numberOfChildren);
        contentValues.put(TOWN_COL, town);
        contentValues.put(COUNTRY_COL, country);
        contentValues.put(GENDER_COL, gender);
        contentValues.put(PERSON_OF_CONCERN_COL, personOfConcern);
        contentValues.put(CONCERNS_COL, concerns);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{String.valueOf(ID)});
        return true;
    }


    public Boolean checkUserInfo(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(EMAIL_COL, email);
        contentValues.put(PW_COL, password);
        try (Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE "
                + EMAIL_COL + " = ? AND " + PW_COL + "= ?", new String[]{email, password})) {
            return cursor.getCount() > 0;
        }
    }

    public Boolean insertRegisterData(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(EMAIL_COL, email);
        contentValues.put(PW_COL, password);

        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }
    // Method to get the login status from the database
    public boolean getLoggedInStatus() {
        boolean isLoggedIn = false;

        try (SQLiteDatabase db = this.getReadableDatabase();
             Cursor cursor = db.query(TABLE_NAM,
                     new String[]{EMAIL_COL}, null, null, null,
                     null, null)) {
            // Query the users table for the login status

            isLoggedIn = (cursor != null && cursor.getCount() > 0);
        }

        return isLoggedIn;
    }

    public void setLoggedInStatus(boolean isLoggedIn) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("is_logged_in", isLoggedIn ? 1 : 0); // Convert boolean value to 1 or 0

        db.update(TABLE_NAME, values, null, null);
        db.close();
    }

    public boolean updatePassword(String email, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PW_COL, newPassword);

        int rowsAffected = db.update(TABLE_NAME, values, EMAIL_COL + "=?", new String[]{email});

        return rowsAffected > 0;
    }



}
