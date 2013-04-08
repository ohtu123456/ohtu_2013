package com.ohtu123456.ohtu_2013.Storage;

import java.util.Map;

/**
 *
 * @author Heikki Kalliokoski
 */
public interface ReferenceInterface {

    String getField(String field);
    void setField(String field, String value);
    Map<String, String> getInMap();
}
