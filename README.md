# Cricket-Match
This is an OOP based Cricket game, with the following entities.

Main: It is the class from where the  program execution starts. It calls the MatchController from where the game starts.

Flow of application:

MatchController:
MatchController takes necessary input from user like  numOfOvers, playerName, playerType, etc
MatchController will create a new Match instance with constructor with these read args


Match:
Match will have fields like team1, team2, winner, totalAvailableBalls 
when MatchController calls startMatch(), game will be started and 
startInning() will be called and target will be taken into consideration for the chaser team
Innings will have total overs given during construction. method' call returns back when 1. Either all over completes 2. 10 Wickets are fallen
3. Target is reached
strike will be changed on odd runs or over completion
winner will be declared based on the score of both teams


Team:
Match constructor will call Team constructor to construct both the team one by one
Team will maintain numberOfWickets, totalNumberOfBallPlayed, currentWickets, list of players, etc
appropriate team will be called to update its run/wicket and balls will be incremented by one
each updation method will also call the method of current Strike player to update his score and balls


Player:
Player will be batsman or bowler or wicketkeeper along with his name, personalscore, personalBalls, etc..


CountryName:
CountryName is an enum which contains the names of the country.


UtilClass:
UtilClass contains method for validation. 
