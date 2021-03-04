package componentScan;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class ComponentScanTest {

    @Test
    public void ComponentScanTest() throws Exception {
        // given
        // when
        Map<Class, Object> beans = ComponentScanManager.run(ComponentScanTest.class);

        // then
        Assertions.assertTrue(beans.containsKey(ComponentA.class));
        Assertions.assertTrue(beans.containsKey(ComponentB.class));
    }
}
