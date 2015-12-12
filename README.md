# streaming-tables
An API for handling data in tables with streams.

It's target is getting Objects out of Tables by only using streams and lambdas. 
No configuration via xml, no Reflection, just POJOs.

The process works as follows:

* load data into a Table
* get a ModelTable
* create one or more Workspaces
** restrict the domain by creating constraints on rows, columns and cells.
** define which data should be collected for each cell that meets the restrictions with a Cellcursor.
* hand over a Consumer that receives all the data per cell that where collected with the Cellcursor. 

A basic Implementation based on ArrayLists is included but not tested and not yet feature-complete.

## Use cases

Right now the API deals with these 5 usecases.

### Basic Models

Restrict the domain on certain rows and columns, collect data on neibouring cells and hand them over to a simple constructor with a Function.
See MainModelDemo for an example.

### Complex Models

Creating a Consumer gives the opportunity to store objects in other structures than lists and to cope with Exceptions in case of complex operations on data.
See ExternalListManagement for an example.

### Explode Data to mulitple objects

The usage of "Workspaces" gives the possibility to collect data twice per target cell.
The restrictions are the same, the only difference is the order of collected values.
See ExplodeTableDemo for an example where bookings in an account are converted to bookings in a journal.

### Collect data in different domains

TODO

### Headers and Footers

TODO

Sometimes reports in Excel are only available in "normalized" form e.g. an article list where every article-family has the family as header and there is a date at the bottom of the report. For this usecase it's necessary to annotate headers and footers.
See HeadersAndFootersDemo for an example.
