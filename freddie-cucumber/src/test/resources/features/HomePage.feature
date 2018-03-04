Feature: The Freddie Mac Application
	As a user of the Hexaware application
	I should be able to login

Scenario Outline: user should be able to login
	Given the user navigates to our home page
	Then the user should enter "<username>" and "<password>"

Examples:
| username | password |
| hexa1@sdettraining.com | password |
| hexa2@sdettraining.com | password |
| hexa3@sdettraining.com | password |
