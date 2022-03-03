package gr.ntua.ece.project.services;

import gr.ntua.ece.project.entities.Event;
import gr.ntua.ece.project.entities.Parent;
import gr.ntua.ece.project.entities.Provider;
import gr.ntua.ece.project.entities.Report;
import gr.ntua.ece.project.forms.EventForm;
import gr.ntua.ece.project.forms.SearchForm;
import gr.ntua.ece.project.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService{

    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ParentRepository parentRepository;

    @Override
    public Report createReport(Event event, Parent parent, String comments){
        Report report = new Report();
        report.setComments(comments);
        //report.setParentUserId(parent.getUserId()); To afhnw gia th diafora!
        report.setParentByParentUserId(parent);
        //report.setEventId(event.getId()); Same!!
        report.setEventByEventId(event);
        report.setDateTime(new Timestamp(System.currentTimeMillis()));

        reportRepository.save(report);

        return report;
    }

    @Override
    public Report findById(int id) {

        return reportRepository.findById(id);
    }

}
