"""
This module contains the class for expresses time periods when calculation Return On Investments
"""

from datetime import datetime

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

    def processTransaction(self, dateData, buyFor, sellFor):
        """! This function receives and stores buying and selling information for an asset and performs the
        ROI calculation for the period is represents
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
        """! Constructor for the RoiYear class. It generates RoiQuarter objects when created
        """
        super().__init__(*args, **kwargs)
        self.roiQuarters = {}
        for i in range(0, 4):
            self.roiQuarters['Q' + str(i+1)] = RoiQuarter('Q' + str(i+1))

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

    def processTransaction(self, dateData, buyFor, sellFor):
        """! Override of the base class implementation. Calculates ROI on itself using the base class implementation
        then pass down the transaction data to the sub roi periods

        Args:
            dateData - string - when the transaction took place - format:  MM/DD/YYYY
            buyFor - int - amount the asset was purchased for in $'s
            sellFor - int - amount the asset was sold for in $'s
        """
        # Process dateData
        dateData = datetime.strptime(dateData, '%m/%d/%Y')

        # Calculate ROI on itself
        super().processTransaction(dateData, buyFor, sellFor)

        # Call processTransaction on relevant roi time period
        year = str(dateData.year)
        if year not in self.roiYears:
            self.roiYears[year] = RoiYear(year)

        self.roiYears[year].processTransaction(dateData, buyFor, sellFor)















