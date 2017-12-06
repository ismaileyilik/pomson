CREATE DATABASE IF NOT EXISTS PomodoroDatabase;
DROP TABLE IF EXISTS UserRoles;
DROP TABLE IF EXISTS GroupMembers;
DROP TABLE IF EXISTS Friends;
DROP TABLE IF EXISTS FriendRequests;
DROP TABLE IF EXISTS Pomodoros;
DROP TABLE IF EXISTS Goals;
DROP TABLE IF EXISTS Groups;
DROP TABLE IF EXISTS Users;

SET SQL_MODE='ALLOW_INVALID_DATES';

CREATE TABLE IF NOT EXISTS Users( 
Username varchar(255) PRIMARY KEY UNIQUE,
LoginPassword varchar(255),
PomodoroLengthPreferenceMins int(10),
PomodoroShortBreakPreferenceMins int(10),
PomodoroLongBreakPreferenceMins int(10));

CREATE TABLE IF NOT EXISTS UserRoles(
Username varchar(255) PRIMARY KEY,
Role varchar(255),
FOREIGN KEY (Username) REFERENCES Users(Username));

CREATE TABLE IF NOT EXISTS Groups(
GroupID int(10) PRIMARY KEY AUTO_INCREMENT,
GroupName varchar(255),
Description varchar(255),
VerificationBeforeJoinBoolean tinyint(1));

CREATE TABLE IF NOT EXISTS GroupMembers(
GroupID int(10), 
GroupMember varchar(255),
GroupRole varchar(255),
JoinDate timestamp,
FOREIGN KEY (GroupID) REFERENCES Groups(GroupID),
FOREIGN KEY (GroupMember) REFERENCES Users(Username),
PRIMARY KEY(GroupID, GroupMember));

CREATE TABLE IF NOT EXISTS Friends(
FirstFriend varchar(255),
SecondFriend varchar(255),
FriendsSinceDate timestamp,
FOREIGN KEY (FirstFriend) REFERENCES Users(Username),
FOREIGN KEY (SecondFriend) REFERENCES Users(Username), 
PRIMARY KEY (FirstFriend, SecondFriend));

CREATE TABLE IF NOT EXISTS FriendRequests(
Requestor varchar(255),
Requestee varchar(255),
StatusBoolean tinyint(1),
DateRequested timestamp,
FOREIGN KEY (Requestor) REFERENCES Users(Username),
FOREIGN KEY (Requestee) REFERENCES Users(Username), 
PRIMARY KEY (Requestor, Requestee));


CREATE TABLE IF NOT EXISTS Goals(
GoalID int(10) PRIMARY KEY AUTO_INCREMENT,
GroupID int(10),
Username varchar(255),
GoalName varchar(255),
Description varchar(255),
StartTime timestamp,
EndTime timestamp,
FOREIGN KEY (GroupId) REFERENCES Groups(GroupID),
FOREIGN KEY (Username) REFERENCES Users(Username));


CREATE TABLE IF NOT EXISTS Pomodoros(
GoalID int(10),
PomodoroID int(10),
TaskDescription varchar(255),
StartTime timestamp,
EndTime timestamp,
Comments varchar(255),
FOREIGN KEY (GoalID) REFERENCES Goals(GoalID),
PRIMARY KEY (GoalID, PomodoroID));



