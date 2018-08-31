/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.android.ohara.telemaco.data.DAO;

import com.android.ohara.telemaco.entity.Rating;
import java.util.ArrayList;

/**
 *
 * @author  Shirley Ohara Telemaco de Freitas (shirleyohara@ufrn.edu.br)
 * @version 28/06/2018
 */
public interface DAORatingSpecialOperations extends DAO<Rating> {
    
    /**
     * Select all comments from one serie.
     * @param idSerie
     * @return
     */
    public ArrayList<Rating> selectBySerie(int idSerie);
}
