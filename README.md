# TheGesichtGedicht-Android

### NOTE
The poems database is not on github, the app will crash if you build without it. The poems were provided by [this friendly website](http://gedichte.xbib.de).

## Description
This android app detects your face and searches for a poem that fits it. Currently it contains over 2000 poems in it's mobile database from the authors Göthe, Rilke and Heine. Once you found a poem for your face, it is saved on your phone and you can always recall it until you search for a new poem.

## Technologies used
* Android Play Services for Face Tracking
* RoboGuice for Dependency Injection
* SQLite for the poems database

## Problems I ran into

### Finding poems

The first problem I ran into was finding the right poems, which I can use for my app. I didn't find any poems databases that are available to download for free. My first reaction was to create a web crawler which collects poems from a poem website I know. Then I noticed that the site I want to crawl has Captcha protection for web crawlers and I searched for a way to bybass it. However, after I finished implementing my Crawler, I noticed that this was illegal, because the sites which were hosting the poems, mentioned it is not allowed to download poems with crawlers. As a last resort, I contacted one german poems website, and they where so kind to provide me the poems I needed.

### Creating a Web Service

My first idea was to create a web service for poems, so I can always update/add new poems, which the app will download. I implemented a very simplistic web service, only to realize, that I needed strong security to protect the poems, or else anyone could donwload them, especially if the code of the app is publicly viewable on github. Then I decided to add an in-app database in SQLLite.

## Future plans

### iOS App

The important task here is to find a library for face tracking.

### Poems Web Service

This is a more delicate subject, because of copyright problems. I didn't add a backend implementation to the app, because I realized that I would need a very secure Web Service which can provide poems **ONLY** to the app.

However, the development of this started [here](https://github.com/raptox/TheGesichtGedicht-API).


