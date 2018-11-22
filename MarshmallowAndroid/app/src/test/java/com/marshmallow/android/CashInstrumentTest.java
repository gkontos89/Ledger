package com.marshmallow.android;

import com.marshmallow.android.models.financialInstrument.CashInstrument;

/**
 * Created by George on 6/3/2018.
 */
public class CashInstrumentTest extends CashInstrumentBaseTest<CashInstrument> {

    @Override
    protected CashInstrument createInstance() {
        return new CashInstrument();
    }
}
