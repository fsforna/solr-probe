/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tai.solr.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author francesco
 */
public final class Utils {

    /**
     * Massimizza il giorno della data portandolo all'ultimo millisecondo, ma
     * senza passare al giorno successivo.
     *
     * @param data
     * @return L'ultima data disponibile per il giorno passato come parametro.
     */
    public static final Date maximizeDay(Date data) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(data);
        calendar.set(Calendar.AM_PM, Calendar.AM);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.MILLISECOND, -1);
        calendar.add(Calendar.DAY_OF_YEAR, 1);

        return calendar.getTime();
    }
    
    

}
