package com.juliett.core.IncidentReportService;

import java.util.Collection;

import com.juliett.core.IncidentReportModel.IncidentReportModel;
import com.xurpas.development.core.service.AbstractService;

public interface IncidentReportService extends AbstractService<IncidentReportModel> {
	public Collection<IncidentReportModel> findReportById(Long id);
}
