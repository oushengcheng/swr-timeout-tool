package com.aps.wicc.ejb;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.aps.wicc.model.Alteration;
import com.aps.wicc.model.Incident;
import com.aps.wicc.model.ServiceGroupAlteration;

@Stateless
public class HistoryBean
{
    private IncidentBean incidentBean;
    private DateTimeZone dateTimeZone;
    private EntityManager entityManager;
    private static final Integer COLUMNS;
    private static final Integer EXCEL_MAX_COL_WIDTH;

    static {
        COLUMNS = 15;
        EXCEL_MAX_COL_WIDTH = 65280;
    }

    @Inject
    public HistoryBean(final IncidentBean incidentBean, final EntityManager entityManager, final DateTimeZone dateTimeZone) {
        super();
        this.incidentBean = incidentBean;
        this.entityManager = entityManager;
        this.dateTimeZone = dateTimeZone;
    }

    public HistoryBean() {
        super();
    }

    public String getFormat() {
        return "application/vnd.ms-excel";
    }

    public String getFilename() {
        return "train service plans.xls";
    }

    public void createReport(final DateTime from, final DateTime until, final OutputStream outputStream) {

        final List<History> history = new ArrayList<History>();
        final AuditReader reader = AuditReaderFactory.get(this.entityManager);

        for (final Incident incident : this.incidentBean.getIncidents(from, until)) {

            Integer revision = 0;

            for (final Number n : reader.getRevisions(Incident.class, incident.getId())) {

                final Incident audit = reader.find(Incident.class, incident.getId(), n);

                if (audit.getServiceGroupAlterations().isEmpty()) {

                    history.add(new History(revision, audit));

                } else {

                    for (final ServiceGroupAlteration serviceGroupAlteration : audit.getServiceGroupAlterations()) {

                        if (serviceGroupAlteration.getAlterations().isEmpty()) {

                            history.add(new History(revision, audit, serviceGroupAlteration));

                        } else {

                            for (final Alteration alteration : serviceGroupAlteration.getAlterations()) {

                                history.add(new History(revision, audit, serviceGroupAlteration, alteration));
                            }

                        }
                    }
                }

                ++revision;

            }

        }

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("dateTimeZone", this.dateTimeZone);
        parameters.put("from", from);
        parameters.put("until", until);

        try {
            final InputStream jasperReport = HistoryBean.class.getResourceAsStream("history.jasper");
            final JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, (JRDataSource)new JRBeanCollectionDataSource(history));
            JRXlsExporter exporter = new JRXlsExporter();
            final ByteArrayOutputStream os = new ByteArrayOutputStream();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, os);
            exporter.exportReport();
            outputStream.write(this.formatting(os.toByteArray()));


        } catch (JRException e) {

            throw new RuntimeException(e);

        } catch (IOException ioe) {

            throw new RuntimeException(ioe);
        }
    }

    private byte[] formatting(final byte[] content) throws IOException {

        HSSFWorkbook hwb = new HSSFWorkbook(new ByteArrayInputStream(content));

        for (int j = 0; j < hwb.getNumberOfSheets(); ++j) {

            hwb.getSheetAt(j).setZoom(4, 5);
            hwb.getSheetAt(j).setHorizontallyCenter(true);
            hwb.getSheetAt(j).getPrintSetup().setPaperSize((short)9);
            hwb.getSheetAt(j).getPrintSetup().setFooterMargin(0.511811024);
            hwb.getSheetAt(j).getPrintSetup().setHeaderMargin(0.511811024);
            hwb.getSheetAt(j).setRepeatingRows(CellRangeAddress.valueOf("1:3"));

            for (int k = 1; k <= HistoryBean.COLUMNS; ++k) {
                hwb.getSheetAt(j).setColumnWidth(k, (int)HistoryBean.EXCEL_MAX_COL_WIDTH);
                hwb.getSheetAt(j).autoSizeColumn(k);
                hwb.getSheetAt(j).setColumnWidth(k, (int)(hwb.getSheetAt(j).getColumnWidth(k) * 1.1));
            }
            hwb.getSheetAt(j).setMargin((short)0, 0.62992126);
            hwb.getSheetAt(j).setMargin((short)1, 0.62992126);
            hwb.getSheetAt(j).setMargin((short)2, 0.708661417);
            hwb.getSheetAt(j).setMargin((short)3, 0.708661417);
        }
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        hwb.write((OutputStream)os);
        return os.toByteArray();
    }
}
