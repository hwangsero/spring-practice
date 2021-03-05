package dependencyInjection;

import annotation.Component;

@Component
public class ComponentB {

    public String run() {
        return "I'm ComponentB";
    }
}
