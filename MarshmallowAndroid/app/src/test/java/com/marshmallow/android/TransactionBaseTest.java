package com.marshmallow.android;

import com.marshmallow.android.models.transaction.Transaction;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by George on 6/2/2018.
 */
public abstract class TransactionBaseTest<T extends Transaction>{
    private T instance;

    protected abstract  T createInstance();
    protected T getInstance() { return instance;}

    @Before
    public void setUp() {
        instance = createInstance();
    }

    @Test
    public void description() {
        String description = "test transaction";
        instance.setDescription(description);
        assertEquals(instance.getDescription(), description);
    }

    @Test
    public void date() {
        String date = "6/15/1989";
        instance.setDate(date);
        assertEquals(instance.getDate(), date);
    }

    @Test
    public void time() {
        String time = "5:15pm";
        instance.setTime(time);
        assertEquals(instance.getTime(), time);
    }

    @Test
    public void value() {
        int value = 1485;
        instance.setValue(value);
        assertEquals(instance.getValue(), value);
    }

    @Test
    public void uniqueId() {
        String uniqueId = "fhu23hr0";
        instance.setUniqueId(uniqueId);
        assertEquals(instance.getUniqueId(), uniqueId);
    }

    @Test
    public void accountingEntry() {
        Transaction.EntryType entryType = Transaction.EntryType.CREDIT;
        instance.setAccountingEntry(entryType);
        assertEquals(instance.getAccountingEntry(), Transaction.EntryType.CREDIT);

        entryType = Transaction.EntryType.DEBIT;
        instance.setAccountingEntry(entryType);
        assertEquals(instance.getAccountingEntry(), Transaction.EntryType.DEBIT);
    }

    @Test
    public void linkTx() {
        String link = "sjfhuu23";
        instance.setLink(link);
        assertEquals(instance.getLink(), link);
    }
}
