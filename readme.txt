
Firebase Rewarded App Invites
-----------------------------

This is a pair of sample Android apps that are an implementation of rewarded app invites:
rewarding the sender for each successful invite and also incentivizing the recipient of
the invite to install the app with an arbitrary amout of in-app currency. 

The 'Sender' app sends an invite (Email) to a user-selected list ofrecipients, inviting them
to install a second (presumably related)app, though sending invites targeting the current app
can also beeasily done by implementing deep links (as in the 'Receiver' app)and modifying the
deep link url to point to the current app.

The key is the Deep Link URL that includes the sender's user ID so they can recieve some reward
for the invite and which targets anactivity in the 'Reciever' app which handles giving a reward
forthe recipient.  

The 'Reciever' app implements an activity which serves as a 'landing page' for invitation recipients
that handles incentivizing both the sender and the recipient of the invite.  The main task is to
correctly implement deep links.

The actual reward and tracking mechanism (not implemented) needs a server component to store data tha
t both apps can access, however there are some easy options: if both apps are under the same Firebase
project, they can share the same Firebase Realtime DBwhich can fill this role.  I also suggest using
Firebase Remote Config to handle setting reward parameters so that they can easily be changed later.





<-- EOF -->
