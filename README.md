# PageSuiteNewsFeed

The MainActivity page shows the current feed (starts with "Front Page"). Change the feed by using the navigation drawer accessed by swiping the screen from the left. I wanted to include an actionBar with a "hamburger" icon as a way to access this but unfortunately ran out of time.

Click on an article to read the full article. The HTML is mostly rendered correctly with clickable links but needs some more polish as there are some image references within the html body that are not fetched and rendered. Probably should be removed all together to keep the reader lightweight. The user can click on the main image to view the full image. The options menu allows the user to launch the original article in a browser and share it.

Hopefully the code is reasonably intuitive. I used Retrofit and GSON to get the JSON, Glide for the image retrieval, RecyclerView for displaying the list of articles, standard widgets for the article reader. Given more time I would have refactored the code into a more Model-View-Presenter architecture, added more documentation and more thorough tests.
