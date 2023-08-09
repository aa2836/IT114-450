<table><tr><td> <em>Assignment: </em> IT114 Chatroom Milestone4</td></tr>
<tr><td> <em>Student: </em> Abdullah Salman (aa2836)</td></tr>
<tr><td> <em>Generated: </em> 8/8/2023 9:24:54 PM</td></tr>
<tr><td> <em>Grading Link: </em> <a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-450-M23/it114-chatroom-milestone4/grade/aa2836" target="_blank">Grading</a></td></tr></table>
<table><tr><td> <em>Instructions: </em> <p>Implement the features from Milestone3 from the proposal document:&nbsp;&nbsp;<a href="https://docs.google.com/document/d/1ONmvEvel97GTFPGfVwwQC96xSsobbSbk56145XizQG4/view">https://docs.google.com/document/d/1ONmvEvel97GTFPGfVwwQC96xSsobbSbk56145XizQG4/view</a></p>
</td></tr></table>
<table><tr><td> <em>Deliverable 1: </em> Client can export chat history of their current session (client-side) </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add screenshot of related UI</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Faa2836%2F2023-08-09T00.41.01Screenshot%202023-08-08%20at%208.36.36%20PM.png.webp?alt=media&token=11264caf-5444-4008-839d-117df2f61ca1"/></td></tr>
<tr><td> <em>Caption:</em> <p>sc of Export conversation button as menu item <br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Faa2836%2F2023-08-09T00.41.04Screenshot%202023-08-08%20at%208.37.09%20PM.png.webp?alt=media&token=3430f823-28cf-45df-8e05-8af9afbbd05b"/></td></tr>
<tr><td> <em>Caption:</em> <p>process of creating a file for exported conversation<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Faa2836%2F2023-08-09T00.41.10Screenshot%202023-08-08%20at%208.38.29%20PM.png.webp?alt=media&token=4aec94e2-fb43-4e95-adec-83a351b97525"/></td></tr>
<tr><td> <em>Caption:</em> <p>sc of confirmation <br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Add screenshot of exported data</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Faa2836%2F2023-08-09T00.43.50Screenshot%202023-08-08%20at%208.37.09%20PM.png.webp?alt=media&token=36f1c25c-83cf-4f98-8f87-501cd781ddfa"/></td></tr>
<tr><td> <em>Caption:</em> <p>process of creating a file for exported conversation<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Faa2836%2F2023-08-09T00.43.52Screenshot%202023-08-08%20at%208.38.29%20PM.png.webp?alt=media&token=1e932ce1-cb28-4231-86c2-00d9afba099e"/></td></tr>
<tr><td> <em>Caption:</em> <p>sc of confirmation<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Faa2836%2F2023-08-09T00.43.44Screenshot%202023-08-08%20at%208.36.11%20PM.png.webp?alt=media&token=2ad4ad64-9338-43bf-96be-22e11853fa90"/></td></tr>
<tr><td> <em>Caption:</em> <p>sc various formats of conversation<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 3: </em> Briefly explain how you implemented this</td></tr>
<tr><td> <em>Response:</em> <p>first a mine team with button export chat history was created so that<br>went clicked on it it can store users chat history to a file<br>and by invoking &nbsp;exportChatHistory() method. it saves users chat history to a file<br>using a special window. Inside a safe zone where errors are handled, a<br>window pops up where users can pick where to save the chat history.<br>If a location is selected, the code sets up a tool to write<br>the chat data into that location. The chat history comes from a place<br>called chatPanel, which holds all the messages. Each message is put into the<br>file one by one. When everything&#39;s done, the tool is closed, and a<br>message appears saying the chat history was saved.&nbsp;<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 2: </em> Client's mute list will persist across sessions (server-side) </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707834-bf5a5b13-ec36-4597-9741-aa830c195be2.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add a screenshot of how the mute list is stored</td></tr>
<tr><td><table><tr><td>Missing Image</td></tr>
<tr><td> <em>Caption:</em> (missing)</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Add a screenshot of the code saving/loading mute list</td></tr>
<tr><td><table><tr><td>Missing Image</td></tr>
<tr><td> <em>Caption:</em> (missing)</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 3: </em> Briefly explain how you implemented this</td></tr>
<tr><td> <em>Response:</em> <p>Was not able to successfully implement this feature&nbsp;<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 3: </em> Client's will receive a message when they get muted/unmuted by another user </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add a screenshot showing the related chat messages</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Faa2836%2F2023-08-09T00.49.50Screenshot%202023-08-08%20at%208.47.55%20PM.png.webp?alt=media&token=634b988a-b724-4c4f-933a-e3afdf3c4abd"/></td></tr>
<tr><td> <em>Caption:</em> <p>sc showing user mute (but was unscuccessful in the muted user getting the<br>Messge that they were muted)<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Faa2836%2F2023-08-09T00.49.52Screenshot%202023-08-08%20at%208.48.52%20PM.png.webp?alt=media&token=6516e335-f9d9-448a-990a-b770ee70fee5"/></td></tr>
<tr><td> <em>Caption:</em> <p>sc show unmuted Tim and Tim getting the message who unmuted him<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Add a screenshot of the related code snippets</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Faa2836%2F2023-08-09T00.51.59Screenshot%202023-08-08%20at%208.32.30%20PM.png.webp?alt=media&token=6f3dd79e-157f-4bfe-b81b-6ec420fc31a2"/></td></tr>
<tr><td> <em>Caption:</em> <p>Showing where/when the muted/unmuted message occurs<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Faa2836%2F2023-08-09T00.51.56Screenshot%202023-08-08%20at%208.33.21%20PM.png.webp?alt=media&token=6b362146-0a39-4168-ad2d-37083af197de"/></td></tr>
<tr><td> <em>Caption:</em> <p>Showing where/when the muted/unmuted message occurs<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 3: </em> Briefly explain how you implemented this</td></tr>
<tr><td> <em>Response:</em> <p>the server check for mute or unmute command if it is mute then<br>it process the mute command and inside the mute command&nbsp;If the target user<br>is found in the current chat room and is not already muted, the<br>server sets their mute status to true. the server sends notifications to both<br>the sender and the muted user. For the sender, it confirms that they<br>have successfully muted the target user. For the muted user, it informs them<br>that they have been muted by the sender.<b>[But I was unable to too<br>see this feature when running the client and processing the command mute]. </b>Same<br>thing for unmute if the command is unmute it process the unmute command,<br>and if the target user is muted it unmutes the user and then<br>send the server sends notifications to both the sender and the muted user.<br>For the sender, it confirms that they have successfully unmuted the target user.<br>For the muted user, it informs them that they have been unmuted by<br>the sender<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 4: </em> User list should update per the status of each user </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add screenshot for Muted users by the client should appear grayed out</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Faa2836%2F2023-08-09T01.04.35Screenshot%202023-08-08%20at%208.47.55%20PM.png.webp?alt=media&token=8b1b1b30-38ca-4a90-a082-dfc5890f378b"/></td></tr>
<tr><td> <em>Caption:</em> <p>sc showing muted user [unable to successfully show grayed out muted user]<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Faa2836%2F2023-08-09T01.04.30Screenshot%202023-08-08%20at%207.53.03%20PM.png.webp?alt=media&token=9401494c-dd5e-4d75-931b-a9c5a29b0553"/></td></tr>
<tr><td> <em>Caption:</em> <p>added sniping of the code to show that I attempted this feature but<br>was unscuccssfull <br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Add screenshot for Last person to send a message gets highlighted</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Faa2836%2F2023-08-09T00.59.56Screenshot%202023-08-08%20at%208.47.55%20PM.png.webp?alt=media&token=26b798fc-61bb-494e-aa86-7b986dfc5945"/></td></tr>
<tr><td> <em>Caption:</em> <p>sc showing last person&#39;s message [unable to get last persons message hihglihgted]<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Faa2836%2F2023-08-09T01.03.19Screenshot%202023-08-08%20at%207.53.03%20PM.png.webp?alt=media&token=29bbb467-1358-4a31-856a-14631a97c8d7"/></td></tr>
<tr><td> <em>Caption:</em> <p>added sniping of the code to show that I attempted this feature but<br>was unscuccssfull <br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 3: </em> Briefly explain how you implemented this</td></tr>
<tr><td> <em>Response:</em> <p>I was unscuccessful in impelling this feature in terms of seeing it in<br>GUI but as I have attached code, this is I the implement should&#39;ve<br>went&nbsp;<div>This code supposed to handle the addition of users to the user list<br>in the chat application&#39;s GUI. It should log the user&#39;s inclusion, track user<br>list size, and create a graphical user item using a JEditorPane to display<br>the client&#39;s name. The text color of this display &nbsp;should adjust based on<br>whether the user is muted or highlighted, using gray for muted and black<br>for highlighted users.<br></div><br></p><br></td></tr>
</table></td></tr>
<table><tr><td><em>Grading Link: </em><a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-450-M23/it114-chatroom-milestone4/grade/aa2836" target="_blank">Grading</a></td></tr></table>
