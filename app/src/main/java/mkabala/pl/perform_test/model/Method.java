package mkabala.pl.perform_test.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by mkabala on 13.09.2017.
 */

@Root(name = "method", strict = false)
public class Method {
    @ElementList(inline = true, name = "parameter")
    private List<Parameter> parameterList;

    public List<Parameter> getParameterList() {
        return parameterList;
    }

    @Root(name = "parameter", strict = false)
    public static class Parameter {
        @Attribute(name = "name")
        private String name;
        @Attribute(name = "value")
        private String value;

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }
    }
}