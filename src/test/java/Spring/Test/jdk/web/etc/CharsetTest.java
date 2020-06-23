package Spring.Test.jdk.web.etc;

import org.junit.Test;
import org.springframework.beans.propertyeditors.CharsetEditor;

import java.nio.charset.Charset;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CharsetTest {
    @Test
    public void charsetEditor(){
        CharsetEditor charsetEditor = new CharsetEditor();
        charsetEditor.setAsText("UTF-8");
        assertThat(charsetEditor.getValue(), is(instanceOf(Charset.class)));
        assertThat((Charset)charsetEditor.getValue(), is(Charset.forName("UTF-8")));
    }
}
