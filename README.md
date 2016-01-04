# Product Matcher

Application to solve the Sortable challenge http://sortable.com/challenge/

##Running

###Pre-requisites
 - Maven Installed

1. Clone the repository
2. Execute configure.sh
3. Execute run.sh **products_file** **listings_file**
  
##Summary

The core of the algorithm is based on HashTables.
As Hashtables are normally applied for exact matches and not similar ones, the most important part of the algorithm is the **normalize** function that tries to create a standard String that will be use to store and search Products.

##Assumptions

>  Products like DSC-W310S and DSC-W310 are considered different products

>  Listing items may be written in English, French or German
