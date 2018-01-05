"""
This module contains the class for expresses time periods when calculation Return On Investments
"""

from datetime import datetime


class InvestmentPeriodManager(object):
    """! This class manages ROI data and calculations for the entire application and all transactions
    """
    def __init__(self):
        """! Constructor for the InvestmentPeriodManager class
        """
        ## instance of an InvestmentAll object
        self.investmentAll = InvestmentAll('All')

    def processTransaction(self, txId, dateData, buyFor, sellFor):
        """! This function kicks off ROI calculations based on a transaction received

        Args:
            dateData - string - when the transaction took place - format 'MM/DD/YYYY'
            buyFor - int - amount the asset was purchased for in $'s
            sellFor - int - amount the asset was sold for in $'s
        """
        dateData = datetime.strptime(dateData, '%m/%d/%Y')
        self.investmentAll.processTransaction(txId, dateData, buyFor, sellFor)

    def getAllTimeInvestments(self):
        """! This function will return all-time transaction data with relevant ROI calculations

        Return:
            tuple of (buyForTotal, sellForTotal, roi) for all time
        """
        return self.investmentAll.getTransactionIds(), self.investmentAll.buyForTotal, self.investmentAll.sellForTotal, self.investmentAll.roi

    def getInvestmentsByYear(self, year):
        """! This function will return transaction data with relevant ROI calculations for the specified year

        Args:
            year - string - the year to return transaction data from

        Return:
            tuple of (buyForTotal, sellForTotal, roi) for the given year
        """
        investmentYear = self.investmentAll.investmentYears[year]
        return investmentYear.getTransactionIds(), investmentYear.buyForTotal, investmentYear.sellForTotal, investmentYear.roi

    def getInvestmentsByMonth(self, year, month):
        """! This function will return transaction data with relevant ROI calculations for the specified year

        Args:
            year - string - the year to return transaction data from
            month - string - returns ledger transactions with ROI numbers for the given month

        Return:
            tuple of (buyForTotal, sellForTotal, roi) for a given month
        """
        investmentMonth = self.investmentAll.investmentYears[year].investmentMonths[month]
        return investmentMonth.getTransactionIds(), investmentMonth.buyForTotal, investmentMonth.sellForTotal, investmentMonth.roi

class InvestmentPeriod(object):
    """!  This is the base class for calculating ROI in a given time period
    """
    def __init__(self, title, *args, **kwargs):
        """! Constructor for the base RoiPeriod class

        Args:
            title - string - name of the given roi period
        """
        ## title of the time period
        self.title = title
        ## total in $'s that assets are purchased for
        self.buyForTotal = 0
        ## total in $'s that assets are sold for
        self.sellForTotal = 0
        ## return on investment in %
        self.roi = 0

    def processTransaction(self, txId, dateData, buyFor, sellFor):
        """! This function receives and stores buying and selling information for an asset and performs the
             ROI calculation for the period is represents

        Args:
            dateData - datetime object - when the transaction took place
            buyFor - int - amount the asset was purchased for in $'s
            sellFor - int - amount the asset was sold for in $'s
        """
        self.buyForTotal += buyFor
        self.sellForTotal += sellFor
        if self.buyForTotal > 0:
            self.roi = (self.sellForTotal - self.buyForTotal) / self.buyForTotal

    def getTransactionIds(self):
        pass

class InvestmentMonth(InvestmentPeriod):
    """! This class represents the smallest division of a time period for calculating ROI
    """
    def __init__(self, *args, **kwargs):
        """! Constructor for the RoiMonth class
        """
        super().__init__(*args, **kwargs)
        ## transactions id's that occurred during this period
        self.txIds = []

    def processTransaction(self, txId, dateData, buyFor, sellFor):
        super().processTransaction(txId, dateData, buyFor, sellFor)
        self.txIds.append(txId)

    def getTransactionIds(self):
        return self.txIds

class InvestmentYear(InvestmentPeriod):
    """! This class represents a year of ROI calculations
    """
    def __init__(self, *args, **kwargs):
        """! Constructor for the RoiYear class. It generates RoiMonth objects when created
        """
        super().__init__(*args, **kwargs)
        ## Dictionary of {'monthId', RoiMonth object}
        self.investmentMonths = {}
        for i in range(0, 12):
            dateObj = datetime.strptime(str(i+1), '%m')
            title = dateObj.strftime('%b')
            self.investmentMonths[title] = InvestmentMonth(title)

    def processTransaction(self, txId, dateData, buyFor, sellFor):
        """! Override of the base class implementation. Calculates ROI on itself using the base class implementation
             then pass down the transaction data to the sub roi periods

        Args:
            dateData - datetime object - when the transaction took place - format
            buyFor - int - amount the asset was purchased for in $'s
            sellFor - int - amount the asset was sold for in $'s
        """
        # Calculate ROI on itself
        super().processTransaction(txId, dateData, buyFor, sellFor)

        # Call processTransaction on specific month
        month = dateData.strftime('%b')
        self.investmentMonths[month].processTransaction(txId, dateData, buyFor, sellFor)

    def getTransactionIds(self):
        txIds = []
        for title, investmentMonth in self.investmentMonths.items():
            txIds += investmentMonth.getTransactionIds()

        return txIds

class InvestmentAll(InvestmentPeriod):
    """! This class represents the all time ROI calculations and is the entry point for processing all ROI
         calculations.  This handles the parsing of date string and converting as necessary
    """
    def __init__(self, *args, **kwargs):
        """! Constructor for the InvestmentAll class.
        """
        super().__init__(*args, **kwargs)
        ## dictionary of {'yearId', RoiYear object}
        self.investmentYears = {}

    def processTransaction(self, txId, dateData, buyFor, sellFor):
        """! Override of the base class implementation. Calculates ROI on itself using the base class implementation
             then pass down the transaction data to the sub roi periods

        Args:
            dateData - datetime object - when the transaction took place - format
            buyFor - int - amount the asset was purchased for in $'s
            sellFor - int - amount the asset was sold for in $'s
        """
        # Calculate ROI on itself
        super().processTransaction(txId, dateData, buyFor, sellFor)

        # Call processTransaction on relevant roi time period
        year = dateData.strftime('%Y')
        if year not in self.investmentYears:
            self.investmentYears[year] = InvestmentYear(year)

        self.investmentYears[year].processTransaction(txId, dateData, buyFor, sellFor)

    def getTransactionIds(self):
        txIds = []
        for title, investmentYear in self.investmentYears.items():
            txIds += investmentYear.getTransactionIds()

        return txIds















