# CSC306: Writing Mobile Apps: Coursework 2
#### Due: 11:00am, Friday 7th December 2018
This coursework involves you creating an Android application. This piece of work accounts for **35% of the module** CSC306: Writing Mobile Apps. The purpose of this Coursework is to develop your programming skills on the Android platform.

### 1 Tasks
For this coursework, you will be creating a *Project Meeting App* using the techniques you have seen and will see during lectures. You should target an emulator device that uses **API level 24**, this is what the marker will be using when assessing your submission alongside an actual Android device running the same API level.

### 1.1 Implementation
Your app should include the following features:
* A user can create a meeting
* A meeting is made up of data such as; time, date, attendees, notes and location
* A user can view a history of all meetings and view details about specfic entries
* A Settings page to allow a user to edit text display in terms of colour and font size
* The ability to view locations on a map
* Once a user has been entered as an attendee, they can be selected from a list for future meetings

If you complete these features fully and wish to introduce new features, then this will be counted in your marks.

### 1.2 Video Demonstration
You are required to create a video to demonstrate the features of your mobile app. Use this video as an opportunity to demonstrate all of the features described in this document that you have implemented, along with any additional features you have added. You should also show that your application **can handle screen orientation changes** in your video (to demonstrate good use of callbacks etc).<br/>
<br/>
Several links to (free) screen capturing software are given on Bob's module web pages (for example - http://cs.swan.ac.uk/~csbob/teaching/cs235-softEng/).<br/>
<br/>
The file(s) is named after the feature(s) being demonstrated, for example:<br/>
*STUDENT_NUMBER*_applicationDemo.mpg.<br/>
<br/>
The movie files are saved in MPEG or MP4 format. You may use as many screen capture files as necessary to capture the features of your application. **Only submit 1 video that show all features**. Your animated screen captures are placed in a folder called demo that resides in your .zip folder.<br/>
<br/>
**Your video should not exceed 5 minutes in length, the marker will not view any content that is beyond the 5 minute mark of a video.**

### 1.3 ReadMe
You should include a ReadMe.txt file in your submission to give guidance on the strategies you have employed in your app code. For example, describe each class you have produced and what the main functionality of the class is. Also, highlight which components you have used and describe how they have been used in your app.<br/>
<br/>
You should also use this file to send any comments to the marker, and also include an special instructions on how to run the app.

### 2 Marking
1. Functionality - You will be marked here on how complete of an app you submit, to achieve full marks here you will need to implement extra features - **50%**
2. Design - Here you will be assessed on the design of you application - have you followed the Android Design Principles, is your app simple to use and have you put together a good looking User Interface? - **20%**
3. Code Quality - Here, you will be marked on how well you have externalised your resources (e.g. using /res/values/Strings.xml), as well as general coding principles such as good indentation and commenting - **15%**
4. Video - A good video will demonstrate features that you have implemented, remember to include the base features and any extra features you have added - **5%**
5. ReadMe - Instructions and guidance on your app, with information on any special instructions to run the app. You are also required to discuss the components that you have used and how - **10%**

### 3 Coursework Guidance
* Start early, you don't want to be rushing things near the deadline.
* Make use of the lab time, you can get guidance there.
* Pay close attention to the marking scheme. Use the rubric to mark your own work and see how you can improve using the next tier of the rubric
* Enjoy yourself, have some fun creating a mobile app and thinking of features you would like to see.

### 4 Submission
You should submit a **single .zip folder** containing:
* all of your project .java and .xml
* A project .apk file
* A (maximum) 5 minute long video demonstrating your application
* A ReadMe file clearly outlining the components used in the application, along with instructions on how to build/use the app.

to Blackboard before **11:00am, Friday 7th December 2018**. Failing to submit all of the files will lose you marks as the marker won't be able to test all features of your app.<br/>
<br/>
This is an **individual** coursework and must be completed by you alone. If you copy files or methods from another source these must be referenced in comments in the usual way. You may not work as groups. You may discuss your work with your colleagues but anything written down must be done alone. By submitting electronically you are implicitly stating that it is your own work, except where otherwise specified. **Academic Misconduct will be treated harshly.**

### Rubric
<table>
  <tr>
    <th></th>
    <th>Fail (<40%)</th> 
    <th>Pass (40-49%)</th>
    <th>2:2 (50-59%)</th>
    <th>2:1 (60-69%)</th>
    <th>First (70%-79%)</th>
    <th>Distinction (80%+)</th>
  </tr>
  <tr>
    <td>Functionality <b>(50%)</b></td>
    <td>Work of very poor quality. Doesn't run, or runs but doesn't have any functionality.</td> 
    <td>The app is limited in functionality, offering very little features</td>
    <td>The app has limited functionality and does not meet all requirements</td>
    <td>All features described in Coursework Outline have been implemented</td>
    <td>All features described in Coursework Outline have been implemented and some basic additional features have been added</td>
    <td>Student has completed all features in Coursework Outline and has introduced new features into app that are of professional standard</td>
  </tr>
  <tr>
    <td>Design <b>(20%)</b></td>
    <td>Poor GUI using inappropriate principles. Few or no design principles used. Difficult to understand how to use.</td> 
    <td>User Interface is basic</td>
    <td>Some attempt at using standard design principles. Moderately easy to understand how to use.</td>
    <td>Evidence that the student has considered the Design Principles and applied them in some cases</td>
    <td>Evidence that the student made good use of Principles and produced a clean, well presented app</td>
    <td>App Is presented in a professional style, making full use of design principles</td>
  </tr>
  <tr>
    <td>Code Quality <b>(15%)</b></td>
    <td>Strings and colours implemented in code. Some attempt at sensible variable names, whitespace and tabbing etc.</td> 
    <td colspan="2">Some resources externalised, though code is difficult to read and understand</td>
    <td>Most resources externalised but not into separate files, or not all resources externalised. Reasonable use of whitespace/ names.</td>
    <td>Most or all resources externalised but should have used more xml files. Good use of whitespace, fairly easy to understand code.</td>
    <td>All resources externalised and separated into appropriate files/directories. Professional quality coding.</td>
  </tr>
  <tr>
    <td>Video <b>(5%)</b></td>
    <td>No Video submitted</td> 
    <td>Video is extremely basic and does not show enough content</td>
    <td>Simple video highlighting basic features</td>
    <td>Good video, most features are obvious</td>
    <td>Excellent quality video, clearly shows features</td>
    <td>Video is of a professional level, highlighting all features</td>
  </tr>
  <tr>
    <td>ReadMe <b>(10%)</b></td>
    <td>No ReadMe submitted</td> 
    <td>ReadMe contains limited information and basic understanding of app components used</td>
    <td>ReadMe contains instructions and evidence of understanding of components used</td>
    <td>Good Readme with instructions and good understanding of components used</td>
    <td colspan="2">Professional standard of ReadMe, clear instructions and excellent understanding of components used</td>
  </tr>
</table>
