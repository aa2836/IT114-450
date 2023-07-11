<table><tr><td> <em>Assignment: </em> IT114 Chatroom Milestone 2</td></tr>
<tr><td> <em>Student: </em> Abdullah Salman (aa2836)</td></tr>
<tr><td> <em>Generated: </em> 7/10/2023 9:45:34 PM</td></tr>
<tr><td> <em>Grading Link: </em> <a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-450-M23/it114-chatroom-milestone-2/grade/aa2836" target="_blank">Grading</a></td></tr></table>
<table><tr><td> <em>Instructions: </em> <p>Implement the features from Milestone2 from the proposal document:&nbsp; <a href="https://docs.google.com/document/d/1ONmvEvel97GTFPGfVwwQC96xSsobbSbk56145XizQG4/view">https://docs.google.com/document/d/1ONmvEvel97GTFPGfVwwQC96xSsobbSbk56145XizQG4/view</a></p>
</td></tr></table>
<table><tr><td> <em>Deliverable 1: </em> Payload </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Payload Screenshots</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Faa2836%2F2023-07-11T01.35.10Screenshot%202023-07-10%20at%209.04.50%20PM.png.webp?alt=media&token=02718d34-44db-4e59-ace8-c7e2b8af4ef7"/></td></tr>
<tr><td> <em>Caption:</em> <p>sc of pay load code for sender and message<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Faa2836%2F2023-07-11T01.35.40Screenshot%202023-07-10%20at%209.05.27%20PM.png.webp?alt=media&token=f9bfcda1-c827-4a87-96e9-3b503a12dd61"/></td></tr>
<tr><td> <em>Caption:</em> <p>sc of payload code sender and message<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Faa2836%2F2023-07-11T01.37.12Screenshot%202023-07-10%20at%209.16.35%20PM.png.webp?alt=media&token=9a9f7e2e-e4d6-459e-9a54-3e1ce43ef94c"/></td></tr>
<tr><td> <em>Caption:</em> <p>sc of display for sender and message<br></p>
</td></tr>
</table></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 2: </em> Server-side commands </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Show the code for the mentioned commands</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Faa2836%2F2023-07-11T01.38.09Screenshot%202023-07-10%20at%209.06.52%20PM.png.webp?alt=media&token=341773ba-5d1f-4ab8-a477-50c3e349923b"/></td></tr>
<tr><td> <em>Caption:</em> <p>sc of /roll and /flip<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Faa2836%2F2023-07-11T01.38.23Screenshot%202023-07-10%20at%209.08.15%20PM.png.webp?alt=media&token=24c70fc7-82e9-4e15-9f2b-77c13516372b"/></td></tr>
<tr><td> <em>Caption:</em> <p>sc of /roll() format1 and format2<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Faa2836%2F2023-07-11T01.38.32Screenshot%202023-07-10%20at%209.09.06%20PM.png.webp?alt=media&token=49d1c199-2974-4ab6-8e41-4eb9f7a592a2"/></td></tr>
<tr><td> <em>Caption:</em> <p>sc of flip()<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Faa2836%2F2023-07-11T01.39.30Screenshot%202023-07-10%20at%209.19.48%20PM.png.webp?alt=media&token=bad071e7-b655-43df-9c23-0e7e4377d781"/></td></tr>
<tr><td> <em>Caption:</em> <p>sc display for /roll and /flip<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Explain the logic/implementation of each commands</td></tr>
<tr><td> <em>Response:</em> <p><b>/roll</b><div><div>When a message with the &quot;/roll&quot; command is received, the roll method is<br>called to handle it. The roll method decides what to do based on<br>the given command. If the command looks like &quot;/roll 0 - X&quot; or<br>&quot;/roll 1 - X&quot;, the roll method checks it using an if-else statement<br>and calls the format1 method in RollFormat1. In format1, a random number within<br>the specified range is generated and shared with the client.</div><div><br></div><div>when it like this&nbsp;<br>&quot;/roll #d#&quot;, the roll method checks it using another if else statement and<br>invokes the format2 method. In RollFormat2 the roll command is split into the<br>number of dice and the number of sides on each die. If both<br>values are greater than zero, a resultMessage is prepared to store the roll<br>results. Random numbers are generated for each roll and added to the resultMessage.<br>then resultMessage is sent to all clients in the room using the currentRoom.broadcastMessage()<br>function.<br></div></div><div><div><b><br></b></div><div><b>/flip</b></div></div><div><div style=""><div>When the message is /flip, and the condition <code>message.equalsIgnoreCase(/flip)</code> is true, we<br>perform the Flip() action. In the Flip() action, we generate a random decimal<br>number between 0 and 1 using Math.random.If the number is less than 0.5<br>it&#39;s heads 0. If it&#39;s equal to or greater than 0.5&nbsp; it&#39;s tails<br>1. If it&#39;s heads 0, message i set to Heads. If it&#39;s tails<br>1 message is set to Tails. Then resultMessage broadcast the result message with<br>the outcome of the coin flip to all clients in the current room.</div></div></div><br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 3: </em> Text Display </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Show the code for the various style handling via markdown or special characters</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Faa2836%2F2023-07-11T01.41.42Screenshot%202023-07-10%20at%209.11.23%20PM.png.webp?alt=media&token=e045c957-133c-4d85-8d28-1c48a04c1eed"/></td></tr>
<tr><td> <em>Caption:</em> <p>sc of code for bold underline italic and color<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Show source message and the result output in the terminal (note, you won't actually see the styles until Milestone3)</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Faa2836%2F2023-07-11T01.42.56Screenshot%202023-07-10%20at%209.24.37%20PM.png.webp?alt=media&token=0a41e5f9-8919-47cf-ac68-fe28df57b3ee"/></td></tr>
<tr><td> <em>Caption:</em> <p>sc of of output display<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Faa2836%2F2023-07-11T01.42.59Screenshot%202023-07-10%20at%209.27.31%20PM.png.webp?alt=media&token=e3ab7ede-eaf7-4fcb-896c-9ca16308963f"/></td></tr>
<tr><td> <em>Caption:</em> <p>sc of output display<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 3: </em> Explain how you got each style applied</td></tr>
<tr><td> <em>Response:</em> <p>The textStyle applies the styles for the given input string by replaceAll. it<br>replaces the substring of the message that matches an given expression.<div><b>bold</b></div><div>when typing message<br>if the message is typed with ** message** &nbsp;replaceAll takes substring of the<br>message and matches is the specific expression it transforms <strong>message</strong> with &nbsp;[B] tags<br>indicating that the messeage is bold. &nbsp;<b><br></b></div><div><i><b>italics</b></i></div><div>when typing message if the message is<br>typed with - message- &nbsp;replaceAll takes substring of the message and matches is<br>the specific expression it transforms -message-into &nbsp;[I] indicating that the messeage is italic.&nbsp;<i><b><br></b></i></div><div><b>color</b></div><div>&nbsp;replaceAll<br>takes substring of the message and matches is the specific expression if it<br>is ==red Hello== it transforms to [COLOR=red]Hello[/COLOR], making Hello appear red same method<br>is used for green and blue. if ==blue Hello== it transforms to `[COLOR=blue]Hello[/COLOR]<br>and ==green Hello== it transforms to [COLOR=green]Hello[/COLOR]</div><div><b><u>underlines</u></b></div><div>when typing message if the message is<br>typed with <strong>message</strong> &nbsp;replaceAll takes substring of the message and matches is the<br>specific expression it transforms <strong>message</strong> into &nbsp;[U] indicating that the <strong>messeage</strong> is underlined.&nbsp;<br></div><br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 4: </em> Misc </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Include the pull request for Milestone2 to main</td></tr>
<tr><td> <a rel="noreferrer noopener" target="_blank" href="https://github.com/aa2836/IT114-450/pull/7">https://github.com/aa2836/IT114-450/pull/7</a> </td></tr>
</table></td></tr>
<table><tr><td><em>Grading Link: </em><a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-450-M23/it114-chatroom-milestone-2/grade/aa2836" target="_blank">Grading</a></td></tr></table>