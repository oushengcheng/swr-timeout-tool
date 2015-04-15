package com.aps.wicc.ejb.export;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

class ReportExporter {

	public void export(List<ReportLine> lines, OutputStream outputStream) {

		final Map<String, Object> parameters = new HashMap<>();

        try {

        	InputStream jasperReport = ReportExporter.class.getResourceAsStream("contingency.jasper");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, (JRDataSource) new JRBeanCollectionDataSource(lines));

            JRXlsxExporter exporter = new JRXlsxExporter();

            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
            exporter.setConfiguration(createConfiguration());

            exporter.exportReport();

        } catch (JRException e) {

            throw new RuntimeException(e);

        }

	}

	private SimpleXlsxReportConfiguration createConfiguration() {
		SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
		return configuration;
	}
}
