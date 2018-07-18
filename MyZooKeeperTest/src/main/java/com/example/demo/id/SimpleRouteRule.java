package com.example.demo.id;


/**
 * @author: lvyong6
 * @time: 16/1/17 下午3:15
 */
public class SimpleRouteRule implements RouteRule<Object> {

    private int totalDataBases;

    private int totalTables;

    private int tablesByPerDataBase;

    public SimpleRouteRule(int totalDataBases, int totalTables) {
        if (totalDataBases <= 0 || totalTables <= 0) {
            throw new IllegalArgumentException("totalDataBases and totalTables must be both positive!");
        }
        this.totalDataBases = totalDataBases;
        this.totalTables = totalTables;
        this.tablesByPerDataBase = (totalTables / totalDataBases);
        if (this.tablesByPerDataBase <= 0) {
            throw new IllegalArgumentException("the expression 'totalDataBases/totalTables'<=0!");
        }
    }

    public int getTotalDataBases() {
        return totalDataBases;
    }

    public void setTotalDataBases(int totalDataBases) {
        this.totalDataBases = totalDataBases;
    }

    public int getTotalTables() {
        return totalTables;
    }

    public void setTotalTables(int totalTables) {
        this.totalTables = totalTables;
    }

    public int getDbIndex(Object routeFactor) {
        if (routeFactor instanceof Integer || routeFactor instanceof Long || routeFactor instanceof String) {
            return (int) (Math.abs(MurmurHash.hash(String.valueOf(routeFactor))) % totalTables / tablesByPerDataBase);
        }
        throw new IllegalArgumentException("Unsupported RouteFactor parameter type! the support RouteFactor parameter is Integer, Long and String.");
    }

    public int getTableIndex(Object routeFactor) {
        if (routeFactor instanceof Integer || routeFactor instanceof Long || routeFactor instanceof String) {
            return (int) (Math.abs(MurmurHash.hash(String.valueOf(routeFactor))) % totalTables);
        }
        throw new IllegalArgumentException("Unsupported RouteFactor parameter type! the support RouteFactor parameter is Integer, Long and String.");
    }
}
