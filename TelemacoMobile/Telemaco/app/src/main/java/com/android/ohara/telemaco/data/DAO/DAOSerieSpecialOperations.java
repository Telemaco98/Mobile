package com.android.ohara.telemaco.data.DAO;

import com.android.ohara.telemaco.entity.Serie;
import java.util.ArrayList;

/**
 *
 * @author Shirley Ohara Telemaco de Freitas (shirleyohara@ufrn.edu.br)
 */
public interface DAOSerieSpecialOperations extends DAO<Serie> {
    /**
     * Select a serie by name.
     * @param name
     * @return
     */
    public Serie select(String name);
    
    /**
     * Select all series and return an array with them.
     * @return
     */
    public ArrayList<Serie> selectAllSeries();
    
    /**
     * Search series by name that user inputs.
     * @param input
     * @return
     */
    public ArrayList<Serie> search(String input);
}
