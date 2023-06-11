package Parser;

import tech.tablesaw.api.Table;

import java.io.InputStream;

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
}
