package gr.ntua.ece.project.services;

import gr.ntua.ece.project.entities.Event;
import gr.ntua.ece.project.entities.Parent;
import gr.ntua.ece.project.entities.Report;
import gr.ntua.ece.project.entities.Provider;
import gr.ntua.ece.project.forms.EventForm;
import gr.ntua.ece.project.forms.SearchForm;

import java.util.List;

public interface ReportService {

    Report createReport(Event event, Parent parent, String comments);
    Report findById(int id);

}
