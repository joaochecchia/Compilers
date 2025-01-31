package analise;

import java.util.HashMap;
import java.util.Map;

public class SymTable {
    private Map<String, String> table;
    private SymTable prev;

    public SymTable(SymTable prev) {
        this.table = new HashMap<>();
        this.prev = prev;
    }

    public void put(String key, String tag){
        table.put(key, tag);
    }

    public String get(String symbol){
        for(SymTable symTable = this; symTable != null; symTable = symTable.prev) {
            String tag = symTable.table.get(symbol);
            if(tag != null){
                return symbol;
            }
        }

        return null;
    }
}
