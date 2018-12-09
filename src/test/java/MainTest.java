import org.junit.Assert;

import static org.junit.Assert.*;

public class MainTest {

    @org.junit.Test
    public void PeselCheckTest() {
        PeselCheck obj = new PeselCheck();
        Assert.assertTrue(obj.checking( "53111239985"));
    }
}