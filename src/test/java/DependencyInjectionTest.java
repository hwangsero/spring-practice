import componentScan.ComponentScanManager;
import dependencyInjection.ComponentA;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import springContainer.SpringContainer;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.stream.Stream;

public class DependencyInjectionTest {

    @Test
    public void DependencyInjectionTest() throws Exception {
        //given
        Map<Class, Object> beans = ComponentScanManager.run(DependencyInjectionTest.class);

        //when
        SpringContainer.getInstance(beans, DependencyInjectionTest.class);

        //then
        ComponentA componentA = new ComponentA();
        Assertions.assertEquals(componentA.run(),"I'm ComponentB");
    }
}
