package com.juliett.core.SampleRepository;

import com.juliett.core.Sample.model.SampleModel;
import com.xurpas.development.core.exception.XDevServiceException;
import com.xurpas.development.core.repository.AbstractRepository;


import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.naming.NamingException;

public interface SampleRepository extends AbstractRepository<SampleModel> {
}
