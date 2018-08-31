package com.android.ohara.telemaco.data.DAO;

import com.android.ohara.telemaco.entity.Episode;
import java.util.ArrayList;

/**
 * Interface to indicate the special operations in the EpsiodeDAO
 *
 * @author Shirley Ohara (shirleyohara@ufrn.edu.br)
 * @version 12 de abr de 2018 | 09:25:18
 */
public interface DAOEpisodeSpecialOperations extends DAO<Episode> {
    /**
     * Select a episode from the database since the name and idSeason values.
     *
     * @param name
     * @param idSeason
     * @return	episode
     */
    public Episode select(String name, int idSeason);

    /**
     * Select a episode from the database since the number and idSeason values.
     *
     * @param number
     * @param idSeason
     * @return	episode
     */
    public Episode select(int number, int idSeason);

    /**
     * Select all episodes of a season
     *
     * @param idSeason
     * @return episodes
     */
    public ArrayList<Episode> selectAllEpisodes(int idSeason);
}
