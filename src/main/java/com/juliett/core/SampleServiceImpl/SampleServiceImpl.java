/**
 * Copyright (c) 2012-2020 Xurpas. All Rights Reserved.
 *
 * @author: Xurpas(doods)
 * @date: 7 Oct 2020
 * @current version: 1.0.0
 * @modified by:
 * @last modified:
 **/
package com.juliett.core.SampleServiceImpl;

import com.juliett.core.Sample.model.SampleModel;
import com.juliett.core.SampleRepository.SampleRepository;
import com.juliett.core.SampleRepositoryImpl.SampleRepositoryImpl;
import com.juliett.core.SampleService.SampleService;
import com.xurpas.development.core.db.DatabaseManager;
import com.xurpas.development.core.exception.XDevServiceException;
import com.xurpas.development.core.service.impl.AbstractServiceImpl;

import javax.naming.NamingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public class SampleServiceImpl extends AbstractServiceImpl<SampleModel> implements SampleService {

    private final SampleRepository sampleRepository;
  

    /**
     * @param databaseManager
     */
    public SampleServiceImpl(DatabaseManager databaseManager) {
    	  super(new SampleRepositoryImpl(databaseManager));
        this.sampleRepository           = (SampleRepository) repository;
    
    }

}
