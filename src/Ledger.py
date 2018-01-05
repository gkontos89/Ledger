"""
"""

from src.InvestmentPeriodManager import InvestmentPeriodManager
from datetime import datetime

class Transaction(object):
    def __init__(self, id, date, order, assetId, quantity, amount):
        self.id = id
        self.date = date
        self.order = order
        self.assetId = assetId
        self.quantity = quantity
        self.amount = amount

        self.txFinePrint = str(self.id) + ' -- ' + self.date + ' -- ' + self.order + ' -- ' + self.assetId + ' -- '\
                           + self.quantity + ' -- ' + self.amount

class AssetPurchase(object):
    def __init__(self, quantity, price):
        self.quantity = quantity
        self.price = price

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
        ## Each transactions increments the counter.  TODO: Look for better way of generating unique tx id's
        self.txIdCounter = -1
        ## Dictionary of {txId : Transaction()} pairs
        self.rawLedger = {}
        ## Dictionary of {assetId : AssetPurchase()} for assets purchased.  Used for calculating ROI when a SELL occurs
        self.fifoAssetDict = {}
        ## Instance of the investment period manager
        self.investmentPeriodManager = InvestmentPeriodManager()

    def enterTransaction(self, date, order, assetId, quantity, price):
        """! This function is used to record a transaction in the ledger, which in turn calculates ROI for all
             appropriate periods.  Creates a unique transaction id.

        Args:
            date - string - date of the transaction - must be in MM/DD/YYYY format
            order - string - type of order of the transaction - BUY, SELL
            assetId - string - id of the asset of the transaction
            quantity - int - number of shares bought/sold
            price - int - price in dollars of the transactions
        """
        self.txIdCounter += 1 #TODO: Look for better way of generating unique tx id's
        tx = Transaction(self.txIdCounter, date, order, assetId, quantity, price)
        self.rawLedger[self.txIdCounter] = tx
        if assetId not in self.fifoAssetDict:
            self.fifoAssetDict[assetId] = []

        # buyFor and sellFor are only relevant when a SELL takes place.  When this occurs the buyFor price is
        # calculated using FIFO methods, and the buyFor/sellFor is passed into the InvestmentPeriodManager for processing
        # and storage.
        if order == 'BUY':
            self.fifoAssetDict[assetId].append(AssetPurchase(quantity, price))
            buyFor = 0
            sellFor = 0
        else:
            # In the case of an asset being sold, the buyFor data is taken from the fifo asset dict
            buyFor = 0
            sellFor = price
            while quantity > 0:
                if quantity >= self.fifoAssetDict[assetId][0].quantity:
                    assetPurchase = self.fifoAssetDict[assetId].pop(0)
                    buyFor += assetPurchase.price
                    quantity -= assetPurchase.quantity
                else:
                    pricePerUnit = self.fifoAssetDict[assetId][0].price / self.fifoAssetDict[assetId][0].quantity
                    self.fifoAssetDict[assetId][0].quantity -= quantity
                    self.fifoAssetDict[assetId][0].price = pricePerUnit * self.fifoAssetDict[assetId][0].quantity
                    buyFor += (pricePerUnit * quantity)
                    quantity = 0

        self.investmentPeriodManager.processTransaction(tx.id, tx.date, buyFor, sellFor)

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
        """! This function will return transaction data for a given year

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



