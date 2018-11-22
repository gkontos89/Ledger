package com.marshmallow.android.models.transaction;

/**
 * Created by George on 6/2/2018.
 */
public class Transaction {

    private String uniqueId;
    private String description;
    private String date;
    private String time;
    private int value;
    private EntryType accountingEntry;
    private String category;
    private String link;
    private String merchant;

    public Transaction() {
    }

    public enum EntryType {
        DEBIT,
        CREDIT
    }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public int getValue() { return value; }
    public void setValue(int value) { this.value = value; }
    public EntryType getAccountingEntry() { return accountingEntry; }
    public void setAccountingEntry(EntryType accountingEntry) { this.accountingEntry = accountingEntry; }
    public String getUniqueId() { return uniqueId; }
    public void setUniqueId(String uniqueId) { this.uniqueId = uniqueId; }
    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }
    public String getMerchant() { return merchant; }
    public void setMerchant(String merchant) { this.merchant = merchant; }
}
