package com.aps.wicc.ejb.export;

import java.util.ArrayList;
import java.util.List;

import com.aps.wicc.model.Incident;
import com.aps.wicc.model.ServiceGroupAlteration;

class ReportContent {

	public List<ReportLine> getReportLines(Incident incident) {

		List<ReportLine> lines = new ArrayList<>();

		for (ServiceGroupAlteration alteration : incident.getServiceGroupAlterations()) {

			lines.add(new ReportLine(incident, alteration));

		}

		return lines;

	}
}
