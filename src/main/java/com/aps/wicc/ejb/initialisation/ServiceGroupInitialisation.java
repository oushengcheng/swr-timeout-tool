package com.aps.wicc.ejb.initialisation;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

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
        final ServiceGrouping other = new ServiceGrouping("Other", 5);
        
        this.entityManager.persist(mainline);
        this.entityManager.persist(suburban);
        this.entityManager.persist(circular);
        this.entityManager.persist(windsor);
        this.entityManager.persist(other);
        
        final List<ServiceGroup> serviceGroups = new ArrayList<ServiceGroup>();
        
        serviceGroups.add(new ServiceGroup(10L, "1A", "1A", FALSE, "Waterloo to Alton", "Alton to Waterloo", "Waterloo to/from Alton", suburban));
        serviceGroups.add(new ServiceGroup(20L, "2A", "2A", FALSE, "Waterloo to Farnham ", "Farnham to Waterloo", "Waterloo to/from Farnham", suburban));
        serviceGroups.add(new ServiceGroup(30L, "1B", "1B", FALSE, "Waterloo to Southampton/Poole", "Southampton/Poole to Waterloo", "Waterloo to/from Southampton/Poole", mainline));
        serviceGroups.add(new ServiceGroup(40L, "2B", "2B", FALSE, "Waterloo to Bournemouth/Poole", "Bournemouth/Poole to Waterloo", "Waterloo to/from Bournemouth/Poole", mainline));
        serviceGroups.add(new ServiceGroup(50L, "1C", "1C", FALSE, "Waterloo to Reading via Richmond", "Reading to Waterloo via Richmond", "Waterloo to/from Reading via Richmond", windsor));
        serviceGroups.add(new ServiceGroup(60L, "2C", "2C", FALSE, "Waterloo to Reading", "Reading to Waterloo", "Waterloo to/from Reading", windsor));
        serviceGroups.add(new ServiceGroup(70L, "1D", "1D", FALSE, "Waterloo to Dorking", "Dorking to Waterloo", "Waterloo to/from Dorking", suburban));
        serviceGroups.add(new ServiceGroup(80L, "2D-G", "2D", FALSE, "Waterloo to Guildford / Effingham Junction (peak) via Leatherhead", "Guildford / Effingham Jn (peak) to Waterloo via Leatherhead", "Waterloo to/from Guildford / Effingham Junction (peak) via Leatherhead", suburban));
        serviceGroups.add(new ServiceGroup(90L, "2D-E", "2D", FALSE, "Waterloo to Epsom (peak)", "Epsom (peak) to Waterloo", "Waterloo to/from Epsom (peak)", suburban));
        serviceGroups.add(new ServiceGroup(100L, "2E", "2E", FALSE, "Portsmouth to Southampton (stopping)", "Southampton to Portsmouth (stopping)", "Portsmouth to/from Southampton (stopping)", mainline));
        serviceGroups.add(new ServiceGroup(110L, "2F", "2F", FALSE, "Waterloo to Woking/Guildford", "Woking/Guildford to Waterloo", "Waterloo to/from Woking/Guildford", suburban));
        serviceGroups.add(new ServiceGroup(120L, "1G", "1G", FALSE, "Waterloo to Portsmouth Harbour via Cobham", "Portsmouth Harbour to Waterloo via Cobham", "Waterloo to/from Portsmouth Harbour via Cobham", mainline));
        serviceGroups.add(new ServiceGroup(130L, "2G", "2G", FALSE, "Waterloo to Guildford (via Cobham)", "Guilford to Waterloo via Cobham", "Waterloo to/from Guildford (via Cobham)", suburban));
        serviceGroups.add(new ServiceGroup(140L, "2H", "2H", FALSE, "Waterloo to Shepperton", "Shepperton to Waterloo", "Waterloo to/from Shepperton", windsor));
        serviceGroups.add(new ServiceGroup(150L, "1J", "1J", FALSE, "Brockenhurst to Lymington Pier", "Lymington Pier to Brockenhurst", "Brockenhurst to/from Lymington Pier", mainline));
        serviceGroups.add(new ServiceGroup(160L, "2J", "2J", FALSE, "Waterloo to Hampton Court", "Hampton Court to Waterloo", "Waterloo to/from Hampton Court", suburban));
        serviceGroups.add(new ServiceGroup(170L, "2K", "2K", TRUE, "", "", "Waterloo to Waterloo M-TW via Kingston/Twickenham", circular));
        serviceGroups.add(new ServiceGroup(180L, "1L", "1L", FALSE, "Waterloo to West of England", "West of England to Waterloo", "Waterloo to/from West of England", mainline));
        serviceGroups.add(new ServiceGroup(190L, "2L", "2L", FALSE, "Waterloo to Basingstoke (stopping)", "Basingstoke to Waterloo (stopping)", "Waterloo to/from Basingstoke (stopping)", suburban));
        serviceGroups.add(new ServiceGroup(200L, "2M", "2M", FALSE, "Waterloo to Chessington South", "Chessington South to Waterloo", "Waterloo to/from Chessington South", suburban));
        serviceGroups.add(new ServiceGroup(210L, "1N", "1N", FALSE, "Waterloo to Aldershot (via Ascot) (peak)", "Aldershot to Waterloo (via Ascot) (peak)", "Waterloo to/from Aldershot (via Ascot) (peak)", windsor));
        serviceGroups.add(new ServiceGroup(220L, "2N", "2N", FALSE, "Guildford to Aldershot / Ascot", "Aldershot/Ascot to Guildford", "Guildford to/from Aldershot / Ascot", windsor));
        serviceGroups.add(new ServiceGroup(230L, "1O", "1O", TRUE, "", "", "Bristol to Salisbury", mainline));
        serviceGroups.add(new ServiceGroup(240L, "2O", "2O", TRUE, "", "", "Waterloo to Waterloo TW-M via Twickenham/Kingston", circular));
        serviceGroups.add(new ServiceGroup(250L, "1P", "1P", FALSE, "Waterloo to Portsmouth Direct (fast)", "Portsmouth to Waterloo (fast)", "Waterloo to/from Portsmouth Direct (fast)", mainline));
        serviceGroups.add(new ServiceGroup(260L, "2P", "2P", FALSE, "Waterloo to Portsmouth Direct (semi-fast & stopping)", "Portsmouth to Waterloo (semi-fast & stopping)", "Waterloo to/from Portsmouth Direct (semi-fast & stopping)", mainline));
        serviceGroups.add(new ServiceGroup(270L, "2R-R", "2R", TRUE, "", "", "Salisbury to Romsey SC-EH", mainline));
        serviceGroups.add(new ServiceGroup(280L, "2R-W", "2R", TRUE, "", "", "Waterloo to Waterloo TW-B via Twickenham/Brentford", windsor));
        serviceGroups.add(new ServiceGroup(290L, "2S", "2S", FALSE, "Waterloo to Weybridge (via Hounslow)", "Weybridge to Waterloo via Hounslow / Staines", "Waterloo to/from Weybridge (via Hounslow)", windsor));
        serviceGroups.add(new ServiceGroup(300L, "2S-S", "2S", TRUE, "", "", "Salisbury to Romsey EH-SC", mainline));
        serviceGroups.add(new ServiceGroup(310L, "1T", "1T", FALSE, "Waterloo to Portsmouth Harbour (via Eastleigh)", "Portsmouth Harbour to Waterloo (via Eastleigh)", "Waterloo to/from Portsmouth Harbour (via Eastleigh)", mainline));
        serviceGroups.add(new ServiceGroup(320L, "2T", "2T", FALSE, "Basingstoke to Portsmouth Harbour (stopping)", "Portsmouth Harbour to Basingstoke (stopping)", "Basingstoke to/from Portsmouth Harbour (stopping)", mainline));
        serviceGroups.add(new ServiceGroup(330L, "2U", "2U", FALSE, "Waterloo to Windsor", "Windsor to Waterloo", "Waterloo to/from Windsor", windsor));
        serviceGroups.add(new ServiceGroup(340L, "1V", "1V", TRUE, "", "", "Salisbury to Bristol", mainline));
        serviceGroups.add(new ServiceGroup(350L, "2V", "2V", TRUE, "", "", "Waterloo to Waterloo B-TW (via Brentford/Twickenham)", circular));
        serviceGroups.add(new ServiceGroup(360L, "1W", "1W", FALSE, "Waterloo to Weymouth", "Weymouth to Waterloo", "Waterloo to/from Weymouth", mainline));
        serviceGroups.add(new ServiceGroup(370L, "2W", "2W", FALSE, "Eastleigh to Weymouth (local)", "Weymouth to Eastleigh (local)", "Eastleigh to/from Weymouth (local)", mainline));

        serviceGroups.add(new ServiceGroup(380L, "2N-S", "2N", FALSE, "Southern Brighton to Portsmouth", "Southern Portsmouth to Brighton", "", other));
        serviceGroups.add(new ServiceGroup(390L, "2S-S", "2S", FALSE, "Southern Littlehampton to Portsmouth", "Southern Portsmouth to Littlehampton", "", other));
        serviceGroups.add(new ServiceGroup(400L, "1C-S", "1C", FALSE, "Southern Victoria to Portsmouth", "Southern Portsmouth to Victoria", "", other));
        serviceGroups.add(new ServiceGroup(410L, "1N-S", "1N", FALSE, "Southern Brighton to Southampton", "Southern Southampton to Brighton", "", other));
        serviceGroups.add(new ServiceGroup(420L, "1J-S", "1J", TRUE, "", "", "Southern Victoria to Southampton", other));
        serviceGroups.add(new ServiceGroup(430L, "1C-S2", "1C", TRUE, "", "", "Southern Southampton to Victoria", other));
        serviceGroups.add(new ServiceGroup(440L, "2E-S", "2E", FALSE, "Southern Victoria to Horsham", "Southern Horsham to Victoria", "", other));
        serviceGroups.add(new ServiceGroup(450L, "2C-S", "2C", FALSE, "Southern Victoria to Portsmouth", "Southern Portsmouth to Victoria", "", other));
        serviceGroups.add(new ServiceGroup(460L, "2K-S", "2K", FALSE, "Southern Victoria to Southampton", "Southern Southampton to Victoria", "", other));

        serviceGroups.add(new ServiceGroup(470L, "XC", "", FALSE, "XC The North to Bournemouth", "XC Bournemouth to The North", "XC The North to/from Bournemouth", other));
        serviceGroups.add(new ServiceGroup(480L, "FGW-CP", "", FALSE, "FGW Cardiff to Portsmouth", "FGW Portsmouth to Cardiff", "FGW Cardiff to/from Portsmouth", other));
        serviceGroups.add(new ServiceGroup(490L, "FGW-CS", "", FALSE, "FGW Cardiff to Southampton & Brighton", "FGW Southampton & Brighton to Cardiff", "FGW Cardiff to/from Southampton & Brighton", other));
        serviceGroups.add(new ServiceGroup(500L, "FGW-RG", "", FALSE, "FGW Reading to Gatwick", "FGW Gatwick to Reading", "FGW Reading to/from Gatwick", other));
        serviceGroups.add(new ServiceGroup(510L, "FGW-RR", "", FALSE, "FGW Redhill/Shalford to Reading", "FGW Reading to Redhill/Shalford", "FGW Redhill/Shalford to/from Reading", other));
        serviceGroups.add(new ServiceGroup(520L, "FGW-WW", "", FALSE, "FGW Westbury to Weymouth", "FGW Weymouth to Westbury", "FGW Westbury to/from Weymouth", other));
        serviceGroups.add(new ServiceGroup(530L, "FGW-PP", "", FALSE, "FGW HST Paddington to Penzance", "FGW HST Penzance to Paddington", "FGW HST Paddington to/from Penzance", other));

        
        for (final ServiceGroup serviceGroup : serviceGroups) {
            this.entityManager.persist((Object)serviceGroup);
        }
    }
}
