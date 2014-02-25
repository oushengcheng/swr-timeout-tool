package com.aps.wicc.ejb.initialisation;

import org.apache.deltaspike.core.api.exclude.*;
import org.apache.deltaspike.core.api.projectstage.*;

import javax.inject.Inject;
import javax.persistence.*;

import com.aps.wicc.model.*;

import java.util.*;

@Exclude(ifProjectStage = { ProjectStage.Production.class })
@Primary
public class ServiceGroupInitialisation implements Initialisable
{
	@Inject
    private EntityManager entityManager;
    
    @Override
    public void init() {
        final ServiceGrouping mainline = new ServiceGrouping("Mainline", 1);
        final ServiceGrouping suburban = new ServiceGrouping("Suburban", 2);
        final ServiceGrouping circular = new ServiceGrouping("Circular", 3);
        final ServiceGrouping windsor = new ServiceGrouping("Windsor", 4);
        this.entityManager.persist((Object)mainline);
        this.entityManager.persist((Object)suburban);
        this.entityManager.persist((Object)circular);
        this.entityManager.persist((Object)windsor);
        final List<ServiceGroup> serviceGroups = new ArrayList<ServiceGroup>();
        serviceGroups.add(new ServiceGroup("1A", "1A", "Waterloo - Alton", suburban));
        serviceGroups.add(new ServiceGroup("2A", "2A", "Waterloo - Alton", suburban));
        serviceGroups.add(new ServiceGroup("1B", "1B", "Waterloo - Southampton/Poole", mainline));
        serviceGroups.add(new ServiceGroup("2B", "2B", "Southampton - Bournemouth/Poole", mainline));
        serviceGroups.add(new ServiceGroup("1C", "1C", "Waterloo - Reading via Richmond", windsor));
        serviceGroups.add(new ServiceGroup("2C", "2C", "Waterloo - Reading", windsor));
        serviceGroups.add(new ServiceGroup("1D", "1D", "Waterloo - Dorking", suburban));
        serviceGroups.add(new ServiceGroup("2D-G", "2D", "Waterloo - Guildford / Effingham Junction (peak) via Leatherhead", suburban));
        serviceGroups.add(new ServiceGroup("2D-E", "2D", "Waterloo - Epsom (peak)", suburban));
        serviceGroups.add(new ServiceGroup("2E", "2E", "Portsmouth to Southampton (stopping)", mainline));
        serviceGroups.add(new ServiceGroup("2F", "2F", "Waterloo - Woking/Guildford", suburban));
        serviceGroups.add(new ServiceGroup("1G", "1G", "Waterloo - Portsmouth Harbour via Effingham Junction", mainline));
        serviceGroups.add(new ServiceGroup("2G", "2G", "Waterloo - Guildford (via Cobham)", suburban));
        serviceGroups.add(new ServiceGroup("2H", "2H", "Waterloo - Shepperton", windsor));
        serviceGroups.add(new ServiceGroup("1J", "1J", "Brockenhurst - Lymington Pier", mainline));
        serviceGroups.add(new ServiceGroup("2J", "2J", "Waterloo - Hampton Court", suburban));
        serviceGroups.add(new ServiceGroup("2K", "2K", "Waterloo - Waterloo via Kingston & Twickenham", circular));
        serviceGroups.add(new ServiceGroup("1L", "1L", "Waterloo - West of England", mainline));
        serviceGroups.add(new ServiceGroup("2L-B", "2L", "Waterloo - Basingstoke (stopping)", suburban));
        serviceGroups.add(new ServiceGroup("2L-E", "2L", "Exeter - Axminster (local)", mainline));
        serviceGroups.add(new ServiceGroup("2M", "2M", "Waterloo - Chessington South", suburban));
        serviceGroups.add(new ServiceGroup("1N", "1N", "Waterloo - Aldershot (via Ascot) (peak)", windsor));
        serviceGroups.add(new ServiceGroup("2N", "2N", "Guildford - Aldershot / Ascot", windsor));
        serviceGroups.add(new ServiceGroup("1O", "1O", "Bristol - Salisbury", mainline));
        serviceGroups.add(new ServiceGroup("2O", "2O", "Waterloo - Waterloo via Twickenham & Kingston", circular));
        serviceGroups.add(new ServiceGroup("1P", "1P", "Waterloo - Portsmouth Direct (fast)", mainline));
        serviceGroups.add(new ServiceGroup("2P", "2P", "Waterloo - Portsmouth Direct (semi-fast & stopping)", mainline));
        serviceGroups.add(new ServiceGroup("2R-R", "2R", "Romsey - Totton", mainline));
        serviceGroups.add(new ServiceGroup("2R-W", "2R", "Waterloo - Waterloo via Richmond and Brentford", windsor));
        serviceGroups.add(new ServiceGroup("2S", "2S", "Waterloo - Weybridges via Hounslow / Staines", windsor));
        serviceGroups.add(new ServiceGroup("1T", "1T", "Waterloo - Portsmouth Harbour (via Eastleigh)", mainline));
        serviceGroups.add(new ServiceGroup("2T", "2T", "Basingstoke - Portsmouth Harbour (stopping)", mainline));
        serviceGroups.add(new ServiceGroup("2U", "2U", "Waterloo - Windsor", windsor));
        serviceGroups.add(new ServiceGroup("1V", "1V", "Salisbury - Bristol", mainline));
        serviceGroups.add(new ServiceGroup("2V", "2V", "Waterloo - Waterloo via Brentford / Twickenham", windsor));
        serviceGroups.add(new ServiceGroup("1W", "1W", "Waterloo - Weymouth", mainline));
        serviceGroups.add(new ServiceGroup("2W", "2W", "Eastleigh - Weymouth (local)", mainline));
        for (final ServiceGroup serviceGroup : serviceGroups) {
            this.entityManager.persist((Object)serviceGroup);
        }
    }
}
