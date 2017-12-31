"""
This module contains the class for expresses time periods when calculation Return On Investments
"""

from datetime import datetime


class RoiManager(object):
    """! This class manages ROI data and calculations for the entire application and all transactions
    """
    def __init__(self):
        """! Constructor fot the RoiManager class
        """
        pass

    def runRoiAnalysis(self, dateString, buyFor, sellFor):
        """! This function kicks off ROI calculations based on a transaction received

        Args:
            dateData - string - when the transaction took place - format 'MM/DD/YYYY'
            buyFor - int - amount the asset was purchased for in $'s
            sellFor - int - amount the asset was sold for in $'s
        """
        pass

    def getAllTimeRoi(self):
        """! This function will return all-time transaction data with relevant ROI calculations
        """
        pass

    def getRoiByYear(self, year):
        """! This function will return transaction data with relevant ROI calculations for the specified year

        Args:
            year - string - the year to return transaction data from
        """
        pass

    def getRoiByMonth(self, year, month):
        """! This function will return transaction data with relevant ROI calculations for the specified year

        Args:
            year - string - the year to return transaction data from
            month - string - returns ledger transactions with ROI numbers for the given month
        """
        pass

class RoiPeriod(object):
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

    def processRoi(self, dateData, buyFor, sellFor):
        """! This function receives and stores buying and selling information for an asset and performs the
             ROI calculation for the period is represents

        Args:
            dateData - datetime object - when the transaction took place
            buyFor - int - amount the asset was purchased for in $'s
            sellFor - int - amount the asset was sold for in $'s
        """
        self.buyForTotal += buyFor
        self.sellForTotal += sellFor
        self.roi = (self.sellForTotal - self.buyForTotal) / self.buyForTotal

class RoiMonth(RoiPeriod):
    """! This class represents the smallest division of a time period for calculating ROI
    """
    def __init__(self, *args, **kwargs):
        """! Constructor for the RoiMonth class
        """
        super().__init__(*args, **kwargs)

class RoiYear(RoiPeriod):
    """! This class represents a year of ROI calculations
    """
    def __init__(self, *args, **kwargs):
        """! Constructor for the RoiYear class. It generates RoiMonth objects when created
        """
        super().__init__(*args, **kwargs)
        ## Dictionary of {'monthId', RoiMonth object}
        self.roiMonths = {}
        for i in range(0, 12):
            dateObj = datetime.strptime(str(i+1), '%m')
            title = dateObj.strftime('%b')
            self.roiMonths[title] = RoiMonth(title)

    def processRoi(self, dateData, buyFor, sellFor):
        """! Override of the base class implementation. Calculates ROI on itself using the base class implementation
             then pass down the transaction data to the sub roi periods

        Args:
            dateData - datetime object - when the transaction took place - format
            buyFor - int - amount the asset was purchased for in $'s
            sellFor - int - amount the asset was sold for in $'s
        """
        # Calculate ROI on itself
        super().processRoi(dateData, buyFor, sellFor)

        # Call processRoi on specific month
        month = dateData.strftime('%b')
        self.roiMonths[month].processRoi(dateData, buyFor, sellFor)

class RoiAll(RoiPeriod):
    """! This class represents the all time ROI calculations and is the entry point for processing all ROI
         calculations.  This handles the parsing of date string and converting as necessary
    """
    def __init__(self, *args, **kwargs):
        """! Constructor for the RoiAll class.
        """
        super().__init__(*args, **kwargs)
        ## dictionary of {'yearId', RoiYear object}
        self.roiYears = {}

    def processRoi(self, dateData, buyFor, sellFor):
        """! Override of the base class implementation. Calculates ROI on itself using the base class implementation
             then pass down the transaction data to the sub roi periods

        Args:
            dateData - datetime object - when the transaction took place - format
            buyFor - int - amount the asset was purchased for in $'s
            sellFor - int - amount the asset was sold for in $'s
        """
        # Calculate ROI on itself
        super().processRoi(dateData, buyFor, sellFor)

        # Call processRoi on relevant roi time period
        year = dateData.strftime('%Y')
        if year not in self.roiYears:
            self.roiYears[year] = RoiYear(year)

        self.roiYears[year].processRoi(dateData, buyFor, sellFor)















