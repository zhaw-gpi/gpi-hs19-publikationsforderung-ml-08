package ch.zhaw.gpi.publfoerderung;

import java.util.List;

import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * GetJournalRatingDelegate
 */
@Named("getJournalRatingAdapter")
public class GetJournalRatingDelegate implements JavaDelegate {

    @Autowired
    private JournalRepository journalRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String journalTitle = (String) execution.getVariable("journalTitle");

        List<JournalEntity> journals = journalRepository.findByJournalTitleIgnoreCase(journalTitle);

        Integer journalRating;
        if(journals.size() == 0){
            journalRating = 0;
        } else {
            journalRating = journals.get(0).getJournalRating();
        }

        execution.setVariable("journalAacsbRating", journalRating);
    }

    
}