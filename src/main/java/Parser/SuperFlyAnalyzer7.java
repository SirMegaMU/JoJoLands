package Parser;

import tech.tablesaw.api.*;
import tech.tablesaw.columns.Column;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CsvLoader {
    private final InputStream residents_stream, stands_stream;

    public CsvLoader(InputStream residents_stream, InputStream stands_stream) {
        this.residents_stream = residents_stream;
        this.stands_stream = stands_stream;
    }

    public Table load_resident() {
        return Table.read().csv(residents_stream);
    }

    public Table load_stand() {
        return Table.read().csv(stands_stream);
    }

    public Table getUpgradablePowerCables() {
        Table standTable = load_stand();
        Table residentsTable = load_resident();
        Table upgradedPowerCables = standTable.joinOn(
                residentsTable,
                standTable.stringColumn("Stand User").isEqualTo(residentsTable.stringColumn("Name"))
        );
        return upgradedPowerCables.select("Stand User", "Stand", "Range");
    }

    public Table getUnnecessaryWaterConnections() {
        Table residentsTable = load_resident();
        Column<String> residentialAreaColumn = residentsTable.stringColumn("Residential Area");
        Table waterConnections = residentsTable.dropDuplicateRows(residentialAreaColumn);
        return waterConnections.select("Residential Area", "Parents");
    }

    public void printUpgradablePowerCables() {
        Table upgradedPowerCables = getUpgradablePowerCables();
        System.out.println("Upgradable Power Cables:");
        System.out.println(upgradedPowerCables);
    }

    public void printUnnecessaryWaterConnections() {
        Table unnecessaryWaterConnections = getUnnecessaryWaterConnections();
        System.out.println("Unnecessary Water Connections:");
        System.out.println(unnecessaryWaterConnections);
    }

    public double getTotalLength(Table connections) {
        DoubleColumn rangeColumn = connections.doubleColumn("Range");
        return rangeColumn.sum();
    }

    public double getMinimizedTotalLength(Table connections) {
        DoubleColumn rangeColumn = connections.doubleColumn("Range");
        return rangeColumn.min();
    }

    public static void main(String[] args) {
        InputStream residentsStream = CsvLoader.class.getResourceAsStream("residents.csv");
        InputStream standsStream = CsvLoader.class.getResourceAsStream("stands.csv");

        CsvLoader loader = new CsvLoader(residentsStream, standsStream);

        // Super Fly - Upgradable Power Cables
        Table upgradablePowerCables = loader.getUpgradablePowerCables();
        System.out.println("Upgradable Power Cables:");
        System.out.println(upgradablePowerCables);
        System.out.println("Total Length: " + loader.getTotalLength(upgradablePowerCables));
        System.out.println("Minimized Total Length: " + loader.getMinimizedTotalLength(upgradablePowerCables));

        // The Hand - Unnecessary Water Connections
        Table unnecessaryWaterConnections = loader.getUnnecessaryWaterConnections();
        System.out.println("Unnecessary Water Connections:");
        System.out.println(unnecessaryWaterConnections);
        System.out.println("Total Length: " + loader.getTotalLength(unnecessaryWaterConnections));
        System.out.println("Minimized Total Length: " + loader.getMinimizedTotalLength(unnecessaryWaterConnections));
    }
}
