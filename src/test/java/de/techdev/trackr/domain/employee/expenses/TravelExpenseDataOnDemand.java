package de.techdev.trackr.domain.employee.expenses;

import de.techdev.trackr.domain.AbstractDataOnDemand;
import de.techdev.trackr.domain.employee.expenses.report.ReportDataOnDemand;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Moritz Schulze
 */
public class TravelExpenseDataOnDemand extends AbstractDataOnDemand<TravelExpense> {

    @Autowired
    private ReportDataOnDemand travelExpenseReportDataOnDemand;

    @Override
    protected int getExpectedElements() {
        return 1;
    }

    @Override
    public TravelExpense getNewTransientObject(int i) {
        TravelExpense travelExpense = new TravelExpense();
        travelExpense.setReport(travelExpenseReportDataOnDemand.getRandomObject());
        travelExpense.setFromDate(new Date());
        travelExpense.setToDate(new Date());
        travelExpense.setCost(BigDecimal.TEN);
        travelExpense.setType(TravelExpense.Type.TAXI);
        travelExpense.setSubmissionDate(new Date());
        travelExpense.setVat(BigDecimal.ONE);
        travelExpense.setComment("comment_" + i);
        return travelExpense;
    }

}
