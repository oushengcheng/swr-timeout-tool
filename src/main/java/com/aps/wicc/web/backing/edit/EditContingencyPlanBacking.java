package com.aps.wicc.web.backing.edit;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.apache.deltaspike.core.api.config.view.ViewConfig;

import com.aps.wicc.ejb.ContingencyPlanBean;
import com.aps.wicc.model.ContingencyPlan;
import com.aps.wicc.web.Pages;

@Named
@RequestScoped
public class EditContingencyPlanBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    private ContingencyPlanBean contingencyPlanBean;
    private CurrentEdit currentEdit;

    private List<ContingencyPlan> contingencyPlans;

    @NotNull(message="{editsummarybacking_contingencyplan_notnull}")
    private ContingencyPlan contingencyPlan;

    public EditContingencyPlanBacking() {
    }

    @Inject
    public EditContingencyPlanBacking(ContingencyPlanBean contingencyPlanBean, CurrentEdit currentEdit) {
        this.contingencyPlanBean = contingencyPlanBean;
        this.currentEdit = currentEdit;
    }

    @PostConstruct
    void init() {
        this.contingencyPlans = this.contingencyPlanBean.findAll();
    }

    public ContingencyPlan getContingencyPlan() {
        return this.contingencyPlan;
    }

    public void setContingencyPlan(final ContingencyPlan contingencyPlan) {
        this.contingencyPlan = contingencyPlan;
    }

    public List<ContingencyPlan> getContingencyPlans() {
        return this.contingencyPlans;
    }

    public Class<? extends ViewConfig> addContingencyPlan() {
        contingencyPlanBean.addContingencyPlanToIncident(currentEdit.getIncident(), contingencyPlan);
        return Pages.Editdetail.class;
    }

    public Class<? extends ViewConfig> cancelContingencyPlan() {
        return Pages.Editdetail.class;
    }

}
