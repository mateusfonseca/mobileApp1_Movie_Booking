# Android App: Movie Booking

**Dorset College Dublin**  
**BSc in Science in Computing & Multimedia**  
**Mobile Applications 1 - BSC20921**  
**Stage 2, Semester 2**  
**Continuous Assessment 2**

**Lecturer name:** Saravanabalagi Ramachandran  
**Lecturer email:** saravanabalagi.ramachandran@faculty.dorset-college.ie

**Student Name:** Mateus Fonseca Campos  
**Student Number:** 24088  
**Student Email:** 24088@student.dorset-college.ie

**Submission date:** 28 March 2022

This repository contains a "Movie Booking" Android app developed for my CA2 at Dorset College BSc in Computing, Year 2, Semester 2.

## Part 1: Requirements Checklist

- [x] 1. Movie recycler view:
    - [x] 1.1. Create a Movie class with the structure specified in movie.json (provided via Moodle)
    - [x] 1.2. Obtain and fill data (minimum of 4 movies)
        - [x] 1.2.1. Obtain relevant movie data from Vue (https://www.myvue.com/cinema/dublin/whats-on) or your favourite provider and add data credits at the bottom of the app
        - [x] 1.2.2. Generate a random number between 0 and 15 for each movie and assign to seats_remaining
        - [x] 1.2.3. Start with an initial default seats_selected value of 0 for all movies
        - [x] 1.2.4. You shall fill random URLs for images from pixabay or other free image providers to begin with
    - [x] 1.3. Build a Recycler View using the specified template, refer to recycler_view_template_*.jpg (provided via Moodle)
    - [x] 1.4. If any seats are selected, show how many seats are selected and hide remaining seats
- [x] 2. Seat selection feature:
    - [x] 2.1. Clicking any item (anywhere on the item) on the movie recycler view should open a new MovieActivity, refer to movie_activity_*.jpg (provided via Moodle)
    - [x] 2.2. Add plus and minus icons, show seats_selected in the middle
    - [x] 2.3. On click plus/minus, update both seats_selected and seats_remaining for that movie
    - [x] 2.4. Add validation, when 0 seats selected minus is disabled, when 0 seats remaining plus is disabled
    - [x] 2.5. When back is pressed, the selected seats are retained and reflected in the recycler view. (Hint: If you don’t see any updates, call adapter notifyDatasetChanged as soon as you return to the recycler view activity)
- [x] 3. Bonus:
    - [x] 3.1. Add "filling fast" badge if less than 3 seats remaining
    - [x] 3.2. Use "Roboto Condensed" font to replicate same style
    - [x] 3.3. Use original movie images from myvue.com or your favourite provider (Hint: check get_movie_image_url.gif (provided via Moodle))

## Part 2: Extra features implemented

In addition to having followed all the guidelines and fulfilled all the requirements stated in the assessment's brief, here is a list of extra features that were implemented during development:

- **1. View biding**
- **2. MovieApi class**  
  This class implements an API that establishes communication with an external datesource, fetches its content and assigns it to the datamodel defined in the Movie class.
- **3. Parcelable interface**  
  In order to pass objects as arguments from one activity to another using intents, custom data types need to implement the [Parcelable](https://developer.android.com/reference/android/os/Parcelable) interface. Objects can then be completely rebuilt somewhere else in the code. It is worth noticing that no references to the original instance are kept. If data manipulation is performed, persistence can be accomplished by sharing intent extras between activities.

## Part 3: Report

Being my second android application, this project was even more straightforward than the previous. Most requirements were fundamentally met with ease. However, I should probably start by saying that, once again, visuals and design were not my main concern and, admittedly, the app is, most certainly, aesthetically flawed.

In this project, I had the opportunity to learn how to connect an android app to an external datasource over the internet, fetch data in the form of JSON objects and use these data to instantiate local objects and populate the application’s views.

Connecting the API to the activities and the adapter was challenging as it required android concepts that were totally new to me:

- **Parcelable:** android/kotlin provide this interface which allows for passing objects between activities as part of intent extras. However, rather than passing the object itself as an argument (or its pertinent memory reference for the matter), the call instantiates another object that is identical to the original, in terms of its members, but not exactly the same instance. Therefore, any data changes are, by default, not persistent.

- **ActivityResultContracts:** to address the issue previously mentioned, this feature allows for an activity to be invoked with an intent while requesting another intent in return as a result, once the called activity finishes. By doing so, as long as the intents are adequately set, activities can send parcelable objects back and forth and handle any data updates once the result is received.

- **runOnUiThread:** asynchronous calls are useful as they allow us to run tasks in the background without locking all other resources and causing the application to become unresponsive. However, views can only be handled by the main thread in the foreground. The API runs in the background in order not to lock the app while fetching content from the datasource, but cannot build the view on its own. Calling runOnUiThread on a block of code ensures that the block will be handled by the main thread in the foreground, making it possible to call the adapter directly from the API.

About what I attempted but could not complete:

- **XML databinding:** through databinding it is possible to create variables directly in the layout files and bind them to their counterpart in the datamodels, something I thought was very neat when I first saw it. Unfortunately, implementation of such feature is not trivial and I could not perform it in time for this project’s submission deadline.

## Part 4: References

Conceptually, every line of code in this project was written based on official documentation:

- **Android Developers Docs:** https://developer.android.com/docs
- **Kotlin Docs:** https://kotlinlang.org/docs/home.html
- **Material Design:** https://material.io/

Visits to our most beloved **StackOverflow** (https://stackoverflow.com/) certainly happened, for insight and understanding. No code was directly copied from anywhere though.

A special amount of *inspiration* was drawn from **ANDROID BASICS IN KOTLIN, Unit 4: Connect to the internet** (https://developer.android.com/courses/android-basics-kotlin/unit-4).

## Part 5: Copyright Disclaimer

This project may feature content that is copyright protected. Please, keep in mind this is an student's project and has no commercial purpose whatsoever. Having said that, if you are the owner of any content featured here and would like for it to be removed, please, contact me and I will do so promptly.

Thank you very much,  
Mateus Campos.