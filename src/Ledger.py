"""
"""

from enum import Enum



class Order(Enum):
    BUY = 0
    SELL = 1

class Ledger(object):
    """! This class handles all the transaction data and computes ROI's based on
         buying and selling orders.

    The following rules apply to the ledger:
        -   ROI calculations are based on when an asset is sold in exchange for American $.
        -   The ROI is simple Return($ received)/ $ paid for.
        -   The Ledger uses a FIFO model for calculating returns
        -   For simplicity, all transactions must be an asset amount for an American $ amount.
            ie.  1 BTC (value of $1,000) is exchanged for 5 XMR.
            This must be recorded as TWO transactions:
                Sell    1 BTC   $1000   (ROI will be performed here)
                BUY     5 XMR   $1000   (This is a reinvestment)
    """

    def __init__(self):
        """! Initialization function of the Ledger class.  This program, for now, is meant to be self contained
             so all transactions information and revelant holdings are stored as python serialized objects and loaded as such
        """
        pass

    def enterTransaction(self, date, order, assetId, quantity, amount):
        """! This function is used to record a transaction in the ledger, which in turn calculates ROI for all
             appropriate periods.  Creates a unique transaction id.

        Args:
            data - string - date of the transaction - must be in MM/DD/YYYY format
            order - Order - type of order of the transaction
            assetId - string - id of the asset of the transaction
            quantity - int - number of shares bought/sold
            amount - int - amount in dollars
        """
        pass

    def deleteTransaction(self, transactionId):
        """! This function will delete a transaction based specified by the unique ID

        Args:
            transactionId - string - id of the transaction to delete
        """
        pass

    def getAllTimeTransactions(self):
        """! This function will return all-time transaction data
        """
        pass

    def getTransactionsByYear(self, year):
        """! This function will return transaction data for a givcen year

        Args:
            year - string - the year to return transaction data from
        """
        pass

    def getTransactionsByMonth(self, year, month):
        """! This function will return transaction data for a given month

        Args:
            year - string - the year to return transaction data from
            month - string - returns ledger transactions with ROI numbers for the given month
        """
        pass

    def getAssetHoldings(self, assetId):
        """! This function returns holdings for a specified asset class

        Args:
            assetId - string - the asset in question
        """
        pass



