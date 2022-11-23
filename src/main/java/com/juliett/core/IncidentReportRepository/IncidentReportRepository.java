package com.juliett.core.IncidentReportRepository;

import java.util.Collection;

import com.juliett.core.IncidentReportModel.IncidentReportModel;
import com.xurpas.development.core.repository.AbstractRepository;

public interface IncidentReportRepository extends AbstractRepository<IncidentReportModel> {
	public Collection<IncidentReportModel> findReportById(Long id);
}
