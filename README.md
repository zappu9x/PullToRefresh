[![](https://jitpack.io/v/vetrio2511/PullToRefresh.svg)](https://jitpack.io/#vetrio2511/PullToRefresh)

PullToRefresh
===================

> **Now PullToRefresh was updated version to 1.x , If you have any question or suggestion  with this library , welcome to tell me !**

## Introduction
PullToRefresh is a collection of nice pull to refresh layout for Android.

## Demo
![avi](demo2.gif)

## Usage

### Step 1
Add it in your root build.gradle at the end of repositories.

```groovy
  allprojects {
	  repositories {
		  ...
		  maven { url 'https://jitpack.io' }
	  }
	}
``` 
  
### Step 2

Add dependencies in build.gradle.
```groovy
    dependencies {
       compile 'com.github.vetrio2511:PullToRefresh:v1.4'
    }
``` 

### Step 3
Add the PullToRefreshView to your layout:

Simple
```java
  <com.vetrio.library.PullToRefreshView
      xmlns:android="http://schemas.android.com/apk/res/android"
      android:id="@+id/pull_to_refresh"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      app:progressColor="@color/colorPrimary"
      app:titleColor="@color/colorPrimary"
      app:titleText="@string/pull_to_refresh">
  
      <android.support.v7.widget.RecyclerView
          android:layout_width="match_parent"
          android:layout_height="match_parent"/>
  </com.vetrio.library.PullToRefreshView>
```

Advance

```java
<com.vetrio.library.PullToRefreshView
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/pull_to_refresh"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:progressName="BallIndicator"
    app:progressColor="@color/colorPrimary"
    app:titleColor="@color/colorPrimary"
    app:titleText="Pull down to refresh!"
    app:showTitle="true">
    
    ......
    
</com.vetrio.library.PullToRefreshView>
```

![avi](demo.gif)
![avi](demo2.gif)
![avi](demo3.gif)
### Step 4
Register listener for list pulled:

```java
 mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //Do some thing when loading
            }
        });
```
## Library
Thanks for share library
+ [AVLoadingIndicatorView](https://github.com/81813780/AVLoadingIndicatorView)
+ [Phoenix Pull-to-Refresh](https://github.com/Yalantis/Phoenix)
