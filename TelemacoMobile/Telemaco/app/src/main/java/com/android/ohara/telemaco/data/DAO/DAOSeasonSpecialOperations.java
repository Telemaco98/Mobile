package com.android.ohara.telemaco.data.DAO;

import com.android.ohara.telemaco.entity.Season;
import java.util.ArrayList;

/**
 * Interface to indicate the special operations in the SeasonDAO
 * @author  Shirley Ohara (shirleyohara@ufrn.edu.br)
 * @version 12 de abr de 2018 | 09:22:25
 */
public interface DAOSeasonSpecialOperations extends DAO<Season> {
	/**
     * Select a season from the database since the number and idSerie values. 
     * @param  number
     * @param  idSerie
     * @return season
     */
    public Season select(int number, int idSerie);
    
    /**
     * Returns all the episodes of the Season
     * @param idSerie
     * @return episodes
     */
    public ArrayList<Season> selectAllSeasons(int idSerie);
}
