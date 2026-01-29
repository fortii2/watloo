package me.forty2.watloo.tasks;

import lombok.extern.slf4j.Slf4j;
import me.forty2.watloo.entity.Term;
import me.forty2.watloo.feign.WaterlooOpenDataClient;
import me.forty2.watloo.repository.TermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class UwOpenapiTask {

    @Autowired
    private WaterlooOpenDataClient waterlooOpenDataClient;

    @Autowired
    private TermRepository termRepository;

    @Scheduled(fixedRate = 86400000)
    public void syncTerms() {
        log.info("task: starting pull terms data from uw open api.");

        try {
            List<Term> data = waterlooOpenDataClient.getAllTerms();

            if (data != null && !data.isEmpty()) {
                termRepository.saveAll(data);
                log.info("task: pulled {} terms data from uw open api.", data.size());
            }
        } catch (Exception e) {
            log.error("task: error in pulled terms data from uw open api, {}", e.getMessage());
        }
    }
}
