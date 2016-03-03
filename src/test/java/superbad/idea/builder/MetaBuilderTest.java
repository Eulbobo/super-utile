package superbad.idea.builder;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static superbad.idea.builder.MetaBuilder.getBuilder;

public class MetaBuilderTest {

    public static class MyLocalClass {
        private String value;
        public MyLocalClass(){
            // public constructor
        }
        public String getValue(){ return value; }
    }

    @Test
    public void should_set_property_on_private_field(){

       MyLocalClass loc = getBuilder(new MyLocalClass()).set("value", "MesCouilles").build();

       assertThat(loc).isNotNull();
       assertThat(loc.getValue()).isNotNull().isEqualTo("MesCouilles");
    }

    @Test
    public void should_set_last_property_on_private_field() throws ReflectiveOperationException{

       MyLocalClass loc = getBuilder(MyLocalClass.class)
                   .set("value", "MesCouilles")
                   .set("value", "Mickey")
                   .set("asterix", "vide")
                   .build();

       assertThat(loc).isNotNull();
       assertThat(loc.getValue()).isNotNull().isEqualTo("Mickey");
    }
}
