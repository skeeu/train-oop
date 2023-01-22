package trains.vagons;

public class Sitting extends Vagon{
    private Integer benches;
    private Integer benchCapacity;

    public Sitting(String type, Integer benches, Integer benchCapacity, Integer vagonId) {
        super(type, new Integer[benches][benchCapacity], vagonId);
        this.benches = benches;
        this.benchCapacity = benchCapacity;
    }

    @Override
    public Integer getCapacity () {
        return benches*benchCapacity;
    }

    public Integer getBenches() {
        return benches;
    }

    public Integer getBenchCapacity() {
        return benchCapacity;
    }
}
