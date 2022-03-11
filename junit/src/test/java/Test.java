import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class Test {

    @Mock
    public MarketApi marketApi;

    @org.junit.jupiter.api.Test
    public void test(){
        System.out.println("hello");
    }

    /*@org.junit.jupiter.api.Test
    public void dollar(){
        DollarCalculator dollarCalculator = new DollarCalculator(null);
        Calculator calculator = new Calculator(dollarCalculator);
        int sum = calculator.sum(100, 100);
        Assertions.assertEquals(20000, sum);
    }*/

    @org.junit.jupiter.api.Test
    public void mock(){
        DollarCalculator dollarCalculator = new DollarCalculator(marketApi);
        dollarCalculator.init();

        System.out.println(dollarCalculator);
        Calculator calculator = new Calculator(dollarCalculator);
        int sum = calculator.sum(100, 100);
        Assertions.assertEquals(20000, sum);
    }
}
