public class DollarCalculator implements ICalculator{

    private int price = 1;
    private MarketApi marketApi;

    public DollarCalculator(MarketApi marketApi){
        this.marketApi = marketApi;
    }

    public void init(){
        System.out.println(marketApi.connect());
        this.price = marketApi.connect();
        System.out.println(this.price);
    }


    @Override
    public int sum(int x, int y) {
        x *= price;
        y *= price;
        return x + y ;
    }

    @Override
    public int minus(int x, int y) {
        x *= price;
        y *= price;
        return x - y;
    }
}
