package com.android.ohara.telemaco.data.DAO;

import com.android.ohara.telemaco.entity.Episode;
import com.android.ohara.telemaco.entity.Serie;
import com.android.ohara.telemaco.entity.User;
import java.util.ArrayList;

/**
 *
 * @author  Shirley Ohara Telemaco de Freitas (shirleyohara@ufrn.edu.br)
 * @version 28/06/2018
 */
public interface DAOUserSpecialOperations extends DAO<User> {
    /**
     * Select an user by email and password, special method to login.
     * @param email
     * @param password
     * @return
     */
    public User select (String email, String password);
    
    /**
     * Select an user by email, special method to register.
     * @param email
     * @return
     */
    public User select (String email);
    
    /**
     * Insert a serie in the list of user.
     * @param idUser
     * @param idSerie
     */
    public void addSeries (int idUser, int idSerie);
    
    /**
     * Select series of the user list.
     * @param id
     * @return
     */
    public ArrayList<Serie> selectSeriesByUser (int id);
    
    /**
     * Remove a serie from the user list.
     * @param idUser
     * @param idSerie
     */
    public void removeSeries (int idUser, int idSerie);

    /**
     * Add a episode from the user list episode seemed
     * @param idUser
     * @param idEpisode
     */
    public void addEpisode (int idUser, int idEpisode);

    /**
     * Remove a episode from the user list
     * @param idUser
     * @param idEpisode
     */
    public void removeEpisode (int idUser, int idEpisode);

    /**
     * Select all the episodes seemed from a user by the its id
     * @param idUser
     * @return
     */
    public ArrayList<Episode> selectEpisodesByUser (int idUser);
}
