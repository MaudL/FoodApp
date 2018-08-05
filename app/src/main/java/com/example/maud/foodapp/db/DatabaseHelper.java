package  com.example.maud.foodapp.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.CallSuper;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper{

    private Dao<RecipeData, Integer> recipeDAO= null;

    public DatabaseHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, databaseName, factory, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, RecipeData.class);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, RecipeData.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<RecipeData, Integer> getRecipeDao() {
        if (recipeDAO == null) {
            try {
                recipeDAO = getDao(RecipeData.class);
            }catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return recipeDAO;
    }


    /**
     * Gets all data in a table
     *
     * @return the list of instance in the data base
     * @throws SQLException sql exception
     */
    @CallSuper
    public List<RecipeData> getAll() throws SQLException {
        Dao<RecipeData, Integer> dao = getDao(RecipeData.class);
        return dao.queryForAll();
    }

    /**
     * Gets all data in a table and order it
     *
     * @param orderBy   sql order request
     * @param ascending ascending or descending
     * @return the list of instance in the data base in order
     * @throws SQLException sql exception
     */
    @CallSuper
    public List<RecipeData> getAllOrdered(String orderBy, boolean ascending) throws SQLException {
        Dao<RecipeData, ?> dao = getDao(RecipeData.class);
        return dao.queryBuilder().orderBy(orderBy, ascending).query();
    }

    /**
     * Gets data in table by id
     *
     * @param aId the id of the object
     * @return the data with the same id
     * @throws SQLException sql exception
     */
    @CallSuper
    public RecipeData getById(String aId) throws SQLException {
        Dao<RecipeData, Object> dao = getDao(RecipeData.class);
        return dao.queryForId(aId);
    }


    /**
     * Create data in the table of the object choose
     *
     * @param obj the instance of the object to load in the data table
     * @throws SQLException sql exception
     */
    @CallSuper
    public void createOrUpdate(RecipeData obj) throws SQLException {
        Dao<RecipeData, ?> dao = getDao(RecipeData.class);
        dao.createOrUpdate(obj);
    }

    /**
     * Delete data in table by id
     *
     * @param aId The id of the object
     * @return The number of rows updated in the database. This should be 1.
     * @throws SQLException sql exception
     */
    @CallSuper
    public int deleteById(String aId) throws SQLException {
        Dao<RecipeData, Object> dao = getDao(RecipeData.class);
        return dao.deleteById(aId);
    }

    /**
     * Delete collection data in table by id
     *
     * @param aObjList the collection of the object
     * @return The number of rows updated in the database. This should be the size() of the collection.
     * @throws SQLException sql exception
     */
    @CallSuper
    public int deleteObjects(Collection<RecipeData> aObjList) throws SQLException {
        Dao<RecipeData, ?> dao = getDao(RecipeData.class);
        return dao.delete(aObjList);
    }

    /**
     * Delete all data in table by id
     *
     * @throws SQLException sql exception
     */
    @CallSuper
    public void deleteAll() throws SQLException {
        Dao<RecipeData, ?> dao = getDao(RecipeData.class);
        dao.deleteBuilder().delete();
    }


    /**
     * Find all raw where the field is equal to the value
     *
     * @param field the field name
     * @param value the field value
     * @return the list of T class
     * @throws SQLException sql exception
     */
    @CallSuper
    public List<RecipeData> where(String field, Object value) throws SQLException {
        Dao<RecipeData, ?> dao = getDao(RecipeData.class);
        return dao.queryForEq(field, value);
    }


}