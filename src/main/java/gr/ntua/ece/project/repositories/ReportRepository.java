package gr.ntua.ece.project.repositories;

import gr.ntua.ece.project.entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long>,JpaSpecificationExecutor<Report> {
    Report findById(int id);
}
