package Spring.Test.jdk.web.etc;

import Spring.user.domain.Level;
import org.junit.Test;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BindingTest {
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        dataBinder.registerCustomEditor(Level.class, new LevelPropertyEditor());
    }

    @Test
    public void simpleBindTest(){
        WebDataBinder dataBinder = new WebDataBinder(null);
        dataBinder.registerCustomEditor(Level.class, new LevelPropertyEditor());
        assertThat(dataBinder.convertIfNecessary("1", Level.class), is(Level.BASIC));
    }

    static class LevelPropertyEditor extends PropertyEditorSupport {
        public String getAsText() {
            return String.valueOf(((Level)this.getValue()).intValue());
        }

        public void setAsText(String text) throws IllegalArgumentException {
            this.setValue(Level.valueOf(Integer.parseInt(text.trim())));
        }
    }
}
