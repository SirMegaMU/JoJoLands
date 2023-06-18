package Parser;

import tech.tablesaw.api.*;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MoodyBlues {
    private final Table salesData;

    public MoodyBlues(InputStream salesDataInputStream) {
        this.salesData = Table.read().csv(salesDataInputStream);
    }

    public Table getSalesDataInRange(LocalDate startDate, LocalDate endDate) {
        DateColumn dateColumn = salesData.dateColumn("Date");
        Table filteredData = salesData.where(
                dateColumn.isAfter(startDate.minusDays(1))
                        .and(dateColumn.isBefore(endDate.plusDays(1)))
        );
        return filteredData;
    }

    public Table getAggregatedInformation(Table salesData, int topK) {
        Table summaryTable = salesData.summary();
        Table sortedTable = summaryTable.sortDescendingOn("Count");
        List<Row> topKRows = sortedTable.rows().subList(0, Math.min(topK, sortedTable.rowCount()));
        Table topKSalesData = Table.create("Top " + topK + " Sales");
        topKSalesData.addColumns(salesData.columnNames());
        for (Row row : topKRows) {
            topKSalesData.append(row);
        }
        return topKSalesData;
    }

    public void displaySalesData(Table salesData) {
        System.out.println(salesData);
    }

    public void displayAggregatedInformation(Table aggregatedInformation) {
        System.out.println(aggregatedInformation);
    }

    public static void main(String[] args) {
        InputStream salesDataInputStream = MoodyBlues.class.getResourceAsStream("sales_data.csv");
        MoodyBlues moodyBlues = new MoodyBlues(salesDataInputStream);

        LocalDate startDate = LocalDate.parse("2023-06-01", DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate endDate = LocalDate.parse("2023-06-10", DateTimeFormatter.ISO_LOCAL_DATE);
        Table salesDataInRange = moodyBlues.getSalesDataInRange(startDate, endDate);
        moodyBlues.displaySalesData(salesDataInRange);

        int topK = 5;
        Table aggregatedInformation = moodyBlues.getAggregatedInformation(salesDataInRange, topK);
        moodyBlues.displayAggregatedInformation(aggregatedInformation);
    }
}
