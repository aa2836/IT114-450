<table><tr><td> <em>Assignment: </em> IT114 - Number Guesser</td></tr>
<tr><td> <em>Student: </em> Abdullah Salman (aa2836)</td></tr>
<tr><td> <em>Generated: </em> 6/12/2023 7:23:44 PM</td></tr>
<tr><td> <em>Grading Link: </em> <a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-450-M23/it114-number-guesser/grade/aa2836" target="_blank">Grading</a></td></tr></table>
<table><tr><td> <em>Instructions: </em> <ol><li>Create the below branch name</li><li>Implement the NumberGuess4 example from the lesson/slides</li><ol><li><a href="https://gist.github.com/MattToegel/aced06400c812f13ad030db9518b399f">https://gist.github.com/MattToegel/aced06400c812f13ad030db9518b399f</a><br></li></ol><li>Add/commit the files as-is from the lesson material (this is the base template)</li><li>Pick two (2) of the following options to implement</li><ol><li>Display higher or lower as a hint after a wrong guess</li><li>Implement anti-data tampering of the save file data (reject user direct edits)</li><li>Add a difficulty selector that adjusts the max strikes per level</li><li>Display a cold, warm, hot indicator based on how close to the correct value the guess is (example, 10 numbers away is cold, 5 numbers away is warm, 2 numbers away is hot; adjust these per your preference)</li><li>Add a hint command that can be used once per level and only after 2 strikes have been used that reduces the range around the correct number (i.e., number is 5 and range is initially 1-15, new range could be 3-8 as a hint)</li><li>Implement separate save files based on a "What's your name?" prompt at the start of the game</li></ol><li>Fill in the below deliverables</li><li>Create an m3_submission.md file and fill in the markdown from this tool when you're done</li><li>Git add/commit/push your changes to the HW branch</li><li>Create a pull request to main</li><li>Complete the pull request</li><li>Grab the link to the m3_submission.md from the main branch and submit that direct link to github</li></ol></td></tr></table>
<table><tr><td> <em>Deliverable 1: </em> Implementation 1 (one of the picked items) </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Two Screenshots: Add a screenshot demonstrating the feature during runtime; Add a screenshot (or so) of the snippets of code that implement the feature</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Faa2836%2F2023-06-12T23.02.49Screenshot%202023-06-12%20at%206.53.53%20PM.png.webp?alt=media&token=f658046c-2f0f-45a2-b2c8-386747a3208e"/></td></tr>
<tr><td> <em>Caption:</em> <p>sc of running the code <br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Faa2836%2F2023-06-12T23.05.51Screenshot%202023-06-12%20at%207.04.38%20PM.png.webp?alt=media&token=ff187fd5-dd44-49aa-b0e7-83fcec1373d9"/></td></tr>
<tr><td> <em>Caption:</em> <p>sc of the modified code<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Briefly explain the logic behind your implementation</td></tr>
<tr><td> <em>Response:</em> <p>added 2 If else statement inside the processedGuess method if the used guesses<br>the correct number its prints out message with the correct number guessed by<br>the user. Else it checks if the number entered by the user is<br>less then the actual number, if it is then it displays a message<br>saying guess higher and if the number entered by the user is grater<br>then the actual number then it says to guess &nbsp;lower .<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 2: </em> Implementation 2 (one of the picked items) </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Two Screenshots: Add a screenshot demonstrating the feature during runtime; Add a screenshot (or so) of the snippets of code that implement the feature</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Faa2836%2F2023-06-12T23.03.21Screenshot%202023-06-12%20at%206.53.53%20PM.png.webp?alt=media&token=d6e00376-eaa8-49b1-8bb3-2311392fc7d7"/></td></tr>
<tr><td> <em>Caption:</em> <p>sc of running the code<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Faa2836%2F2023-06-12T23.09.34Screenshot%202023-06-12%20at%207.06.41%20PM.png.webp?alt=media&token=ad568c49-c706-48b8-bbd0-006c696a7a7c"/></td></tr>
<tr><td> <em>Caption:</em> <p>sc of the modified code<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Faa2836%2F2023-06-12T23.09.29Screenshot%202023-06-12%20at%207.07.53%20PM.png.webp?alt=media&token=0502ce95-8253-46bf-9912-0bef0a314f6d"/></td></tr>
<tr><td> <em>Caption:</em> <p>sc of the file created with users inputed name <br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Briefly explain the logic behind your implementation</td></tr>
<tr><td> <em>Response:</em> <p>Added a new implement that separate save files based on a &quot;what&#39;s your<br>name&quot;? prompt. so I first added a new method called setPlayersName to set<br>the users name when the input it.Then created another method called setPlayername &nbsp;to<br>modify the filename to include the users name in the file and called<br>the method inside &#39;start&#39; to ask for the usernames at the begging passed<br>that data and create a file with the start name and record the<br>Level, Strikes, Number, and MaxLevel.&nbsp;<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 3: </em> Misc </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add a link to the related pull request of this hw</td></tr>
<tr><td> <a rel="noreferrer noopener" target="_blank" href="https://github.com/aa2836/IT114-450/pull/3">https://github.com/aa2836/IT114-450/pull/3</a> </td></tr>
<tr><td> <em>Sub-Task 2: </em> Discuss anything you learned during this lesson/hw or any struggles you had</td></tr>
<tr><td> <em>Response:</em> <p>i learned that when dealing with user input in Java, it is essential<br>to use the scanner class to read input from the console it provides<br>various methods to parse different data types like integers, floats, and strings from<br>the user input and its important to handle exceptions when converting user input<br>to the desired data type to avoid program crashes.&nbsp;<br></p><br></td></tr>
</table></td></tr>
<table><tr><td><em>Grading Link: </em><a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-450-M23/it114-number-guesser/grade/aa2836" target="_blank">Grading</a></td></tr></table>