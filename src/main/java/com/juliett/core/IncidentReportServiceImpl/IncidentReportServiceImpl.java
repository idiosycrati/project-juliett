package com.juliett.core.IncidentReportServiceImpl;

import java.util.Collection;

import com.juliett.core.IncidentReportModel.IncidentReportModel;
import com.juliett.core.IncidentReportRepository.IncidentReportRepository;
import com.juliett.core.IncidentReportRepositoryImpl.IncidentReportRepositoryImpl;
import com.juliett.core.IncidentReportService.IncidentReportService;
import com.juliett.core.InsuranceEntity.model.InsuranceEntityModel;
import com.xurpas.development.core.db.DatabaseManager;
import com.xurpas.development.core.service.impl.AbstractServiceImpl;

public class IncidentReportServiceImpl extends AbstractServiceImpl<IncidentReportModel>
		implements IncidentReportService {

	private final IncidentReportRepository incidentReportRepository;

	public IncidentReportServiceImpl(DatabaseManager databaseManager) {
		super(new IncidentReportRepositoryImpl(databaseManager));
		this.incidentReportRepository = (IncidentReportRepository) repository;
	}
	


	@Override
	public Collection<IncidentReportModel> findReportById(Long id) {
		// TODO Auto-generated method stub
		return this.incidentReportRepository.findReportById(id);
	}

}
