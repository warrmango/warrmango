
## Overview
### The core target of project Improved SocialPlatform (Option 3) is to create a Java program that permits different users to message each other with fully covered Graphic User Interface(GUI) and Internet IO. Users have the option to message 1 or more users, edit their account, edit their conversations, import a conversation or exit the program. File Input/Output techniques are also utilized widely to make sure data can persist even when the program shuts down or the user(s) is offline. As a social platform, each user who has existing account can message different accounts on the platform (identified as their friend list) or edit a message that has already been sent. 

### For organization and readability of the project, there are 7 classes: Utils, Message, Messenger Server, GUI, DuplicateUserException, MessengerClient and User.
 
 
 
## 1. Message class
#### The message class contains methods mainly handling the process and save functions of each Message, which are saved and read in proper formation, under certain user who sends it.
### Fields
| Name | Type | Modifier | Description |
|  :---      |     :---   |      :---  |      :---  |
| user | Users   | private    |   individual user   |
| users     | ArrayList<User>       | private      |   ArrayList of users on platform    |
| file | File   | private    |    existing file for saving messages        |


### Constructor
  
  | Parameters | Modifier |
  | :--- | :--- |
  | File file, ArrayList<User> users1, User user | public |
 
 Add the elements of Arraylist users1 to users ArrayList. 
  
### Methods
  
 
  | Name	 | Return Type	| Parameters | Modifier | Description |
  | :--- | :--- | :--- | :---| :---|
  | getTimestamp | String | void | public | returns timestamp for message sent |
  | addUser | void  | User user | public| add user to  ArrayList<User> users |
  | getFile | File | none | public |  return file |
  | printConvo | void | none | public | process the data file and return it as a String with buffedreader  |
  | printConvoLines | void | none | public | process the data file and return it as a String in mutiple lines with buffedreader |
  | addMessage | void | User user, String message | public | save the message input into user file under certain user in format USERNAME,MESSAGE,TIMESTAMP |
  | toString | String | ArrayList<User> users | public | adds usernames in users Arraylist to formatted string |
  | getUsers | ArrayList<User> | none | public | return users  |
 
## 2. DuplicateUserException class
### Constructor
  
  | Constructor | Description |
  | :--- | :--- |
  | DuplicateUserException() | throws exception when one attempts to create account with username that already exists; extends Exception |
 
## 3. User class
#### User class initialize the information of every registered user and several methods to return the needed information of one user.
  ### Fields
| Name | Type | Modifier | Description |
|  :---      |     :---   |         :--- |  :---  |
| username | String   | private    |   account username        |
| password     | String       | private      |   account password       |
| currentlyMessaging   | ArrayList<Message>    | private      |    current existing messages Arraylist    |
| messagingNames | File  | private    |    message csv file for each user     |
 
### Constructor
  | Parameters | Modifier |
  | :--- | :--- |
  | String username, String password | public |
 
### Methods
  | Name	 | Return Type	| Parameters | Modifier | Description |
  | :--- | :--- | :--- | :---| :---|
  | getUsername | String | none | public | return username|
  | getPassword | String | none | public | return password |
  | addMessage | void | Message m, String filename, boolean addToFile | public | add a new message conversation to the message array |
  | addUser | ArrayList<User> | File userFile, User user, ArrayList<User> users | public | adds a user to the users array list and it also writes to the file and stop when catches ducplicate username |
  | equals | boolean | Object o | public | check if certain user has same username as current one |
  | toString | String | none |  public | prints username and password |
  | getMessagingNames | File | none | public | return messagingNames|
  | setUsername | void | String username | public | set username |
  | setPassword | void | String password | public | set password   |
  | addMessage | void | Message m | public | adds message to currentlyMessage ArrayList   |
  | getMessages | ArrayList<Message> | none |  public  | return currentlyMessaging (messages in ArrayList)| 
 
  ## 4. Utils class
 #### Utils class contains important methods that are frequently used by other objects through the whole project.
  ### Methods
 | Name	 | Return Type	| Parameters | Modifier | Description |
 | :--- | :--- | :--- | :---| :---|
 | loginOrSignUp | int | none | public |  ask the user if they want to login or sign up and return their choice as integer |
 | reLogInOrSignUp | void | ArrayList<User> users |  public | log in / sign up menu for after starts running with list of users in ArrayList; throws FileNotFoundException, IOException, DuplicateUserException | 
 | menuForOneUser | void | User user, ArrayList<User> users | public |  process the input choice of menu shown when there is one existing user |
 | menu | void | User user, ArrayList<User> users |  public | process the input choice of menu shown when there are more than one existing users |
 | editMessages | void | User user, ArrayList<User> users | public | edit or delete message determined by what line user selects, updates changes to files |
 | editAccount | void | User user, ArrayList<User> users | public | edit the user name or password of the current account  |
 | signUp | ArrayList<User> | File userFile, ArrayList<User> users | public | create and save new user with simple GUI windows |
 | logIn | ArrayList<User> | File userFile, ArrayList<User> users | public | log in to already-made account with simple GUI windows   |
 | changeUserFile | void | ArrayList<User> users | public | change the information of user on how they edited their account (change username, change password, delete account)  |
 | parseUsers | ArrayList<User> | File userFile, ArrayList<User> users | public | parses the users from userFile |
 | parseMessage | ArrayList<User> | ArrayList<User> users, User user | public | parse messages of a user  |
 | findDuplicate | User | ArrayList<User> users, User user1 | public | find the potential duplicate user |
 | showFriends | void | User user, ArrayList<User> users | public | shows other existing users |
 | showExistingFriends | void | User user, ArrayList<User> users | public | shows users with precious chat record  |
 | existingChat | void | User user, ArrayList<User> users | public | print the contact list, process the input option to send one or more user messages and save those messages sent  |
 | createConvo | void | User user, ArrayList<User> users | public | creates new conversation; notifies user when they want to view existing chats if no previous chat exists  |
 | getNumber | int | Scanner in, String prompt, int min, int max | public | robust input for integer. If user does not type an integer, it tells them to put in an integer with accurate boundaries  |
 | getMatch | String | Scanner in, String prompt, String match | public | checks if password is valid when logging in |
 | importFile | void | User currentUser, ArrayList<User> userList | public | user imports CSV file and file updates conversations |
 | getMessageWithUserList | Message | User currentUser, ArrayList<User> otherUsersList | public | returns new Message object with file, users list (with removed current user), and current user |
 


## 5. MessengerClient
  
### MessengerClient class creats the Client socket that connects to the MessengerServer.
  
## 6. MessengerServer
### Fields
| Name | Type | Modifier | Description |
|  :---      |     :---   |      :---  |      :---  |
| o | Object  | none   |   an Object created for synchronization |
| user  | User       | private      |   certain user in the users ArrayList   |
|users | ArrayList<User>  | private    |    users ArrayList      |
| userFile | File | public | file contains the data of certain user which make sure data can persist after an completed shutdown|

### Methods
 Name	 | Return Type	| Parameters | Modifier | Description |
 | :--- | :--- | :--- | :---| :---|
| run | void | none | public   |   the run method of MessengerServer thread |
#### In the run method, firstly it Initialize userFile and creat a utils object. Then it reads all the up-to-date existed users from userFile.csv. Then if there are more than one user in userFile, it will parse the message for the message function.  After that it will promt the loginOrSign window which is a while loop will never end until the user put the matched imformation save in the userFile. Once the login window loop breaks, it will show the GUI by launch GUI thread.The
 
### Main 
Main method will start MessengerServer thread whenever new MessengerClient socket is accepted.
  

## 7. GUI
  ### Fields
| Name | Type | Modifier | Description |
|  :---      |     :---   |      :---  |      :---  |
| o | Object  | none   |   an Object created for synchronization |
| user  | User       | private      |   certain user in the users ArrayList   |
|users | ArrayList<User>  | private    |    users ArrayList      |
| u | utils | private | initilization of an Utils(where has many utility methods used frequently) object |

  
### Constructor
  
  | Parameters | Modifier |
  | :--- | :--- |
  | User user, ArrayList<User> users | public |
 
### Panel and contents
  #### FIRST PANEL - MENU
    JPanel menuPanel;
    JLabel welcomeHeader;
    JLabel usernameDisplay;
    JButton editAcc;
    JButton messageSomeone;
    JButton importConversation;
    JButton logout;

  #### MENU -> EDIT ACCOUNT PANEL
    JPanel editAccPanel;
    JButton editAccBack;
    JPanel panelEdit;
    JLabel editAccHeader;
    JButton editFirstName;
    JPanel editFirstNamePanel;
    JPanel editFirstNameSubPanel;
    JButton editLastName;
    JPanel editLastNamePanel;
    JPanel editLastNameSubPanel;
    JButton editPassword;
    JPanel editPasswordPanel;
    JPanel editPasswordSubPanel;
    JButton editUsername;
    JPanel editUsernamePanel;
    JPanel editUsernameSubPanel;
    JLabel firstNameHeader;
    JLabel lastNameHeader;
    JLabel usernameHeader;
    JLabel passwordHeaader;
    JTextField usernameText;
    JTextField passwordText;
    JTextField firstNameText;
    JTextField lastNameText;
    JButton usernameConfirm;
    JButton passwordConfirm;
    JButton firstNameConfirm;
    JButton lastNameConfirm;

  #### MENU -> MESSAGE SOMEONE PANEL
    JPanel messagePanel;
    JLabel optionText;
    JLabel optionText2;
    JButton newChat;
    JButton existingChat;
    JButton backToMenu;
    JPanel messageBottomPanel;

  ##### MESSAGE -> CREATE NEW CHAT
    JPanel listOfFriendsPanel;
    JLabel listOfFriendsText;
    JPanel bottomPanel;
    JPanel otherOptionPanel;
    JButton backToMessageMenu;
    JButton startChat;
    JPanel displayTextPanel;
    JLabel displayingText;
    JButton[] selectFriend;
    String selectedUser;

  ##### MESSAGE -> VIEW EXISTING CHAT
    JPanel existingChatPanel;
    JScrollPane usersListScrollable;
    JPanel displayTextPanel2;
    JLabel instructionText;
    JPanel listOfMessage;
    JButton[] selectChat;
    JButton backToMessageMenu2;


  ##### MESSAGE -> SEND MESSAGE
    JPanel topChatPanel;
    JLabel chatMemberHeader;
    JPanel sendPanel;
    JTextField messageBox;
    JButton sendMessage;
    JButton editThisMessage;
    JButton backFromMessage;
    JButton doneWithMessage;

  ##### MESSAGE -> EDIT MESSAGE
    JPanel editTopPanel;
    JLabel editHeader;
    JScrollPane editScrollPane;
    JPanel editTextPanel;
    JTextField editTextBox;
    JButton doneWithEdit;
    JButton backFromEdit;
    JButton deleteMessage;
    ArrayList<JButton> messageButtons;


  #### MENU -> IMPORT MESSAGE
    JPanel importPanel;
    JPanel textPanel;
    JPanel interactPanel;
    JTextField fileNameBox;
    JLabel instructionTextForImport;
    JButton open;
    JButton back;

  #### Edit Message JTextArea
    JTextArea editText;
    JPanel editMessagePanel;
    JPanel editPanel;
### Methods
 
 Name	 | Return Type	| Parameters | Modifier | Description |
 | :--- | :--- | :--- | :---| :---|
| run | void | none | public   |   the run method of GUI, creating all panels at the beginning and start GUI of SocialMedia |
| message | JScrollPane |  Container content, Jpanel menuPanel | public | creating JScrollPane for panels in the SocialMedia,  including panel to choose either create new chat or view existing chat, panel to show list of all user's friends, Jbuttons of choosing current available friends and message and actionlistener of choosing existing friends and messages  |
 | findDuplicate | User | ArrayList<User> users, User user1 | public | return the duplicated user, otherwise return false   |
  |createNewChat | JScrollPane | Container content, JPanel messagePanel, ArrayList<User> listChat | public | creates files to show the message and contactor and make sure panels listed in correct position, then create new message with the created file ("___.csv") and Users array ("messageUsers")and assign this message to the user's Arraylist of Message Object    |
  | editMessage | JScrollPane | Container content, Message m| public |  the panels for message editing and adding action listeners for various message editing functions  |
  | editAcc | JPanel| Container content, JPanel menuPanel | public|  the panels for acc editing ( change username or password ) and the panels of confirmation  | 
  | getMessageWithUserList | Message |  User currentUser, ArrayList<User> otherUsersList | public |  return the message information (sender, receiver, content and time stamp) sent or received by current user |
  | importMessage | JPanel | Container content, JPanel menuPanel | public | import message(sender, receiver, content and time stamp) files from specify directory |
