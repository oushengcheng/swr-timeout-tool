package com.aps.wicc.model;

import org.apache.deltaspike.core.api.exclude.*;

import javax.persistence.*;

import com.google.common.collect.*;

import java.util.*;

import org.apache.commons.lang3.builder.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.Type;

@Exclude
@Entity
public class ContingencyPlan
{
	@Id
	@GenericGenerator(name="hilogen", strategy="hilo")
	@GeneratedValue(strategy=GenerationType.TABLE, generator="hilogen")
    private Long id;

	@Version
    private Long version;

    private String code;
    private String title;

    @Type(type="text")
    private String description;

    @OneToMany(cascade=CascadeType.ALL)
    @IndexColumn(name="position")
    private List<ContingencyAlteration> contingencyAlterations;

    public ContingencyPlan() {
        super();
        this.contingencyAlterations = new ArrayList<ContingencyAlteration>();
    }

    private ContingencyPlan(String code, String title, String description, List<ContingencyAlteration> contingencyAlterations) {
        super();
        this.contingencyAlterations = new ArrayList<ContingencyAlteration>();
        this.code = code;
        this.title = title;
        this.description = description;
        this.contingencyAlterations = contingencyAlterations;
    }

    public Long getId() {
        return this.id;
    }

    public Long getVersion() {
        return this.version;
    }

    public String getCode() {
        return this.code;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
		return description;
	}

	public List<ContingencyAlteration> getContingencyAlterations() {
        return ImmutableList.copyOf(this.contingencyAlterations);
    }

    @Override
    public String toString() {
        final ToStringBuilder builder = new ToStringBuilder((Object)this);
        builder.append("id", (Object)this.id);
        builder.append("version", (Object)this.version);
        builder.append("code", (Object)this.code);
        builder.append("title", (Object)this.title);
        return builder.toString();
    }

    @Override
    public int hashCode() {
        final HashCodeBuilder builder = new HashCodeBuilder();
        builder.append((Object)this.code);
        return builder.toHashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ContingencyPlan)) {
            return false;
        }
        final ContingencyPlan that = (ContingencyPlan)obj;
        final EqualsBuilder builder = new EqualsBuilder();
        builder.append((Object)this.code, (Object)that.code);
        return builder.isEquals();
    }

    public static class PlanBuilder
    {
        private String code;
        private String title;
        private String description;
        private List<ContingencyAlteration> contingencyAlterations;

        public PlanBuilder() {
            super();
            this.contingencyAlterations = new ArrayList<ContingencyAlteration>();
        }

        public PlanBuilder setCode(final String code) {
            this.code = code;
            return this;
        }

        public PlanBuilder setTitle(final String title) {
            this.title = title;
            return this;
        }

        public PlanBuilder setDescription(final String description) {
            this.description = description;
            return this;
        }

        public PlanBuilder add(final ContingencyAlteration contingencyAlteration) {
            this.contingencyAlterations.add(contingencyAlteration);
            return this;
        }

        public ContingencyPlan build() {
            return new ContingencyPlan(code, title, description, contingencyAlterations);
        }
    }
}
