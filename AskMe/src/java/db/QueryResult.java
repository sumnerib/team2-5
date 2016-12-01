/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.*;

/**
 *
 * @author Isaac Sumner
 * @version 11.23.16
 */
public class QueryResult {

    private Vector<String> columnNames      
            = new Vector<String>();   
    private Vector<String> fieldValues      
            = new Vector<String>();   
    public void addColumnName(String name) {     
        columnNames.add(name);   
    }   
    public void addFieldValue(String value) {
        fieldValues.add(value);   
    }   
    public Iterator<String> columnNamesIterator() {     
        return columnNames.iterator();   
    }   
    public Iterator<String> fieldValuesIterator() {     
        return fieldValues.iterator();   
    }
}
