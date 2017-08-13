# jInstagram
Java implementation of the private Instagram REST services. 

This code is based on this GitHub project: https://github.com/brunocvcunha/instagram4j.  I decided
to modify it due to runtime linkage errors on Wildfly 10 and because the login functionality as 
implemented requested a complete list of friends which Instagram's API was failing 
due to being called too frequently.

I did not issue a PR for these changes because I made extensive changes,
  - Eliminated the use of the Lombok annotations
  - Removed the deprecated HTTP api usage
  - Removed dependency on eois UUID class that was crashing Wildfly

Usage
--------

Download [the latest release JAR][1] or grab via Maven:
```xml
<dependency>
  <groupId>org.jinstagram</groupId>
  <artifactId>jinstagram</artifactId>
  <version>1.1</version>
</dependency>


Supported Operations & Examples
--------

#### Login

```java
// Login to instagram
JInstagram instagram = JInstagram.builder().username("username").password("password").build();
instagram.setup();
instagram.login();
```

#### Search user by handle
```java
InstagramSearchUsernameResult userResult = instagram.sendRequest(new InstagramSearchUsernameRequest("github"));
System.out.println("ID for @github is " + userResult.getUser().getPk());
System.out.println("Number of followers: " + userResult.getUser().getFollower_count());
```

#### Follow user
```java
instagram.sendRequest(new InstagramFollowRequest(userResult.getUser().getPk()));
```

#### Unfollow user
```java
instagram.sendRequest(new InstagramUnfollowRequest(userResult.getUser().getPk()));
```

#### Get user followers
```java
InstagramGetUserFollowersResult githubFollowers = instagram.sendRequest(new InstagramGetUserFollowersRequest(userResult.getUser().getPk()));
List<InstagramUserSummary> users = githubFollowers.getUsers();
for (InstagramUserSummary user : users) {
    System.out.println("User " + user.getUsername() + " follows Github!");
}

```

#### Search user by handle
```java
InstagramSearchUsernameResult userResult = instagram.sendRequest(new InstagramSearchUsernameRequest("github"));
System.out.println("ID for @github is " + userResult.getUser().getPk());
System.out.println("Number of followers: " + userResult.getUser().follower_count);
```

#### Upload a photo your feed
```java
instagram.sendRequest(new InstagramUploadPhotoRequest(
        new File("/tmp/file-to-upload.jpg"),
        "Posted with Instagram4j, how cool is that?"));
```

#### Upload a video your feed
```java
instagram.sendRequest(new InstagramUploadVideoRequest(
        new File("/tmp/file-to-upload.mp4"),
        "Video posted with Instagram4j, how cool is that?"));
```

#### Get feed for a hashtag
```java
InstagramFeedResult tagFeed = instagram.sendRequest(new InstagramTagFeedRequest("github"));
for (InstagramFeedItem feedResult : tagFeed.getItems()) {
    System.out.println("Post ID: " + feedResult.getPk());
}
```

#### Perform a like operation for a media
```java
instagram.sendRequest(new InstagramLikeRequest(feedResult.getPk()));
```

#### Add a comment for a media
```java
instagram.sendRequest(new iInstagramPostCommentRequest(feedResult.getPk(), "Hello! How are you?"));
```


#### Get comments from media
```java
InstagramGetMediaCommentsResult commentsResult = instagram.sendRequest(new InstagramGetMediaCommentsRequest(mediaId, maxCommentId));
```

#### Share message
```java
instagram.sendRequest(InstagramDirectShareRequest.builder(ShareType.MESSAGE, recipients).message(message).build());
```

#### Share media
```java
instagram.sendRequest(InstagramDirectShareRequest.builder(ShareType.MEDIA, recipients).mediaId(mid).message(message).build());
```

#### Edit media
```java
InstagramEditMediaRequest r = new InstagramEditMediaRequest(mediaId, caption);
UserTags tags = r.new UserTags();
tags.getTagsToAdd().add(r.new UserTag(userId, posX, posY));
tags.getTagsToAdd().add(r.new UserTag(userId2, posX2, posY2));
tags.getUserIdsToRemoveTag().add("1231231231");
tags.getUserIdsToRemoveTag().add("3213213213");
r.setUserTags(tags);
instagram.sendRequest(r);
```

 # Terms and conditions
- Do NOT use this API for marketing purposes (spam, botting, harassment, massive bulk messaging...).
- We do NOT give support to anyone who wants to use this API to send spam or commit other crimes.
- We reserve the right to block any user of this repository that does not meet these conditions.

## Legal

This code is in no way affiliated with, authorized, maintained, sponsored or endorsed by Instagram or any of its affiliates or subsidiaries. This is an independent and unofficial API. Use at your own risk.
