# Cricket-Match

This is an OOP based Cricket game, with the following entities.

Flow of application:

Main: It is the class from where the  program execution starts. It calls the MatchController from where the game starts.After returning from previous match it asks do you want to play another game or not.


MatchController:
It is the class which basically controls the whole match. MatchController takes necessary input from user like  numOfOvers, playerName, playerType, etc
MatchController will create a new Match instance with constructor with these read args.


Match:
Match is having fields like team1, team2, winner, totalAvailableBalls 
when MatchController calls startMatch(), game will be started and 
startInning() will be called and target will be taken into consideration for the chaser team
Innings will have total overs given during construction. method' call returns back when 1. Either all over completes 2. 10 Wickets are fallen
3. Target is reached.
The strike will be changed on odd runs or over completion and
winner will be declared based on the score of both teams


Team:
Match constructor calls Team constructor to construct both the team one by one
Team will maintain numberOfWickets, totalNumberOfBallPlayed, currentWickets, list of players, etc
appropriate team will be called to update its run/wicket and balls will be incremented by one
each updation method will also call the method of current Strike player to update his score and balls


Player:
Player will be batsman or bowler or wicketkeeper along with his name, personalscore, personalBalls, etc. The methods of this class are mainly called from Teams class's object because every Player in this game belongs to a Team. The info of player gets updated when the team's updation takes place. 
This class contains methods of individual players.


CountryName:
CountryName is an enum which contains the names of the country.It also contains a random method which randommly allocates two teams for the match to take place.


UtilClass:
It is a utility class that contains method for validation. 
