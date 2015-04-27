package com.aps.wicc.ejb.initialisation;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aps.wicc.web.email.PublishDistributionList;

public class StartUpLogging implements Initialisable {

	private static final Logger LOGGER = LoggerFactory.getLogger(StartUpLogging.class);

	private String publishDistributionList;

	@Inject
	public StartUpLogging(@PublishDistributionList String publishDistributionList) {
		this.publishDistributionList = publishDistributionList;
	}

	@Override
	public void init() {
		LOGGER.info(String.format("Using email distribution list %s", publishDistributionList));
	}

}
