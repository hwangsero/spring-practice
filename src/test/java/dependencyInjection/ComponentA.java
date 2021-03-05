package dependencyInjection;

import annotation.Autowired;
import annotation.Component;

@Component
public class ComponentA {

    @Autowired
    ComponentB componentB;

    public String run() {
        return componentB.run();
    }
}
