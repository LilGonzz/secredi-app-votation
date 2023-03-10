package com.LilGonzz.sicredappvotation.utils.cron;

import com.LilGonzz.sicredappvotation.model.SessionVote;
import com.LilGonzz.sicredappvotation.services.SessionVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SessionVoteSchedule {
    @Autowired
    SessionVoteService service;
    @Scheduled(cron = "0 */5 * * * *")
    public void cronTimeToExpire(){
           List<SessionVote> sessions = service.getAllSessionVoteActive();
           for (SessionVote sessionVote : sessions)
               service.checkIfNotExpired(sessionVote);
    }
}
