package de.techdev.trackr.domain.employee.expenses;

import de.techdev.trackr.domain.company.Company;
import de.techdev.trackr.domain.employee.Employee;
import de.techdev.trackr.domain.project.Project;
import de.techdev.trackr.domain.validation.constraints.ProjectBelongsToCompany;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Moritz Schulze
 */
@Data
@Entity
@ToString(exclude = {"expenses", "employee", "approver", "comments"})
@ProjectBelongsToCompany(companyField = "debitor")
public class TravelExpenseReport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Integer version;

    @ManyToOne
    private Employee employee;

    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL)
    private List<TravelExpense> expenses = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private TravelExpenseReportStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date submissionDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date approvalDate;

    @ManyToOne
    private Employee approver;

    @OneToMany(mappedBy = "travelExpenseReport", cascade = CascadeType.REMOVE)
    private List<TravelExpenseReportComment> comments;

    @ManyToOne(optional = false)
    private Company debitor;

    @ManyToOne
    private Project project;
}
