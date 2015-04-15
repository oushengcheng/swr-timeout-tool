package com.aps.wicc.ejb.export;

import java.util.ArrayList;
import java.util.List;

import com.aps.wicc.model.Incident;
import com.aps.wicc.model.ServiceGroupAlteration;

public class ReportLine {

	private static final int ALTERATIONS_SHOWN = 4;

	private String title;
	private String description;
	private String serviceGroup;
	private String direction;
	private String delay;
	private String freeform;
	private String affect;
	private List<String> alterationType = new ArrayList<>();
	private List<String> location = new ArrayList<>();

	public ReportLine(Incident incident, ServiceGroupAlteration alteration) {
		title = incident.getTitle();
		description = incident.getDescription();
		serviceGroup = alteration.getServiceGroup().getUid();
		direction = alteration.getDirection().name();
		delay = alteration.getDelay();
		freeform = alteration.getFreeform();
		affect = alteration.getAffect().name();
		for (int index = 0; index < ALTERATIONS_SHOWN; index++) {
			createAlteration(alteration, index);
		}
	}

	private void createAlteration(ServiceGroupAlteration alteration, int index) {
		if (alteration.getAlterations().size() >= index + 1) {
			alterationType.add(alteration.getAlterations().get(index).getAlterationType().name());
			location.add(alteration.getAlterations().get(index).getLocation());
		}
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getServiceGroup() {
		return serviceGroup;
	}

	public String getDirection() {
		return direction;
	}

	public String getDelay() {
		return delay;
	}

	public String getFreeform() {
		return freeform;
	}

	public String getAffect() {
		return affect;
	}

	public String getAlterationType0() {
		return getAlterationType(0);
	}

	public String getLocation0() {
		return getLocation(0);
	}

	public String getAlterationType1() {
		return getAlterationType(1);
	}

	public String getLocation1() {
		return getLocation(1);
	}

	public String getAlterationType2() {
		return getAlterationType(2);
	}

	public String getLocation2() {
		return getLocation(2);
	}

	public String getAlterationType3() {
		return getAlterationType(3);
	}

	public String getLocation3() {
		return getLocation(3);
	}

	private String getAlterationType(int index) {
		if (alterationType.size() >= index + 1) {
			return alterationType.get(index);
		} else {
			return "";
		}
	}

	private String getLocation(int index) {
		if (location.size() >= index + 1) {
			return location.get(index);
		} else {
			return "";
		}
	}
}
