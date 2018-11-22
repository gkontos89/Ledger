package com.marshmallow.android.models.account;

/**
 * Created by George on 6/3/2018.
 */
public class AccountInformation {

    private String uniqueId;
    private String accountName;
    private String accountOpenedDate;
    private String accountClosedDate;

    public AccountInformation() {

    }

    public String getUniqueId() { return uniqueId; }
    public void setUniqueId(String uniqueId) { this.uniqueId = uniqueId; }
    public String getAccountName() { return accountName; }
    public void setAccountName(String accountName) { this.accountName = accountName; }
    public String getAccountOpenedDate() { return accountOpenedDate; }
    public void setAccountOpenedDate(String accountOpenedDate) { this.accountOpenedDate = accountOpenedDate; }
    public String getAccountClosedDate() { return accountClosedDate; }
    public void setAccountClosedDate(String accountClosedDate) { this.accountClosedDate = accountClosedDate; }
}
