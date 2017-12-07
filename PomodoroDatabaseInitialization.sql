CREATE DATABASE IF NOT EXISTS PomodoroDatabase;
DROP TABLE IF EXISTS UserRoles;
DROP TABLE IF EXISTS GroupMembers;
DROP TABLE IF EXISTS Friends;
DROP TABLE IF EXISTS FriendRequests;
DROP TABLE IF EXISTS Pomodoros;
DROP TABLE IF EXISTS Goals;
DROP TABLE IF EXISTS Groups;
DROP TABLE IF EXISTS Users;
DROP PROCEDURE IF EXISTS CreateGroupFirstTime;
DROP VIEW IF EXISTS GroupMemberGroups;
DROP VIEW IF EXISTS IncomingFriendRequests;
DROP VIEW IF EXISTS OutgoingFriendRequests;
DROP TRIGGER IF EXISTS ProcessFriendRequests;
DROP TRIGGER IF EXISTS AddUserRole;
DROP FUNCTION IF EXISTS CheckIfInUserRoles;

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
JoinDate timestamp NOT NULL,
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
AcceptedStatusBoolean tinyint(1),
DateRequested timestamp,
FOREIGN KEY (Requestor) REFERENCES Users(Username),
FOREIGN KEY (Requestee) REFERENCES Users(Username), 
PRIMARY KEY (Requestor, Requestee));


CREATE TABLE IF NOT EXISTS Goals(
GoalID int(10) PRIMARY KEY AUTO_INCREMENT UNIQUE,
GroupID int(10),
Username varchar(255),
GoalName varchar(255),
Description varchar(255),
StartTime timestamp,
EndTime timestamp,
FOREIGN KEY (GroupId) REFERENCES Groups(GroupID),
FOREIGN KEY (Username) REFERENCES Users(Username));


CREATE TABLE IF NOT EXISTS Pomodoros(
PomodoroID int(10) PRIMARY KEY AUTO_INCREMENT UNIQUE,
GoalID int(10),
TaskDescription varchar(255),
StartTime timestamp,
EndTime timestamp,
Comments varchar(255),
FOREIGN KEY (GoalID) REFERENCES Goals(GoalID));

CREATE VIEW GroupMemberGroups AS SELECT gm.GroupMember,gm.GroupRole,gm.JoinDate,g.GroupID,g.GroupName,g.Description,g.VerificationBeforeJoinBoolean
FROM GroupMembers gm INNER JOIN Groups g ON gm.GroupID = g.GroupID;

CREATE VIEW IncomingFriendRequests AS SELECT fr.Requestor
FROM FriendRequests fr WHERE fr.Requestor <> CURRENT_USER() and fr.Requestee = CURRENT_USER();

CREATE VIEW OutgoingFriendRequests AS SELECT fr.Requestee
FROM FriendRequests fr WHERE fr.Requestee <> CURRENT_USER() and fr.Requestor = CURRENT_USER();


DELIMITER //
CREATE TRIGGER ProcessFriendRequests AFTER UPDATE ON FriendRequests
FOR EACH ROW 
IF NEW.AcceptedStatusBoolean = 0 THEN
	DELETE FROM FriendRequests WHERE NEW.Requestor = Requestor and NEW.Requestee = Requestee;
ELSEIF NEW.AcceptedStatusBoolean = 1 THEN
	INSERT INTO Friends(FirstFriend, SecondFriend) VALUES(NEW.Requestor, NEW.Requestee);
	DELETE FROM FriendRequests WHERE NEW.Requestor = Requestor and NEW.Requestee = Requestee;
END IF;
;;
DELIMITER ;

DELIMITER //
CREATE FUNCTION CheckIfInUserRoles(username varchar(255)) RETURNS INT
	BEGIN
	DECLARE existsBool INT;
    SET existsBool = (SELECT COUNT(*) FROM UserRoles ur WHERE ur.Username = username);
    RETURN existsBool;
    END//
DELIMITER ;

DELIMITER //
CREATE TRIGGER AddUserRole AFTER INSERT ON Users
FOR EACH ROW BEGIN
    IF (CheckIfInUserRoles(NEW.Username) = 0) THEN
		INSERT INTO UserRoles(Username, Role) VALUES(NEW.Username, 'UserRole');
    END IF;
    END//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE CreateGroupFirstTime
(IN GroupName varchar(255),
IN Description varchar(255),
IN VerificationBeforeJoinBoolean tinyint(1),
IN Username varchar(255))
BEGIN
	DECLARE keyPrimary Int;
	INSERT INTO Groups(GroupName, Description, VerificationBeforeJoinBoolean) VALUES(GroupName, Description, VerificationBeforeJoinBoolean);
    SET keyPrimary = LAST_INSERT_ID();
    INSERT INTO GroupMembers(GroupID, GroupMember, GroupRole) VALUES(keyPrimary, Username, 'Admin');
END //





    

