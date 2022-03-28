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
    - [x] 1.1. Create a Movie class with the structure specified in movie.json
    - [x] 1.2. Obtain and fill data (minimum of 4 movies)
        - [x] 1.2.1. Obtain relevant movie data from Vue (https://www.myvue.com/cinema/dublin/whats-on) or your favourite provider and add data credits at the bottom of the app
        - [x] 1.2.2. Generate a random number between 0 and 15 for each movie and assign to seats_remaining
        - [x] 1.2.3. Start with an initial default seats_selected value of 0 for all movies
        - [x] 1.2.4. You shall fill random URLs for images from pixabay or other free image providers to begin with
    - [x] 1.3. Build a Recycler View using the specified template, refer to recycler_view_template_*.jpg
    - [x] 1.4. If any seats are selected, show how many seats are selected and hide remaining seats
- [x] 2. Seat selection feature:
    - [x] 2.1. Clicking any item (anywhere on the item) on the movie recycler view should open a new MovieActivity, refer to movie_activity_*.jpg
    - [x] 2.2. Add plus and minus icons, show seats_selected in the middle
    - [x] 2.3. On click plus/minus, update both seats_selected and seats_remaining for that movie
    - [x] 2.4. Add validation, when 0 seats selected minus is disabled, when 0 seats remaining plus is disabled
    - [x] 2.5. When back is pressed, the selected seats are retained and reflected in the recycler view. (Hint: If you don’t see any updates, call adapter notifyDatasetChanged as soon as you return to the recycler view activity)
- [x] 3. Bonus:
    - [x] 3.1. Add "filling fast" badge if less than 3 seats remaining
    - [x] 3.2. Use "Roboto Condensed" font to replicate same style
    - [x] 3.3. Use original movie images from myvue.com or your favourite provider (Hint: check get_movie_image_url.gif)

## Part 2: Extra features implemented

In addition to having followed all the guidelines and fulfilled all the requirements stated in the assessment's brief, here is a list of extra features that were implemented during development:

- **1. View biding**
- **2. MovieApi class**
    This class implements an API that established communication with an external datesource, fetches its content and assigns it to the datamodel defined in the Movie class.
- **3. Parcelable interface**
    In order to pass objects as arguments from one activity to another using intents, custom data types need to implement the [Parcelable](https://developer.android.com/reference/android/os/Parcelable) interface. Objects can then be completely rebuilt somewhere else in the code. It is worth noticing that no references to the original instance are kept. If data manipulation is performed, persistence can be accomplished by sharing intent extras between activities.

## Part 3: Report



## Part 4: References



## Part 5: Copyright Disclaimer

This project may feature content that is copyright protected. Please, keep in mind this is an student's project and has no commercial purpose whatsoever. Having said that, if you are the owner of any content featured here and would like for it to be removed, please, contact me and I will do so promptly.

Thank you very much,  
Mateus Campos.