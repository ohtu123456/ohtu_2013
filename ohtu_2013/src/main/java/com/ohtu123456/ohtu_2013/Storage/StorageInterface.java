package com.ohtu123456.ohtu_2013.Storage;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Heikki Kalliokoski
 */
public interface StorageInterface {

    void addReference(Map<String, String> ref) throws Exception;

    List<Map<String, String>> getReferences();
    
}
