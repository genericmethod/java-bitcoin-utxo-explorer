import org.bitcoinj.core.AddressFormatException;
import org.bitcoinj.core.Base58;
import org.junit.Test;

import static org.junit.Assert.fail;

public class Base58Test {

    @Test
    public void validBase58AddressShouldNotThrowException(){

        try {
            Base58.decodeChecked("1MDUoxL1bGvMxhuoDYx6i11ePytECAk9QK");
            Base58.decodeChecked("17A16QmavnUfCW11DAApiJxp7ARnxN5pGX");
            Base58.decodeChecked("1NU7uRQHYYCmchJCAJcsb8bbGNEeoynQDN");
            Base58.decodeChecked("xpub6CUGRUonZSQ4TWtTMmzXdrXDtypWKiKrhko4egpiMZbpiaQL2jkwSB1icqYh2cfDfVxdx4df189oLKnC5fSwqPfgyP3hooxujYzAu3fDVmz");
        } catch (AddressFormatException e) {
            fail("Should not throw exception");
        }
    }

    @Test(expected = AddressFormatException.class)
    public void invalidBase58AddressShouldException(){
        Base58.decodeChecked("elon_musk_is_satoshi_nakamoto");
    }

}
