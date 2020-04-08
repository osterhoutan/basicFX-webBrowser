basicFX-webBrowser
=================
_Andrew Osterhout (a02201315)_<br/>
_USU: Sp2020 -> CS2410: Event Driven Programing and GUI's_<br/>
_Final Project: basicFX-webBrowser_
<br/><br/>


## About:
This is a basic web browser with some limited themeing options and basic history and bookmarking features built in

### Disclaimer:
This is a basic web browser built using JavaFX in using JDK-10 (which was out of support when made), as a final project for Utah State University's (USU) CS2410 Event Driven Programing and GUI's course.
As such this is not a secure web browser, nor a fast web browser, using outdated and unsupported development tools.
It should not be used as your daily driver web browser.
It exists on the web as the beginnings of a personal portfolio, and an exercise in developing and showing off different techniques and methodologies of event driven programing and building GUI interfaces.
<br/><br/>

## Project Table Of Contents: 
- [basicFX-webBrowser](#basicfx-webbrowser)
  - [About:](#about)
    - [Disclaimer:](#disclaimer)
  - [Project Table Of Contents:](#project-table-of-contents)
  - [Notes:](#notes)
    - [Mermaid:](#mermaid)
  - [Getting Started:](#getting-started)
    - [vs-code:](#vs-code)
    - [Other IDEs:](#other-ides)
    - [Command line:](#command-line)
<br/><br/>


## Notes:
### Mermaid: 
When going through the documentation you will find sections of the markdown that will render like this:
```
classDiagram
    class example~test~ {
        int item
        - exampleMethod() String
    }
```
This is _Mermaid Diagraming Language ([about](https://medium.com/better-programming/mermaid-create-charts-and-diagrams-with-markdown-88a9e639ab14)) ([GitHub](https://github.com/mermaid-js/mermaid)) ([user manual](https://mermaidjs.github.io/))_, and to get it to render you will need to add support to your markdown viewer, via one of the following:
 - [GitHub+mermaid chrome-extension](https://chrome.google.com/webstore/detail/mermaid-diagrams/phfcghedmopjadpojhmmaffjmfiakfil?hl=en-US) ([GitHub](https://github.com/BackMarket/github-mermaid-extension))
 - [vs-code Mermaid in markdown Preview Extension](https://marketplace.visualstudio.com/items?itemName=bierner.markdown-mermaid) ([GitHub](https://github.com/mjbvz/vscode-markdown-mermaid.git))
 - [Paid IntelliJ Markdown enhanced Plugin](https://plugins.jetbrains.com/plugin/7896-markdown-navigator-enhanced)

You can also use the [Mermaid live editor](https://mermaid-js.github.io/mermaid-live-editor/), by coppying and pasting the mermaid code to the editor to get the picture.

When you have added mermaid support into your markdown rendering engine you should get diagrams like this:
![Mermaid Diagrams Examples](https://github.com/mermaid-js/mermaid/raw/develop/docs/img/new-diagrams.png)


## Getting Started:
This is not meant to be an application for every day use, so is best run in a developer environment of some kind.

First thing you will need to do is clone or download this repo:
```bash
git clone <URL/SSH> basicFX-webBrowser
```
Then run it in the development environment of your choice using jdk-10 (available to download via the oracle archives [here](https://www.oracle.com/java/technologies/java-archive-javase10-downloads.html)).

### vs-code:
If you want to run the application, this repo contains all of the vs-code workspace files nessisary to run it in vs code with the [Java Extension pack](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack) installed along with 

### Other IDEs:
If you are usign another IDE vs-code's java language server implements Eclipse's project format so you can usually just import it as a Eclipse project into an IDE like IntelliJ (by jet-brains), Eclipse or NetBeans with minimal issues.

### Command line:
If you are good at old school command line java development, you don't need me to tell you how to do what you need to do.
