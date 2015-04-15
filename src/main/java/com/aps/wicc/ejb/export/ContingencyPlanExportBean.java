package com.aps.wicc.ejb.export;

import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

import com.aps.wicc.model.Incident;

public class ContingencyPlanExportBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public String getFormat() {
		return "application/vnd.ms-excel";
	}

	public String getFilename() {
		return "contingency plan export.xlsx";
	}

	public void createReport(Incident incident, OutputStream outputStream) {
		ReportContent content = new ReportContent();
		List<ReportLine> lines = content.getReportLines(incident);
		ReportExporter exporter = new ReportExporter();
		exporter.export(lines, outputStream);
	}

}
