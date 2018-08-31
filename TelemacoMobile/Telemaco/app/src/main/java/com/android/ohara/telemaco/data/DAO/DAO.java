package com.android.ohara.telemaco.data.DAO;

/**
 * @author Shirley Ohara Telemaco de Freitas (shirleyohara@ufrn.edu.br)
 * @version 26/06/2018 | 10:21
 * @param <Type>
 */
public interface DAO<Type> {
    /**
     * Receives an object as parameter and inserts into database.
     * @param object
     */
    public void insert(Type object);
    
    /**
     * Run a script to select the query in the database through id.
     * @param id
     * @return result with filled filds.
     */
    public Type select(int id);
    
    /**
     * Receives an object and remove it from database.
     * @param object
     */
    public void delete(Type object);
    
    /**
     * Receives an object with the new values and update then by id in database.
     * @param object
     */
    public void update(Type object);
}
