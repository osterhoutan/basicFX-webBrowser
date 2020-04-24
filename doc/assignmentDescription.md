<p><h1>Final Project</h1></p>
<p><span style="font-weight: 400;">Due April 24th 11:59pm</span></p>
<p><span style="font-weight: 400;">150 points</span></p>
<p><br><br><br></p>
<p><br><br><br></p>
<p><h2>Project Requirements</h2></p>
<p><span style="font-weight: 400;">JavaFX GUI Application that utilizes the following JavaFX features and Java Programming Methods:</span></p>
<p>&nbsp;</p>
<p><span style="font-weight: 400;">At least 2 types JavaFX Panes organized well, to give your GUI a nice appearance and usability. (30 points)</span></p>
<p><span style="font-weight: 400;">At least 6 types of JavaFX Nodes, i.e. Buttons, TextField, Labels, CheckBoxes, RadioButtons, ImageView, etc. (30 points)</span></p>
<p><span style="font-weight: 400;">Animation i.e. Timeline or FadeTransition (15 points)</span></p>
<p><span style="font-weight: 400;">Events (20 points)</span></p>
<p><span style="font-weight: 400;">Bindings (20 points)</span></p>
<p><span style="font-weight: 400;">Listeners (20 points)</span></p>
<p><span style="font-weight: 400;">Server-Client, or Client-Client connectivity (15 points)</span></p>
<p><br><br></p>
<p><h2>Project Suggestions</h2></p>
<p><span style="font-weight: 400;">Three project suggestions are provided below.&nbsp; You are allowed to choose a different project of your own, but you must discuss and approve it with me.&nbsp; If you do choose your own project, it must meet all the project requirements above, and it must also be a similar size and difficulty level to the projects suggested below.</span></p>
<p><br><br></p>
<p><h2>Chess Game</h2></p>
<p><span style="font-weight: 400;">This project implements a Chess game that is playable between two separate computers.&nbsp; In other words, the application should start up, and ask for the ip address and port of another player on another computer.&nbsp; It must also meet the following requirements:</span></p>
<p>&nbsp;</p>
<p><span style="font-weight: 400;">If you add the logic to only allow legal moves for all pieces on the chess board, implement the following:</span></p>
<ul>
<li style="font-weight: 400;"><span style="font-weight: 400;">Detect a checkmate move, and display the winner somewhere on the GUI.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">Detect a check move, and display this on the GUI.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">Detect when one piece has taken another piece, and display it somewhere on the GUI.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">Your GUI needs a chat feature, so the users can chat with each other.&nbsp; This satisfies the Client-Client connectivity above</span></li>
</ul>
<p>&nbsp;</p>
<p><span style="font-weight: 400;">If you do NOT add the logic to only allow legal moves for all pieces on the chess board, implement the following:</span></p>
<ul>
<li style="font-weight: 400;"><span style="font-weight: 400;">Add a button (or another method) to communicate to the other user that you have put them in check or check mate.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">You must add logic the only allows your King to make legal moves, so when the King is put in check mate, they cannot run away.&nbsp; They have to die, and the winner is displayed somewhere in the GUI.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">Detect when one piece has taken another piece, and display it somewhere on the GUI.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">Your GUI needs a chat feature, so the users can chat with each other.&nbsp; This satisfies the Client-Client connectivity above</span></li>
</ul>
<p>&nbsp;</p>
<p><h2>Sudoku Game</h2></p>
<p><span style="font-weight: 400;">This project implements a Sudoku game played by a single user.&nbsp; The application should start up and load sudoku.txt boards that I will provide for you.&nbsp; You are not required to generate possible sudoku boards from scratch. It must also implement the following requirements:</span></p>
<ul>
<li style="font-weight: 400;"><span style="font-weight: 400;">The program should load a sudoku.txt file into your GUI and the default color for the numbers should be black.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">The GUI should have “hint” button which should solve the puzzle provided in sudoku.txt.&nbsp; It should also color any incorrect numbers that the user has entered in red, and also add two new correct numbers to the sudoku board in the color blue.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">The GUI should automatically detect when the puzzle has been solved and display this on the GUI.&nbsp; Possibly using an animation to satisfy the project requirement listed above.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">I suggest a chatting feature somewhere in the GUI to meet the Client-Server or Client-Client requirement above,&nbsp; but I am open if you want to do something else to meet this requirement. The GUI can chat another Sudoku game, or a Java program console.</span></li>
</ul>
<p><br>Sudoku boards can be found <a href="https://usu.instructure.com/courses/567106/files/folder/Sudoku" class="external" target="_blank" rel="noreferrer noopener"><span>here</span><span class="ui-icon ui-icon-extlink ui-icon-inline" title="Links to an external site."><span class="screenreader-only">Links to an external site.</span></span></a></p>
<p><h2>Web Browser GUI</h2></p>
<p><span style="font-weight: 400;">This project implements a Web Browser that allows the user to browse the internet.&nbsp; The application should start up and automatically load the page: </span><a href="https://cs.usu.edu/" class="external" target="_blank" rel="noreferrer noopener"><span><span style="font-weight: 400;">https://cs.usu.edu/</span></span><span aria-hidden="true" class="ui-icon ui-icon-extlink ui-icon-inline" title="Links to an external site."></span><span class="screenreader-only">&nbsp;(Links to an external site.)</span></a><span style="font-weight: 400;"> .&nbsp; It must implement the following requirements:</span></p>
<ul>
<li style="font-weight: 400;"><span style="font-weight: 400;">Have an address bar at the top of the GUI, a user can type an address, to load a website.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">Successfully allow a user to browse around a website, redirecting correctly to all the links selected.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">Add the functionality to store favorite links in a dropdown menu that users can add their favorite sites to.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">When a user selects a site in the favorite links dropdown it should automatically load in the browser.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">Add a “back” button that allows the user to go back to the previously visited website.&nbsp; It only needs to go back one site (not store multiple sites).</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">Note: This project does not need to add a chat feature, since the project itself already fulfills the Client-Server requirement.</span></li>
<li style="font-weight: 400;">
<span style="font-weight: 400;">You are allowed to use the WebView class </span><span style="font-weight: 400;">javafx.scene.web.WebView.&nbsp;&nbsp;</span>
</li>
<li style="font-weight: 400;"><span style="font-weight: 400;">Have multiple tabs in the Web Browser that store multiple websites.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">Add functionality to save the bookmarks to a file, so when I restart your GUI the bookmarks will reload in to the Web Browser Favorites dropdown menu.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">When the user types a non-url into the address bar, automatically detect its not a url and perform a google search with that string</span></li>
</ul>