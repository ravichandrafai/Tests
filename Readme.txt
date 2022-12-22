Read below instructions to understand the components of the Automation
1. Execution has config, lib, execution-0.1.jar,log4j.xml,testng.xml
2.config folder has subfolders config,dbkey,excel,executables and testfiles 
3.config folder contains the config properties which is used to read the client name, applicatoin URL, Report names, SMTP details and DB details
4.make the changes as per the requirement in config.properties such as client name, URL and DB Name
5.excel folder contains the testdata.xls file this is used to execute the automation tests. This file holds the tests that are to be exexuted along with the data required for each test.
6. testdata.xlsx has 4 sheets RUNMANAGER,DATA,DateFormats and Taxanomy
7. RUNMANAGER is used to control the execution of tests. if user does not want to run a particular test then set the value of execute column to no.
8. testdata sheet is used to read the test for each test case. Select Queue is used to select queue name during upload. searchText is used to search the files in work list based on status or file name
   uploadFile is the name of the file which will be uploaded. Place this file under /config/testFiles folder.'exp Status' is the e[ected status of the file which is uploaded. new category name is used to check the newly created category name after the split
   gotopage is used to select a particular page to move, rotate,zoomin,Zoomout. Move to us used to specify the page number beofer/after which the selected page will be moved.
   Position to Move is sepcify the direction/or the position where the selected page should be moved or drag and dropped
   split threshhold is used to split a document which has more than threshhold limit
   matterid is used for file uploads, used in field extractions. Use the test matter ids
   expected document name is used to validate the expected split of a file and the documnet type of each category
9. Date formats sheet used to read the different date formats and verify the valid and invalid date formats 
10. Taxanmy sheet is used when automation has to verify the key value pair of the extraction for different combinations of the documnet category and document sub categories
    maintian the expected the key value fields for each category and sub category which will be checked against the data present in shown in the UI
   

