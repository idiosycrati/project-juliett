package com.juliett.api.SampleProcess;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.juliett.api.model.enums.ResponseCode;
import com.juliett.commons.process.AbstractProcess;
import com.juliett.core.SampleService.SampleService;
import com.xurpas.development.core.exception.XDevServiceException;
import com.xurpas.development.core.logger.Logger;

public class SampleProcess extends AbstractProcess{

	private SampleService sampleService;
	
	public SampleProcess(SampleService sampleService, Logger logger) {
		super(logger);
		this.sampleService=sampleService;
		// TODO Auto-generated constructor stub
	}

	public void getSample(HttpServletRequest request, HttpServletResponse response) {
		
			
		 try {
			sendResponse(response, ResponseCode.OK, sampleService.list());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XDevServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
